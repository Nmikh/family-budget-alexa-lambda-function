package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.HELP_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.HELP_MESSAGE;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(HELP_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(HELP_MESSAGE)
                .withSimpleCard("HELP", HELP_MESSAGE)
                .withReprompt(HELP_MESSAGE)
                .build();
    }
}