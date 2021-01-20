package tests;

import driverSetup.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import helpers.TestHelpers;



public class SpeedTest extends BaseTest {
	@Test
	@Description("Trigger the SpeedTest")
	public void triggerSpeedTest() {

		driver.get("https://speedtest.net");

		HomePage homePage = new HomePage(driver);
		TestHelpers testHelpers = new TestHelpers();
		Assert.assertEquals(driver.getTitle(), "Speedtest by Ookla - The Global Broadband Speed Test");
		homePage.goButton.click();


		testHelpers.waitForElement(homePage.pingSpeedResult,60);
		testHelpers.waitForElement(homePage.downloadSpeedResult,5);
		testHelpers.waitForElement(homePage.uploadSpeedResult,5);
		testHelpers.waitForElement(homePage.resultLabel, 60);

		int pingResult = Integer.parseInt(homePage.pingSpeedResult.getText());
		int thresholdPingResult = 100;
		Assert.assertTrue(pingResult < thresholdPingResult);

		double downloadResult = Double.parseDouble(homePage.downloadSpeedResult.getText());
		double thresholdDownloadResult = 500;
		Assert.assertFalse(downloadResult < thresholdDownloadResult,
				"Your download speeds are abysmal! Do something! ....Just Kidding! :D \n");

		double uploadResult = Double.parseDouble(homePage.downloadSpeedResult.getText());
		double thresholdUploadResult = 500;
		Assert.assertFalse(uploadResult < thresholdUploadResult, "Your upload speeds are abysmal! Do something!\n");
	}
}
