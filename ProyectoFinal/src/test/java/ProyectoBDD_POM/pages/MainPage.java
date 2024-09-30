package ProyectoBDD_POM.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;


public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Locators
    private By createAccountLink = By.cssSelector("div.panel.header ul.header.links a[href='https://magento.softwaretestingboard.com/customer/account/create/']");
    private By menCategoryMenu = By.xpath("//a/span[contains(text(),'Men')]");

    public boolean isUserLoggedIn() {
        return driver.findElements(createAccountLink).isEmpty();
    }

    @Step("Click on Men Category")
    public void clickMenCategoryMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menCategoryMenu));
        driver.findElement(menCategoryMenu).click();
    }

    @Step("Men Category Page is Loaded?")
    public boolean isMenCategoryPageLoaded() {
        wait.until(ExpectedConditions.urlContains("men.html"));
        return driver.getCurrentUrl().contains("men.html");
    }

}
