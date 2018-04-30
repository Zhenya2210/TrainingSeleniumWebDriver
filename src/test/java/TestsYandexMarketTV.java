import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestsYandexMarketTV {


    @BeforeAll
    public static void setUp(){
       MyDSL.setUp();
    }

    @BeforeEach
    public void openYandexMarketTV(){
        MyDSL.openYandexMarketTV();
    }

    @Test
    public void filtersAndSearchTV() throws InterruptedException {
        MyDSL.showAllFiltersTV();
        MyDSL.enterMinPriceTV("20000");

        MyDSL.clickCheckBoxBrandOfTV(LocatorsTV.allFiltersCheckBoxLG);
        MyDSL.clickCheckBoxBrandOfTV(LocatorsTV.allFiltersCheckBoxSamsung);

        MyDSL.showSuitableTV();

        MyDSL.waitUntilAllTVAreLoaded();
        int quantityOfTVOnPage = MyDSL.getQuantityOfTVOnPage();

        assertEquals(48, quantityOfTVOnPage,"The number of elements on the page doesn't equal 48"); //проверка кол-ва элементов

        String nameFirstTVFromPage = MyDSL.getFirstNameTVFromPage();

        MyDSL.searchOnYandexMarket(nameFirstTVFromPage);

        String nameOfTheFoundObject = MyDSL.getNameOfTheFoundObject();

        assertEquals(nameFirstTVFromPage, nameOfTheFoundObject, "The search didn't find the object:" + nameFirstTVFromPage); //проверка названия телевизора

    }

    @AfterAll
    public static void tearDown(){
       MyDSL.tearDown();
    }



}
