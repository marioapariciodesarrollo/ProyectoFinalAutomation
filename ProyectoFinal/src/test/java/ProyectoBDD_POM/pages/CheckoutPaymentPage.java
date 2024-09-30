package ProyectoBDD_POM.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String expectedPaymentURL = "https://magento.softwaretestingboard.com/checkout/#payment";

    public CheckoutPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By placeOrderButton = By.cssSelector("button[title=\"Place Order\"]");
    private By divLoadingMask = By.cssSelector("div.loading-mask");


    private By discountCodeInput = By.id("discount-code");
    private By applyDicountButton = By.cssSelector("button[value='Apply Discount']");
    //20poff

    @Step("Is Checkout Payment Page?")
    public boolean isOnPaymentPage() {
        wait.until(ExpectedConditions.urlToBe(expectedPaymentURL));
        String actualUrl = driver.getCurrentUrl();
        return actualUrl.equals(expectedPaymentURL);
    }

    @Step("Click on Place Order")
    public void clickPlaceOrderButton() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(divLoadingMask));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        driver.findElement(placeOrderButton).click();
    }


}
