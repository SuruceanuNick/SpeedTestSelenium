package tests;

import driverSetup.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import helpers.TestHelpers;

public class LoginTest extends BaseTest {

    @Test
    @Description("Validate the login")
    public void checkEmail() {

        driver.get("https://speedtest.net/login");

        String[] invalidEmailData = {"missingAtEmail.com",
                "no@periodcom",
                "junk#@%^%#$@#$@#@email.com",
                "@missingUsername.com",
                ".dot@infront.com",
                "two@dots..com",
                "dashAfter@-email.com"};

        LoginPage loginPage = new LoginPage(driver);

        for (String invalidEmailDatum : invalidEmailData) {
            Allure.step("Testing with " + invalidEmailDatum + " email.");
            loginPage.emailField.clear();
            loginPage.emailField.sendKeys(invalidEmailDatum);
            loginPage.pwdField.click();
            new TestHelpers().waitForElement(loginPage.invalidEmailMessage, 5);
            String invalidMessageText = loginPage.invalidEmailMessage.getText();
            Assert.assertEquals(invalidMessageText, "This value should be a valid email.");
        }
    }

    @Test
    @Description("Testing empty password field")
    public void checkPassword() {

        driver.get("https://speedtest.net/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("some@email.com");
        loginPage.loginButton.click();
        Assert.assertEquals(loginPage.invalidPwdMessage.getText(), "This value is required.");
    }

    @Test
    @Description("Testing empty password field")
    public void checkCapcha() {

        driver.get("https://speedtest.net/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("some@email.com");
        loginPage.pwdField.sendKeys("somePassword");
        loginPage.loginButton.click();
        Assert.assertEquals(loginPage.alertMessage.getText(), "Recaptcha is required");
    }
}
