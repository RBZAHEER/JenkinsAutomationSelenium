package org.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static String getProperties(String key){

        try{
            prop = new Properties();

            FileInputStream fs = new FileInputStream("src/test/resources/config.properties");

            prop.load(fs);
        } catch (Exception e){
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
