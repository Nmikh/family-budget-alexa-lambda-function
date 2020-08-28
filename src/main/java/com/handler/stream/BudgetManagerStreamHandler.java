package com.handler.stream;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.handler.AuthIntentHandler;
import com.handler.BudgetIntentHandler;
import com.handler.CancelAndStopIntentHandler;
import com.handler.FallbackIntentHandler;
import com.handler.HelpIntentHandler;
import com.handler.ItemsIntentHandler;
import com.handler.LaunchRequestHandler;
import com.handler.SessionEndedRequestHandler;

public class BudgetManagerStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new FallbackIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new AuthIntentHandler(),
                        new ItemsIntentHandler(),
                        new BudgetIntentHandler()

                )
                .build();
    }

    public BudgetManagerStreamHandler() {
        super(getSkill());
    }
}
