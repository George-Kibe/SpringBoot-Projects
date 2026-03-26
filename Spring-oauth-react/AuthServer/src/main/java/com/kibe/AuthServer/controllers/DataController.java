package com.kibe.AuthServer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    @GetMapping("/data-sample")
    public String getData() {
        return "This is the data sample from the auth server";
    }
}
