package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.FALLBACK_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.FALLBACK_MESSAGE;

public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(FALLBACK_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(FALLBACK_MESSAGE)
                .withSimpleCard("FallBack", FALLBACK_MESSAGE)
                .withReprompt(FALLBACK_MESSAGE)
                .build();
    }
}