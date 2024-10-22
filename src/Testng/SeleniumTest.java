package Testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumTest {
    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws MalformedURLException {
        // Set the hub URL for Selenium Grid
        String hubUrl = "http://localhost:4444/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if (browser.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
        } else if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        } else if (browser.equalsIgnoreCase("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
        }

        // Initialize the remote WebDriver with the Grid hub URL
        driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
    }

    @Test
    public void amazonTitleTest() {
        // Open Amazon and get the page title
        driver.get("https://www.amazon.in");
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
