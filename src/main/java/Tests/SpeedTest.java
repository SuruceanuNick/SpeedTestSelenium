package Tests;

import Fixtures.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Helpers.TestHelpers;

public class SpeedTest extends BaseTest {
	@Test
	@Description("Trigger the SpeedTest")
	public void triggerSpeedTest() {

		driver.get("https://speedtest.net");

		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(driver.getTitle(), "Speedtest by Ookla - The Global Broadband Speed Test");
		homePage.goButton.click();


		TestHelpers.waitForElement(homePage.pingSpeedResult,60);
		TestHelpers.waitForElement(homePage.downloadSpeedResult,5);
		TestHelpers.waitForElement(homePage.uploadSpeedResult,5);
		TestHelpers.waitForElement(homePage.resultLabel, 60);

		int pingResult = Integer.parseInt(homePage.pingSpeedResult.getText());
		int thresholdPingResult = 100;
		Assert.assertTrue(pingResult < thresholdPingResult);

		double downloadResult = Double.parseDouble(homePage.downloadSpeedResult.getText());
		double uploadResult = Double.parseDouble(homePage.uploadSpeedResult.getText());
		Allure.addAttachment ("Download Result: ",
				downloadResult + " " + homePage.downloadResultDataUnit.getText());
		Allure.addAttachment ("Upload Result: ",
				uploadResult + " " + homePage.uploadResultDataUnit.getText());

		// Test Assertions (test will fail on purpose)
		double thresholdDownloadResult = 500;
		double thresholdUploadResult = 500;
		Assert.assertFalse(downloadResult < thresholdDownloadResult,
				"Your download speeds are abysmal! Do something!\n ....Just Kidding! :D \n Test failed on purpose.");
		Assert.assertFalse(uploadResult < thresholdUploadResult,
				"Your upload speeds are abysmal! Do something!\n ....Just Kidding! :D \n Test failed on purpose.");
	}
}
