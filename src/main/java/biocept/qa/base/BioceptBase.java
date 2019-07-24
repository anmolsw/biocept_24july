package biocept.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BioceptBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Properties accessionprop;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;
		
	public BioceptBase(){
		
		try{
			 prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/biocept/qa/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		try{
			 accessionprop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Accession.properties");
			accessionprop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")){
					
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/biocept/qa/config/Browsers Driver/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			 options.addArguments("disable-infobars");	
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/amarendra.singh/workspace/Biocept/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		

		
	}
	
	
	
public static void startReport()
{
	    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testing.html");
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

public void flu()
{
    extent.flush();
}
	
	
}
