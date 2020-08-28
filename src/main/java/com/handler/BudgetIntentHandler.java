package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.back.model.User;
import com.back.service.BackApiService;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.BUDGET_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.BUDGET_MESSAGE;
import static java.lang.String.format;

public class BudgetIntentHandler implements RequestHandler {
    private BackApiService backApiService;

    public BudgetIntentHandler() {
        backApiService = BackApiService.getInstance();
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(BUDGET_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String alexaUserId = input.getRequestEnvelope().getSession().getUser().getUserId();
        User user = backApiService.getUserBalance(alexaUserId);

        String message = format(BUDGET_MESSAGE, user.getUsername(), user.getBudget());

        return input.getResponseBuilder()
                .withSpeech(message)
                .withSimpleCard("Balance", message)
                .withReprompt(message)
                .build();
    }
}
