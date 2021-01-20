package driverSetup;


import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public abstract class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver =  new ChromeDriver();
    }

    @AfterMethod
    public void driverTearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            String name = "fail_" + result.getName();
            File path = new File(System.getProperty("user.dir"));
            File getScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(getScreenshot, new File(path.getAbsolutePath() + "/screenshots/"
                                                        + name + " "
                                                        + new Date()
                                                        + ".png"));
            Allure.addAttachment(name, FileUtils.openInputStream(getScreenshot));
            System.out.println(driver.getPageSource());
        }

        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
