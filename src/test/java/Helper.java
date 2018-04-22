import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Helper {

    public static void clickCheckBoxBrandOfTV(By locatorOfBrandTV){
        WebElement checkBoxBrandOfTV = TestsYandexMarket.driver.findElement(locatorOfBrandTV);
        TestsYandexMarket.executor.executeScript("arguments[0].click()", checkBoxBrandOfTV);
    }
}
