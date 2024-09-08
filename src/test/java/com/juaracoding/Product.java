package com.juaracoding;

import com.aventstack.extentreports.ExtentTest;
import com.juaracoding.pages.CartPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.ProductPage;
import com.juaracoding.utils.ExtentReportManager;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.By;


public class Product {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ProductPage productPage = new ProductPage(Hooks.driver);
    private ExtentTest test;


    @Given("User is on login page for product")
    public void user_Is_On_Login_Page_for_product() {
        test = ExtentReportManager.getInstance().createTest("User is on login page for product");
        Hooks.driver.get("https://www.saucedemo.com/");
        test.info("Navigated to login page.");
    }

    @When("User enters valid username and password for product")
    public void user_Enters_Valid_Username_And_Password_For_Product() {
        test = ExtentReportManager.getInstance().createTest("User enters valid username and password for product");
        loginPage.enterUsername("standard_user");
        delay(2);
        loginPage.enterPassword("secret_sauce");
        test.info("Entered valid username and password.");
    }

    @And("User clicks login button for product")
    public void user_Clicks_Login_Button_On_Product_Page() {
        test = ExtentReportManager.getInstance().createTest("User clicks login button for product");
        delay(2);
        loginPage.clickLoginButton();
        test.info("Clicked login button.");
    }

    @And("User should be redirected to the product page")
    public void user_Should_Be_Redirected_To_The_Product_Page_For_Product_Steps() {
        test = ExtentReportManager.getInstance().createTest("User should be redirected to the product page");
        delay(2);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
        test.pass("User is redirected to the product page.");
    }

    @And("User adds two products to the cart")
    public void user_Adds_Two_Products_To_The_Cart() {
        test = ExtentReportManager.getInstance().createTest("User adds two products to the cart");
        delay(2);
        productPage.addFleeceJacketToCart();
        delay(2);
        productPage.addBoltShirtToCart();
        test.info("Added two products to the cart.");
    }

    @And("User goes to the cart")
    public void user_Goes_To_The_Cart() {
        test = ExtentReportManager.getInstance().createTest("User goes to the cart");
        delay(2);
        productPage.goToCart();
        test.info("Navigated to the cart.");
    }

    @Then("User should see two products in the cart")
    public void user_Should_See_Two_Products_In_The_Cart() {
        test = ExtentReportManager.getInstance().createTest("User should see two products in the cart");
        delay(2);
        String cartItemCount = Hooks.driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals("2", cartItemCount);
        test.pass("Verified two products are in the cart.");
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
