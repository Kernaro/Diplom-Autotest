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

public class TestForCatalogPage {
        private WebDriver driver;
        private WebDriverWait wait;


        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 5);
        }

        @After

        public void tearDown() {
            driver.quit();
        }

        //Переменные для тестов
        private By titleCard = By.cssSelector("span:not([class])");
        private By titlePage = By.className("current");
        private By buttonUpPage = By.id("ak-top");

        //Тест 1 Открытие карточки товара из общего списка кликом по карточке:
        @Test
        public void openCardProductToClick(){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var firstCardProduct = driver.findElement(By.className("collection_combine"));
        firstCardProduct.click();
            Assert.assertEquals("Неверно выбрана карточка товара","Apple Watch 6",
                    driver.findElement(titleCard).getText());
        }

        //Тест 2 Добавление карточки товара в корзину
    @Test
    public void addCardProductToCart (){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var firstButtonCartProduct = driver.findElement(By.cssSelector("a.button"));
        firstButtonCartProduct.click();
        var buttonMoreCardToCart = driver.findElement(By.cssSelector("a.added_to_cart.wc-forward"));
        buttonMoreCardToCart.click();
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        var stringProducts = driver.findElement(By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item"));
        Assert.assertTrue("Строка товара не отображается",stringProducts.isDisplayed());
        var checkTitleProductInCart = driver.findElement(By.cssSelector("td.product-name"));
        Assert.assertTrue("Имя товара не соответствует добавленному",checkTitleProductInCart.isDisplayed());
    }

    //Тест 3 Выбор товара из списка категорий товара:
    @Test
    public void choseCategoryProducts(){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var choseCategoryTv = driver.findElement(By.cssSelector("li.cat-item.cat-item-25  a "));
        choseCategoryTv.click();
        Assert.assertEquals("Неверное названия категории","Телевизоры",driver.findElement(titleCard).getText());
    }

    //Тест 4 Выбор карточки товара из списка из раздела "Товара"
    @Test
    public void choseCardProductToBlockProduct(){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var firstCardProduct = driver.findElement(By.cssSelector("ul.product_list_widget li"));
        firstCardProduct.click();
        Assert.assertTrue("Строка товара не отображается",driver.findElement(titleCard).isDisplayed());
    }

    //Тест 5 Переход на 4 страницу каталога
    @Test
    public void goToPageFour (){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var pageFour = driver.findElement(By.cssSelector("a.page-numbers[href$='4/']"));
        pageFour.click();
        Assert.assertEquals("Мы не на странице 4 ","Page 4",driver.findElement(titleCard).getText());
    }

    //Тест 6 Перейти на следующую страницу каталога и обратно стрелочками
    @Test
    public void switchingBetweenCatalogPages (){
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var pageTwo = driver.findElement(By.cssSelector("a.page-numbers[href$='2/']"));
        pageTwo.click();
        Assert.assertEquals("Мы не на странице 2","Page 2",driver.findElement(titleCard).getText());
        var forwardArrow = driver.findElement(By.className("next"));
        forwardArrow.click();
        Assert.assertEquals("Мы не на странице 3","Page 3",driver.findElement(titleCard).getText());
        var backArrow = driver.findElement(By.className("prev"));
        backArrow.click();
        Assert.assertEquals("Мы не на странице 2","Page 2",driver.findElement(titleCard).getText());
    }

    //Тест 7 Кликнуть на кнопку вверх в Footer странице каталог
    @Test
    public void clickButtonUpPage() {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var findElementFooter = driver.findElement(By.cssSelector(".site-info"));
        findElementFooter.click();
        driver.findElement(buttonUpPage).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonUpPage));
    }
    }

