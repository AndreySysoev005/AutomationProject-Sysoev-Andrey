import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GoogleSearchTest {
    @Test
    public void test1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.ru/");
        driver.findElement(By.name("q")).sendKeys("Hellow world", Keys.ENTER);
        System.out.println(driver.findElement(By.tagName("h3")).getText());
        System.out.println(driver.findElement(By.xpath("//h3[1]")).getText());
       driver.quit();
    }
}
