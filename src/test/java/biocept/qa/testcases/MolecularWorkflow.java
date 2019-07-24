package biocept.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import biocept.qa.base.BioceptBase;
import biocept.qa.pages.Accession;
import biocept.qa.pages.BarcodeandLabelBuilder;
import biocept.qa.pages.Dashboard_LIMS;
import biocept.qa.pages.LeftMenuItems;
import biocept.qa.pages.LoginAndLogOut;
import biocept.qa.pages.MolecularGenericResulting;
import biocept.qa.pages.MolecularProfessionalInterpretation;
import biocept.qa.pages.MolecularReviewAndApproveWorklist;
import biocept.qa.pages.MyWorklist;
import biocept.qa.pages.Sequencing;
import biocept.qa.pages.UploadMacroFile;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.Screenshot;
import biocept.qa.utill.WorkflowCommonMethods;

public class MolecularWorkflow extends BioceptBase{
	
	LoginAndLogOut loginAndLogOut;
	Dashboard_LIMS dashboard_LIMS;
	Accession accession;
	BarcodeandLabelBuilder barcodeandLabelBuilder;
	String AccessionID;
	LeftMenuItems leftMenuItems;
	MyWorklist myWorklist;
	WorkflowCommonMethods workflowCommonMethods;
	UploadMacroFile uploadMacroFile;
	MolecularGenericResulting molecularGenericResulting;
	Sequencing sequencing;
	MolecularProfessionalInterpretation molecularProfessionalInterpretation;
	MolecularReviewAndApproveWorklist molecularReviewAndApproveWorklist;


	
	@BeforeMethod
	public void setup() {
		loginAndLogOut = new LoginAndLogOut();
		dashboard_LIMS = new Dashboard_LIMS();
		accession = new Accession();
		barcodeandLabelBuilder = new BarcodeandLabelBuilder();
		leftMenuItems = new LeftMenuItems();
		myWorklist = new MyWorklist();
		workflowCommonMethods = new WorkflowCommonMethods();
		uploadMacroFile = new UploadMacroFile();
		molecularGenericResulting = new MolecularGenericResulting();
		sequencing = new Sequencing();
		molecularProfessionalInterpretation = new MolecularProfessionalInterpretation();
		molecularReviewAndApproveWorklist = new MolecularReviewAndApproveWorklist();
	}
	
	

	
	@Test(priority= 1, description="Verify user is able to create a new accession for Molecular Test")
	public void PlaceNewOrderForMolecular() throws InterruptedException{
		test=extent.createTest("Verify user is able to create a new accession for Molecular Test", "User should be able to create a acession with Molecular Test.");
		test.assignCategory("Molecular Workflow");
		loginAndLogOut.LIMSlogin();
		dashboard_LIMS.clickOnPlaceNewOrder("Place a New Order");
		ExplicitWait.invisibilityOfLoader();
		accession.addLabClientPhysicianInfor();
		accession.addpatientInformation();
		accession.addInsurance();
		accession.addSpecimen();
		accession.addBRAFTest();
		accession.addKRASTest();
		//accession.addMolEGFRTest();
		accession.addNRASest();
		//accession.addCTCFISHTest();
		accession.saveTest();
		accession.addICDCPTCodes();
		accession.addClinicalInformation();
		accession.saveAccesion();
		ExplicitWait.invisibilityOfLoader();
		boolean SummaryReport = barcodeandLabelBuilder.isSummaryReportAvailable();
		AccessionID = barcodeandLabelBuilder.getAccessionId();
		Assert.assertEquals(SummaryReport, false);
		//Assert.assertEquals(true, true);
	}
	

	
	@Test(priority= 2, dependsOnMethods = { "PlaceNewOrderForMolecular"}, description="Verify user is able to open the upload Macro File page")
	public void UploadMacroFile() throws InterruptedException, IOException{
		test=extent.createTest("Verify user is able to open the upload Macro File page", "User should be able to open and upload the Macro File for BRAF/KRAS/NRAS and MolEGFR test.");
		test.assignCategory("Molecular Workflow");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Macro File");
		String PageTitle = uploadMacroFile.pageTitle();
		ExplicitWait.invisibilityOfLoader();
		uploadMacroFile.updateMacro(AccessionID,"BRAF");
		uploadMacroFile.uploadMacro("BRAF");
		uploadMacroFile.updateMacro(AccessionID,"KRAS");
		uploadMacroFile.uploadMacro("KRAS");
		uploadMacroFile.updateMacro(AccessionID,"NRAS");
		uploadMacroFile.uploadMacro("NRAS");
		Assert.assertEquals(PageTitle, "Molecular Macro & Sequence Files");
	}
	
	
	@Test(priority= 3,dependsOnMethods = { "UploadMacroFile"}, description="Verify user is able to open the NRAS Molecular Generic Resulting Activity")
	public void NRASResultingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the NRAS Molecular Generic Resulting Activity", "After processing macro file user should be able to open Molecular Generic Resulting for NRAS test.");
		test.assignCategory("NRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "NRAS", "Molecular Generic Resulting", "Molecular Generic Resulting-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = molecularGenericResulting.testName();
		if(PageTitle.contains("Molecular Generic Resulting")&&TestName.contains("NRAS")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}
	
	@Test(priority= 4,dependsOnMethods = { "NRASResultingActivityTitle"}, description="Verify user is able to complete the NRAS Molecular Generic Resulting activity")
	public void NRASResultingUpdateResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the NRAS Molecular Generic Resulting activity", "User should be able to complete the NRAS Molecular Generic Resulting using complete button.");
		test.assignCategory("NRAS Workflow");
		molecularGenericResulting.braf_kras_nras_GenericUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}	
	
	
	
	

