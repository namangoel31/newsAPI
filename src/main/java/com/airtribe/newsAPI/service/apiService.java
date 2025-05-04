package com.airtribe.newsAPI.service;

import com.airtribe.newsAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class apiService {
    @Autowired
    WebClient webClient;

//    NewsApiClient newsApiClient = new NewsApiClient("YOUR_API_KEY");
}
