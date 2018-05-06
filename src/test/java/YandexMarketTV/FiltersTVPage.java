package YandexMarketTV;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;

public class FiltersTVPage {

    private By allFiltersMinPrice = By.id("glf-pricefrom-var");
    private By allFiltersShowSuitable = By.linkText("Показать подходящие");
    private By buttonShowAllBrandsOfTV = By.xpath("//div[@class='n-filter-block__list n-filter-block__list_type_more more-list__top i-bem']/button[@class=\"button button_size_xs button_pseudo_yes button_theme_pseudo i-bem button_js_inited\"][1]");
    private By fieldOfSearchBrandTV = By.xpath("//div[@class='n-filter-block__body i-bem n-filter-block__body_js_inited']//input[@class='input__control']");
    private By foundBrandsOfTV = By.xpath("//span[starts-with(@data-filter, 'glf,7893318')]");

    private static Set<String> setOfSelectedBrands = new HashSet<>();

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor executor;

    public FiltersTVPage(WebDriver driver, WebDriverWait webDriverWait, JavascriptExecutor executor) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.executor = executor;
    }

    public void enterMinPriceTV(String minPrice){
        driver.findElement(allFiltersMinPrice).sendKeys(minPrice);
    }

    public void clickCheckBoxBrandOfTV(String brandTV){
        By locatorOfBrandTV = By.xpath("//div[@class='n-filter-block__list-items i-bem']//label[text()=\"" + brandTV + "\"]");
        WebElement checkBoxBrandOfTV = driver.findElement(locatorOfBrandTV);
        executor.executeScript("arguments[0].click()", checkBoxBrandOfTV);
    }

    public ListOfTVsPage showSuitableTV(){
        driver.findElement(allFiltersShowSuitable).click();
        return new ListOfTVsPage(driver, webDriverWait);
    }

    public void clickButtonShowAllBrandsOfTV(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buttonShowAllBrandsOfTV));
        driver.findElement(buttonShowAllBrandsOfTV).click();
    }

    public void searchByWordAndSelectionRandomBrandsOfTVFromAll(String brandOfTVOrPartOfTheWord){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(fieldOfSearchBrandTV));

        driver.findElement(fieldOfSearchBrandTV).sendKeys(brandOfTVOrPartOfTheWord);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='n-filter-block__list-items-wrap']//div[@class='n-filter-block__item i-bem n-filter-block__item_js_inited'][last()]//label[starts-with(text(),'" + brandOfTVOrPartOfTheWord + "')]")));

        int quantityOfFoundBrands = driver.findElements(foundBrandsOfTV).size();

        setOfSelectedBrands.clear();
        By locatorOfBrandTV;
        WebElement checkBoxBrandOfTV;
        Set<Integer> numbersOfFoundBrands = new HashSet<>();
        int randomNumberOfBrand;
        for(int i = 1; i <= 1 + (int) (Math.random() * (quantityOfFoundBrands - 1)); i++){
            randomNumberOfBrand = 1 + (int) (Math.random() * (quantityOfFoundBrands - 1));
            while(true){
                if(!numbersOfFoundBrands.contains(randomNumberOfBrand)){
                    numbersOfFoundBrands.add(randomNumberOfBrand);
                    break;}
                else{randomNumberOfBrand = 1 + (int) (Math.random() * (quantityOfFoundBrands - 1));}
            }
            locatorOfBrandTV = By.xpath("//div[@class='n-filter-block__list-items-wrap']//div[@class='n-filter-block__item i-bem n-filter-block__item_js_inited'][" + randomNumberOfBrand + "]//label");
            setOfSelectedBrands.add(driver.findElement(locatorOfBrandTV).getText());
            checkBoxBrandOfTV = driver.findElement(locatorOfBrandTV);
            executor.executeScript("arguments[0].click()", checkBoxBrandOfTV);
        }
    }

    public Set<String> getSetOfSelectedBrands(){
        return setOfSelectedBrands;
    }


}
