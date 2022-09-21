package driver;

import browswers.*;
import configuration.Configuration;
import enums.Browsers;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class DriverManager {
    private static DriverManager instance;
    private BrowserSelectable browserSelectable;
    private WebDriver driver;
    private Browsers browser;

    private DriverManager() {

    }
    public static DriverManager getInstance() {
        if (instance == null)
            instance = new DriverManager();
        return instance;
    }

    public void startDriverService() {
        var browserType = System.getProperty("browser") == null
                ? Configuration.getInstance().getBrowser()
                : System.getProperty("browser");
        setBrowser(Browsers.valueOf(browserType.toUpperCase(Locale.ENGLISH)));
        switch (getBrowser()) {
            case SAFARI -> {
                browserSelectable = new Safari();
                browserSelectable.serviceStart();
            }
            case CHROME -> {
                browserSelectable = new Chrome();
                browserSelectable.serviceStart();
            }
            case EDGE -> {
                //TODO
            }
            case OPERA -> {
                browserSelectable = new Opera();
                browserSelectable.serviceStart();
            }
            case FIREFOX -> {
                browserSelectable = new Firefox();
                browserSelectable.serviceStart();
            }
        }
    }

    public void lunchBrowser() {
        setDriver(browserSelectable.getBrowser());
        getDriver().manage().window().maximize();
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public WebDriver getDriver() {
        if (driver == null)
            lunchBrowser();
        return driver;
    }

    public void closeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public void stopService() {
        browserSelectable.serviceStop();
    }

    private void setBrowser(Browsers browser){
        this.browser = browser;
    }

    public Browsers getBrowser(){
        return browser;
    }
}
