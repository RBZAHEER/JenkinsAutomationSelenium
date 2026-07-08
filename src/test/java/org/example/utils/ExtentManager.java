package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent;

    public static ExtentReports getReports(){
        if(extent == null){
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestReport.html");

            reporter.config().setReportName("Automation_report");

            reporter.config().setDocumentTitle("automationexercise");

            extent = new ExtentReports();

            extent.attachReporter(reporter);
        }
        return extent;
    }
}
