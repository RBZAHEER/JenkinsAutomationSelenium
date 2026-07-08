package org.example.tests;

import listeners.Testlistners;
import org.example.base.BasicTest;
import org.example.pages.*;
import org.example.utils.ConfigReader;
import org.example.utils.ExcelReader;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
@Listeners(Testlistners.class)
public class VerifyLogin extends BasicTest {

    @Test(dataProvider="excelData")
    public void testLogin(String mailId, String passwd, String userId, String productName,
                          String cardName,
                          String cardNumber,
                          String cvv,
                          String month,
                          String year,
                          String expectedMsg){

        LoginPage lp = new LoginPage(driver);
        ProductPage pp = lp.fullLogin(mailId,passwd);

        System.out.println(mailId + passwd);


        if(lp.loginSuccess()){
            pp.setAdClose();
            Assert.assertEquals(
                    lp.validateSuccess(),
                    userId
            );

            System.out.println("Log in successful");


            pp.clickOnProductTab();


            pp.setProductName(productName);


            pp.clickSubmitSearch();


            Assert.assertTrue(
                    pp.isProdDisplayed()
            );


            List<String> products = pp.getAllProducts();


            for(String prod : products){

                System.out.println(prod);

            }
                CartPage cp = pp.addToCart();

           CheckoutPage checkoutPage =  cp.checkout();

            PaymentPage paymentPage = checkoutPage.placeOrder();

            OrderplacePage orderplacePage = paymentPage.payment(cardName, cardNumber,cvv, month, year);

            String confirmMsg = orderplacePage.getConfirmMsg();

            Assert.assertEquals(confirmMsg, expectedMsg);
        }

        else {

            Assert.assertEquals(
                    lp.errorMsg(),
                    "Your email or password is incorrect!"
            );

            System.out.println("Log in Error");

        }

    }


    @DataProvider(name = "excelData")
    public Object[][] getData(){

        return ExcelReader.readExcel(
                "src/test/resources/LoginData.xlsx",
                "Login"
        );

    }
}