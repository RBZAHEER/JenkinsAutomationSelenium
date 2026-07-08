package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BasicTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        String browser = ConfigReader.getProperties("browser");
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            Map<String,Object> prefs = new HashMap<>();

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

            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperties("url"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
