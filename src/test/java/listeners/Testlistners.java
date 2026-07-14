package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.base.BasicTest;
import org.example.utils.DriverManager;
import org.example.utils.ExtentManager;
import org.example.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Testlistners implements ITestListener {

    ExtentReports extent = ExtentManager.getReports();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result){

        test = extent.createTest(result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result){

        test.pass("Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result){

        test.fail(result.getThrowable());
        test.fail(
                "Screenshot captured"
        );
        //Get test class object
        BasicTest test = (BasicTest) result.getInstance();
        //Call Screenshot method
        try {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver(), result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onFinish(
            org.testng.ITestContext context
    ){


        extent.flush();


    }

}
