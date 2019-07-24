package biocept.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;

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
import biocept.qa.pages.BioviewScanData;
import biocept.qa.pages.CTCICCResultsSummary;
import biocept.qa.pages.CellSeProcessing;
import biocept.qa.pages.CellSepBatching;
import biocept.qa.pages.CompletedWorklist;
import biocept.qa.pages.Dashboard_LIMS;
import biocept.qa.pages.Dashbord_ClientPortal;
import biocept.qa.pages.EnumerationImageReview;
import biocept.qa.pages.FISHAutobatchedworklist;
import biocept.qa.pages.FISHBatchReviewAndApproveWorklist;
import biocept.qa.pages.FISHReloAnalysis;
import biocept.qa.pages.FISHResultsSummary;
import biocept.qa.pages.FISHReviewAndApproveWorklist;
import biocept.qa.pages.LeftMenuItems;
import biocept.qa.pages.LoginAndLogOut;
import biocept.qa.pages.MEMSBatching;
import biocept.qa.pages.MEMSProcessingActivity;
import biocept.qa.pages.MyWorklist;
import biocept.qa.pages.OrderHistory;
import biocept.qa.pages.QAReportingWorklist;
import biocept.qa.pages.TechOnlyProfessionalInterpretation;
import biocept.qa.pages.UploadAccessionImages;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.Screenshot;
import biocept.qa.utill.WorkflowCommonMethods;

public class CTC_FISHTechOnlyWorkflow extends BioceptBase{

	
	
