package Helpers;

import Fixtures.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelpers extends BaseTest {

    public static boolean waitForElement(WebElement element, long timeout){
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        }
}
