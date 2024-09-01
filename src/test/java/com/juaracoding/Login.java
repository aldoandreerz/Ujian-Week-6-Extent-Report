package com.juaracoding;

import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Login{

    private LoginPage loginPage = new LoginPage(Hooks.driver);

    @Given("User is on login page for login")
    public void user_is_on_login_page_for_login() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }


    @When("User enters valid username and password for login")
    public void user_enters_valid_username_and_password_for_login() {
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
    }

    @And("User clicks login button on login page")
    public void user_clicks_login_button_on_login_page() {
        loginPage.clickLoginButton();
        delay(2);
    }

    @And("User enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        loginPage.enterUsername("invalid_user");
        delay(3);
        loginPage.enterPassword("secret_sauce");
        delay(3);
    }

    @When("User enters valid username and invalid password for login")
    public void user_enters_valid_username_and_invalid_password_for_login() {
        loginPage.enterUsername("standard_user");
        delay(3);
        loginPage.enterPassword("wrong_password");
        delay(3);
    }

    @When("User enters empty username and empty password for login")
    public void user_enters_empty_username_and_empty_password_for_login() {
        loginPage.enterUsername("");
        delay(3);
        loginPage.enterPassword("");
        delay(3);
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
        delay(2);
    }

    @Then("User should see an error message for login")
    public void user_should_see_an_error_message_for_login() {
        loginPage.getErrorMessage();
    }

    @Then("User should be redirected to the product page for login")
    public void user_should_be_redirected_to_the_product_page_for_login() {
       delay(3);
        String currentUrl = Hooks.driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"));
    }



    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