	LoginAndLogOut loginAndLogOut;
	Dashboard_LIMS dashboard_LIMS;
	Accession accession;
	BarcodeandLabelBuilder barcodeandLabelBuilder;
	String AccessionID;
	LeftMenuItems leftMenuItems;
	MyWorklist myWorklist;
	WorkflowCommonMethods workflowCommonMethods;
	CellSeProcessing cellSeProcessing;
	CellSepBatching cellSepBatching;
	MEMSProcessingActivity memsProcessingActivity;
	MEMSBatching memsBatching;
	BioviewScanData bioviewScanData;
	EnumerationImageReview enumerationImageReview;
	UploadAccessionImages uploadAccessionImages;
	CTCICCResultsSummary ctciccResultsSummary;
	FISHAutobatchedworklist fishAutobatchedworklist;
	FISHReloAnalysis fishReloAnalysis;
	FISHResultsSummary fishResultsSummary;
	TechOnlyProfessionalInterpretation techOnlyProfessionalInterpretation;
	QAReportingWorklist qaReportingWorklist;
	FISHReviewAndApproveWorklist fishReviewAndApproveWorklist;
	CompletedWorklist completedWorklist;
	FISHBatchReviewAndApproveWorklist fishBatchReviewAndApproveWorklist;
	Dashbord_ClientPortal dashbord_ClientPortal;
	OrderHistory orderHistory = new OrderHistory();

	
	@BeforeMethod
	public void setup() {
		loginAndLogOut = new LoginAndLogOut();
		dashboard_LIMS = new Dashboard_LIMS();
		accession = new Accession();
		barcodeandLabelBuilder = new BarcodeandLabelBuilder();
		leftMenuItems = new LeftMenuItems();
		myWorklist = new MyWorklist();
		workflowCommonMethods = new WorkflowCommonMethods();
		cellSeProcessing = new CellSeProcessing();
		cellSepBatching = new CellSepBatching();
		memsProcessingActivity = new MEMSProcessingActivity();
		memsBatching = new MEMSBatching();
		bioviewScanData = new BioviewScanData();
		enumerationImageReview = new EnumerationImageReview();
		uploadAccessionImages = new UploadAccessionImages ();
		ctciccResultsSummary = new CTCICCResultsSummary();
		fishAutobatchedworklist = new FISHAutobatchedworklist();
		fishReloAnalysis = new FISHReloAnalysis();
		fishResultsSummary= new FISHResultsSummary();
		techOnlyProfessionalInterpretation = new TechOnlyProfessionalInterpretation();
		qaReportingWorklist = new QAReportingWorklist();
		fishReviewAndApproveWorklist = new FISHReviewAndApproveWorklist();
		completedWorklist = new CompletedWorklist();
		fishBatchReviewAndApproveWorklist = new FISHBatchReviewAndApproveWorklist();
		dashbord_ClientPortal = new Dashbord_ClientPortal();
		orderHistory = new OrderHistory();
	}
	
	
	@Test(priority= 1, description="Verify the accession type when user create an accession without selecting Assign Professional Work checkbox")
	public void AccesstionType() throws InterruptedException{
		test=extent.createTest("Verify the accession type when user create an accession with Assign Professional Work checkbox selection", "Accession type should be Tech.");
		test.assignCategory("My Worklist");
		loginAndLogOut.LIMSlogin();
		dashboard_LIMS.clickOnPlaceNewOrder("Place a New Order");
		ExplicitWait.invisibilityOfLoader();
		accession.addLabClientPhysicianInfor();
		accession.addpatientInformation();
		accession.addInsurance();
		accession.addSpecimen();
		accession.addCTCFISHTest();
		accession.saveTest();
		accession.assignProfessionalWork();
		accession.addICDCPTCodes();
		accession.addClinicalInformation();
		accession.saveAccesion();
		ExplicitWait.invisibilityOfLoader();
		AccessionID = barcodeandLabelBuilder.getAccessionId();
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Accession Images");
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.enterAccessionNumber(AccessionID);
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.enumerationImages();
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.fishImages();
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		ExplicitWait.invisibilityOfLoader();
		boolean accesstiontype=myWorklist.accesionType(AccessionID, "Tech");
		Assert.assertEquals(accesstiontype, true);

	}
	
	
	@Test(priority= 2,dependsOnMethods = { "AccesstionType"}, description="Verify the QC CompleteButton button on Professional Interpretation page")
	public void QC_CompleteButtonOnProfessionalInterpretation() throws InterruptedException{
		test=extent.createTest("Verify the QC CompleteButton button on Professional Interpretation page", "QC CompleteButton should be available for Tech cases on Professional Interpretation activity");
		test.assignCategory("CTC-FISH Workflow for Tech cases");
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		cellSeProcessing.selectLeucosepTube();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		cellSepBatching.cellSepInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		memsProcessingActivity.selectICCTest();
		memsProcessingActivity.selectFISHTest();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		memsBatching.memsInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		bioviewScanData.bioviewData();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		enumerationImageReview.enumerationAnalysis();
		enumerationImageReview.enumerationInitials();
		enumerationImageReview.enumerationDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		ctciccResultsSummary.ctcResult();
		ctciccResultsSummary.ctcTestImage();
		ctciccResultsSummary.iccResult();
		ctciccResultsSummary.iccTestImage();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		fishAutobatchedworklist.fishAutobatchInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		fishReloAnalysis.uploadCSVFile();
		ExplicitWait.invisibilityOfLoader();
		fishReloAnalysis.fishReloInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		fishResultsSummary.fishTestComponenet();
		fishResultsSummary.fishResult();
		fishResultsSummary.fishTestImage();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(3000);
		techOnlyProfessionalInterpretation.fishReviewTab();
		ExplicitWait.invisibilityOfLoader();
		boolean state = techOnlyProfessionalInterpretation.qcCompleteButton();
		Assert.assertEquals(state, true);
		

	}
	
