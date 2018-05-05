import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

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
    public void filtersAndSearchTV(){
        MyDSL.showAllFiltersTV();

        MyDSL.enterMinPriceTV("20000");
        MyDSL.clickCheckBoxBrandOfTV("LG");
        MyDSL.clickCheckBoxBrandOfTV("Samsung");

        MyDSL.showSuitableTV();

        MyDSL.waitUntilAllTVAreLoaded();
        int quantityOfTVOnPage = MyDSL.getQuantityOfTVOnPage();

        assertEquals(48, quantityOfTVOnPage,"The number of elements on the page doesn't equal 48");

        String nameFirstTVFromPage = MyDSL.getFirstNameTVFromPage();

        MyDSL.searchOnYandexMarket(nameFirstTVFromPage);

        String nameOfTheFoundObject = MyDSL.getNameOfTheFoundObject();

        assertEquals(nameFirstTVFromPage, nameOfTheFoundObject, "The search didn't find the object:" + nameFirstTVFromPage); //проверка названия телевизора

    }

    @ParameterizedTest
    @ValueSource(strings = {"S", "Sa", "LG"})
    public void filterBrandOfTV(String brandOrPartOfWord){
        MyDSL.showAllFiltersTV();
        MyDSL.clickButtonShowAllBrandsOfTV();
        MyDSL.chooseBrandOfTVFromAll(brandOrPartOfWord);
        Set<String>expectedSetOfSelectedBrands = MyDSL.getSetOfSelectedBrands();
        MyDSL.showSuitableTV();
        Set<String>actualSetOfSelectedBrands = MyDSL.getActualSetOfSelectedBrands();
        assertEquals(expectedSetOfSelectedBrands, actualSetOfSelectedBrands, "Selected filters were incorrectly applied");
    }


    @AfterAll
    public static void tearDown(){
       MyDSL.tearDown();
    }

}
