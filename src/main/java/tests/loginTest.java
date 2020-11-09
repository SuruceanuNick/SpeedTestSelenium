package tests;

import driverSetup.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.TestHelpers;

public class loginTest extends BaseTest {

    @Test(description = "Validate the login")
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
            loginPage.emailField.clear();
            loginPage.emailField.sendKeys(invalidEmailDatum);
            loginPage.pwdField.click();
            new TestHelpers().WaitForElement(driver, loginPage.invalidEmailMessage, 5);
            String invalidMessageText = loginPage.invalidEmailMessage.getText();
            Assert.assertEquals(invalidMessageText, "This  value should be a valid email.");
        }
    }

    @Test(description = "Testing empty password field")
    public void checkPassword() {

        driver.get("https://speedtest.net/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailField.clear();
        loginPage.emailField.sendKeys("some@email.com");
        loginPage.loginButton.click();
        Assert.assertEquals(loginPage.invalidPwdMessage.getText(), "This value is required.");
    }

    @Test(description = "Testing empty password field")
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
