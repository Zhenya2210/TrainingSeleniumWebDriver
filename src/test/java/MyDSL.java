import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyDSL {

    private MyDSL(){}

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static JavascriptExecutor executor;

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
        driver.findElement(LocatorsTV.buttonShowAllBrandsOfTV).click();
    }

    public static void chooseBrandOfTVFromAll(String brandOfTV){
        driver.findElement(LocatorsTV.fieldOfSearchBrandTV).sendKeys(brandOfTV);

    }

    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
