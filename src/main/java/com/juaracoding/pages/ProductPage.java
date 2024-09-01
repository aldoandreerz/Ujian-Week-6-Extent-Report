package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement addToCartFleeceJacket;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement addToCartBoltShirt;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartLink;

    public void addFleeceJacketToCart() {
        addToCartFleeceJacket.click();
    }

    public void addBoltShirtToCart() {
        addToCartBoltShirt.click();
    }

    public void goToCart() {
        cartLink.click();
    }
}
