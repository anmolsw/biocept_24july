package biocept.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import biocept.qa.base.BioceptBase;
import biocept.qa.pages.LoginAndLogOut;
import biocept.qa.utill.ExplicitWait;

public class NewTest1 extends BioceptBase{
	
  @Test(priority =1)
  public void Actual() throws InterruptedException {
	  

		test=extent.createTest("Verify user is able to Login to Mission Control", "User should be able to login to Missin Control with valid credentials");
		test.assignCategory("first US");
		
		LoginAndLogOut loginAndLogOut = new LoginAndLogOut();
		loginAndLogOut.ClientPortalLogin();
		ExplicitWait.invisibilityOfLoader();
		String a =loginAndLogOut.validateLoginPageTitle();
		System.out.println(a);
		loginAndLogOut.ClientPortalLogOut();
		Thread.sleep(3000);

Assert.assertEquals(true, true);
	  
  
//  
//  test=extent.createTest("Verify user is able to Login to Biocept", "User should be able to login to Biocept with valid credentials");
//  test.assignCategory("Secound US");
//	Assert.assertEquals(true, false);


}
  
//  @AfterMethod
//	 public static void getResult(ITestResult result) throws IOException
// {
//     if (result.getStatus() == ITestResult.FAILURE)
//     {
//     	 
//     	String screenShotPath = Screenshot.captureScreenshot(result.getName());            
//         test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
//         test.fail(result.getThrowable());
//         test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
//              }
//     else if(result.getStatus() == ITestResult.SUCCESS)
//     {
//         test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
//     }
//     else if(result.getStatus() == ITestResult.SKIP)
//     {
//         test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
//         test.skip(result.getThrowable());
//        
//     }
//     extent.flush();
// }
}