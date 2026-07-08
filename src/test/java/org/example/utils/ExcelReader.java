package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;

public class ExcelReader {

    public static Object[][] readExcel(String filePath, String sheetName){
        //create 2D array
        Object[][] data = null;
        try{
            //Take excel using fileInputStream
            FileInputStream file = new FileInputStream(filePath);

            Workbook wb = WorkbookFactory.create(file);

            Sheet sheet = wb.getSheet(sheetName);

            //count rows
            int rows = sheet.getPhysicalNumberOfRows();
            int columns = sheet.getRow(0).getPhysicalNumberOfCells();
            DataFormatter formatter =
                    new DataFormatter();
            //Create object array size

            data = new Object[rows-1][columns];

            //Insert data into object

            for(int i=1;i<rows;i++){
                //get current row value
                Row row = sheet.getRow(i);

                for(int j=0;j<columns;j++){
                    //get current cell value
                    Cell cell = row.getCell(j);

                    //store excel into that object
                    data[i-1][j] = formatter.formatCellValue(cell).trim();
                }
            }
            wb.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
