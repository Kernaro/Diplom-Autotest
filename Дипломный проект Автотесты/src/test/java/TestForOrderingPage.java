import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.concurrent.TimeUnit;

public class TestForOrderingPage {
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

    //!!!Задачи!!!!
    //Составь тестовые сценарии для оформления заказа.
    //Автоматизируй сценарии и залей результат в Git.

    //Переменные для тестов
    private By inputName = By.id("billing_first_name");
    private By inputLastName = By.id("billing_last_name");
    private By inputAddress = By.id("billing_address_1");
    private By inputCity = By.id("billing_city");
    private By inputState = By.id("billing_state");
    private By inputPostcode = By.id("billing_postcode");
    private By inputPhone = By.id("billing_phone");
    private By inputEmail = By.id("billing_email");
    private By buttonOrderingForm = By.id("place_order");
    private String name = "Николай";
    private String lastName = "Соколов";
    private String address = "Мира 5";
    private String city = "Норильск";
    private String state = "Алтайская";
    private String postcode = "111000";
    private String phone = "111000000011";
    private String email = "Test@mail.ru";
    private By firstButtonCartProduct = By.cssSelector("a.button");
    private By titlePage = By.className("current");
    private By checkAlertMessage = By.className("woocommerce-info");
    private By buttonMoreCardToCart = By.cssSelector("a.added_to_cart.wc-forward");
    private By stringProducts = By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item");
    private By checkTitleProductInCart = By.cssSelector("td.product-name");
    private By orderingButton = By.className("checkout-button");
    private String login = "Reat@mail.ru";
    private String password = "Reat@mail.ru";
    private By inputLogin = By.id("username");
    private By inputPassword = By.id("password");
    private  By buttonComeIn = By.cssSelector("[name='login']");
    private By checkTitleMyAccount = By.cssSelector(".woocommerce-MyAccount-content p");
    private By checkOrderingProduct = By.className("post-title");
    private By orderingMessageAlert = By.className("woocommerce-NoticeGroup");
    private By orderingLinkCoupon = By.className("showcoupon");
    private By orderingInputCoupon = By.id("coupon_code");
    private By orderingButtonCoupon = By.className("button");
    private By orderingStringCoupon = By.className("coupon-sert500");
    private By alertMessage = By.cssSelector(".woocommerce-error li");
    private By radioPayCash = By.id("payment_method_cod");


    //Тест 1 Оформление товара не авторизованного пользователя c оплатой онлайн
    @Test
    public void orderingProductDeAuthorizationUserPayCredit() {
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        Assert.assertEquals("Мы не на странице Корзина", "Корзина", driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается", driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act2
        driver.findElement(orderingButton).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());
        Assert.assertEquals("Неверное сообщение об авторизации", "Зарегистрированы на сайте? Авторизуйтесь",
                driver.findElement(checkAlertMessage).getText());
    }

