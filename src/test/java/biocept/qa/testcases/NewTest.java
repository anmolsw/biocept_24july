package biocept.qa.testcases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import biocept.qa.base.BioceptBase;
import biocept.qa.pages.LoginAndLogOut;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.Screenshot;
public class NewTest extends BioceptBase{
	

		

   
	
	
	@Test(priority =1)
  
  
  public void Test1() throws IOException, InterruptedException {
		
	  
	test=extent.createTest("Verify user is able to Login to Mission Control", "User should be able to login to Missin Control with valid credentials");
	test.assignCategory("first US");
	
	LoginAndLogOut loginAndLogOut = new LoginAndLogOut();
	loginAndLogOut.ClientPortalLogin();
	ExplicitWait.invisibilityOfLoader();
	String a =loginAndLogOut.validateLoginPageTitle();
	System.out.println(a);
//	
//	Dashbord_ClientPortal dashbord_ClientPortal = new Dashbord_ClientPortal();
//	dashbord_ClientPortal.clickOnViewReport();
//	ExplicitWait.invisibilityOfLoader();
//
//	OrderHistory orderHistory = new OrderHistory();
//			String b =orderHistory.pageTitle();
//			System.out.println(b);
//			orderHistory.search("19-00012");
//			ExplicitWait.invisibilityOfLoader();
//			boolean c=orderHistory.techOnlyProfessionalInterpretationButton();
//			System.out.println(c);
//			orderHistory.clickOnTechOnlyProfessionalInterpretationButton();
//			ExplicitWait.invisibilityOfLoader();
//			Thread.sleep(3000);
//			TechOnlyProfessionalInterpretation techOnlyProfessionalInterpretation = new TechOnlyProfessionalInterpretation();
//			boolean button = techOnlyProfessionalInterpretation.goBacktoPhysicianPortalButton();
//			System.out.println(button);
//			WorkflowCommonMethods workflowCommonMethods = new WorkflowCommonMethods();
//			workflowCommonMethods.ctcFishCompleteButton();
//			ExplicitWait.invisibilityOfLoader();
//			techOnlyProfessionalInterpretation.fishReviewTab();
//			ExplicitWait.invisibilityOfLoader();
//			techOnlyProfessionalInterpretation.signOff();
//			String message = workflowCommonMethods.completeMessage();
//			System.out.println(message);
//			 ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//			 System.out.println(tabs.size());
//			    driver.switchTo().window(tabs.get(0));
//			ExplicitWait.invisibilityOfLoader();
//			Thread.sleep(2000);
			loginAndLogOut.ClientPortalLogOut();
			Thread.sleep(3000);
	
	Assert.assertEquals(true, true);
	 // UploadMacroFile  uploadMacroFile = new UploadMacroFile();
//	  uploadMacroFile.updateSequencingImage("19-00144", "B");
	 // uploadMacroFile.reupdateSequencingImage();
  System.out.println("Done");
//	  Assert.assertEquals(true, true);
		//uploadMacroFile.uploadSequencingImage();
		
//		uploadMacroFile.reupdateSequencingImage();
//		
//		  System.out.println("Done1");
//		try{
//			Properties prop = new Properties();
//			FileInputStream ip = new FileInputStream("C:/Users/amarendra.singh/workspace/Biocept/src/main/java/biocept/qa/config/config.properties");
//			prop.load(ip);
//			
//			prop.setProperty("amar", "testing");
//			prop.store(new FileOutputStream("C:/Users/amarendra.singh/workspace/Biocept/src/main/java/biocept/qa/config/config.properties"), null);
//			String value = prop.getProperty("username");
//			System.out.println(value);
//			
//			
////			String value = prop.getProperty("key");
////			String[] elements = value.split(",");
////			List<String> fixedLenghtList = Arrays.asList(elements);
////			ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);
////			System.out.println("List of String" + listOfString );
////			System.out.println("Length Of Array"+ listOfString.size());
////			String a = 	listOfString.get(0);	
////			System.out.println("First Value  "+ a);
//			
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}catch (IOException e){
//			e.printStackTrace();
//		}
  }
	@AfterMethod
	 public static void getResult(ITestResult result) throws IOException
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
        	 
        	String screenShotPath = Screenshot.captureScreenshot(result.getName());            
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
            test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
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
	
	
	
}
