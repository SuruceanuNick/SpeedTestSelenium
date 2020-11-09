package driverSetup;


import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public abstract class BaseTest {

    public WebDriver driver;

    @BeforeSuite
    public void globalSetup(){

    }

    @BeforeMethod
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver =  new ChromeDriver();

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
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
            System.out.println(driver.getPageSource());
        }

        if (driver != null) {
            driver.close();
            driver = null;
        }
    }


    @AfterSuite
    public void globalTearDown(){

    }

}
