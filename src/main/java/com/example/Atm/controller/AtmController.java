package com.example.Atm.controller;

import com.example.Atm.service.AtmService;
import com.example.Atm.service.RequiredFields;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/atm", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AtmController {
    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }


    @PostMapping(value = "/deposit", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> depositBalance(@RequestBody RequiredFields requiredFields) {
        boolean successful = this.atmService.deposit(requiredFields.getCardNo(), requiredFields.getPin(), requiredFields.getAmount());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccessful", successful);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> withdrawBalance(@RequestBody RequiredFields requiredFields) {
        boolean done = this.atmService.withdraw(requiredFields.getCardNo(), requiredFields.getPin(), requiredFields.getAmount());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccessful", done);

        return new ResponseEntity<>(jsonObject.toString(),
                HttpStatus.OK);
    }

    @PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transferBalance(@RequestBody RequiredFields requiredFields) {
        boolean done = this.atmService.transfer(requiredFields);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccessful", done);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
