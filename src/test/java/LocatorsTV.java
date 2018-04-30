import org.openqa.selenium.By;

public class LocatorsTV {

    private LocatorsTV(){}

    public static By yandexMarket = By.cssSelector("[data-id='market']");
    public static By electronics = By.linkText("Электроника");
    public static By tv = By.linkText("Телевизоры");
    public static By allFilters = By.className("_1z9VjXlbXw");
    public static By allFiltersMinPrice = By.id("glf-pricefrom-var");
    public static By allFiltersCheckBoxLG = By.id("glf-7893318-153074");
    public static By allFiltersCheckBoxSamsung = By.id("glf-7893318-153061");
    public static By allFiltersShowSuitable = By.linkText("Показать подходящие");
    public static By mainObjectsOfPage = By.className("n-snippet-card2__content");
    public static By nameOfFirstTVFromPage = By.className("n-snippet-card2__title");
    public static By fieldOfSearch = By.id("header-search");
    public static By buttonSearch = By.className("search2__button");
    public static By nameObjectSearch = By.className("n-title__text");

}
