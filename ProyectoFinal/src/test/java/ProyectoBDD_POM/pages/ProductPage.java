package ProyectoBDD_POM.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locators
    private By itemSizes = By.cssSelector("div.swatch-option.text");
    private By itemColors = By.cssSelector("div.swatch-option.color");
    private By addToCartButton = By.id("product-addtocart-button");
    private By itemName = By.cssSelector("h1.page-title");
    private By successMessage =  By.cssSelector("div[data-ui-id='message-success'] div");
    private By shoppingCartButton = By.cssSelector("a.action.showcart");
    private By checkOutButton = By.id("top-cart-btn-checkout");

    @Step("Get Product Name")
    public String getProductName(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemName));
        WebElement item = driver.findElement(itemName);
        return item.getText();
    }

    @Step("Get Number of Item Sizes")
    public int getNumberofItemsSizes(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemSizes));
        List<WebElement> elements = driver.findElements(itemSizes);
        return elements.size();
    }

    @Step("Get Number of Item Colors")
    public int getNumberofItemsColors(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemColors));
        List<WebElement> elements = driver.findElements(itemColors);
        return elements.size();
    }

    @Step("Click on random size")
    public void clickOnRandomSize(int randomSizeIndex){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemSizes));
        driver.findElements(itemSizes).get(randomSizeIndex).click();
    }

    @Step("Click on random color")
    public void clickOnRandomColor(int randomColorIndex){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemColors));
        driver.findElements(itemColors).get(randomColorIndex).click();
    }

    @Step("Click on add to cart button")
    public void clickOnAddToCartButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();

    }

    @Step("Get Success message")
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }


    public boolean isSuccessMessageDisplayed(String productName) {
        String message = getSuccessMessage();
        return message.contains(productName);
    }

    @Step("Click on shopping cart button")
    public void clickOnShoppingCartButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(shoppingCartButton));
        driver.findElement(shoppingCartButton).click();
    }

    @Step("Click on checkout button")
    public void clickOnCheckOutButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(checkOutButton));
        driver.findElement(checkOutButton).click();
    }

}
