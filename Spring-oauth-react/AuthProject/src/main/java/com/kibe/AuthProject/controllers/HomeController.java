package com.kibe.AuthProject.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/")
    public Map home(OAuth2AuthenticationToken token) {
        // return "Welcome" + token.getPrincipal() + token.getCredentials();
        return Map.of(
                "message", "Welcome",
                "name", token.getPrincipal().getName(),
                "email", token.getPrincipal().getAttribute("email"),
                "username", token.getPrincipal(),
                "roles", token.getAuthorities()
        );
    }
}
