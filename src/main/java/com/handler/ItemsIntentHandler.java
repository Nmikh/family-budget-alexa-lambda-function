package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.back.service.BackApiService;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.ITEMS_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.ITEM_ERROR_MESSAGE;
import static com.constants.MessageConstants.ITEM_SUCCESS_MESSAGE;

public class ItemsIntentHandler implements RequestHandler {
    private BackApiService backApiService;

    public ItemsIntentHandler() {
        backApiService = BackApiService.getInstance();
    }

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(ITEMS_INTENT_HANDLER_NAME));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
        double price = Double.parseDouble(requestHelper.getSlotValue("price").get());
        String categoryName = requestHelper.getSlotValue("categoryName").get();
        String itemName = requestHelper.getSlotValue("itemName").get();

        String alexaUserId = handlerInput.getRequestEnvelope().getSession().getUser().getUserId();

        String message;
        try {
            backApiService.addItemToUserCategory(alexaUserId, categoryName, itemName, price);
            message = ITEM_SUCCESS_MESSAGE;
        } catch (Exception ex) {
            message = ITEM_ERROR_MESSAGE;
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(message)
                .withSimpleCard("ITEM", message)
                .withReprompt(message)
                .build();
    }
}
