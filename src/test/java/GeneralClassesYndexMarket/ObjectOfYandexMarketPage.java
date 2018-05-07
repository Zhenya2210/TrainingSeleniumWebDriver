package GeneralClassesYndexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObjectOfYandexMarketPage {

    private By nameObjectSearch = By.xpath("//h1[@class='title title_size_28 title_bold_yes']");

    private WebDriver driver;

    public ObjectOfYandexMarketPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNameOfTheFoundObject(){
        return driver.findElement(nameObjectSearch).getText();
    }
}
