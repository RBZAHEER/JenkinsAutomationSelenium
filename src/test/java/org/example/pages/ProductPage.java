package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;
    public ProductPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productAnchor;

    @FindBy(id = "search_product")
    private WebElement searchProduct;

    @FindBy(id = "submit_search")
    private WebElement submitSearch;

    @FindBy(className = "features_items")
    private WebElement isProdDisplay;

    @FindBy(xpath = "//div[@class='productinfo text-center']/p")
    private List<WebElement> products;

    @FindBy(xpath = "//a[@data-product-id= '2']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//button[@data-dismiss= 'modal']")
    private WebElement continuePopUp;

    @FindBy(xpath = "//a[@href = '/view_cart']")
    private WebElement cartBtn;


    //actions

    public void setAdClose(){

        try{

            List<WebElement> frames =
                    driver.findElements(
                            By.xpath("//iframe[contains(@id,'aswift')]")
                    );


            System.out.println(
                    "Total Ad Frames : " + frames.size()
            );


            for(WebElement frame : frames){


                driver.switchTo().defaultContent();


                driver.switchTo().frame(frame);


                List<WebElement> innerFrames =
                        driver.findElements(By.tagName("iframe"));


                if(innerFrames.size() > 0){

                    driver.switchTo().frame(0);

                }


                List<WebElement> closeBtn =
                        driver.findElements(
                                By.xpath("//div[text()='Close']")
                        );


                if(closeBtn.size() > 0){


                    closeBtn.get(0).click();


                    System.out.println("Ad Closed");


                    break;

                }

            }


            driver.switchTo().defaultContent();


        }
        catch(Exception e){


            driver.switchTo().defaultContent();


            System.out.println("No Ad Found");


        }

    }

    public void clickOnProductTab(){
        wait.until(
                ExpectedConditions.elementToBeClickable(productAnchor)
        ).click();
        System.out.println(
                "Current URL: " + driver.getCurrentUrl()
        );
    }

    public void setProductName(String productName){
        wait.until(
                        ExpectedConditions.visibilityOf(searchProduct)
                )
                .sendKeys(productName);

    }

    public void clickSubmitSearch(){
        submitSearch.click();
    }

    public boolean isProdDisplayed(){
       return isProdDisplay.isDisplayed();
    }


    public List<String> getAllProducts(){
        List<String> prods = new ArrayList<>();

        for(WebElement prod : products){
            prods.add(prod.getText());
        }

        return prods;
    }

    public CartPage addToCart(){
        setAddToCartBtn();
        setContinuePopUp();
        openCartPage();

        return new CartPage(driver);
    }

    public void setAddToCartBtn(){

        wait.until(
                ExpectedConditions.visibilityOf(addToCartBtn)
        );


        ((JavascriptExecutor)driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);",
                        addToCartBtn
                );


        ((JavascriptExecutor)driver)
                .executeScript(
                        "arguments[0].click();",
                        addToCartBtn
                );

    }
    public void setContinuePopUp(){
        wait.until(ExpectedConditions.elementToBeClickable(continuePopUp)).click();
    }
    public void openCartPage(){
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn)).click();
    }
}
