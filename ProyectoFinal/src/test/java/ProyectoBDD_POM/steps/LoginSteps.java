package ProyectoBDD_POM.steps;
import ProyectoBDD_POM.factory.DriverFactory;
import ProyectoBDD_POM.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import io.qameta.allure.Step;
import io.qameta.allure.*;

import java.util.Random;


public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private MenPage menPage;
    private ProductPage productPage;
    private CheckoutShippingPage checkoutShippingPage;
    private CheckoutPaymentPage checkoutPaymentPage;
    private CheckoutSuccessPage checkoutSuccessPage;

    private String expectedItemName;

    @Step("Given I navigate to the login page")
    @Given("I navigate to the login page")
    public void openLoginPage() {

        driver = DriverFactory.createDriver();
        loginPage = new LoginPage(driver);
        loginPage.visit();
        Allure.step("Visit login page");

    }

    @Step("When I enter valid credentials")
    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.enterUsername("juanperez@test.com");
        loginPage.enterPassword("JuanPerez1$");
        Allure.step("Step 1: Login Page");
    }

    @Step("When I click the login button")
    @When("I click the login button")
    public void i_click_the_login_button() {

        loginPage.clickLoginButton();
    }

    @Step("Then I should see the user logged in")
    @Then("I should see the user logged in")
    public void verifyUserLoggedIn() {
        mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isUserLoggedIn(), "The user is not logged in!");
    }

    @Step("When I click on Men Category")
    @When("I click on Men Category")
    public void selectMenCategory() {
        //mainPage = new MainPage(driver);
        mainPage.clickMenCategoryMenu();
    }

    @Step("Then I should be redirected to Men page")
    @Then("I should be redirected to Men page")
    public void verifyURLofMenPage() {
        Assert.assertTrue(mainPage.isMenCategoryPageLoaded(), "We are not in Men Page!");
    }

    @Step("When I select a random item and click it")
    @When("I select a random item and click it")
    public void addRandomItemtoCart() {
        menPage = new MenPage(driver);

        int ItemsNumber = menPage.getNumberofItems();
        Random random = new Random();
        int randomNumber = random.nextInt(ItemsNumber);
        expectedItemName = menPage.getItemNameByIndex(randomNumber);
        menPage.clickOnRandomItem(randomNumber);
    }

    @Step("Then I should be able to see the product page")
    @Then("I should be able to see the product page")
    public void verifyProductPage() {
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductName(),expectedItemName);
    }

    @Step("When I choose a random size")
    @When("I choose a random size")
    public void selectRandomSize() {
        int SizesAvailables = productPage.getNumberofItemsSizes();
        Random random = new Random();
        int randomSizeIndex = random.nextInt(SizesAvailables);
        //expectedItemName = menPage.getItemNameByIndex(randomSizeIndex);
        productPage.clickOnRandomSize(randomSizeIndex);
    }

    @Step("When I choose a random color")
    @When("I choose a random color")
    public void selectRandomColor() {
        int ColorsAvailables = productPage.getNumberofItemsColors();
        Random random = new Random();
        int randomColorIndex = random.nextInt(ColorsAvailables);
        //expectedItemName = menPage.getItemNameByIndex(randomSizeIndex);
        productPage.clickOnRandomColor(randomColorIndex);
    }

    @Step("When I click the Add to Cart button")
    @When("I click the Add to Cart button")
    public void clickOnAddToCart() {
        productPage.clickOnAddToCartButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Then I should see a success message")
    @Then("I should see a success message")
    public void verifySuccessMessage() {
        Assert.assertTrue(productPage.isSuccessMessageDisplayed(expectedItemName), "Success message not displayed!");
    }

    @Step("When I click on Shopping cart button")
    @When("I click on Shopping cart button")
    public void clickOnShoppingCartButton() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickOnShoppingCartButton();
    }

    @Step("When I click on Checkout button")
    @When("I click on Checkout button")
    public void clickOnCheckoutButton() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productPage.clickOnCheckOutButton();
    }

    @Step("Then I should be in the Checkout first step")
    @Then("I should be in the Checkout first step")
    public void verifyCheckoutFirstStepURL() {
        checkoutShippingPage = new CheckoutShippingPage(driver);
        Assert.assertTrue(checkoutShippingPage.isOnCheckoutPage(),"The user was not redirected to the expected 'Checkout' page.");

    }

    @Step("When I click on Next Button")
    @When("I click on Next Button")
    public void clickNextButton() {
        checkoutShippingPage.clickNextButton();
    }

    @Step("Then I should be redirected to Checkout Payment Page")
    @Then("I should be redirected to Checkout Payment Page")
    public void verifyCheckoutPaymentStepURL() {
        checkoutPaymentPage = new CheckoutPaymentPage(driver);
        Assert.assertTrue(checkoutPaymentPage.isOnPaymentPage(),"The navigation to the 'Payment' page was not successful.");
    }

    @Step("I click on Place Order Button")
    @When("I click on Place Order Button")
    public void clickPlaceOrderButton() {
        checkoutPaymentPage.clickPlaceOrderButton();
    }

    @Step("Then I should see the order completed")
    @Then("I should see the order completed")
    public void verifyCheckoutSuccessURL() {
        checkoutSuccessPage = new CheckoutSuccessPage(driver);
        Assert.assertTrue(checkoutSuccessPage.isOnSuccessPage(),"The navigation to the 'Success' page was NOT successful.");
        Assert.assertEquals(checkoutSuccessPage.getSuccessfulPurchaseMessage(), "Thank you for your purchase!","The successful purchase message does NOT match the expected message.");
    }

    @When("When I click on Continue Shopping Button")
    @When("I click on Continue Shopping Button")
    public void clickConinueShoppingButton() {
        checkoutSuccessPage.clickOnContinueShoppingButton();

    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}





