package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderplacePage {
    WebDriver driver;
    WebDriverWait wait;
    public OrderplacePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locators
    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your order has been confirmed!')]")
    private WebElement confirmMsg;


    //Actions
    public String getConfirmMsg(){
        return wait.until(ExpectedConditions.visibilityOf(confirmMsg)).getText();
    }
}