	@Test(priority= 5,dependsOnMethods = { "NRASResultingUpdateResult"}, description="Verify user is able to open the BRAF Molecular Generic Resulting Activity")
	public void BRAFResultingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the BRAF Molecular Generic Resulting Activity", "After processing macro file user should be able to open Molecular Generic Resulting for BRAF test.");
		test.assignCategory("BRAF Workflow");
		ExplicitWait.invisibilityOfLoader();
//		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "BRAF", "Molecular Generic Resulting", "Molecular Generic Resulting-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = molecularGenericResulting.testName();
		if(PageTitle.contains("Molecular Generic Resulting")&&TestName.contains("BRAF")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}
	
	
	@Test(priority= 6,dependsOnMethods = { "BRAFResultingActivityTitle"}, description="Verify user is able to complete the BRAF Molecular Generic Resulting activity")
	public void BRAFResultingUpdateResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the BRAF Molecular Generic Resulting activity", "User should be able to complete the BRAF Molecular Generic Resulting using complete button.");
		test.assignCategory("BRAF Workflow");
		molecularGenericResulting.braf_kras_nras_GenericUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
//		Thread.sleep(4000);
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	

	
	
	@Test(priority= 7,dependsOnMethods = { "BRAFResultingUpdateResult"}, description="Verify user is able to open the KRAS Molecular Generic Resulting activity")
	public void KRASResultingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Molecular Generic Resulting for KRAS test", "After processing macro file user should be able to open Molecular Generic Resulting for KRAS test.");
		test.assignCategory("KRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		//leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "KRAS", "Molecular Generic Resulting", "Molecular Generic Resulting-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = molecularGenericResulting.testName();
		if(PageTitle.contains("Molecular Generic Resulting")&&TestName.contains("KRAS")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}
	
	@Test(priority= 8, dependsOnMethods = { "KRASResultingActivityTitle"},description="Verify user is able to complete the KRAS Molecular Generic Resulting activity")
	public void KRASResultingUpdateResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the KRAS Molecular Generic Resulting activity", "User should be able to complete the KRAS Molecular Generic Resulting using complete button.");
		test.assignCategory("KRAS Workflow");
		molecularGenericResulting.braf_kras_nras_GenericUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(4000);
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
//	@Test(priority= 6, description="Verify user is able to open the Molecular Generic Resulting for MolEGFR test")
//	public void MolEGFRResultingActivityTitle() throws InterruptedException{
//		test=extent.createTest("Verify user is able to open the Molecular Generic Resulting for MolEGFR test", "After processing macro file user should be able to open Molecular Generic Resulting for MolEGFR test.");
//		test.assignCategory("MolEGFR Workflow");
//		ExplicitWait.invisibilityOfLoader();
//		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
//		myWorklist.molecularSearch(AccessionID, "MolEGFR", "Molecular Generic Resulting", "Molecular Generic Resulting-In-Progress");
//		ExplicitWait.invisibilityOfLoader();
//		String PageTitle = workflowCommonMethods.pageTitle();
//		String TestName = molecularGenericResulting.testName();
//		if(PageTitle.contains("Molecular Generic Resulting")&&TestName.contains("MolEGFR")){
//			Assert.assertEquals(true, true);
//		}else{
//			Assert.assertEquals(false, true);
//		}
//	}
	


	
	@Test(priority= 9, dependsOnMethods = { "KRASResultingUpdateResult"}, description="Verify user is able to upload the Sequence image")
	public void UploadSequenceImages() throws InterruptedException, IOException{
		test=extent.createTest("Verify user is able to upload the Sequence image", "User should be able to upload the Sequence file from Sequence image section.");
		test.assignCategory("Molecular Workflow");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Macro File");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = uploadMacroFile.pageTitle();
		uploadMacroFile.updateSequencingImage(AccessionID, "B");
		uploadMacroFile.uploadSequencingImage();
		uploadMacroFile.reupdateSequencingImage();
		uploadMacroFile.updateSequencingImage(AccessionID, "K");
		uploadMacroFile.uploadSequencingImage();
		uploadMacroFile.reupdateSequencingImage();
		
		Assert.assertEquals(PageTitle, "Molecular Macro & Sequence Files");
	}
	
	
	
	
	
	
	@Test(priority= 10, dependsOnMethods = { "UploadSequenceImages"},description="Verify user is able to open NRAS Molecular Professional Interpretation activity")
	public void NRASPIActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open NRAS Molecular Professional Interpretation activity", "User should be able to open the NRAS Molecular Professional Interpretation activity from My worklist.");
		test.assignCategory("NRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID, "NRAS");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		//String TestName = molecularProfessionalInterpretation.testName();
		if(PageTitle.contains("Molecular Professional Interpretation")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 11, dependsOnMethods = { "NRASPIActivityTitle"},description="Verify user is able to complete the NRAS Molecular Professional Interpretation activity")
	public void NRASPIActivityResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the NRAS Molecular Professional Interpretation activit", "User should be able to complete the NRAS Molecular Professional Interpretation activity using complete button.");
		test.assignCategory("NRAS Workflow");
		molecularProfessionalInterpretation.piUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	
	@Test(priority= 12, dependsOnMethods = { "NRASPIActivityResult"}, description="Verify user is able to Signoff the NRAS Test Report from Molecular Review & Approve Worklist")
	public void NRASReportSignoff() throws InterruptedException{
		test=extent.createTest("Verify user is able to Signoff the NRAS Test Report from Molecular Review & Approve Worklist", "After complete PI activity user should be able to Signoff the NRAS Test Report from Molecular Review & Approve Worklist");
		test.assignCategory("NRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		molecularReviewAndApproveWorklist.reportSignoff();
		ExplicitWait.invisibilityOfLoader();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");
		Thread.sleep(4000);
	}
	
	@Test(priority= 13, dependsOnMethods = { "NRASReportSignoff"},description="Verify user is able to open BRAF Sequencing activity")
	public void BRAFSequencingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open BRAF Sequencing activity", "After processing Sequencing file user should be able to open Sequencing activity for BRAF test.");
		test.assignCategory("BRAF Workflow");
		myWorklist.molecularSearch(AccessionID, "BRAF", "Sequencing","Sequencing-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = sequencing.testName();
		if(PageTitle.contains("Sequencing")&&TestName.contains("BRAF")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 14,dependsOnMethods = { "BRAFSequencingActivityTitle"}, description="Verify user is able to complete the BRAF Sequencing activity")
	public void BRAFSequencingActivityResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the BRAF Sequencing activity", "User should be able to complete the BRAF Sequencing activity using complete button.");
		test.assignCategory("BRAF Workflow");
		ExplicitWait.invisibilityOfLoader();
		sequencing.braf_kras_nras_sequencingUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
		
	}
	
	@Test(priority= 15, dependsOnMethods = { "BRAFSequencingActivityResult"},description="Verify user is able to open KRAS Sequencing activity")
	public void KRASSequencingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open KRAS Sequencing activity", "After processing Sequencing file user should be able to open Sequencing activity for KRAS test.");
		test.assignCategory("KRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		//leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "KRAS", "Sequencing","Sequencing-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = sequencing.testName();
		if(PageTitle.contains("Sequencing")&&TestName.contains("KRAS")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 16, dependsOnMethods = { "KRASSequencingActivityTitle"},description="Verify user is able to complete the KRAS Sequencing activity")
	public void KRASSequencingActivityResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the KRAS Sequencing activity", "User should be able to complete the KRAS Sequencing activity using complete button.");
		test.assignCategory("KRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		sequencing.braf_kras_nras_sequencingUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
		
	}
	
	@Test(priority= 17, dependsOnMethods = { "KRASSequencingActivityResult"},description="Verify user is able to open BRAF Molecular Professional Interpretation activity")
	public void BRAFPIActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open BRAF Molecular Professional Interpretation activity", "User should be able to open the BRAF Molecular Professional Interpretation activity from My worklist.");
		test.assignCategory("BRAF Workflow");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID, "BRAF");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		//String TestName = molecularProfessionalInterpretation.testName();
		if(PageTitle.contains("Molecular Professional Interpretation")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 18,dependsOnMethods = { "BRAFPIActivityTitle"}, description="Verify user is able to complete the BRAF Molecular Professional Interpretation activity")
	public void BRAFPIActivityResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the BRAF Molecular Professional Interpretation activit", "User should be able to complete the BRAF Molecular Professional Interpretation activity using complete button.");
		test.assignCategory("BRAF Workflow");
		molecularProfessionalInterpretation.piUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	
	@Test(priority= 19, dependsOnMethods = { "BRAFPIActivityResult"}, description="Verify user is able to Signoff the BRAF Test Report from Molecular Review & Approve Worklist")
	public void BRAFReportSignoff() throws InterruptedException{
		test=extent.createTest("Verify user is able to Signoff the BRAF Test Report from Molecular Review & Approve Worklist", "After complete PI activity user should be able to Signoff the BRAF Test Report from Molecular Review & Approve Worklist");
		test.assignCategory("BRAF Workflow");
		ExplicitWait.invisibilityOfLoader();
		molecularReviewAndApproveWorklist.reportSignoff();
		ExplicitWait.invisibilityOfLoader();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");
		Thread.sleep(3000);
	}
	
	@Test(priority= 20, dependsOnMethods = { "BRAFReportSignoff"},description="Verify user is able to open KRAS Molecular Professional Interpretation activity")
	public void KRASPIActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open KRAS Molecular Professional Interpretation activity", "User should be able to open the KRAS Molecular Professional Interpretation activity from My worklist.");
		test.assignCategory("KRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID, "KRAS");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		//String TestName = molecularProfessionalInterpretation.testName();
		if(PageTitle.contains("Molecular Professional Interpretation")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 21,dependsOnMethods = { "KRASPIActivityTitle"}, description="Verify user is able to complete the KRAS Molecular Professional Interpretation activity")
	public void KRASPIActivityResult() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the KRAS Molecular Professional Interpretation activit", "User should be able to complete the KRAS Molecular Professional Interpretation activity using complete button.");
		test.assignCategory("KRAS Workflow");
		molecularProfessionalInterpretation.piUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	
	@Test(priority= 22, dependsOnMethods = { "KRASPIActivityResult"}, description="Verify user is able to Signoff the KRAS Test Report from Molecular Review & Approve Worklist")
	public void KRASReportSignoff() throws InterruptedException{
		test=extent.createTest("Verify user is able to Signoff the KRAS Test Report from Molecular Review & Approve Worklist", "After complete PI activity user should be able to Signoff the KRAS Test Report from Molecular Review & Approve Worklist");
		test.assignCategory("KRAS Workflow");
		ExplicitWait.invisibilityOfLoader();
		molecularReviewAndApproveWorklist.reportSignoff();
		ExplicitWait.invisibilityOfLoader();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");
		
	}
	
	
	@AfterMethod
	
    public void getResult(ITestResult result) throws IOException
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
	

	@AfterClass
	public void LogOut(){
	loginAndLogOut.LIMSLogOut();
	}
	
 
}
