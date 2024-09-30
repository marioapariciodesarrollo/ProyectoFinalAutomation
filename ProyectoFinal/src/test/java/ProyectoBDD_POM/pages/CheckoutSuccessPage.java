package ProyectoBDD_POM.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CheckoutSuccessPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String expectedSuccessURL = "https://magento.softwaretestingboard.com/checkout/onepage/success/";

    public CheckoutSuccessPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators

    private By ThankYouForPurchaseMessage = By.xpath("//*[contains(text(), 'Thank you for your purchase!')]");
    private By continueShoppingButton = By.xpath("//span[contains(text(), 'Continue Shopping')]");
    private By printReceiptLink = By.xpath("//*[contains(text(), 'Print receipt')]");

    @Step("Is Checkout Success Page?")
    public boolean isOnSuccessPage() {
        wait.until(ExpectedConditions.urlToBe(expectedSuccessURL));
        String actualUrl = driver.getCurrentUrl();
        return actualUrl.equals(expectedSuccessURL);
    }

    @Step("Get Successful Message")
    public String getSuccessfulPurchaseMessage() {
        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(ThankYouForPurchaseMessage));
        return thankYouMessage.getText();
    }


    public String confirmContinueButtonPresent() {
        WebElement continueShoppingBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));
        return continueShoppingBtn.getText();
    }

    public String confirmPrintReceiptLinkPresent(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(printReceiptLink));
        return driver.findElement(printReceiptLink).getText();
    }

    @Step("Click on Continue Button")
    public void clickOnContinueShoppingButton(){
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        driver.findElement(continueShoppingButton).click();

    }
}
