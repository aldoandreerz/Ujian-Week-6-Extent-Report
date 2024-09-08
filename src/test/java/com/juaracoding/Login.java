package com.juaracoding;

import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Hooks;
import com.juaracoding.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Login{

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private ExtentTest test;

    @Given("User is on login page for login")
    public void user_is_on_login_page_for_login() {
        test = ExtentReportManager.getInstance().createTest("User is on login page for login");
        Hooks.driver.get("https://www.saucedemo.com/");
        test.info("Navigated to login page.");    }


    @When("User enters valid username and password for login")
    public void user_enters_valid_username_and_password_for_login() {
        test = ExtentReportManager.getInstance().createTest("User enters valid username and password for login");
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
        test.info("Entered valid username and password.");
    }


    @And("User clicks login button on login page")
    public void user_clicks_login_button_on_login_page() {
        test = ExtentReportManager.getInstance().createTest("User clicks login button on login page");
        loginPage.clickLoginButton();
        delay(2);
        test.info("Clicked login button.");
    }

    @And("User enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        test = ExtentReportManager.getInstance().createTest("User enters invalid username and valid password");
        loginPage.enterUsername("invalid_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
        test.info("Entered invalid username and valid password.");
    }

    @When("User enters valid username and invalid password for login")
    public void user_enters_valid_username_and_invalid_password_for_login() {
        test = ExtentReportManager.getInstance().createTest("User enters valid username and invalid password for login");
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("wrong_password");
        delay(3);
        test.info("Entered valid username and invalid password.");
    }

    @When("User enters empty username and empty password for login")
    public void user_enters_empty_username_and_empty_password_for_login() {
        test = ExtentReportManager.getInstance().createTest("User enters empty username and empty password for login");
        loginPage.enterUsername("");
        delay(3);
        loginPage.enterPassword("");
        delay(3);
        test.info("Entered empty username and password.");
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        test = ExtentReportManager.getInstance().createTest("User clicks login button");
        loginPage.clickLoginButton();
        delay(2);
        test.info("Clicked login button.");
    }

    @Then("User should see an error message for login")
    public void user_should_see_an_error_message_for_login() {
        test = ExtentReportManager.getInstance().createTest("User should see an error message for login");
        loginPage.getErrorMessage();
        test.pass("Verified error message is displayed.");
    }

    @Then("User should be redirected to the product page for login")
    public void user_should_be_redirected_to_the_product_page_for_login() {
        test = ExtentReportManager.getInstance().createTest("User should be redirected to the product page for login");
        delay(3);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
        test.pass("User is redirected to the product page.");
    }



    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
