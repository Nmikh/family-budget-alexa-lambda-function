package com.constants;

public interface MessageConstants {
    //helpIntent
    String HELP_MESSAGE = "Dear user. It is family budget manager app. " +
            "You can add your add your expenses and get report on them. " +
            "This app also diploma of Nikolay Mikhaliuk and Igor Schevchuck from AC 153. " +
            "Do not be an asshole and do not steal it";

    //LaunchIntent
    String WELCOME_MESSAGE_ANONYMOUS = "Welcome to family budget manager. Verify yourself, please." +
            "You can verify yourself by our mobile app";
    String WELCOME_MESSAGE = "Welcome to family budget manager, %s";

    //authIntent
    String ERROR_OTP_MESSAGE = "Sorry, Your code is not correct. Please, try again";

    //fallbackIntent
    String FALLBACK_MESSAGE = "Sorry, I don't know that. Try one more time";

    //stopIntent
    String GOODBYE_MESSAGE = "Goodbye";

}
