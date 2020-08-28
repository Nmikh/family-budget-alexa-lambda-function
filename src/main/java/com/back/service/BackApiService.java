package com.back.service;

import com.back.model.Item;
import com.back.model.ItemDTO;
import com.back.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class BackApiService {
    public static final Logger logger = Logger.getLogger(BackApiService.class.getName());

    private static final String BACK_API_URL = System.getenv("BACK_API_URL");
    private static final String AUTH_API_URL = "%s/alexa/auth/%s";
    private static final String BUDGET_BALANCE_API_URL = "%s/alexa/balance";
    private static final String ITEM_API_URL = "%s/alexa/item";

    private static BackApiService backApiService;

    private BackApiService() {
    }

    public static BackApiService getInstance() {
        if (isNull(backApiService)) {
            backApiService = new BackApiService();
        }

        return backApiService;
    }

    public User authorizeAlexaOnApi(String userOTP, String alexaId) {
        try {
            String url = format(AUTH_API_URL, BACK_API_URL, userOTP);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("alexaId", alexaId);

            HttpEntity entity = new HttpEntity(httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> exchange = restTemplate.exchange(url, POST, entity, User.class);

            return exchange.getBody();
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return null;
        }
    }

    public User getUserBalance(String alexaId) {
        try {
            String url = format(BUDGET_BALANCE_API_URL, BACK_API_URL);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("alexaId", alexaId);

            HttpEntity entity = new HttpEntity(httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> exchange = restTemplate.exchange(url, GET, entity, User.class);

            return exchange.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public Item createItem(String alexaId, ItemDTO itemDTO) {
        try {
            String url = format(ITEM_API_URL, BACK_API_URL);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("alexaId", alexaId);
            httpHeaders.setContentType(APPLICATION_JSON);

            HttpEntity entity = new HttpEntity(itemDTO, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Item> exchange = restTemplate.exchange(url, POST, entity, Item.class);

            return exchange.getBody();
        } catch (Exception ex) {
            logger.info(ex.getMessage());

            return null;
        }
    }
}
