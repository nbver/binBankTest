package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestRestController {

    @Autowired
    BinRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    static final String BIN_URL = "https://lookup.binlist.net/";

    @GetMapping("/")
    public String toIndex() {
        return "index.html";
    }


    @PostMapping(value = "/check_bin")
    public String sayHello(@RequestParam("bin") long bin, Model model) {

        if (!repository.existsByBin(bin)) {
            ResponseEntity<String> response
                    = restTemplate.getForEntity(BIN_URL + "/" + bin, String.class);
            System.out.println(response.getBody());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;

            try {
                root = mapper.readTree(response.getBody());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            String bankName = root.path("bank").path("name").asText();
            model.addAttribute("bank", bankName);
            repository.save(new Bank(bin, bankName));
        }

        model.addAttribute("bank", repository.getBankByBin(bin).getBank());
        return "showbank.html";
    }

}
