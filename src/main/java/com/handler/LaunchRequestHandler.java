package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.back.model.User;
import com.back.service.BackApiService;

import java.util.Optional;

import static com.constants.MessageConstants.WELCOME_MESSAGE;
import static com.constants.MessageConstants.WELCOME_MESSAGE_ANONYMOUS;
import static java.lang.String.format;
import static java.util.Objects.isNull;

public class LaunchRequestHandler implements RequestHandler {

    private BackApiService backApiService;

    public LaunchRequestHandler() {
        backApiService = BackApiService.getInstance();
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String alexaUserId = input.getRequestEnvelope().getSession().getUser().getUserId();
        User user = backApiService.getUserBalance(alexaUserId);
        if (isNull(user)) {
            return input.getResponseBuilder()
                    .withSpeech(WELCOME_MESSAGE_ANONYMOUS)
                    .withSimpleCard("WELCOME", WELCOME_MESSAGE_ANONYMOUS)
                    .withReprompt(WELCOME_MESSAGE_ANONYMOUS)
                    .build();
        }

        String message = format(WELCOME_MESSAGE, user.getUsername());
        return input.getResponseBuilder()
                .withSpeech(message)
                .withSimpleCard("WELCOME", message)
                .withReprompt(message)
                .build();
    }
}
