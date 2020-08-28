package com.constants;

public interface MessageConstants {
    //helpIntent
    String HELP_MESSAGE = "Dear user. It is budget manager app. " +
            "You can add your add your expenses and get report on them. " +
            "This app also diploma of Nikolay Mikhaliuk and Igor Schevchuck from AC 153. " +
            "Do not be an asshole and do not steal it";

    //LaunchIntent
    String WELCOME_MESSAGE_ANONYMOUS = "Welcome to budget manager. Verify yourself, please." +
            "You can verify yourself by our mobile app";
    String WELCOME_MESSAGE = "Welcome to budget manager, %s.";

    //authIntent
    String ERROR_OTP_MESSAGE = "Sorry, Your code is not correct. Please, try again";

    //fallbackIntent
    String FALLBACK_MESSAGE = "Sorry, I don't know that. Try one more time";

    //stopIntent
    String GOODBYE_MESSAGE = "Goodbye";

    //budgetIntent
    String BUDGET_MESSAGE = "%s, your budget is %.2f";

    //itemsIntent
    String ITEM_ERROR_MESSAGE = "Sorry, Something wrong with your request. Please try one more time latter";
    String ITEM_SUCCESS_MESSAGE = "Done, %s in %s category with price: %.2f";

}
