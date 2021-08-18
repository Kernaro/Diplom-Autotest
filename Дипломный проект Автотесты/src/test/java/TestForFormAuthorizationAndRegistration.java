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

public class TestForFormAuthorizationAndRegistration {
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
    private By titlePage = By.className("current");
    private String login = "Ubunt@mail.ru";
    private String password = "Ubunt@mail.ru";
    private By inputLogin = By.id("username");
    private By inputPassword = By.id("password");
    private  By buttonComeIn = By.cssSelector("[name='login']");
    private By checkTitleMyAccount = By.cssSelector(".woocommerce-MyAccount-content p");
    private By buttonLogout = By.className("logout");
    private By linkComeIn = By.className("account");
    private By buttonRegistration = By.className("custom-register-button");
    private By alertMessage = By.cssSelector(".woocommerce-error li");
    private By registrationUserName = By.id("reg_username");
    private By registrationEmail = By.id("reg_email");
    private By registrationLogin = By.id("reg_password");
    private By buttonRegistrationForm = By.cssSelector("[name='register']");
    private By buttonEyeInputPassword = By.cssSelector("[class='show-password-input']:not([class='display-password'])");
    private By showMePassword = By.className("display-password");
    private By checkboxRemeberMe = By.id("rememberme");




    //!!!Задачи!!!
    //Что надо сделать:
    //
    //Составь тестовые сценарии для функций регистрации и авторизации.
    //Автоматизируй сценарии и залей результат в Git.

    //Тест 1 Переход с главной страницы в форму авторизации кликом по ссылке "Войти"
    @Test
    public void clickToLinkComeIn(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/");

        //Act
        driver.findElement(linkComeIn).click();

        //Assert
        Assert.assertEquals("Мы не на странице Мой Аккаунт","Мой Аккаунт",
                driver.findElement(titlePage).getText());
    }

    //Тест 2 Переход с главной страницы в форму регистрации кликом на ссылку "Регистрация" в Footer
    @Test
    public void clickToLinkRegistrationInFooter(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/");
        var linkRegistration = driver.findElement(By.cssSelector("[href $= 'register/']:not([rel])"));

        //Act
        linkRegistration.click();

        //Assert
        Assert.assertEquals("Заголовок страницы не соответствует актуальному","Регистрация",
                driver.findElement(titlePage).getText());
    }

    // Тест 3 Авторизация зарегистрированного пользователя
    @Test
    public void loggedInUserAuthorization(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Ubunt@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());
    }

    //Тест 4
    @Test
    public void DeAuthorizationUser (){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Ubunt@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());
        driver.findElement(buttonLogout).click();
        driver.findElement(linkComeIn);
    }

    //Тест 5 Переход в форму "Регистрации" из формы авторизации
    @Test
    public void goToRegistrationForm(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(buttonRegistration).click();

        //Assert
        Assert.assertEquals("Мы не в форме Регистрации","Регистрация",driver.findElement(titlePage).getText());
    }

    //Тест 6 Некорректный ввод поля авторизации
    @Test
    public void incorrectEntryAuthorizationField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys("@@@@@");
        driver.findElement(inputPassword).sendKeys("@");
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверный текст ошибки",
                "Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.",
                driver.findElement(alertMessage).getText());
    }

    //Тест 7 Не корректный ввод в поля в форме Регистрации
    @Test
    public void incorrectEntryRegistrationField(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/register/");

        //Act
        driver.findElement(registrationUserName).sendKeys("@@@");
        driver.findElement(registrationEmail).sendKeys("test@mail.ru");
        driver.findElement(registrationLogin).sendKeys("@@@");
        driver.findElement(buttonRegistrationForm).click();

        //Assert
        Assert.assertEquals("Неверное сообщение об ошибке",
                "Error: Учетная запись с такой почтой уже зарегистировавана. Пожалуйста авторизуйтесь.",
                driver.findElement(alertMessage).getText());
    }

    //Тест 8 Клик на иконку глаза при авторизации пользователя
    @Test
    public void clickIconEyeInInputPassword(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonEyeInputPassword).click();

        //Assert
        wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonEyeInputPassword));
        //Это строчка здесь для из за того что кнопка отображения не успевала найтись
        driver.findElement(showMePassword);
        Assert.assertTrue("Пароль не отобразился",driver.findElement(showMePassword).isDisplayed());
    }

    //Тест 9 Авторизация с кликом в checkbox запомнить меня
    @Test
    public  void userAuthorizationUsingCheckbox(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(checkboxRemeberMe).click();
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Ubunt@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());

        //Act 2
        driver.navigate().to("https://skillbox.ru/");
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Assert
        Assert.assertEquals("Неверное приветствие в Моем Аккаунте","Привет Ubunt@mail.ru (Выйти)",
                driver.findElement(checkTitleMyAccount).getText());
    }

    //Тест 10 Отправка пустой формы авторизации
    @Test
    public  void submittingEmptyLoginForm(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/my-account/");

        //Act
        driver.findElement(buttonComeIn).click();

        //Assert
        Assert.assertEquals("Некорректный текст ошибки","Error: Имя пользователя обязательно.",
                driver.findElement(alertMessage).getText());
    }
    
    //Тест 11 Отправка пустой формы регистрации
    @Test
    public  void submittingEmptyRegistrationForm(){
        //Arrange
        driver.navigate().to("http://intershop5.skillbox.ru/register/");

        //Act
        driver.findElement(buttonRegistrationForm).click();

        //Assert
        Assert.assertEquals("Некорректный текст ошибки","Error: Пожалуйста, введите корректный email.",
                driver.findElement(alertMessage).getText());
    }
}
