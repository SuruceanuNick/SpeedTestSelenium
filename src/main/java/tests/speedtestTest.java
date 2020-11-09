package tests;

import driverSetup.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.TestHelpers;



public class speedtestTest extends BaseTest {
	@Test
	@Description("Trigger the SpeedTest")
	public void triggerSpeedTest() {

		driver.get("https://speedtest.net");

		HomePage homePage = new HomePage(driver);
		TestHelpers testHelpers = new TestHelpers();
		Assert.assertEquals(driver.getTitle(), "Speedtest by Ookla - The Global Broadband Speed Test");
		homePage.goButton.click();


		testHelpers.WaitForElement(driver, homePage.pingSpeedResult,60);
		testHelpers.WaitForElement(driver, homePage.downloadSpeedResult,5);
		testHelpers.WaitForElement(driver, homePage.uploadSpeedResult,5);
		testHelpers.WaitForElement(driver, homePage.resultLabel, 60);

		int pingResult = Integer.parseInt(homePage.pingSpeedResult.getText());
		int thresholdPingResult = 100;
		Assert.assertTrue(pingResult < thresholdPingResult);

		double downloadResult = Double.parseDouble(homePage.downloadSpeedResult.getText());
		double thresholdDownloadResult = 500;
		Assert.assertFalse(downloadResult < thresholdDownloadResult, "Your download speeds are abysmal! Do something!\n");

		double uploadResult = Double.parseDouble(homePage.downloadSpeedResult.getText());
		double thresholdUploadResult = 500;
		Assert.assertFalse(uploadResult < thresholdUploadResult, "Your upload speeds are abysmal! Do something!\n");
	}
}
