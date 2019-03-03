package opera;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperaTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        //Set the path of operadriver. replace with operadriver path. Use can use below any System property
        System.setProperty("webdriver.opera.driver", "./driver/operadriver");
        //OR
        System.setProperty(OperaDriverService.OPERA_DRIVER_EXE_PROPERTY, "./driver/operadriver");
        //OperaDriver instantiation
        WebDriver driver = new OperaDriver();
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
    public void tearDown() {
        //Close the browser
        driver.quit();
    }
}