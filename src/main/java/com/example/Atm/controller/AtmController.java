package com.example.Atm.controller;

import com.example.Atm.service.AtmService;
import com.example.Atm.service.RequiredFields;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
public class AtmController {
    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }




    @PostMapping("/deposit")
    public ResponseEntity<?> depositBalance(@RequestBody RequiredFields requiredFields) {
        // atmService.deposit(actions.getCardNo(), actions.getPin(), actions.getAmount());

        boolean successful = this.atmService.deposit(requiredFields.getCardNo(), requiredFields.getPin(), requiredFields.getAmount());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isSuccessful", successful);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    public ResponseEntity<?> withdrawBalance(@RequestBody RequiredFields requiredFields) {

        return new ResponseEntity<>(//"Card Number ?" +  atmService.withdraw(requiredFields.getCardNo(), requiredFields.getPin(), requiredFields.getAmount())
                HttpStatus.OK);
    }

}
