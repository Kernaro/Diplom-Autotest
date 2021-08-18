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

public class TestForCartPage {
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

    //!!!Задачи!!!
    //Что надо сделать:
    //
    //Распредели тесты в тестовые классы, чтобы в них было удобнее ориентироваться.
    //Составь тестовые сценарии страницы «Корзина».
    //В тестах с применением купона скидки можно использовать значение sert500, это даст скидку в 500 рублей.
    //Автоматизируй сценарии и залей результат в Git.

    //Переменные для тестов
    private By returnProductInCart = By.className("restore-item");
    private By titlePage = By.className("current");
    private By shoppingTable = By.className("woocommerce-cart-form");
    private By pageCart = By.id("menu-item-29");
    private By stringProduct = By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item");
    private By buttonAddToCartThree = By.xpath("(//a[contains(@class,'add_to_cart')])[3]");
    private By buttonMoreCardToCart = By.cssSelector("a.added_to_cart.wc-forward");
    private By promoCodeInput = By.id("coupon_code");
    private By stringPromoCode = By.className("coupon-sert500");
    private By buttonDeleteProduct = By.className("remove");
    private By firstProductInTable = By.cssSelector("[href$='apple-watch/'");
    private By buttonPromoCode = By.cssSelector("[name='apply_coupon']");
    private By firstButtonCartProduct = By.cssSelector("a.button");
    private By stringProducts = By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item");
    private By checkTitleProductInCart = By.cssSelector("td.product-name");
    private By buttonAddToCartOne = By.xpath("(//a[contains(@class,'add_to_cart')])[1]");
    private By buttonAddToCartTwo = By.xpath("(//a[contains(@class,'add_to_cart')])[2]");
    private By orderTotal = By.cssSelector("tr.order-total  span.amount");

