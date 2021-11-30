import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Task_5 {
    WebDriver driver = null;

    @BeforeTest
    public void precondition() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");
    }
    public void calculate () {
    WebElement calc = driver.findElement(By.xpath("//*[@class='btn btn-secondary btn-lg tocalc']"));
    calc.click();
    }
    public void select (){
        WebElement direction_laying = driver.findElement(By.name("calc_direct"));
        Select select = new Select(direction_laying);
        select.selectByValue("toh");
    }
    public List<String> getCalculateData() {
        return driver.findElements(By.cssSelector("div[class*='whiteback']>div")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void enter (String element, String value) {
        driver.findElement(By.name(element)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE), value.toString(),Keys.ENTER);
    }
    @Test(dataProvider = "dataProvider")
    public void test(HashMap<String, String> map, List<String> expectedResult){
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");
        map.forEach((key, value) -> {
            if(key.equals("calc_direct")) {
                select();
            } else {
                enter(key, value);
            }
            enter(key, value);
        });
        calculate();
        Assert.assertEquals(getCalculateData (), expectedResult);
        System.out.println(map);
        System.out.println(expectedResult);
    }
    @DataProvider
    public Object [][] dataProvider() {
        return new Object[][]{
                {
                    new HashMap<String, String>() {{
                    put("calc_roomwidth","10");
                    put("calc_roomheight", "10");
                    put("calc_lamwidth", "300");
                    put("calc_lamheight", "50");
                    put("calc_inpack", "10");
                    put("calc_price", "10");
                    put("calc_bias", "10");
                    put("calc_walldist", "10");
                    put("calc_direct", "toh");
                }},
                        Arrays.asList("РЕЗУЛЬТАТ РАСЧЕТА:", "Площадь укладки: 99.60 м2.", "Кол-во панелей: 6778 шт.", "Кол-во упаковок: 678 шт.", "Стоимость: 1017 руб.", "Остатки: 2 шт.", "Отрезки: 356 шт."
                                 , getCalculateData())
                }
        };
    }
    /*@Test
    public void test1() {
        enter("calc_roomwidth", 10);
        enter("calc_roomheight", 10);
        enter("calc_lamwidth", 300);
        enter("calc_lamheight", 50);
        enter("calc_inpack", 10);
        enter("calc_price", 10);
        enter("calc_bias", 10);
        enter("calc_walldist", 10);
    }*/
    /*@Test
        public void test2 () {
            List<String> textList = new ArrayList<>();
            driver.findElements(By.xpath("//*[@class='col-xs-12 col-sm-12']")).forEach(data -> textList.add(data.getText()));
            Assert.assertTrue(textList.contains("Площадь укладки: 99.60 м2."));
            Assert.assertTrue(textList.contains("Кол-во панелей: 6778 шт."));
            Assert.assertTrue(textList.contains("Кол-во упаковок: 678 шт."));
            Assert.assertTrue(textList.contains("Стоимость: 1017 руб."));
            Assert.assertTrue(textList.contains("Остатки: 2 шт."));
            Assert.assertTrue(textList.contains("Отрезки: 356 шт."));

        }*/

    @AfterTest
    public void postcondition() {
        driver.quit();

    }
}
 /*   Открыть сайт https://masterskayapola.ru/kalkulyator/laminata.html
        Ввести параметры для расчета.
        Нажать на кнопку ‘Рассчитать’.
        Проверить полученные значения.
        Закрыть окно браузера.*/
