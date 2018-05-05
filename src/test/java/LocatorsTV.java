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
    public static By buttonShowAllBrandsOfTV = By.xpath("//div[@class='n-filter-block__list n-filter-block__list_type_more more-list__top i-bem']/button[@class=\"button button_size_xs button_pseudo_yes button_theme_pseudo i-bem button_js_inited\"][1]");
    public static By fieldOfSearchBrandTV = By.xpath("//div[@class='n-filter-block__body i-bem n-filter-block__body_js_inited']//input[@class='input__control']");
    public static By foundBrandsOfTV = By.xpath("//span[starts-with(@data-filter, 'glf,7893318')]");
    public static By selectedBrandsOnResultPage = By.xpath("//ul[@class='_2y67xS5HuR']/li//input[@checked]");

}
