package YandexMarketTV;

import GeneralClassesYndexMarket.YandexMarketStart;
import GeneralClassesYndexMarket.ObjectOfYandexMarketPage;
import GeneralClassesYndexMarket.SearchOnTheYandexMarket;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestsYandexMarketTV {

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;
    private static JavascriptExecutor executor;

    @BeforeAll
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "ForChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void openYandexMarketTV(){
        YandexMarketStart yandexMarketTV = new YandexMarketStart(driver);
        yandexMarketTV.openYandexMarketTV();
    }

    @Test
    public void filtersAndSearchTV(){
        ListOfTVsPage defaultListOfTVsPage = new ListOfTVsPage(driver, webDriverWait);
        defaultListOfTVsPage.showAllFiltersTV();
        FiltersTVPage filtersTV = new FiltersTVPage(driver, webDriverWait, executor);
        filtersTV.enterMinPriceTV("20000");
        filtersTV.clickCheckBoxBrandOfTV("LG");
        filtersTV.clickCheckBoxBrandOfTV("Samsung");
        ListOfTVsPage resultListOfTVsPage = filtersTV.showSuitableTV();
        resultListOfTVsPage.waitUntilAllTVAreLoaded();
        int quantityOfTVOnPage = resultListOfTVsPage.getQuantityOfTVOnPage();
        assertEquals(48, quantityOfTVOnPage,"The number of elements on the page doesn't equal 48");
        String nameFirstTVFromPage = resultListOfTVsPage.getFirstNameTVFromPage();
        SearchOnTheYandexMarket searchTVOnTheYandexMarket = new SearchOnTheYandexMarket(driver);
        ObjectOfYandexMarketPage foundTV = searchTVOnTheYandexMarket.searchOnYandexMarket(nameFirstTVFromPage);
        String nameOfTheFoundObject = foundTV.getNameOfTheFoundObject();
        assertEquals(nameFirstTVFromPage, nameOfTheFoundObject, "The search didn't find the object:" + nameFirstTVFromPage); //проверка названия телевизора

    }

    @ParameterizedTest
    @ValueSource(strings = {"S", "Sa", "LG"})
    public void filterBrandOfTV(String brandOrPartOfWord){
        ListOfTVsPage defaultListOfTVsPage = new ListOfTVsPage(driver, webDriverWait);
        defaultListOfTVsPage.showAllFiltersTV();
        FiltersTVPage filtersTV = new FiltersTVPage(driver, webDriverWait, executor);
        filtersTV.clickButtonShowAllBrandsOfTV();
        filtersTV.searchByWordTVBrandsFromAll(brandOrPartOfWord);
        filtersTV.selectionOfRandomTVBrandsFromTheFound();
        Set<String>expectedSetOfSelectedBrands = filtersTV.getSetOfSelectedBrands();
        ListOfTVsPage resultListOfTVsPage = filtersTV.showSuitableTV();
        Set<String>actualSetOfSelectedBrands = resultListOfTVsPage.getActualSetOfSelectedBrands();
        assertEquals(expectedSetOfSelectedBrands, actualSetOfSelectedBrands, "Selected filters were incorrectly applied");
    }


    @AfterAll
    public static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
