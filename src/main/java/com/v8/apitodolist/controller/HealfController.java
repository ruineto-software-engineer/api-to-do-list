package com.v8.apitodolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealfController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String healf() {
        return "I'm live with tasks!";
    }

}
