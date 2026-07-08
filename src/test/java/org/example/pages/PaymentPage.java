package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;

import java.time.Duration;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    public PaymentPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locators
    @FindBy(name = "name_on_card")
    private WebElement name;

    @FindBy(name = "card_number")
    private WebElement cardNumber;

    @FindBy(name = "cvc")
    private WebElement cvc;

    @FindBy(name = "expiry_month")
    private WebElement expiryDate;

    @FindBy(name = "expiry_year")
    private WebElement expiryYear;

    @FindBy(id = "submit")
    private WebElement payBtn;


    //Actions

    public void setName(String Name){
        wait.until(ExpectedConditions.visibilityOf(name)).sendKeys(Name);
    }

    public void setCardNumber(String cardNo){
        cardNumber.sendKeys(cardNo);
    }

    public void setCvc(String cvcNo) {cvc.sendKeys(cvcNo);}

    public void setExpiryDate(String Date){expiryDate.sendKeys(Date);}

    public void setExpiryYear(String Year){expiryYear.sendKeys(Year);}

    public void clickPaymentBtn(){
        payBtn.click();
    }

    public OrderplacePage payment(String name, String cardNum, String cvcNum, String expDate, String expYear){
        setName(name);
        setCardNumber(cardNum);
        setCvc(cvcNum);
        setExpiryDate(expDate);
        setExpiryYear(expYear);
        clickPaymentBtn();

        return new OrderplacePage(driver);
    }
}
