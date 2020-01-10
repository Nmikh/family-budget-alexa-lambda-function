package com.back.service;

import com.back.model.AuthoriseModel;
import com.back.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpMethod.POST;

public class BackApiService {
    private static BackApiService backApiService;
    private static final String BACK_API_URL = System.getenv("BACK_API_URL");
    private static String AUTH_BACK_API_URL = "/auth";

    private BackApiService() {
    }

    public static BackApiService getInstance() {
        if (isNull(backApiService)) {
            backApiService = new BackApiService();
        }

        return backApiService;
    }

    public User authorizeAlexaOnServer(String userOTP, String alexaId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            AuthoriseModel authoriseModel = new AuthoriseModel(alexaId, userOTP);
            HttpEntity entity = new HttpEntity(authoriseModel, null);

            ResponseEntity<User> exchange = restTemplate.exchange(BACK_API_URL + AUTH_BACK_API_URL, POST, entity, User.class);
            return exchange.getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
