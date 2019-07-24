package biocept.qa.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import biocept.qa.base.BioceptBase;
import biocept.qa.pages.LoginAndLogOut;

public class BioceptTestBase extends BioceptBase{
	
	LoginAndLogOut loginAndLogOut = new LoginAndLogOut();
	
	public BioceptTestBase(){
		super();
	}
	
	

  @BeforeSuite
  public void beforeSuite() {
	  BioceptBase.startReport();
  }

  
  @BeforeTest
  public void beforeTest() {
	  initialization();
	  
  }
  
//  @AfterTest
//  public void afterTest() {
//	  loginAndLogOut.LIMSLogOut();
//  }
  
@AfterSuite
public void tearDown()
{
    extent.flush();
    driver.quit();
}
}
