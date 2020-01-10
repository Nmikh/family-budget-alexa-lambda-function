package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

import static com.constants.MessageConstants.WELCOME_MESSAGE_ANONYMOUS;

public class LaunchRequestHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(WELCOME_MESSAGE_ANONYMOUS)
                .withSimpleCard("WELCOME", WELCOME_MESSAGE_ANONYMOUS)
                .withReprompt(WELCOME_MESSAGE_ANONYMOUS)
                .build();
    }
}
