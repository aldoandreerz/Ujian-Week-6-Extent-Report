package com.juaracoding;

import com.aventstack.extentreports.ExtentTest;
import com.juaracoding.pages.CartPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductPage;
import com.juaracoding.utils.ExtentReportManager;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Checkout {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ProductPage productPage = new ProductPage(Hooks.driver);
    private CartPage cartPage = new CartPage(Hooks.driver);
    private CheckoutPage checkoutPage = new CheckoutPage(Hooks.driver);
    private ExtentTest test;

    @Given("User has added two products to the cart")
    public void user_has_added_two_products_to_the_cart() {
        test = ExtentReportManager.getInstance().createTest("User has added two products to the cart");
        Hooks.driver.get("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
        loginPage.clickLoginButton();
        test.info("Logged in successfully.");

        productPage.addFleeceJacketToCart();
        delay(3);
        productPage.addBoltShirtToCart();
        delay(3);
        productPage.goToCart();
        test.info("Added two products to the cart and navigated to the cart.");
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        test = ExtentReportManager.getInstance().createTest("User proceeds to checkout");
        cartPage.proceedToCheckout();
        test.info("Proceeded to checkout.");
    }

    @And("User enters valid checkout information")
    public void user_enters_valid_checkout_information() {
        test = ExtentReportManager.getInstance().createTest("User enters valid checkout information");
        checkoutPage.enterFirstName("Aldo");
        delay(3);
        checkoutPage.enterLastName("Andre");
        delay(3);
        checkoutPage.enterPostalCode("140664");
        delay(3);
        test.info("Entered valid checkout information.");
    }

    @And("User leaves the checkout information empty")
    public void user_leaves_the_checkout_information_empty() {
        test = ExtentReportManager.getInstance().createTest("User leaves the checkout information empty");
        test.info("Checkout information left empty.");
    }

    @And("User clicks the continue button")
    public void user_clicks_the_continue_button() {
        test = ExtentReportManager.getInstance().createTest("User clicks the continue button");
        delay(3);
        checkoutPage.clickContinueButton();
        test.info("Clicked the continue button.");
    }

    @And("User should proceed to the next step in checkout")
    public void user_should_proceed_to_the_next_step_in_checkout() {
        test = ExtentReportManager.getInstance().createTest("User should proceed to the next step in checkout");
        delay(3);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two.html"));
        test.pass("Successfully proceeded to the next step in checkout.");
    }

    @Then("User should see an error message on checkout page")
    public void user_should_see_an_error_message_on_checkout_page() {
        test = ExtentReportManager.getInstance().createTest("User should see an error message on checkout page");
        delay(3);
        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"));
        test.pass("Verified error message on checkout page.");
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
