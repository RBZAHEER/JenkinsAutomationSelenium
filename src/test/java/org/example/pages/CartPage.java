package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //COMING SOON -> 1. Table Handling karun Verify this :->
    //Product Name
    //Quantity
    //Price

    @FindBy(xpath = "//a[contains(.,'Proceed To Checkout')]")
    private WebElement checkOutBtn;

    public CheckoutPage checkout(){
        checkOutBtn.click();

        return new CheckoutPage(driver);
    }
}
