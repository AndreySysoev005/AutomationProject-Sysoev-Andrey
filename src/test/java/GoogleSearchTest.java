import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest {
    //проверить поиск если ввести “Привет мир” (проверить что в ссылках после поиска отображается текст  “Привет мир”)
    @Test
    public void test1()  {
        WebDriver driver = new ChromeDriver();
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
    //проверить поиск если ввести “Привет мир” (проверить что в ссылках после поиска отображается текст  “Привет мир”,
    // а также что этот текст отображается в поисковой строке)
    @Test
    public void test2() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru/");
        driver.findElement(By.name("text")).sendKeys("Hello world", Keys.ENTER);
        Thread.sleep(10000);
        System.out.println(driver.findElement(By.xpath("//*[@class='input__control mini-suggest__input']")).getText());
        driver.findElements(By.xpath("//*[@class='input__control mini-suggest__input']")).forEach(el -> {
            System.out.println(el.getText());
        });
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='input__control mini-suggest__input']")).getText(), "Hello world");
        driver.quit();
    }
    // проверить поиск если ввести “//” (проверить что отображается текст “По запросу // ничего не найдено. ”)
    @Test
    public void test3() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru/");
        driver.findElement(By.name("text")).sendKeys("//", Keys.ENTER);
        System.out.println(driver.findElement(By.className("misspell__message")).getText());
        Assert.assertEquals(driver.findElement(By.className("misspell__message")).getText(),"По вашему запросу ничего не нашлось");
        driver.quit();
    }
}


