import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;


public class MyDSL {

    private MyDSL(){}

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static JavascriptExecutor executor;
    private static Set<String> setOfSelectedBrands = new HashSet<>();

    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "ForChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    public static void showAllFiltersTV(){
        driver.findElement(LocatorsTV.allFilters).click();
    }

    public static void enterMinPriceTV(String minPrice){
        driver.findElement(LocatorsTV.allFiltersMinPrice).sendKeys(minPrice);
    }

    public static void openYandexMarketTV(){
        driver.get("https://www.yandex.ru/");
        driver.findElement(LocatorsTV.yandexMarket).click();
        driver.findElement(LocatorsTV.electronics).click();
        driver.findElement(LocatorsTV.tv).click();
    }

    public static void clickCheckBoxBrandOfTV(String brandTV){
        By locatorOfBrandTV = By.xpath("//div[@class='n-filter-block__list-items i-bem']//label[text()=\"" + brandTV + "\"]");
        WebElement checkBoxBrandOfTV = driver.findElement(locatorOfBrandTV);
        executor.executeScript("arguments[0].click()", checkBoxBrandOfTV);
    }

    public static void showSuitableTV(){
        driver.findElement(LocatorsTV.allFiltersShowSuitable).click();
    }

    public static void waitUntilAllTVAreLoaded(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(LocatorsTV.mainObjectsOfPage));
    }

    public static int getQuantityOfTVOnPage(){
        return driver.findElements(LocatorsTV.mainObjectsOfPage).size();
    }

    public static String getFirstNameTVFromPage(){
        return driver.findElement(LocatorsTV.nameOfFirstTVFromPage).getText();
    }

    public static void searchOnYandexMarket(String searchElement){
        driver.findElement(LocatorsTV.fieldOfSearch).clear();
        driver.findElement(LocatorsTV.fieldOfSearch).sendKeys(searchElement);
        driver.findElement(LocatorsTV.buttonSearch).click();
    }

    public static String getNameOfTheFoundObject(){
        return driver.findElement(LocatorsTV.nameObjectSearch).getText();
    }

    public static void clickButtonShowAllBrandsOfTV(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(LocatorsTV.buttonShowAllBrandsOfTV));
        driver.findElement(LocatorsTV.buttonShowAllBrandsOfTV).click();
    }

    public static void chooseBrandOfTVFromAll(String brandOfTVOrPartOfTheWord){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(LocatorsTV.fieldOfSearchBrandTV));

        driver.findElement(LocatorsTV.fieldOfSearchBrandTV).sendKeys(brandOfTVOrPartOfTheWord);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='n-filter-block__list-items-wrap']//div[@class='n-filter-block__item i-bem n-filter-block__item_js_inited'][last()]//label[starts-with(text(),'" + brandOfTVOrPartOfTheWord + "')]")));

        int quantityOfFoundBrands = driver.findElements(LocatorsTV.foundBrandsOfTV).size();

        setOfSelectedBrands.clear();
        By locatorOfBrandTV;
        WebElement checkBoxBrandOfTV;
        Set<Integer>numbersOfFoundBrands = new HashSet<>();
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

    public static Set<String> getSetOfSelectedBrands(){
        return setOfSelectedBrands;
    }

    public static Set<String> getActualSetOfSelectedBrands(){
        int quantityOfActualSelectedBrands = driver.findElements(LocatorsTV.selectedBrandsOnResultPage).size();
        Set<String>resultSetBrands = new HashSet<>();
        String atributID;
        String selectedBrand;
        for(int i = 1; i <= quantityOfActualSelectedBrands; i++){
            atributID = driver.findElement(By.xpath("//ul[@class='_2y67xS5HuR']/li[" + i + "]//input[@checked]")).getAttribute("id");
            selectedBrand = driver.findElement(By.xpath("//ul[@class='_2y67xS5HuR']/li//label[@for='" + atributID + "']//span")).getText();
            resultSetBrands.add(selectedBrand);
        }
        return resultSetBrands;
    }

    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
