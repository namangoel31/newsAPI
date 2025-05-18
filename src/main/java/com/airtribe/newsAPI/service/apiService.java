package com.airtribe.newsAPI.service;

import com.airtribe.newsAPI.dto.ArticlesWrapper;
import com.airtribe.newsAPI.entity.newsUser;
import com.airtribe.newsAPI.repository.UserRepository;
import com.airtribe.newsAPI.repository.VerificationTokenRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.airtribe.newsAPI.utils.JwtUtil.getUsernameFromJwtToken;

@Service
public class apiService {
    @Autowired
    WebClient webClient;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;

//    NewsApiClient newsApiClient = new NewsApiClient("YOUR_API_KEY");
    private static String apiKey = "";

//    public String getNewsArticles(String token) {
//        verificationToken tokenObj = verificationTokenRepository.findByToken(token);
//        String username = getUsernameFromJwtToken(token);
//        newsUser user = userRepository.findByUsername(username);
//        String pref = user.getPreferences();
//    }
    public Mono<ArticlesWrapper> getNewsArticles(String token) {
        System.out.println("Starting the async api call");
        String username = getUsernameFromJwtToken(token);
        newsUser user = userRepository.findByUsername(username);
        String pref = user.getPreferences();
        System.out.println("pref = " + pref);
        System.out.println("dmoz/" + pref);

        Long startTime = System.currentTimeMillis();

        JSONObject query = new JSONObject()
                .put("$query", new JSONObject()
                        .put("$and", new JSONArray()
                                .put(new JSONObject().put("categoryUri", "dmoz/"+pref))
                        )
                );

        JSONObject payload = new JSONObject()
                .put("action", "getArticles")
                .put("articlesPage", 1)
                .put("articlesCount", 10)
                .put("articlesSortBy", "date")
                .put("articlesSortByAsc", false)
                .put("articlesArticleBodyLen", -1)
                .put("resultType", "articles")
                .put("forceMaxDataTimeWindow", 31)
                .put("apiKey", "")
                .put("query", query);

        Mono<ArticlesWrapper> apiResultMono = webClient.post().uri("https://eventregistry.org/api/v1/article/getArticles")
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(payload.toString()))
                .retrieve()
                .bodyToMono(ArticlesWrapper.class)
                .doOnSuccess(apiResult -> {
                    System.out.println("Async Request was handled by thread " + Thread.currentThread().getName());
                    Long endTime = System.currentTimeMillis();
                    System.out.println("Time taken: " + (endTime - startTime));
                });

//        System.out.println("Outside Async Request was handled by thread " + Thread.currentThread().getName());
//        for(int i=0;i<1000L;i++) {
//            //System.out.println("Doing some work in the foreground");
//        }
        return apiResultMono;
    }
}
