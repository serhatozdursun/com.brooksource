package utilities;

import configuration.Configuration;
import driver.DriverManager;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    private WebDriverWait getWait() {
        var waitTime = Configuration.getInstance().getWaitTime();
        return new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(waitTime));
    }

    private Actions getActions() {
        return new Actions(DriverManager.getInstance().getDriver());
    }

    private WebElement clickableWait(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement visibleWait(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected JavascriptExecutor javaScriptExecute() {
        return (JavascriptExecutor) DriverManager.getInstance().getDriver();
    }

    protected void waitUntilPageLoad() {
        getWait().until(new PageLoaded());
    }

    protected void mouseHover(WebElement element) {
        getActions()
                .moveToElement(element)
                .build()
                .perform();
    }

    protected void click(WebElement element) {
        clickableWait(element).click();
    }

    protected void sendKeys(WebElement element, String text) {
        clickableWait(element).sendKeys(text);
    }

    protected String getText(WebElement element) {
        return visibleWait(element).getText();
    }
}

class PageLoaded implements ExpectedCondition {
    @Override
    public @Nullable Object apply(Object o) {
        return String.valueOf(new BaseTest().javaScriptExecute().executeScript("return document.readyState")).equals("complete");
    }
}
