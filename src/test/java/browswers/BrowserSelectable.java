package browswers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public interface BrowserSelectable {
    MutableCapabilities getCapabilities();

    RemoteWebDriver getBrowser();

    void serviceStop();

    void serviceStart();
}
