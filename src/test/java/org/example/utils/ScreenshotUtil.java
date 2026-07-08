package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v137.page.model.Screenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {

        try {

            //Initiate SS Interface
            TakesScreenshot takeSs = (TakesScreenshot) driver;

            //Source
            File source = takeSs.getScreenshotAs(OutputType.FILE);

            //Add unique using time

            String time = String.valueOf(System.currentTimeMillis());
            File folder = new File("screenshots");
            if(!folder.exists()){
                folder.mkdir();
            }
            //Destication
            File destination = new File(folder+ "/" + testName + "_" + time + ".png");

            //Copy ss
            Files.copy(source.toPath(),destination.toPath());

            System.out.println("SS Taken");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
