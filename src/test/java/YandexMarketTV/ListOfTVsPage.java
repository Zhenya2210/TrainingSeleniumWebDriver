package YandexMarketTV;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;


public class ListOfTVsPage {


    private By allFilters = By.className("_1z9VjXlbXw");
    private By mainObjectsOfPage = By.className("n-snippet-card2__content");
    private By nameOfFirstTVFromPage = By.className("n-snippet-card2__title");
    private By selectedBrandsOnResultPage = By.xpath("//ul[@class='_2y67xS5HuR']/li//input[@checked]");


    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor executor;


    public ListOfTVsPage(WebDriver driver, WebDriverWait webDriverWait, JavascriptExecutor executor) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.executor = executor;
    }


    public FiltersTVPage showAllFiltersTV(){
        driver.findElement(allFilters).click();
        return new FiltersTVPage(driver, webDriverWait, executor);
    }

    public void waitUntilAllTVAreLoaded(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(mainObjectsOfPage));
    }

    public int getQuantityOfTVOnPage(){
        return driver.findElements(mainObjectsOfPage).size();
    }

    public String getFirstNameTVFromPage(){
        return driver.findElement(nameOfFirstTVFromPage).getText();
    }

    public Set<String> getActualSetOfSelectedBrands(){
        int quantityOfActualSelectedBrands = driver.findElements(selectedBrandsOnResultPage).size();
        Set<String>resultSetBrands = new HashSet<>();
        String attributeID;
        String selectedBrand;
        for(int i = 1; i <= quantityOfActualSelectedBrands; i++){
            attributeID = driver.findElement(By.xpath("//ul[@class='_2y67xS5HuR']/li[" + i + "]//input[@checked]")).getAttribute("id");
            selectedBrand = driver.findElement(By.xpath("//ul[@class='_2y67xS5HuR']/li//label[@for='" + attributeID + "']//span")).getText();
            resultSetBrands.add(selectedBrand);
        }
        return resultSetBrands;
    }

}
