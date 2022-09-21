package page;

import configuration.Configuration;
import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.BaseTest;

public class HomePage extends BaseTest {
    public HomePage() {
        var waitTime = Configuration.getInstance().getWaitTime();
        var ajax = new AjaxElementLocatorFactory(DriverManager.getInstance().getDriver(), waitTime);
        PageFactory.initElements(ajax, this);
        waitUntilPageLoad();
    }

    @FindBy(className = "navlink-findwork")
    private WebElement findWork;
    @FindBy(css = ".navlink-seekingposition")
    private WebElement seekingPosition;
    @FindBy(css = ".navlink-whatweoffer")
    private WebElement whatWeOffer;
    @FindBy(css = ".navlink-findworkprocess")
    private WebElement findWorkProcess;

    public void hoverToFindWork(){
        mouseHover(findWork);
    }

    public void clickSeekingPosition(){
        click(seekingPosition);
    }

    public void clickWhatWeOffer(){
        click(whatWeOffer);
    }

    public void clickFindWorkProcess(){
        click(findWorkProcess);
    }

}
