package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.back.service.BackApiService;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.BUDGET_INTENT_HANDLER_NAME;

public class BadgetIntentHandler implements RequestHandler {
    private BackApiService backApiService;

    public BadgetIntentHandler() {
        backApiService = BackApiService.getInstance();
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(BUDGET_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return Optional.empty();
    }
}
