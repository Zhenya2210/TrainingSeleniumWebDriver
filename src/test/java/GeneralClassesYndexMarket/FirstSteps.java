package GeneralClassesYndexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstSteps {

    private By yandexMarket = By.cssSelector("[data-id='market']");
    private By electronics = By.linkText("Электроника");
    private By tv = By.linkText("Телевизоры");

    private WebDriver driver;

    public FirstSteps(WebDriver driver){
        this.driver = driver;
    }

    public void openYandexMarketTV(){
        driver.get("https://www.yandex.ru/");
        driver.findElement(yandexMarket).click();
        driver.findElement(electronics).click();
        driver.findElement(tv).click();
    }
}
