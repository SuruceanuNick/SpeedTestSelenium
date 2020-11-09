package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "login-email")
    public WebElement emailField;

    @FindBy(id = "login-password")
    public WebElement pwdField;

    @FindBy(id = "parsley-id-5")
    public WebElement invalidEmailMessage;

    @FindBy(id = "parsley-id-7")
    public WebElement invalidPwdMessage;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(className = "alertbox")
    public WebElement alertMessage;


}
