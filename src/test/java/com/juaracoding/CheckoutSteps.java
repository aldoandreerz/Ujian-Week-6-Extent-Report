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

public class CheckoutSteps {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ProductPage productPage = new ProductPage(Hooks.driver);
    private CartPage cartPage = new CartPage(Hooks.driver);
    private CheckoutPage checkoutPage = new CheckoutPage(Hooks.driver);

    @Given("User has added two products to the cart")
    public void userHasAddedTwoProductsToTheCart() {
        Hooks.driver.get("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        delay(1);
        loginPage.enterPassword("secret_sauce");
        delay(2);
        loginPage.clickLoginButton();
        productPage.addBackpackToCart();
        delay(1);
        productPage.addBikeLightToCart();
        delay(1);
        productPage.goToCart();
    }

    @When("User proceeds to checkout")
    public void user_Proceeds_To_Checkout() {
        cartPage.proceedToCheckout();
    }

    @And("User enters valid checkout information")
    public void user_EntersVal_id_Checkout_Information() {
        checkoutPage.enterFirstName("Anif");
        delay(1);
        checkoutPage.enterLastName("Biantoro");
        delay(2);
        checkoutPage.enterPostalCode("15317");
        delay(1);
    }

    @And("User leaves the checkout information empty")
    public void user_Leaves_The_Checkout_Information_Empty() {
        // Saya simulasikan dengan tidak mengisi data apapun pada form checkout
    }

    @And("User clicks the continue button")
    public void user_Clicks_The_Continue_Button() {
        delay(1);
        checkoutPage.clickContinueButton();
    }

    @And("User should proceed to the next step in checkout")
    public void user_Should_Proceed_To_The_Next_Step_In_Checkout() {
        // Verifikasi bahwa pengguna melanjutkan ke tahap checkout berikutnya
        delay(1);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two.html"));
    }

    @Then("User should see an error message on checkout page")
    public void user_Should_See_An_Error_Message_On_Checkout_Page() {
        // Verifikasi pesan error muncul
       delay(1);
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