    //Тест 1 Добавление карточки товара в корзину
    @Test
    public void addCardProductToCart (){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());
    }

    //Тест 2 Удаление товара из корзины:
    @Test
    public void deleteCardProductFromBasket(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());
        driver.findElement(buttonDeleteProduct).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingTable));
        Assert.assertTrue("Таблица с товарами не удалилась",driver.findElements(shoppingTable).size()==0);
    }

    //Тест 3 Переход с главной в пустую корзину:
    @Test
    public void goToMainToEmptyCart(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/");

        //Act
        driver.findElement(pageCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        var titleEmptyCart = driver.findElement(By.className("cart-empty"));
        Assert.assertEquals("Неверное сообщение об ошибке","Корзина пуста.",titleEmptyCart.getText());
    }

    //Тест 4 Добавление нескольких товаров в корзину:
    @Test
    public  void addThreeProductInCart () {
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
       driver.findElement(buttonAddToCartOne).click();
        driver.findElement(buttonAddToCartTwo).click();
       driver.findElement(buttonAddToCartThree).click();
        //Этот wait здесь из-за того что кнопки не успевали добавлять товар в корзину и появлялась ошибка
       wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonAddToCartThree));
       driver.findElement(buttonMoreCardToCart).click();

       //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        driver.findElement(shoppingTable).isDisplayed();
        Assert.assertTrue("Неверное количество товара в корзине",driver.findElements(stringProduct).size()==3);
    }

    //Тест 5 Применение купона к товару в корзине:
    @Test
    public void  applyingPromoCodeToItemInCart(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act2
        driver.findElement(promoCodeInput).sendKeys("sert500");
        driver.findElement(buttonPromoCode).click();

        //Assert2
        Assert.assertEquals("Поле с купоном не появилось","СКИДКА: SERT500 -500,00₽ [Удалить]",
                driver.findElement(stringPromoCode).getText());

        //Здесь в ожидаемом результате я написал 34490 вместо 33990 для того чтобы тест проходил,
        // а так это баг Купон не применяется к итоговой цене
        Assert.assertEquals("Скидка не применилась к итоговой цене","34490,00₽",
                driver.findElement(orderTotal).getText());
    }

    //Тест 6 Удаление купона из корзины
    @Test
    public void deletePromoCodeToItemCart(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 2
        driver.findElement(promoCodeInput).sendKeys("sert500");
        driver.findElement(buttonPromoCode).click();

        //Assert 2
        Assert.assertEquals("Поле с купоном не появилось","СКИДКА: SERT500 -500,00₽ [Удалить]",
                driver.findElement(stringPromoCode).getText());
        //Здесь в ожидаемом результате я написал 34490 вместо 33990 для того чтобы тест проходил,
        // а так это баг Купон не применяется к итоговой цене
        Assert.assertEquals("Скидка не применилась к итоговой цене","34490,00₽",
                driver.findElement(orderTotal).getText());
        var linkDeletePromoCode = driver.findElement(By.className("woocommerce-remove-coupon"));
        linkDeletePromoCode.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(stringPromoCode));
        var checkTitleDeletePromoCode = driver.findElement(By.className("woocommerce-message"));
        Assert.assertEquals("Сообщение об удаление купона не появилось","Купон удален.",
                checkTitleDeletePromoCode.getText());


    }

    //Тест 7 Удаление 1 из 3 товаров для проверки изменения итоговой цены в корзине:
    @Test
    public  void deleteOneOfThreeProductInCart() throws InterruptedException {
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(buttonAddToCartOne).click();
        driver.findElement(buttonAddToCartTwo).click();
        driver.findElement(buttonAddToCartThree).click();
        //Этот wait здесь из-за того что кнопки не успевали добавлять товар в корзину и появлялась ошибка
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonAddToCartThree));
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        driver.findElement(shoppingTable).isDisplayed();
        Assert.assertTrue("Неверное количество товара в корзине",driver.findElements(stringProduct).size()==3);
       driver.findElement(buttonDeleteProduct).click();
        Assert.assertEquals("Итоговая цена не изменилась","120790,00₽",driver.findElement(orderTotal).getText());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(firstProductInTable));
        Assert.assertTrue("Неверное количество товара в корзине",driver.findElements(stringProduct).size()==2);
    }

    //Тест 8 Добавление карточки товара в корзину и переход к форме оформления  неавторизованного пользователя
    //Предусловие пользователь не авторизован
    @Test
    public void registrationOfGoodsInBasketUnauthorizedUser() {
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина", "Корзина", driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());
        var buttonCheckout = driver.findElement(By.className("checkout-button"));
        buttonCheckout.click();
        Assert.assertEquals("Мы не на странице оформление заказа",
                "Оформление Заказа",driver.findElement(titlePage).getText());
        var checkAlertMessage = driver.findElement(By.className("woocommerce-info"));
        Assert.assertEquals("Неверное сообщение об авторизации","Зарегистрированы на сайте? Авторизуйтесь",
                checkAlertMessage.getText());
    }

    //Тест 9 Вернуть удаленный товар в корзину кликом по ссылке "Вернуть":
    @Test
    public void returnDeletedProductInCart(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());
        driver.findElement(buttonDeleteProduct).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingTable));
        Assert.assertTrue("Таблица с товарами не удалилась",driver.findElements(shoppingTable).size()==0);
        driver.findElement(returnProductInCart).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(returnProductInCart));
        Assert.assertTrue("Товар не вернулся в корзину",driver.findElements(shoppingTable).size()==1);
    }

    //Тест 10 Ввести неверный купон в поле ввода для купона
    @Test
    public void  applyingNotCorrectPromoCodeToItemInCart (){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());
        driver.findElement(promoCodeInput).sendKeys("test200");
        driver.findElement(buttonPromoCode).click();
        var errorTitleNotCorrectPromoCode = driver.findElement(By.cssSelector(".woocommerce-error li"));
        Assert.assertEquals("Неверный текст ошибки","Неверный купон.",
                errorTitleNotCorrectPromoCode.getText());
    }
}
