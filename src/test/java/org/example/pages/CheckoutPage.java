package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v137.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
    public CheckoutPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[@href='/payment']")
    private WebElement placeorderBtn;

    public void clickPlaceOrder(){
        wait.until(ExpectedConditions.elementToBeClickable(placeorderBtn)).click();
    }

    public PaymentPage placeOrder(){
        clickPlaceOrder();
        return new PaymentPage(driver);
    }
}
