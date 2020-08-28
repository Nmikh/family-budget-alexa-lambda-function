package com.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.back.model.Item;
import com.back.model.ItemDTO;
import com.back.service.BackApiService;

import java.util.Optional;
import java.util.logging.Logger;

import static com.amazon.ask.request.Predicates.intentName;
import static com.constants.HandleConstants.ITEMS_INTENT_HANDLER_NAME;
import static com.constants.MessageConstants.ITEM_ERROR_MESSAGE;
import static com.constants.MessageConstants.ITEM_SUCCESS_MESSAGE;
import static java.lang.String.format;
import static java.util.Objects.isNull;

public class ItemsIntentHandler implements RequestHandler {
    public static final Logger logger = Logger.getLogger(BackApiService.class.getName());

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
        String alexaUserId = handlerInput.getRequestEnvelope().getSession().getUser().getUserId();

        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
        double price = Double.parseDouble(requestHelper.getSlotValue("price").orElse("0"));
        String itemName = requestHelper.getSlotValue("itemName").orElse("item");
        String categoryName = (requestHelper.getSlotValue("categoryName")).orElse("default");

        logger.info("price " + price);
        logger.info("itemName " + itemName);
        logger.info("categoryName " + categoryName);

        ItemDTO itemDTO = ItemDTO.builder()
                .name(itemName)
                .categoryName(categoryName)
                .price(price)
                .build();

        String message;
        Item item = backApiService.createItem(alexaUserId, itemDTO);
        if (isNull(item)) {
            message = ITEM_ERROR_MESSAGE;
        } else {
            message = format(ITEM_SUCCESS_MESSAGE,
                    item.getName(),
                    item.getCategory().getName(),
                    item.getPrice());
        }

        return handlerInput.getResponseBuilder()
                .withSpeech(message)
                .withSimpleCard("ITEM", message)
                .withReprompt(message)
                .build();
    }
}