    //Тест 2 Оформление заказа авторизованого пользователя с корректным вводом в поля/Оплата банковским переводом
    @Test
    public void orderingProductAuthorizationUser(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4

        Assert.assertEquals("Неверное сообщение о заказе","Оформление заказа",
                driver.findElement(checkOrderingProduct).getText());
    }
    //Тест 3 Оформление заказа пользователя без заполнения поля "Имя"
    @Test
    public void userCheckoutWithoutFillingNameField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке","Имя для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 4 Оформление заказа пользователя без заполнения поля "Фамилия"
    @Test
    public void userCheckoutWithoutFillingLastNameField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке","Фамилия для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 5 Оформление заказа пользователя без заполнения поля "Адрес"
    @Test
    public void userCheckoutWithoutFillingAddressField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке","Адрес для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 6 Оформление заказа пользователя без заполнения поля "Город"
    @Test
    public void userCheckoutWithoutFillingCityField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "Город / Населенный пункт для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 7 Оформление заказа пользователя без заполнения поля "Область"
    @Test
    public void userCheckoutWithoutFillingStateField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке","Область для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 8 Оформление заказа пользователя без заполнения поля "Индекс"
    @Test
    public void userCheckoutWithoutFillingPostcodeField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "Почтовый индекс для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 9 Оформление заказа пользователя без заполнения поля "Телефон"
    @Test
    public void userCheckoutWithoutFillingPhoneField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "неверный номер телефона.\n" +
                        "Телефон для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 10 Оформление заказа пользователя без заполнения поля "Email"
    @Test
    public void userCheckoutWithoutFillingEmailField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(inputEmail).clear();
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "Адрес почты для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 11 Оформление заказа пользователя c некорректным заполнением поля "Email"
    @Test
    public void incorrectFillingEmailField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(inputEmail).clear();
        driver.findElement(inputEmail).sendKeys("@@@@");
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "Invalid billing email address",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 12 Отправка пустой формы оформления заказа
    @Test
    public void submittingBlankCheckoutForm(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputLastName).clear();
        driver.findElement(inputAddress).clear();
        driver.findElement(inputCity).clear();
        driver.findElement(inputState).clear();
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputEmail).clear();
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertEquals("Неверный текст сообщения об ошибке",
                "Имя для выставления счета обязательное поле.\n" +
                        "Фамилия для выставления счета обязательное поле.\n" +
                        "Адрес для выставления счета обязательное поле.\n" +
                        "Город / Населенный пункт для выставления счета обязательное поле.\n" +
                        "Область для выставления счета обязательное поле.\n" +
                        "Почтовый индекс для выставления счета обязательное поле.\n" +
                        "неверный номер телефона.\n" +
                        "Телефон для выставления счета обязательное поле.\n" +
                        "Адрес почты для выставления счета обязательное поле.",
                driver.findElement(orderingMessageAlert).getText());
    }

    //Тест 13 Оформление заказа c применением купона
    @Test
    public void orderingProductСouponApplication(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Reat@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(orderingLinkCoupon).click();
        driver.findElement(orderingInputCoupon).sendKeys("sert500");
        driver.findElement(orderingButtonCoupon).click();
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);

        driver.findElement(buttonOrderingForm).click();

        //Assert 4

        Assert.assertTrue("Строка с купоном не появилась",driver.findElement(orderingStringCoupon).isDisplayed());
        Assert.assertEquals("Неверное сообщение о заказе","Оформление заказа",
                driver.findElement(checkOrderingProduct).getText());
    }
    //Тест 14 Оформление заказа c применением купона 2 раза
    @Test
    public void orderingProductTwoСouponApplication(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Николай Соколов (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(orderingLinkCoupon).click();
        driver.findElement(orderingInputCoupon).sendKeys("sert500");
        driver.findElement(orderingButtonCoupon).click();
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        //Повторное применение купона
        driver.findElement(orderingLinkCoupon).click();
        driver.findElement(orderingInputCoupon).clear();
        driver.findElement(orderingInputCoupon).sendKeys("sert500");
        driver.findElement(orderingButtonCoupon).click();
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(buttonOrderingForm).click();

        //Assert 4
        Assert.assertTrue("Строка с купоном не появилась",driver.findElement(orderingStringCoupon).isDisplayed());
        Assert.assertEquals("Неправильный текст ошибки купона","Coupon code already applied!",
                driver.findElement(alertMessage).getText());
        Assert.assertEquals("Неверное сообщение о заказе","Оформление заказа",
                driver.findElement(checkOrderingProduct).getText());
    }
    //Тест 15 Оформление заказа с оплатой наличными
    @Test
    public void orderingProductAuthorizationUserPayCash(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Николай Соколов (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Arrange 2
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");

        //Act 2
        driver.findElement(firstButtonCartProduct).click();
        driver.findElement(buttonMoreCardToCart).click();

        //Assert 2
        Assert.assertEquals("Мы не на странице Корзина","Корзина",driver.findElement(titlePage).getText());
        Assert.assertTrue("Строка товара не отображается",driver.findElement(stringProducts).isDisplayed());
        Assert.assertTrue("Имя товара не соответствует добавленному",
                driver.findElement(checkTitleProductInCart).isDisplayed());

        //Act 3
        driver.findElement(orderingButton).click();

        //Assert 3
        Assert.assertEquals("Мы не на странице Оформление заказа", "Оформление Заказа",
                driver.findElement(titlePage).getText());

        //Act 4
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputLastName).clear();
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputCity).clear();
        driver.findElement(inputCity).sendKeys(city);
        driver.findElement(inputState).clear();
        driver.findElement(inputState).sendKeys(state);
        driver.findElement(inputPhone).clear();
        driver.findElement(inputPhone).sendKeys(phone);
        driver.findElement(inputPostcode).clear();
        driver.findElement(inputPostcode).sendKeys(postcode);
        driver.findElement(radioPayCash).click();
        driver.findElement(buttonOrderingForm).click();

        //Assert 4

        Assert.assertEquals("Неверное сообщение о заказе","Оформление заказа",
                driver.findElement(checkOrderingProduct).getText());
    }
}
