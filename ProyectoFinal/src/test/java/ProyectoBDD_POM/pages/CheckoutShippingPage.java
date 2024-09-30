package ProyectoBDD_POM.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutShippingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String expectedCheckoutURL = "https://magento.softwaretestingboard.com/checkout/#shipping";

    public CheckoutShippingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    //private By nextButton = By.xpath("//*[@id='shipping-method-buttons-container']/div/button");

    private By nextButton = By.cssSelector("div#shipping-method-buttons-container button");
    private By copyright = By.cssSelector("small.copyright");
    private By divLoadingMask = By.cssSelector("div.loading-mask");

    @Step("Click on Next Button")
    public void clickNextButton() {
        scrollToElement(driver.findElement(copyright));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(divLoadingMask));
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }

    @Step("Is Checkout Shipping Page?")
    public boolean isOnCheckoutPage() {
        wait.until(ExpectedConditions.urlToBe(expectedCheckoutURL));
        String actualUrl = driver.getCurrentUrl();
        return actualUrl.equals(expectedCheckoutURL);
    }


    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

}
