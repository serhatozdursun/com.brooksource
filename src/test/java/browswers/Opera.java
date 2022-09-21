package browswers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

public class Opera implements BrowserSelectable {

    private final Logger log = LogManager.getLogger(Opera.class);
    private OperaDriverService service;

    @Override
    public MutableCapabilities getCapabilities() {
        var options = new FirefoxOptions();
        options.addArguments("--disabled-notifications");
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
        WebDriverManager.operadriver().setup();
        service = new OperaDriverService
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
