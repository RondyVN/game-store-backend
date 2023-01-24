package com.api.gameshop.controller;

import com.api.gameshop.DTO.UserDTO;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/liqpay")
public class LiqPayController {
    @PostMapping(consumes = "application/json")
    public String  generateLiqPayUrl(
        @org.springframework.web.bind.annotation.RequestBody UserDTO userDTO
    ) throws URISyntaxException {
        URI uri = new URI("https://rxzkhq3zdrhcazww7ors7dheje0mbpmf.lambda-url.eu-central-1.on.aws/");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<UserDTO> httpEntity = new HttpEntity<>(userDTO, headers);
        httpEntity.getBody();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(uri, httpEntity, String.class);
        return result.getBody();
    }
}
