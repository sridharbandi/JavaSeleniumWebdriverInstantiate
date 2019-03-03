package chrome;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeServiceTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeAll
    public static void setUpService() throws IOException {
        //Start the chromedriver service
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("./driver/chromedriver"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @BeforeEach
    public void setUpDriver(){
        //WebDriver instantiation
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
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
        //Stop the chromedriver service
        service.stop();
    }

}