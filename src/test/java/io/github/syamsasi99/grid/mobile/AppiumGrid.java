package io.github.syamsasi99.grid.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 *
 * <h2>Demo selenium grid with grid</h2>
 *
 * Test: Run parallel grid tests with selenium grid
 *
 * <p>Learning points:
 *
 * <ul>
 *   <li>Creating the nodes
 *   <li>Registering the nodes to hub
 *   <li>Calling specific node from java grid
 * </ul>
 *
 * @author Syam Sasi, syamsasi99@gmail.com
 */
public class AppiumGrid {

  /**
   * java -cp *:. org.openqa.grid.selenium.GridLauncherV3 -role hub -port 4443
   * appium --nodeconfig node-config-device4724.json -p 4724 -cp 4726 --chromedriver-executable="/Users/carousell/SeleniumGridVietnam/drivers/emulator/chromedriver"
   */

  private AndroidDriver driver;

  private void setUp(String UDID, int SYSTEM_PORT, String DEVICE_NAME)
      throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, UDID);
    capabilities.setCapability(MobileCapabilityType.UDID, UDID);
    capabilities.setCapability("avd", DEVICE_NAME);
    capabilities.setCapability("systemPort", SYSTEM_PORT);
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");


    driver = new AndroidDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void testPhoneCallPixel3() throws MalformedURLException {

    final String UDID = "device";
    final int SYSTEM_PORT = 5724;
    final String DEVICE_NAME = "Pixel3";

    setUp(UDID, SYSTEM_PORT, DEVICE_NAME);
    driver.get("https://www.google.com/");

  }


}
