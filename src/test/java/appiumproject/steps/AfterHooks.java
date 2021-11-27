package appiumproject.steps;

import java.util.logging.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class AfterHooks {
    private final Logger logger = Logger.getLogger(AfterHooks.class.getName());

    @After
    public void after(Scenario scenario){
        logger.info(String.format("Test result is %s", scenario.getStatus()));
    }
}
