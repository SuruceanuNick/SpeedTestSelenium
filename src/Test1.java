import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.spec.PSource;

public class Test1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		WebDriver driver=(WebDriver) new ChromeDriver();
		driver.get("https://speedtest.net");
		// Printing Out the Title
		System.out.println(driver.getTitle());
		// Printing Out URL
		System.out.println(driver.getCurrentUrl());
		// Triggerinng the Speed Test by Clicking on "GO"
		driver.findElement(By.className("start-button")).findElement(By.className("start-text")).click();
		// Confirming that Speed Test started by locating Gauge within 10 seconds
		WebDriverWait gaugeWait = new WebDriverWait(driver, 10);
		WebElement gauge = gaugeWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.className("gauge-speed-needle")));

		// Waiting until "GO" button is displayed timeout is 60 sec, "Go" button is displayed again when Speedtest is done along with results
		WebDriverWait goWait = new WebDriverWait(driver, 60);
		WebElement goButton = goWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.className("start-text")));
		// Getting Server name
		String currentServer = driver.findElement(By.className("server-current")).findElement(By.className("name")).getText();
		// Getting Ping result (only the number)
		String pingResult = driver.findElement(By.className("result-container-speed")).findElement(By.xpath("//span[@class='result-data-large number result-data-value ping-speed']")).getText();
		// Getting Download speed result
		String downloadResult = driver.findElement(By.className("result-container-speed")).findElement(By.xpath("//span[@class='result-data-large number result-data-value download-speed']")).getText();
		// Getting Upload speed result
		String uploadResult = driver.findElement(By.className("result-container-speed")).findElement(By.xpath("//span[@class='result-data-large number result-data-value upload-speed']")).getText();
		// Getting speed units
		String speedUnits = driver.findElement(By.className("result-container-speed"))
				.findElement(By.xpath("//div[@class='result-item result-item-download updated test-mode-multi']"))
				.findElement(By.className("result-data-unit")).getText();
		// Printing out the results
		System.out.println(" -- SpeedTest RESULTS -- ");
		System.out.println("Current server location: " + currentServer);
		System.out.println("PING: " + pingResult + " ms");
		System.out.println("Download: " + downloadResult + " " + speedUnits );
		System.out.println("Upload: " + uploadResult + " " + speedUnits);
		System.out.println(" --- End of RESULTS --- ");
		// Closing the Browser
		driver.close();



	}

}
