package io.github.syamsasi99.grid.browser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <h2>Demo Selenium Grid for firefox, safari and chrome</h2>
 * <p>
 * Test: Open google and get title of home page
 *
 * <p>Learning points:
 *
 * <ul>
 * <li>How to set up hub and node
 * <li>Calling grid from java
 * </ul>
 *
 * @author Syam Sasi, syamsasi99@gmail.com
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleBrowserGrid {


    /**
     *
     * java -jar selenium-server-standalone-3.141.59.jar -role hub
     * java -Dwebdriver.gecko.driver="/Users/carousell/SeleniumGridVietnam/drivers/geckodriver" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5566
     * java -jar selenium-server-standalone-3.141.59.jar -role hub -hubConfig hubConfig.json
     * java -Dwebdriver.gecko.driver="/Users/carousell/SeleniumGridVietnam/drivers/geckodriver" -Dwebdriver.chrome.driver="/Users/carousell/SeleniumGridVietnam/drivers/emulator/chromedriver" -jar selenium-server-standalone-3.141.59.jar -role node -nodeConfig nodeConfig.json
     *
     */
    private WebDriver driver;
    private String baseUrl, hubUrl;
    private String ip = "localhost";

    @BeforeAll
    public void setUp() throws IOException {

        baseUrl = "https://google.com/";
        hubUrl = "http://"+ip+":4444/wd/hub";

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testFirefox() throws MalformedURLException {
        initilize("firefox");
        driver.get(baseUrl);
        Assertions.assertEquals("Google", driver.getTitle());

    }


    @Test
    public void testChrome() throws MalformedURLException {
        initilize("chrome");
        driver.get(baseUrl);
        Assertions.assertEquals("Google", driver.getTitle());

    }


    @Test
    public void testSafari() throws MalformedURLException {
        initilize("safari");
        driver.get(baseUrl);
        Assertions.assertEquals("Google", driver.getTitle());

    }

    private void initilize(String browser) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = null;
        switch (browser) {
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setBrowserName("firefox");
                desiredCapabilities.setPlatform(Platform.MAC);
                break;

            case "chrome":
                desiredCapabilities = DesiredCapabilities.chrome();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.MAC);
                break;

            case "safari":
                desiredCapabilities = DesiredCapabilities.safari();
                desiredCapabilities.setBrowserName("safari");
                desiredCapabilities.setPlatform(Platform.MAC);
                break;

        }

        driver = new RemoteWebDriver(new URL(hubUrl), desiredCapabilities);
    }
}