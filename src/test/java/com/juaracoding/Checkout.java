package com.juaracoding;

import com.juaracoding.pages.CartPage;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductPage;
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

    @Given("User has added two products to the cart")
    public void user_has_added_two_products_to_the_cart() {
        Hooks.driver.get("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
        loginPage.clickLoginButton();
        productPage.addFleeceJacketToCart();
        delay(3);
        productPage.addBoltShirtToCart();
        delay(3);
        productPage.goToCart();
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @And("User enters valid checkout information")
    public void user_enters_valid_checkout_information() {
        checkoutPage.enterFirstName("Aldo");
        delay(3);
        checkoutPage.enterLastName("Andre");
        delay(3);
        checkoutPage.enterPostalCode("140664");
        delay(3);
    }

    @And("User leaves the checkout information empty")
    public void user_leaves_the_checkout_information_empty() {
    }

    @And("User clicks the continue button")
    public void user_clicks_the_continue_button() {
        delay(3);
        checkoutPage.clickContinueButton();
    }

    @And("User should proceed to the next step in checkout")
    public void user_should_proceed_to_the_next_step_in_checkout() {
        delay(3);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two.html"));
    }

    @Then("User should see an error message on checkout page")
    public void user_should_see_an_error_message_on_checkout_page() {
       delay(3);
        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Error: First Name is required"));
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
