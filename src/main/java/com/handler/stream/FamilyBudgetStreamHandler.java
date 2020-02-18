package com.handler.stream;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.handler.BadgetIntentHandler;
import com.handler.CancelAndStopIntentHandler;
import com.handler.HelpIntentHandler;
import com.handler.ItemsIntentHandler;
import com.handler.LaunchRequestHandler;
import com.handler.SessionEndedRequestHandler;

public class FamilyBudgetStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new ItemsIntentHandler(),
                        new BadgetIntentHandler())
                .build();
    }

    public FamilyBudgetStreamHandler() {
        super(getSkill());
    }

}
