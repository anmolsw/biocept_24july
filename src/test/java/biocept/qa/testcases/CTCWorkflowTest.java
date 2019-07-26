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
import biocept.qa.pages.BioviewScanData;
import biocept.qa.pages.CTCICCResultsSummary;
import biocept.qa.pages.CellSeProcessing;
import biocept.qa.pages.CellSepBatching;
import biocept.qa.pages.CompletedWorklist;
import biocept.qa.pages.Dashboard_LIMS;
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
import biocept.qa.pages.QAReportingWorklist;
import biocept.qa.pages.TechOnlyProfessionalInterpretation;
import biocept.qa.pages.UploadAccessionImages;
import biocept.qa.utill.DownloadedFiles;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.Screenshot;
import biocept.qa.utill.WorkflowCommonMethods;

public class CTCWorkflowTest extends BioceptBase{
	
//	ExtentReports extent;
	//ExtentTest test;
//	ExtentHtmlReporter htmlReporter;
	
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
	
//	public CTCWorkflowTest(){
//		super();
//	}
//	
//	@BeforeClass
//	public void Initi() throws InterruptedException{
//		initialization();
//	}
	
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
	}
	
	
	@Test(priority= 1, description="Verify user is able to login in LIMS portal")
	public void LIMSLogin(){
		test=extent.createTest("Verify user is able to login in LIMS portal", "User should be able to login in LIMS portal with valid credentials.");
		test.assignCategory("Login");
		loginAndLogOut.LIMSlogin();
		String Title = loginAndLogOut.validateLoginPageTitle();
		Assert.assertEquals("Helix - Laboratory Information System", Title);
	}
	
	@Test(priority= 2, dependsOnMethods = { "LIMSLogin" }, description="Verify the total link count on dashboard ")
	public void dashboard() throws InterruptedException{
		test=extent.createTest("Verify the total link count on dashboard", "Total link in LIMS dashboard should be 6.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		int allLinks = dashboard_LIMS.dashboardLinks();
		Assert.assertEquals(6, allLinks);
	}
	
	@Test(priority= 3,dependsOnMethods = { "LIMSLogin" }, description="Verify user is able to create a new accession for CTC-FISH")
	public void PlaceNewOrder() throws InterruptedException{
		test=extent.createTest("Verify user is able to create a new accession for CTC-FISH", "User should be able to create a acession with CTC-FISH test.");
		test.assignCategory("Accession");
		dashboard_LIMS.clickOnPlaceNewOrder("Place a New Order");
		ExplicitWait.invisibilityOfLoader();
		accession.addLabClientPhysicianInfor();
		accession.addpatientInformation();
		accession.addInsurance();
		accession.addSpecimen();
		accession.addCTCFISHTest();
		accession.saveTest();
		accession.addICDCPTCodes();
		accession.addClinicalInformation();
		accession.saveAccesion();
		ExplicitWait.invisibilityOfLoader();
		boolean SummaryReport = barcodeandLabelBuilder.isSummaryReportAvailable();
		Assert.assertEquals(SummaryReport, true);

	}

	
	@Test(priority= 4,dependsOnMethods = { "PlaceNewOrder" },description="Verify patient name on barcode page")
	public void PatientNameOnBarcodePage() throws InterruptedException{
		test=extent.createTest("Verify patient name on barcode page", "Patient name on barcode page should be same as we enter while accessioning.");
		test.assignCategory("Barcode Page");
		ExplicitWait.invisibilityOfLoader();
		String PatientName = barcodeandLabelBuilder.patientName();
		AccessionID = barcodeandLabelBuilder.getAccessionId();
		barcodeandLabelBuilder.click_On_SummaryReporButton();
		barcodeandLabelBuilder.click_On_ScoreSheetButton();
		barcodeandLabelBuilder.click_On_SampleTravelerButton();
		barcodeandLabelBuilder.click_On_Generate_LongLabelButton();
		barcodeandLabelBuilder.click_On_Generate_SmallLabelButton();
		barcodeandLabelBuilder.click_On_Generate_MolecularLabelButton();
		Assert.assertEquals(PatientName, "TestPatientFN TestPatientLN");
	}
	
	@Test(priority= 5, dependsOnMethods = { "PlaceNewOrder" }, description="Verify user is able to upload the CTC/FISH images")
	public void UploadAccessionImagesPageTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to upload the CTC/FISH images", "User should be able to upload CTC/FISH images from Upload Accession Images page.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Accession Images");
		//String PageTitle = uploadAccessionImages.title();
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.enterAccessionNumber(AccessionID);
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.enumerationImages();
		ExplicitWait.invisibilityOfLoader();
		uploadAccessionImages.fishImages();
		String FileUploadMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(FileUploadMessage, "Image(s) saved successfully");
	}
	
	
	@Test(priority= 6, dependsOnMethods = { "PlaceNewOrder" },description="Verify user is able to open the My Worklist")
	public void MyWorklistTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the My Worklist", "User should be able to open the MY worklist from left menu.");
		test.assignCategory("My Worklist");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		String WorklistTitle = myWorklist.title();
		Assert.assertEquals(WorklistTitle, "My Worklist");
	}
	
	@Test(priority= 7, dependsOnMethods = { "MyWorklistTitle" },description="Verify the accession type when user create an accession without selecting Assign Professional Work checkbox")
	public void AccesstionType() throws InterruptedException{
		test=extent.createTest("Verify the accession type when user create an accession without selecting Assign Professional Work checkbox", "Accession type should be Global.");
		test.assignCategory("My Worklist");
		ExplicitWait.invisibilityOfLoader();
		boolean accesstiontype=myWorklist.accesionType(AccessionID, "Global");
		myWorklist.click_On_PlasmaCSVButton();
		Assert.assertEquals(accesstiontype, true);
	}
		
	
	@Test(priority= 8, dependsOnMethods = { "MyWorklistTitle" },description="Verify user is able to open the Cell Cep Processing Activity")
	public void CellCepProcessingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Cell Cep Processing Activity", "User should be able to open the Cell Cep Processing Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "Cell Sep Processing");
	}
	
	@Test(priority= 9, dependsOnMethods = { "CellCepProcessingActivityTitle" },description="Verify user is able to complete the Cell Cep Processing Activity")
	public void CellCepProcessing() throws InterruptedException{
		test=extent.createTest("erify user is able to complete the Cell Cep Processing Activity", "User should be able to complete the Cell Cep Processing Activity after select Leucosep Tube value.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		cellSeProcessing.selectLeucosepTube();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	@Test(priority= 10,dependsOnMethods = { "CellCepProcessing" },description="Verify user is able to open the Cell Cep Batching Activity")
	public void CellSepBatchingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the cell Cep Batching Activity", "User should be able to open the Cell Cep Batching Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "Cell Sep Batching");
	}
	
	@Test(priority= 11,dependsOnMethods = { "CellSepBatchingActivityTitle" }, description="Verify user is able to complete the Cell Cep Batching Activity")
	public void CellSepBatching() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the Cell Cep Batching Activity", "User should be able to save and complete the Cell Cep Batching Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		cellSepBatching.cellSepInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	
	@Test(priority= 12,dependsOnMethods = { "CellSepBatching" },description="Verify user is able to open the Mems Processing Activity")
	public void MEMSProcessingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Mems Processing Activity", "User should be able to open the Mems Processing from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "MEMS Processing Activity");
	}
	
	@Test(priority= 13,dependsOnMethods = { "MEMSProcessingActivityTitle" },  description="Verify user is able to complete the Mems Processing Activity")
	public void MEMSProcessing() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the Mems Processing Activity", "User should be able to select the CTC/ICCtest and complete the Mems Processing Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		memsProcessingActivity.selectICCTest();
		memsProcessingActivity.selectFISHTest();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	@Test(priority= 14, dependsOnMethods = { "MEMSProcessing" },description="Verify user is able to open the Mems Batching Activity")
	public void MemsBatchingActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Mems Batching Activity", "User should be able to open the Mems Batching Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "MEMS Batching");
	}
	
	@Test(priority= 15,dependsOnMethods = { "MemsBatchingActivityTitle" }, description="Verify user is able to complete the Mems Batching Activity")
	public void MemsBatching() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the Mems Batching Activity", "User should be able to complete the Mems Batching Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		memsBatching.memsInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	@Test(priority= 16, dependsOnMethods = { "MemsBatching" },description="Verify user is able to open the Bioview Scan Data Activity")
	public void BioviewScanDataActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Bioview Scan Data Activity", "User should be able to open the Bioview Scan Data Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "Bioview Scan Data");
	}
	
	@Test(priority= 17,dependsOnMethods = { "BioviewScanDataActivityTitle" }, description="Verify user is able to complete the Bioview Scan Data Activity")
	public void BioviewScanData() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the Bioview Scan Data Activity", "User should be able to save and complete the Bioview Scan Data Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		bioviewScanData.bioviewData();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	
	@Test(priority= 18,dependsOnMethods = { "BioviewScanData" }, description="Verify user is able to open the Enumeration Image Review Activity")
	public void EnumerationImageReviewActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Enumeration Image Review Activity", "User should be able to open the Enumeration Image Review Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "Enumeration Image Review");
	}
	@Test(priority= 19, dependsOnMethods = { "EnumerationImageReviewActivityTitle" },description="Verify user is able to complete the Enumeration Image Review Activity")
	public void EnumerationImageReview() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the Enumeration Image Review Activity", "User should be able to save and complete the Enumeration Image Review Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		enumerationImageReview.enumerationAnalysis();
		enumerationImageReview.enumerationInitials();
		enumerationImageReview.enumerationDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	
	@Test(priority= 20,dependsOnMethods = { "EnumerationImageReview" },  description="Verify user is able to open the CTC ICC Results Summary Activity")
	public void CTCICCResultsSummaryActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the CTC ICC Results Summary Activity", "User should be able to open the CTC ICC Results Summary Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "CTC/ICC Results Summary");
	}
	@Test(priority= 21,dependsOnMethods = { "CTCICCResultsSummaryActivityTitle"}, description="Verify user is able to complete the CTC ICC Results Summary Activity")
	public void CTCICCResultsSummary() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the CTC ICC Results Summary Activity", "User should be able to save and complete the CTC ICC Results Summary Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		ctciccResultsSummary.ctcResult();
		ctciccResultsSummary.ctcTestImage();
		ctciccResultsSummary.iccResult();
		ctciccResultsSummary.iccTestImage();
		ctciccResultsSummary.CompleteButton();
		//workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	@Test(priority= 22, dependsOnMethods = { "CTCICCResultsSummary"}, description="Verify user is able to open the FISH Auto batched worklist Activity")
	public void FISHAutobatchedworklistActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the FISH Auto batched worklist Activity", "User should be able to open the FISH Auto batched worklist Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "FISH Auto batched worklist");
	}
	@Test(priority= 23, dependsOnMethods = { "FISHAutobatchedworklistActivityTitle"},description="Verify user is able to complete the FISH Auto batched worklist Activity")
	public void FISHAutobatchedworklist() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the FISH Auto batched worklist Activity", "User should be able to save and complete the FISH Auto batched worklist Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		fishAutobatchedworklist.fishAutobatchInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	}
	
	@Test(priority= 24,dependsOnMethods = { "FISHAutobatchedworklist"},  description="Verify user is able to open the FISH Relo Analysis Activity")
	public void FISHReloAnalysisActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the FISH Relo Analysis Activity", "User should be able to open the FISH Relo Analysis Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "FISH Relo Analysis");
	}
	@Test(priority= 25,dependsOnMethods = { "FISHReloAnalysisActivityTitle"}, description="Verify user is able to complete the FISH Relo Analysis Activity")
	public void FISHReloAnalysis() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the FISH Relo Analysis Activity", "User should be able to save and complete the FISH Relo Analysis Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		fishReloAnalysis.uploadCSVFile();
		ExplicitWait.invisibilityOfLoader();
		fishReloAnalysis.fishReloInitials();
		workflowCommonMethods.activityStartEndDateTime();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "File Uploaded successfully");
	}
	
	@Test(priority= 26,  dependsOnMethods = { "FISHReloAnalysis"},description="Verify user is able to open the FISH Results Summary Activity")
	public void FISHResultsSummaryActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the FISH Results Summary Activity", "User should be able to open the FISH Results Summary Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "FISH Results Summary");
	}
	@Test(priority= 27, dependsOnMethods = { "FISHResultsSummaryActivityTitle"},description="Verify user is able to complete the FISH Results Summary Activity")
	public void FISHResultsSummary() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the FISH Results Summary Activity", "User should be able to save and complete the FISH Results Summary Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		fishResultsSummary.fishTestComponenet();
		fishResultsSummary.fishResult();
		fishResultsSummary.fishTestImage();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
	
	}
	
	@Test(priority= 28, dependsOnMethods = { "FISHResultsSummary"}, description="Verify user is able to open the TechOnly Professional Interpretation Activity")
	public void PIActivityTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the TechOnly Professional Interpretation Activity", "User should be able to open the TechOnly Professional Interpretation Activity from MY worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID,"CTC-FISH");
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "TechOnly Professional Interpretation");
	}
	@Test(priority= 29, dependsOnMethods = { "PIActivityTitle"},description="Verify user is able to complete the PI Activity")
	public void TechOnlyProfessionalInterpretation() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the PI Activity", "User should be able to save and complete the PI Activity.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		workflowCommonMethods.ctcFishCompleteButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully completed.");
		
	}
	
	@Test(priority= 30, dependsOnMethods = { "TechOnlyProfessionalInterpretation"}, description="Verify user is able to open the QA Reporting Worklist")
	public void QAReportingWorklistTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the QA Reporting Worklist", "User should be able to open the QA Reporting Worklis from Left Menu.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		qaReportingWorklist.openQAWorklist("QA Reporting Worklist");
		ExplicitWait.invisibilityOfLoader();
		String ActivityTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(ActivityTitle, "QA Reporting Worklist");
	}
	
	@Test(priority= 31, dependsOnMethods = { "QAReportingWorklistTitle"}, description="Verify user is able to complete the QA Reporting")
	public void QAReportingWorklist() throws InterruptedException{
		test=extent.createTest("Verify user is able to complete the QA Reporting", "User should be able to do QA reporting for particular record.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		qaReportingWorklist.search(AccessionID,"CTC-FISH");
		qaReportingWorklist.qaReporting();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(4000);
		Assert.assertEquals(ActivityCompleteMessage, "QA Reporting has been completed");
	}
	
	
	@Test(priority= 32, dependsOnMethods = { "QAReportingWorklist"},description="Verify user is able to open FISH Batch Review & Approve Worklist")
	public void FishBatchReviewApproveWorklistTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open FISH Batch Review & Approve Worklist", "User should be able to open the FISH Batch Review & Approve from Left Menu.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("FISH Review & App","Batch");
		ExplicitWait.invisibilityOfLoader();
		String WorklistTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(WorklistTitle, "Batch Review & Approve FISH");
	}	
	
	
	@Test(priority= 33, dependsOnMethods = { "FishBatchReviewApproveWorklistTitle"}, description="Verify user is able to Signoff the CTC-FISH Report from FISH Batch Review & Approve Worklist")
	public void FishReportSignoff_Batch() throws InterruptedException{
		test=extent.createTest("Verify user is able to Signoff the CTC-FISH Report from FISH Batch Review & Approve Worklist", "User should be able to Signoff the CTC-FISH Report from FISH Batch Review & Approve Worklist.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		fishBatchReviewAndApproveWorklist.search(AccessionID);
		ExplicitWait.invisibilityOfLoader();
		fishBatchReviewAndApproveWorklist.reportSignoff();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Thread.sleep(4000);
		Assert.assertEquals(ActivityCompleteMessage, "Report Reviewed & Accession sent for billing successfully.");
	}
	
	
//	@Test(priority= 39, description="Verify user is able to open FISH Review & Approve Worklist")
//	public void FISHReviewApproveTitle() throws InterruptedException{
//		test=extent.createTest("Verify user is able to open FISH Review & Approve Worklist", "User should be able to open the FISH Review & Approve from Left Menu.");
//		test.assignCategory("CTC-FISH Workflow for Global cases");
//		ExplicitWait.invisibilityOfLoader();
//		leftMenuItems.leftMenuSelection("FISH Review & App","Individual");
//		ExplicitWait.invisibilityOfLoader();
//		String WorklistTitle = workflowCommonMethods.pageTitle();
//		Assert.assertEquals(WorklistTitle, "Review & Approve Worklist");
//	}
//	
//	@Test(priority= 40,  description="Verify user is able to Signoff the CTC-FISH Report from FISH Review & Approve Worklist")
//	public void FISHReportSignoff() throws InterruptedException{
//		test=extent.createTest("Verify user is able to Signoff the CTC-FISH Report from FISH Review & Approve Worklist", "User should be able to Signoff the CTC-FISH Report from FISH Review & Approve Worklist.");
//		test.assignCategory("CTC-FISH Workflow for Global cases");
//		ExplicitWait.invisibilityOfLoader();
//		fishReviewAndApproveWorklist.search(AccessionID, "CTC-FISH");
//		fishReviewAndApproveWorklist.reportSignoff();
//		ExplicitWait.invisibilityOfLoader();
//		Thread.sleep(4000);
//		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
//		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");
//	}
	//////////////////
	
	@Test(priority= 34,dependsOnMethods = { "FishReportSignoff_Batch"}, description="Verify user is able to open the Completed Worklist")
	public void CompletedWorklist() throws InterruptedException{
		test=extent.createTest("Verify user is able to open the Completed Worklist", "User should be able to open the Completed Worklist from left menu.");
		test.assignCategory("Completed Worklist");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","Completed Worklist");
		String WorklistTitle = myWorklist.title();
		Assert.assertEquals(WorklistTitle, "Completed Worklist");
	}
	
	@Test(priority= 35,  dependsOnMethods = { "CompletedWorklist"},description="Verify the functionality when user tries to reopen the case from completed worklist")
	public void ConfirmationPopupOnCompletedWorklist() throws InterruptedException{
		test=extent.createTest("Verify the functionality when user tries to reopen the case from completed worklist", "A confirmation popup for reopening the case should be open.");
		test.assignCategory("Completed Worklist");
		ExplicitWait.invisibilityOfLoader();
		completedWorklist.search(AccessionID, "CTC-FISH");
		String ConfirmationMessage =completedWorklist.confirmationMessage();
		Assert.assertEquals(ConfirmationMessage, "Are you sure, you want to reopen this report? This will require a new report to be created and issued.");
	}
	
	@Test(priority= 36,  dependsOnMethods = { "ConfirmationPopupOnCompletedWorklist"},description="Verify the activity after reopen the case from completed worklist")
	public void ActivityTitleAfterReOpen() throws InterruptedException{
		test=extent.createTest("Verify the activity after reopen the case from completed worklist", "TechOnly Professional Interpretation page should be open.");
		test.assignCategory("Completed Worklist");
		completedWorklist.accecptConfirmation();
		ExplicitWait.invisibilityOfLoader();
		String pageTitle=workflowCommonMethods.pageTitle();
	    workflowCommonMethods.ctcFishCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		qaReportingWorklist.openQAWorklist("QA Reporting Worklist");
		ExplicitWait.invisibilityOfLoader();
		qaReportingWorklist.search(AccessionID,"CTC-FISH");
		qaReportingWorklist.qaReporting();
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(4000);
		Assert.assertEquals(pageTitle, "TechOnly Professional Interpretation");
	}
		
	@Test(priority= 37,dependsOnMethods = { "ActivityTitleAfterReOpen"}, description="Verify user is able to open FISH Review & Approve Worklist")
	public void FishReviewApproveTitle() throws InterruptedException{
		test=extent.createTest("Verify user is able to open FISH Review & Approve Worklist", "User should be able to open the FISH Review & Approve from Left Menu.");
		test.assignCategory("CTC-FISH Workflow for Global cases");
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("FISH Review & App","Individual");
		ExplicitWait.invisibilityOfLoader();
		String WorklistTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(WorklistTitle, "Review & Approve Worklist");
	}
	
	
	@Test(priority= 38, dependsOnMethods = { "FishReviewApproveTitle"}, description="Verify the functionality when user tries to signoff the report again")
	public void ReportVersionSelectionPopUp() throws InterruptedException{
		test=extent.createTest("Verify the functionality when user tries to signoff the report again", "A popup should be open to select the report version.");
		test.assignCategory("Report Versioning");
		fishReviewAndApproveWorklist.search(AccessionID, "CTC-FISH");
		fishReviewAndApproveWorklist.reportSignoff();
		String PopupTitle=fishReviewAndApproveWorklist.reportVersionSelectionPopup();
		Assert.assertEquals(PopupTitle, "Report Version Selection");
	}
	
	@Test(priority= 39, dependsOnMethods = { "ReportVersionSelectionPopUp"}, description="Verify user is able to create a new version of report")
	public void SignoffTheReportForNewVersion() throws InterruptedException{
		test=extent.createTest("Verify user is able to create a new version of report", "user should be able to signoff the report after select the Version Type and entering the reason.");
		test.assignCategory("Report Versioning");
		fishReviewAndApproveWorklist.reportVersionType("Amended");
		fishReviewAndApproveWorklist.reason("Testing");
		fishReviewAndApproveWorklist.clickOnSaveChanges();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");

	}
	
	@Test(priority= 40,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download CTC-FISH Summary Report")
	public void CTC_FISH_SummaryReport() throws InterruptedException{
		test=extent.createTest("Verify user is able to download CTC-FISH Summary Report", "CTC-FISH Summary Report file should be downloaded after click on CTC-FISH Summary Report button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("CTC-FISH_Summary_Report");
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 41,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download CTC-FISH Score Sheet")
	public void CTC_FISH_ScoreSheet() throws InterruptedException{
		test=extent.createTest("Verify user is able to download CTC-FISH Score Sheet", "CTC-FISH Score Sheet file should be downloaded after click on CTC-FISH Score Sheet button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("CTC-FISH_Score_Sheet");
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 42,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download CTC-FISH Sample Traveler")
	public void CTC_FISH_SampleTraveler() throws InterruptedException{
		test=extent.createTest("Verify user is able to download CTC-FISH Sample Traveler", "CTC-FISH Sample Traveler file should be downloaded after click on CTC-FISH Sample Traveler button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("CTC-FISH_Sample_Traveler");
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 43,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download Generate Long Label(s)")
	public void GenerateLongLabel() throws InterruptedException{
		test=extent.createTest("Verify user is able to download Generate Long Label(s)", "Long Label(s) file should be downloaded after click on Generate Long Label(s) button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("Bar_"+AccessionID);
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 44,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download Generate Small Label")
	public void GenerateSmallLabel() throws InterruptedException{
		test=extent.createTest("Verify user is able to download Generate Small Label", "Small Label file should be downloaded after click on Generate Small Label button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("Bar_"+AccessionID);
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 45,dependsOnMethods = { "PlaceNewOrder"},description="Verify user is able to download Generate Molecular Label")
	public void GenerateMolecularLabel() throws InterruptedException{
		test=extent.createTest("Verify user is able to download Generate Molecular Label", "Molecular Label file should be downloaded after click on Generate Molecular Label button.");
		test.assignCategory("Barcode Page");
		boolean found= DownloadedFiles.verify("Bar_"+AccessionID);
		Assert.assertEquals(found, true);
	}
	
	@Test(priority= 46, dependsOnMethods = { "MyWorklistTitle"},description="Verify the download functionality for PlasmaCSV")
	public void DownloadPlasmaCSV() throws InterruptedException{
		test=extent.createTest("Verify the download functionality for PlasmaCSV", "PlasmaCSV file should be downloaded after click on Plasma CSV button.");
		test.assignCategory("My Worklist");
		boolean found=DownloadedFiles.verify("HamiltonPlasmaCsv");
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(found, true);	
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
	

//@BeforeTest
//public void startReport()
//{
//	    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testing.html");
//	    extent = new ExtentReports();
//	    extent.attachReporter(htmlReporter);
//	    
//	    extent.setSystemInfo("Project Name", "Biocept");
//	    extent.setSystemInfo("Environment", "Hotfix");
//	    extent.setSystemInfo("Operating Systems", "Window 10");
//	    extent.setSystemInfo("User Name", "Amarendra Singh");
//	    htmlReporter.config().setChartVisibilityOnOpen(true);
//	    //htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
//	    htmlReporter.config().setReportName("Smoke Testing");
//	    htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//	    htmlReporter.config().setTheme(Theme.DARK);
//}
//@AfterTest
//public void tearDown()
//{
//    extent.flush();
//}
	
	
	
	
	
	
	
@AfterClass
	public void LogOut(){
	loginAndLogOut.LIMSLogOut();
	}
	

}
