package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.back.model.User;
import com.back.service.BackApiService;

import java.util.Optional;
import java.util.logging.Logger;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.AUTH_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.ERROR_OTP_MESSAGE;
import static com.constants.MessageConstants.WELCOME_MESSAGE;
import static java.util.Objects.isNull;

public class AuthIntentHandler implements RequestHandler {
    public static final Logger logger = Logger.getLogger(AuthIntentHandler.class.getName());

    private BackApiService backApiService;

    public AuthIntentHandler() {
        backApiService = BackApiService.getInstance();
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(AUTH_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(input);
        String userOTP = requestHelper.getSlotValue("userOTP").get();
        String alexaUserId = input.getRequestEnvelope().getSession().getUser().getUserId();

        User user = backApiService.authorizeAlexaOnApi(userOTP, alexaUserId);

        String message;
        if (isNull(user)) {
            message = ERROR_OTP_MESSAGE;
        } else {
            message = String.format(WELCOME_MESSAGE, user.getUsername());
        }

        return input.getResponseBuilder()
                .withSpeech(message)
                .withSimpleCard("AUTH", message)
                .withReprompt(message)
                .build();
    }
}