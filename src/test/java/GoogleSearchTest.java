import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest {
    @Test
    public void test1()  {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://yandex.ru/");
        driver.findElement(By.name("text")).sendKeys("Hello world", Keys.ENTER);
        System.out.println(driver.findElement(By.tagName("h2")).getText());
        System.out.println(driver.findElement(By.xpath("//h2[1]")).getText());
        driver.findElements(By.tagName("h2")).forEach(el -> {
            System.out.println(el.getText());
                });
       Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(),"Hello, world! — Википедия");
       driver.quit();
    }
}
