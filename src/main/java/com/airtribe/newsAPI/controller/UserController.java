package com.airtribe.newsAPI.controller;

import com.airtribe.newsAPI.dto.ArticlesWrapper;
import com.airtribe.newsAPI.dto.UserDTO;
import com.airtribe.newsAPI.entity.newsUser;
import com.airtribe.newsAPI.repository.UserRepository;
import com.airtribe.newsAPI.service.AuthenticationService;
import com.airtribe.newsAPI.service.apiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    apiService apiService;

    @PostMapping("/api/register")
    public newsUser register(@RequestBody UserDTO user){
        return authenticationService.registerUser(user);
    }

    @GetMapping("/api/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @PostMapping("/api/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return authenticationService.loginUser(username,password);
    }

    @GetMapping("/api/tokenDashboard")
    public String tokenDashboard(){
        return "tokenDashboard";
    }

    @GetMapping("/api/preferences")
    public String preferences(@RequestHeader("Authorization") String token){
        return authenticationService.getUserPreferences(token);
    }

    @PutMapping("/api/preferences")
    public newsUser updateUserPreferences(@RequestHeader("Authorization") String token, @RequestParam String preferences){
        return authenticationService.updateUserPreferences(token,preferences);
    }

    @GetMapping("/api/news")
    public Mono<ArticlesWrapper> news(@RequestHeader("Authorization") String token){
        return apiService.getNewsArticles(token);
    }
}
