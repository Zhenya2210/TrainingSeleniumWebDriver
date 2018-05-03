import org.openqa.selenium.By;

public class LocatorsTV {

    private LocatorsTV(){}

    public static By yandexMarket = By.cssSelector("[data-id='market']");
    public static By electronics = By.linkText("Электроника");
    public static By tv = By.linkText("Телевизоры");
    public static By allFilters = By.className("_1z9VjXlbXw");
    public static By allFiltersMinPrice = By.id("glf-pricefrom-var");
    public static By allFiltersShowSuitable = By.linkText("Показать подходящие");
    public static By mainObjectsOfPage = By.className("n-snippet-card2__content");
    public static By nameOfFirstTVFromPage = By.className("n-snippet-card2__title");
    public static By fieldOfSearch = By.id("header-search");
    public static By buttonSearch = By.className("search2__button");
    public static By nameObjectSearch = By.className("n-title__text");
    public static By buttonShowAllBrandsOfTV = By.className("button button_size_xs button_pseudo_yes button_theme_pseudo i-bem button_js_inited");
    public static By fieldOfSearchBrandTV = By.className("input__control");
}
