package browswers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

public class Chrome implements BrowserSelectable {
    private final Logger log = LogManager.getLogger(Chrome.class);
    private ChromeDriverService service;

    @Override
    public MutableCapabilities getCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-–start-fullscreen");
        return options;
    }

    @Override
    public RemoteWebDriver getBrowser() {
        return new RemoteWebDriver(service.getUrl(), getCapabilities());
    }

    @Override
    public void serviceStop() {
        service.stop();
    }

    @Override
    public void serviceStart() {
        WebDriverManager.chromedriver().setup();
        service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
