package com.juaracoding;

import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage(Hooks.driver);

    @Given("User is on login page for login")
    public void user_Is_On_Login_Page_for_login() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }


    @When("User enters valid username and password for login")
    public void user_Enters_Valid_Username_And_Password_For_Login() {
        loginPage.enterUsername("standard_user");
        delay(1);
        loginPage.enterPassword("secret_sauce");
    delay(2);
    }

    @And("User clicks login button for login")
    public void user_Clicks_Login_Button_On_Login_Page() {
        loginPage.clickLoginButton();
    }

    @And("User enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        loginPage.enterUsername("invalid_user");
        delay(1);
        loginPage.enterPassword("secret_sauce");
    delay(2);
    }

    @When("User enters valid username and invalid password for login")
    public void user_enters_valid_username_and_invalid_password_for_login() {
        loginPage.enterUsername("standard_user");
        delay(1);
        loginPage.enterPassword("wrong_password");
   delay(2);
    }

    @When("User enters empty username and empty password for login")
    public void user_enters_empty_username_and_empty_password_for_login() {
        loginPage.enterUsername("");
        delay(1);
        loginPage.enterPassword("");
   delay(2);
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
        delay(1);
    }

    @Then("User should see an error message for login")
    public void user_should_see_an_error_message_for_login() {
        loginPage.getErrorMessage();
    }

    @Then("User should be redirected to the product page for login")
    public void user_Should_Be_Redirected_To_The_Product_Page_for_login() {
       delay(1);
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
