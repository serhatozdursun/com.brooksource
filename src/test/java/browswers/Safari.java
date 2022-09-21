package browswers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;

public class Safari implements BrowserSelectable {
    private final Logger log = LogManager.getLogger(Firefox.class);
    private SafariDriverService service;

    @Override
    public MutableCapabilities getCapabilities() {
        var options = new SafariOptions();
        options.setCapability("browser.local", true);
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
        service = new SafariDriverService
                .Builder()
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
