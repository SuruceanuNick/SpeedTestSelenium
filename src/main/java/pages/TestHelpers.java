package pages;

import driverSetup.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelpers{

        public boolean WaitForElement(WebDriver driver, WebElement element,long timeout){
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        }
}