	@Test(priority= 3,dependsOnMethods = { "QC_CompleteButtonOnProfessionalInterpretation"}, description="Verify the confirmation message when user clicks on QC complete button")
	public void ConfirmationOnTechOnlyPI() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the PI Activity", "User should be able to save and complete the PI Activity.");
		test.assignCategory("CTC-FISH Workflow for Tech cases");
		ExplicitWait.invisibilityOfLoader();
		String message=techOnlyProfessionalInterpretation.qccompleteConfirmation();
		Assert.assertEquals(message, "Are you sure you want to complete QC results?");
		
	}
	

	@Test(priority= 4, dependsOnMethods = { "ConfirmationOnTechOnlyPI"},description="Verify user is able to QC Complete the PI Activity")
	public void QCComplete() throws InterruptedException{
		test=extent.createTest("Verify user is able to QC Complete the PI Activity", "CTC/ICC results successfully approved message should be appear.");
		test.assignCategory("CTC-FISH Workflow for Tech cases");
		ExplicitWait.invisibilityOfLoader();
		techOnlyProfessionalInterpretation.clickOnOkButton();
		String message =workflowCommonMethods.completeMessage();
		Assert.assertEquals(message, "CTC/ICC results successfully approved.");
		Thread.sleep(4000);
		loginAndLogOut.LIMSLogOut();
	}
	
	
	@Test(priority= 5, dependsOnMethods = { "QCComplete"},description="Verify user is able to login in Physician Portal")
	public void PhysicianPortalLogin() throws InterruptedException{
		test=extent.createTest("Verify user is able to login in Physician Portal", "User should be able to login in Physician Portal with valid credentials.");
		test.assignCategory("Login");
		loginAndLogOut.ClientPortalLogin();
		String Title = loginAndLogOut.validateLoginPageTitle();
		Assert.assertEquals(Title, "Biocept Client portal");
	}
	
	
	@Test(priority= 6, dependsOnMethods = { "PhysicianPortalLogin"},description="Verify Tech Only Professional Interpretation Button on Order History Worklist")
	public void OrderHistoryWorklist() throws InterruptedException{
		test=extent.createTest("Verify Tech Only Professional Interpretation Button on Order History Worklist", "Tech Only Professional Interpretation Button should be available for tech type accession.");
		test.assignCategory("Physician Portal");
		dashbord_ClientPortal.clickOnViewReport();
		ExplicitWait.invisibilityOfLoader();
		orderHistory.search(AccessionID);
		ExplicitWait.invisibilityOfLoader();
		boolean State =orderHistory.techOnlyProfessionalInterpretationButton();
        Assert.assertEquals(State, true);
	}
	
	@Test(priority= 7, dependsOnMethods = { "OrderHistoryWorklist"},description="Verify Go Back to Physician Portal Button on Tech Only Professional Interpretation page.")
	public void GoBacktoPhysicianPortalButton() throws InterruptedException{
		test=extent.createTest("Verify Go Back to Physician Portal Button on Tech Only Professional Interpretation page", "Go Back to Physician Portal Button should be available on Tech Only Professional Interpretation page for tech type accession.");
		test.assignCategory("Physician Portal");
		orderHistory.clickOnTechOnlyProfessionalInterpretationButton();
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(3000);
		boolean State = techOnlyProfessionalInterpretation.goBacktoPhysicianPortalButton();
		Assert.assertEquals(State, true);
	}

	
	@Test(priority= 8, dependsOnMethods = { "GoBacktoPhysicianPortalButton"},description="Verify user is able to signoff the report from Physician Portal.")
	public void SignoffReportFromPhysicianPortal() throws InterruptedException{
		test=extent.createTest("Verify user is able to signoff the report from Physician Portal", "User should be able to complete and signoff the report from physician portal.");
		test.assignCategory("Physician Portal");
		ExplicitWait.invisibilityOfLoader();
		workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		techOnlyProfessionalInterpretation.fishReviewTab();
		ExplicitWait.invisibilityOfLoader();
		techOnlyProfessionalInterpretation.signOff();
		String message = workflowCommonMethods.completeMessage();
		System.out.println(message);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(message, "The report has been successfully created and saved");
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
		loginAndLogOut.ClientPortalLogOut();
	}
	
}
