package page;

import configuration.Configuration;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.BaseTest;

public class JobDescriptionPage extends BaseTest {
    public JobDescriptionPage() {
        var waitTime = Configuration.getInstance().getWaitTime();
        var ajax = new AjaxElementLocatorFactory(DriverManager.getInstance().getDriver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
    }

    @FindBy(tagName = "h1")
    private WebElement jobTitle;

    public String getJobTitle(){
        return getText(jobTitle);
    }

}
