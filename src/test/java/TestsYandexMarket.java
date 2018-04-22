import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestsYandexMarket {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static JavascriptExecutor executor;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "ForChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void openYandexMarket(){
        driver.get("https://www.yandex.ru/");
        driver.findElement(Locators.yandexMarket).click();
    }

    @Test
    public void filtersAndSearchTV() throws InterruptedException {

        driver.findElement(Locators.electronics).click();
        driver.findElement(Locators.tv).click();
        driver.findElement(Locators.allFilters).click();

        driver.findElement(Locators.allFiltersMinPrice).sendKeys("20000");

        Helper.clickCheckBoxBrandOfTV(Locators.allFiltersCheckBoxLG);
        Helper.clickCheckBoxBrandOfTV(Locators.allFiltersCheckBoxSamsung);

        driver.findElement(Locators.allFiltersShowSuitable).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Locators.mainObjectsOfPage));
        assertEquals(24, driver.findElements(Locators.mainObjectsOfPage).size(),"The number of elements on the page doesn't equal 12"); //проверка кол-ва элементов

        String nameFirstTVFromPage = driver.findElement(Locators.nameOfFirstTVFromPage).getText();
        driver.findElement(Locators.fieldOfSearch).clear();
        driver.findElement(Locators.fieldOfSearch).sendKeys(nameFirstTVFromPage);
        driver.findElement(Locators.buttonSearch).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(Locators.nameObjectSearch));
        assertEquals(nameFirstTVFromPage, driver.findElement(Locators.nameObjectSearch).getText(), "The search didn't find the object:" + nameFirstTVFromPage); //проверка названия телевизора

    }
    

    @AfterAll
    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }



}
