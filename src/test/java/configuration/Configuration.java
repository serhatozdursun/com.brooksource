package configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static enums.PropertiesType.*;

public class Configuration {

    private static Configuration instance;
    private static final Logger log = LogManager.getLogger(Configuration.class);
    private static final String PROP_FILE_NAME = "config.properties";
    private String baseUrl;
    private Integer waitTime;
    private String browser;

    public static Configuration getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
    }

    private Configuration() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME)) {
            Properties configProps = new Properties();
            configProps.load(is);
            baseUrl = configProps.getProperty(BASE_URL.getText());
            waitTime = Integer.valueOf(configProps.getProperty(WAIT_TIME.getText()));
            browser = configProps.getProperty(BROWSER.getText());
        } catch (Exception e) {
            log.error(e);
        } finally {
            log.info("Properties read finished.");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }
    public Integer getWaitTime() {
        return waitTime;
    }

}