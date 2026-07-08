package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //Locators
    @FindBy(xpath = "//a[text() = ' Signup / Login']")
    private WebElement login;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = ".btn.btn-default")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Logged in as')]/b")
    private WebElement loginUserId;

    @FindBy(xpath = "//form/p")
    private WebElement errorMsg;
    //Actions

    public void clicklogin(){
        login.click();
    }

    public void setEmail(String mail){
        email.sendKeys(mail);
    }

    public void setPassword(String passwd){
        password.sendKeys(passwd);
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }
    public boolean loginSuccess(){

        try{

            return loginUserId.isDisplayed();

        }catch(Exception e){

            return false;

        }
    }
    public String validateSuccess(){
       return loginUserId.getText();
    }
    public String errorMsg(){
        return errorMsg.getText();
    }

    public ProductPage fullLogin(String mail, String passwd){
        clicklogin();
        setEmail(mail);
        setPassword(passwd);
        clickLoginBtn();

        return new ProductPage(driver);
    }
}
