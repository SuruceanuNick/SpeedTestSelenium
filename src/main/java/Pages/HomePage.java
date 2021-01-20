package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class='start-text']")
    public WebElement goButton;

    @FindBy(xpath = "//canvas[@class='gauge-speed-needle']")
    public WebElement speedNeedle;

    @FindBy(className = "start-text")
    public WebElement speedText;

    @FindBy(className = "server-current")
    public WebElement currentServer;

    @FindBy(xpath = "//div[@class='result-area result-area-nav result-area-nav-right']")
    public WebElement resultLabel;

    @FindBy(xpath = "//span[@class='result-data-large number result-data-value ping-speed']")
    public WebElement pingSpeedResult;

    @FindBy(xpath = "//span[@class='result-data-large number result-data-value download-speed']")
    public WebElement downloadSpeedResult;

    @FindBy(xpath = "//span[@class='result-data-large number result-data-value upload-speed']")
    public WebElement uploadSpeedResult;

    @FindBy(xpath = "//div[@class='result-item result-item-download updated test-mode-multi']/div/span[@class='result-data-unit']")
    public WebElement downloadResultDataUnit;
    @FindBy(xpath = "//div[@class='result-item result-item-upload updated test-mode-multi']/div/span[@class='result-data-unit']")
    public WebElement uploadResultDataUnit;
}
