import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task_6 {
    WebDriver driver = null;

    @BeforeTest
    public void precondition() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void test1 () {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.cssSelector("input.submit-button.btn_action")).click();
        Assert.assertTrue(driver.findElement(By.tagName("h3")).isDisplayed());

    }





    @AfterTest
    public void postcondition() {     driver.quit();  }
}