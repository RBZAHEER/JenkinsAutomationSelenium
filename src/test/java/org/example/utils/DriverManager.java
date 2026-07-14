package org.example.utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    // Creates separate WebDriver storage for every thread
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Store driver inside current thread
    public static void setDriver(WebDriver webDriver){
        driver.set(webDriver);
    }

    //get driver belonging to current thread
    public static WebDriver getDriver(){
        return driver.get();
    }

    //remove driver from current thread
    public static void removeDriver(){
        driver.remove();
    }


}
