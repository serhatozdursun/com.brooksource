package utilities;

import driver.DriverManager;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage {
    private final Logger log = LogManager.getLogger(BasePage.class);

    @After
    public void teardown(){
        DriverManager.getInstance().closeDriver();
        DriverManager.getInstance().stopService();
        log.info("Test completed");
    }

}
