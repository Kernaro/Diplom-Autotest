import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestForMainPage {
    private WebDriver driver;
    private WebDriverWait wait;



    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    @After

    public void tearDown() {
        driver.quit();
    }
    //Переменные для тестов

    //Основные страницы сайта
    private By mainPage = By.id("menu-item-26");
    private By catalogPage = By.id("menu-item-46");
    private By myAccountPage = By.id("menu-item-30");
    private By pageCart = By.id("menu-item-29");
    //Переменные для тестов
    private By buttonUpPage = By.id("ak-top");
    private By titlePage = By.className("current");

    //Тест 1 Переход между основными страницами сайта
    @Test
    public void switchingBetweenMainPagesOfSite(){
        //Переход с главной в Каталог и обратно
    driver.navigate().to("http://intershop5.skillbox.ru/");
    driver.findElement(catalogPage).click();
    var titleCatalogPage = driver.findElement(By.xpath("//header//span"));
    Assert.assertEquals("Мы не на странице Каталог","Каталог",titleCatalogPage.getText());
    driver.findElement(mainPage).click();
    //Переход с главной в Мой Аккаунт и обратно
        driver.findElement(myAccountPage).click();
        Assert.assertEquals("Мы не на странице Мой Аккаунт","Мой Аккаунт",
                driver.findElement(titlePage).getText());
        driver.findElement(mainPage).click();
    //Переход с главной на страницу Корзины и обратно
    driver.findElement(pageCart).click();
   driver.findElement(titlePage);
    Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        driver.findElement(mainPage).click();
    }

    //Тест 2 Переход на страницу Мой Аккаунт по ссылке "Войти"
    @Test
    public void clickToLinkComeIn(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var linkComeIn = driver.findElement(By.className("account"));
        linkComeIn.click();
        Assert.assertEquals("Мы не на странице Мой Аккаунт","Мой Аккаунт",
                driver.findElement(titlePage).getText());
    }

    //Тест 3 Выбрать товар кликом по кнопке посмотреть в промо разеделе сайта:
    @Test
    public void goToCatalogPageToSelector(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var buttonCardTablet = driver.findElement(By.xpath("(//span[@class='btn promo-link-btn'])[2]"));
        buttonCardTablet.click();
        var titleTabletCategory = driver.findElement(By.cssSelector("div.woocommerce-breadcrumb span"));
        Assert.assertEquals("Мы не на страницы категории Планшеты","Планшеты",titleTabletCategory.getText());
    }

    //Тест 4 Перейти к карточке товара кликом по карточке из раздела распродажа
    @Test
    public void clickProductToCartSale (){
        driver.navigate().to("http://intershop5.skillbox.ru/");
       var clickAnyCardProduct =
               driver.findElement(By.cssSelector("div.item-img a[title='платье для вечеринки']"));
       clickAnyCardProduct.click();
       var titleCardProduct = driver.findElement(By.cssSelector("header span"));
        Assert.assertTrue("Не открылась карточка товара",titleCardProduct.isDisplayed());
    }

    //Тест 5 Перейти в карточку товара кликом по кнопке"Посмотреть товар" в Акционом баннере
    @Test
    public void clickStockBanner () {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var clickStockButtonBanner = driver.findElement(By.xpath("//*//img[@alt='Уже в продаже!']"));
        clickStockButtonBanner.click();
        var titleStockCard = driver.findElement(By.cssSelector("header span"));
        Assert.assertTrue("Не открылась каротока акционого товара",titleStockCard.isDisplayed());
    }

    //Тест 6 Переход к карточке товара кликом на карточку товара из раздела Новые поступления:
    @Test
    public void clickProductToCartNewProduct(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var clickAnyCardIsNew = driver.findElement(By.cssSelector(".slick-slide.slick-active"));
        clickAnyCardIsNew.click();
        var titleStockCard = driver.findElement(By.cssSelector("header span"));
        Assert.assertTrue("Не открылась карточка акционного товара",titleStockCard.isDisplayed());
    }

    //Тест 7 Кликнуть по кнопке вверх в самом низу страницы:
    @Test
    public void clickButtonUpPage(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var findElementFooter = driver.findElement(By.cssSelector(".site-info"));
        findElementFooter.click();
        driver.findElement(buttonUpPage).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonUpPage));
    }

    //Тест 8 Использовать поиск с указанием категории товара:
    @Test
    public void searchByProductCategory(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var searchLine = driver.findElement(By.className("search-field"));
        searchLine.sendKeys("Планшеты");
        var searchButton = driver.findElement(By.cssSelector(".fa.fa-search"));
        searchButton.click();
        var titleSearch = driver.findElement(By.cssSelector(".entry-title.ak-container"));
        Assert.assertEquals("Заголовок поиска не соответствует запросу","РЕЗУЛЬТАТЫ ПОИСКА: “ПЛАНШЕТЫ”",
                titleSearch.getText());
    }

    //Тест 9 Клик по ссылке "Регистрация" в Footer:
    @Test
    public void clickToLinkRegistrationInFooter(){
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var linkRegistration = driver.findElement(By.cssSelector("[href $= 'register/']:not([rel])"));
        linkRegistration.click();
       driver.findElement(titlePage);
        Assert.assertEquals("Заголовок страницы не соответствует актуальному","Регистрация",
                driver.findElement(titlePage).getText());
    }

    
}
