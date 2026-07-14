package org.example.base;

import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashMap;
import java.util.Map;


public class BasicTest {


    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser)
            throws MalformedURLException {


        String headless =
                ConfigReader.getProperties("headless");


        // ==============================
        // CHROME
        // ==============================

        if(browser.equalsIgnoreCase("chrome")){


            ChromeOptions options =
                    new ChromeOptions();


            Map<String,Object> prefs =
                    new HashMap<>();


            prefs.put(
                    "credentials_enable_service",
                    false
            );


            prefs.put(
                    "profile.password_manager_enabled",
                    false
            );


            prefs.put(
                    "profile.password_manager_leak_detection",
                    false
            );


            options.setExperimentalOption(
                    "prefs",
                    prefs
            );


            options.addArguments(
                    "--disable-popup-blocking"
            );


            options.addArguments(
                    "--disable-notifications"
            );


            if(headless.equalsIgnoreCase("true")){

                options.addArguments(
                        "--headless=new"
                );

            }


            DriverManager.setDriver(

                    new RemoteWebDriver(

                            new URL(
                                    "http://192.168.0.104:4444"
                            ),

                            options

                    )

            );

        }


        // ==============================
        // FIREFOX
        // ==============================

        else if(browser.equalsIgnoreCase("firefox")){


            FirefoxOptions options =
                    new FirefoxOptions();


            if(headless.equalsIgnoreCase("true")){

                options.addArguments(
                        "-headless"
                );

            }


            DriverManager.setDriver(

                    new RemoteWebDriver(

                            new URL(
                                    "http://192.168.0.104:4444"
                            ),

                            options

                    )

            );

        }


        // ==============================
        // EDGE
        // ==============================

        else if(browser.equalsIgnoreCase("edge")){


            EdgeOptions options =
                    new EdgeOptions();


            options.addArguments(
                    "--disable-popup-blocking"
            );


            options.addArguments(
                    "--disable-notifications"
            );


            if(headless.equalsIgnoreCase("true")){

                options.addArguments(
                        "--headless=new"
                );

            }


            DriverManager.setDriver(

                    new RemoteWebDriver(

                            new URL(
                                    "http://192.168.0.104:4444"
                            ),

                            options

                    )

            );

        }


        else {

            throw new IllegalArgumentException(

                    "Invalid Browser: " + browser

            );

        }


        DriverManager
                .getDriver()
                .manage()
                .window()
                .maximize();


        DriverManager
                .getDriver()
                .get(
                        ConfigReader
                                .getProperties("url")
                );

    }


    @AfterMethod
    public void tearDown(){


        if(DriverManager.getDriver() != null){


            DriverManager
                    .getDriver()
                    .quit();


            DriverManager
                    .removeDriver();

        }

    }

}