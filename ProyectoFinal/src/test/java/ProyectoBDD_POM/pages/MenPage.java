package ProyectoBDD_POM.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MenPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public MenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locators
    private By inventoryItem = By.cssSelector("div[class='product-item-info']");
    private By itemName = By.xpath("//div[@class='product-item-details']//a[@class='product-item-link']");

    @Step("Get Number of Items")
    public int getNumberofItems(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(inventoryItem));
        List<WebElement> elements = driver.findElements(inventoryItem);
        return elements.size();
    }

    @Step("Get Item Name by Index")
    public String getItemNameByIndex(int itemNumber){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemName));
        WebElement item = driver.findElements(itemName).get(itemNumber);
        return item.getText();
    }

    @Step("Click on Random Item")
    public void clickOnRandomItem(int itemNumber){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(itemName));
        driver.findElements(itemName).get(itemNumber).click();
    }
}
