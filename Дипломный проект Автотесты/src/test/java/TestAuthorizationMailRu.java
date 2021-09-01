import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestAuthorizationMailRu {
    private WebDriver driver;
    private WebDriverWait wait;



    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @After

    public void tearDown() {
        driver.quit();
    }
    //Переменные для тестов
    private String login = "kernaro";
    private String password = "Ybvthbz7";
    private By emailInput = By.className("email-input");
    private By buttonPassword = By.cssSelector("button.button");
    private By passwordInput = By.className("password-input");
    private By buttonComeIN = By.className("second-button");
    private By buttonWriteLetter = By.className("compose-button__txt");
    private By inputWhom = By.className("container--H9L5q");
    private By inputTheme = By.className("subject__wrapper--2mk6m");
    private By mailTextArea = By.cssSelector(".cke_widget_editable div");
    private By buttonSendMail = By.xpath("(//span[@class='button2__txt'])[5]");
    private By checkSendMail = By.className("layer__link");

    //!!!Задача!!!
    //Написать тестовый проект с использованием Java, Selenium, jUnit/TestNG и Page object паттерна и любого сборщика.
    // Тест должен уметь следующее: залогиниться на mail.ru; написать письмо любого содержания c заполнением поля Body
    // (текста самого письма); отправить письмо. Проверка доставки письма не нужна, только отправка.
    // Тестовый проект вставить как ссылку на GitHub в поле ответа.
    @Test
    public void authorizationAndSendingLetter(){
        //Arrange
       driver.navigate().to("https://mail.ru/");

       //Act
        driver.findElement(emailInput).sendKeys(login);
        driver.findElement(buttonPassword).click();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(buttonComeIN).click();
        driver.findElement(buttonWriteLetter).click();
        driver.findElement(inputWhom).sendKeys("Test@mail.ru");
        driver.findElement(inputTheme).sendKeys("Привет это отправка тестового задания");
        driver.findElement(mailTextArea).sendKeys("Надеюсь данные от моего аккаунта не попадут в чужие руки," +
                "иначе я очень огорчусь");
        driver.findElement(buttonSendMail).click();

        //Assert
        Assert.assertEquals("Некорректное сообщение об отправки письма","Письмо отправлено",
                driver.findElement(checkSendMail).getText());


    }
}
