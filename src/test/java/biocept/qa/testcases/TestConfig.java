package biocept.qa.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestConfig {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	 ExtentHtmlReporter htmlReporter;
	@BeforeSuite
	public void startReport()
	{
		    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/TestingReport.html");
		    extent = new ExtentReports();
		    extent.attachReporter(htmlReporter);
		    
		    extent.setSystemInfo("Project Name", "Biocept");
		    extent.setSystemInfo("Environment", "Hotfix");
		    extent.setSystemInfo("Operating Systems", "Window 10");
		    extent.setSystemInfo("User Name", "Amarendra Singh");
		    htmlReporter.config().setChartVisibilityOnOpen(true);
		    //htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		    htmlReporter.config().setReportName("Smoke Testing");
		    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		    htmlReporter.config().setTheme(Theme.DARK);
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
        	 
        	//String screenShotPath = Screenshot.captureScreenshot(result.getName());            
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
           // test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
                 }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else if(result.getStatus() == ITestResult.SKIP)
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
           
        }
        extent.flush();
    }

	@BeforeTest
	public void testBeforeTest() {
		System.out.println("testBeforeTest()");
	}

	@AfterTest
	public void testAfterTest() {
		System.out.println("testAfterTest()");
	}
}
