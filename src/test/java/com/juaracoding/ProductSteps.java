package com.juaracoding;

import com.juaracoding.pages.CartPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductPage;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.By;


public class ProductSteps {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ProductPage productPage = new ProductPage(Hooks.driver);
    private CartPage cartPage = new CartPage(Hooks.driver);

    @Given("User is on login page for product")
    public void user_Is_On_Login_Page_for_product() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }

    @When("User enters valid username and password for product")
    public void user_Enters_Valid_Username_And_Password_For_Product() {
        loginPage.enterUsername("standard_user");
        delay(2);
        loginPage.enterPassword("secret_sauce");
    }

    @And("User clicks login button for product")
    public void user_Clicks_Login_Button_On_Product_Page() {
        delay(1);
        loginPage.clickLoginButton();
    }

    @And("User should be redirected to the product page")
    public void user_Should_Be_Redirected_To_The_Product_Page_For_Product_Steps() {
        delay(1);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
    }

    @And("User adds two products to the cart")
    public void user_Adds_Two_Products_To_The_Cart() {
        delay(1);
        productPage.addBackpackToCart();
        delay(1);
        productPage.addBikeLightToCart();
    }

    @And("User goes to the cart")
    public void user_Goes_To_The_Cart() {
        delay(1);
        productPage.goToCart();
    }

    @Then("User should see two products in the cart")
    public void user_Should_See_Two_Products_In_The_Cart() {
       delay(1);
        String cartItemCount = Hooks.driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals("2", cartItemCount);
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
