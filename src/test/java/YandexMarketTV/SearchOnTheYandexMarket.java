package YandexMarketTV;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchOnTheYandexMarket {

    private By fieldOfSearch = By.id("header-search");
    private By buttonSearch = By.className("search2__button");

    private WebDriver driver;

    public SearchOnTheYandexMarket(WebDriver driver) {
        this.driver = driver;
    }

    public void searchOnYandexMarket(String searchElement){
        driver.findElement(fieldOfSearch).clear();
        driver.findElement(fieldOfSearch).sendKeys(searchElement);
        driver.findElement(buttonSearch).click();
    }
}
