import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.spec.PSource;

public class login_test {
 public static void main(String[] args) throws InterruptedException {
  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
  WebDriver driver=(WebDriver) new ChromeDriver();
  driver.get("https://speedtest.net/login");
  // Printing Out the Title
  System.out.println(driver.getTitle());
  // Printing Out URL
  System.out.println(driver.getCurrentUrl());

  /*
  Testing invalid emails in email field!
  */

  // Array of invalid emails
  String[] invalidEmailData = {"missingAtEmail.com", "no@periodcom", "junk#@%^%#$@#$@#@email.com", "@missingUsername.com", ".dot@infront.com", "two@dots..com", "dashAfter@-email.com"};
  // Loop that validates if for each email in the array error is displayed.
  for (int i=0; i< invalidEmailData.length; i++) {
  	 driver.findElement(By.id("login-email")).clear(); // clearing the email field before entering the email
  	 driver.findElement(By.id("login-email")).sendKeys(invalidEmailData[i]); //Sending the email
  	 driver.findElement(By.id("login-password")).click(); // Clicking on the password field to trigger email validation
	  // Expecting invalid email message to be displayed within 2 seconds
  	 WebDriverWait invalidMessage = new WebDriverWait(driver, 2);
  	 WebElement invalid = invalidMessage.until(
  			 ExpectedConditions.visibilityOfElementLocated(By.id("parsley-id-5")));
  	  // Validating is email error matches "This value should be a valid email."
  	 String invalidMessageText = driver.findElement(By.id("parsley-id-5")).getText();
  		if(invalidMessageText.equals("This value should be a valid email.")) {
  			System.out.println("For \"" + invalidEmailData[i] + "\", error is displayed: " + "\"" + driver.findElement(By.id("parsley-id-5")).getText() + "\""); // Printing out email and error message
  		}
  }

  /*
  Testing emply password field!
  */

  driver.findElement(By.id("login-email")).clear();
  driver.findElement(By.id("login-email")).sendKeys("some@email.com");
  driver.findElement(By.id("login-button")).click();
  ExpectedConditions.textToBe(By.id("parsley-id-7"), "This value is required.");
  System.out.println("\n Empty password test passed!");
  /*
  Testing captcha error!
  */
  driver.findElement(By.id("login-email")).clear();
  driver.findElement(By.id("login-email")).sendKeys("some@email.com");
  driver.findElement(By.id("login-password")).sendKeys("somePassword");
  driver.findElement(By.id("login-button")).click();
  ExpectedConditions.textToBe(By.className("alert-box"), "Recaptcha is required");
  System.out.println("\n Recaptcha error test passed!");
  // Closing the Browser
  driver.close();
  }
}
