package safari;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SafariServiceTest {

    private static SafariDriverService service;
    private WebDriver driver;

    @BeforeAll
    public static void setUpService() throws IOException {
        //Start the SafariDriverService service
        service = new SafariDriverService.Builder()
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @BeforeEach
    public void setUpDriver(){
        //WebDriver instantiation
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.safari());
        //Maximizes the browser
        driver.manage().window().maximize();
        //Open the URL
        driver.get("http://www.google.com/");
    }

    @Test
    public void verifyTitle() {
        //Verify the title is Google
        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void searchSelenium() {
        //WebElement instantiation
        WebElement searchBox = driver.findElement(By.name("q"));
        //Type Selenium
        searchBox.sendKeys("Selenium");
        //Simulate Hit Keyboard Enter
        searchBox.submit();
    }

    @AfterEach
    public void closeBrowser() {
        //Close the browser
        driver.quit();
    }

    @AfterAll
    public static void closeService(){
        //Stop the driver service
        service.stop();
    }

}