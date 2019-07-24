package biocept.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;



public class Accession extends BioceptBase{
	

	
	////Lab / Client / Physician Information////
	@FindBy(className ="requisition-number")
	WebElement requisitionNumber;
	@FindBy(css ="input[data-bind='value: CancerType']")
	WebElement cancerType;
	@FindBy(css ="input[data-bind='textInput:$root.Accession().ClientAccessionId']")
	WebElement clientAccessionId;
	@FindBy(css ="input[data-bind='value: FedexTracking']")
	WebElement fedexTracking;
	@FindBy(css ="select[data-bind*='optionsText: 'LabName'']")
	WebElement lab;
	
	@FindBy(id ="facility")
	WebElement Ordering_Location;
	@FindBy(id ="physician")
	WebElement Ordering_Provider;
//	/////Patient Information/////
	@FindBy(css ="input[placeholder='First Name']")
	WebElement patientFN;
	@FindBy(css ="input[placeholder='Last Name']")
	WebElement patientLN;
	@FindBy(css ="input[placeholder='Date of Birth']")
	WebElement patientDOB;
	@FindBy(css ="select[data-bind*='value: GenderId']")
	WebElement patientGender;
	@FindBy(id ="countryId")
	WebElement patientCountry;
	@FindBy(css ="input[placeholder='Address Line 1']")
	WebElement patientAddress;
//	
//	/////Insurance/////
	@FindBy(linkText ="Add Insurance")
	WebElement addInsurance;
	@FindBy(css ="input[placeholder='Payor Name']")
	WebElement payorName;
	@FindBy(id ="relationshipId")
	WebElement patientRelationship;
//	
//	/////Specimen / Test/////
	@FindBy(css ="button[data-bind*='EditSpecimen']")
	WebElement addSpecimen;
	@FindBy(css ="input[data-bind*='ExternalSpecimenId']")
	WebElement externalSpecimenId;
	
	@FindBy(id ="autoSpecimenTypes")
	WebElement addSpecimenType;
	@FindBy(id ="autoBodySite")
	WebElement addBodySite;
	@FindBy(id ="autoTransportType")
	WebElement addTransportType;
	@FindBy(id ="collectionTime")
	WebElement addcollectionTime;
	@FindBy(id ="txtReceivedDate")
	WebElement addReceivedDate;
	@FindBy(id ="autoUnitOfMeasurement")
	WebElement addUnitOfMeasurement;
	@FindBy(id ="btn-AddNew")
	WebElement addTubes;
	@FindBy(css ="tbody[data-bind='foreach: SpecimenTubes']")
	WebElement SpecimenTubes;	
	@FindBy(xpath ="//*[@id='addSpecimenModal']/div[2]/div/div[3]/button[2]")
	WebElement saveSpecimen;
	@FindBy(className ="test-assign")
	WebElement testassign;
	@FindBy(css ="input[value='Search Tests']")
	WebElement searchTests;	
	@FindBy(className ="todo-title")
	List<WebElement> childTest;
	
	@FindBy(css ="button[data-bind='click: $root.SaveTestAndPanelConfiguration']")
	WebElement saveTestButton;
	
	
	
////////AssignProfessionalWork
	@FindBy(css ="button[data-bind*='SetupAssignProfessionalWork']")
	WebElement assignProfessionalWork_Button;
	
	@FindBy(id ="chkAssignProfessionalWork")
	WebElement assignProfessionalWork_Checkbox;
	
	@FindBy(id ="CloseBtnAssignProfessionalWork")
	WebElement assignProfessionalWork_CloseButton;

	
/////Add ICD 10 and CPT Code///
	@FindBy(id ="icd10CodeSelect")
	WebElement icd10Code;
	@FindBy(id ="cptCodeSelect")
	WebElement cptCode;
	
	//////Special Instructions/Clinical Information/Treatment Status/Cancer State/Comments////
	@FindBy(css ="textarea[data-bind='value:SpecialInstructions']")
	WebElement specialInstructions;
	@FindBy(css ="textarea[data-bind='value:ClinicalInformation']")
	WebElement clinicalInformation;
	@FindBy(css ="input[data-bind='value: TreatmentStatus']")
	WebElement treatmentStatus;
	@FindBy(css ="input[data-bind='value: CancerState']")
	WebElement cancerState;
	@FindBy(css ="input[placeholder='Add Comment ..']")
	WebElement addComment;
	
	@FindBy(id ="btnSaveAccession")
	WebElement saveAccession;
	
	
	WebDriverWait wait=new WebDriverWait(driver, 30);
	
public Accession(){

	PageFactory.initElements(driver, this);
}

public void addLabClientPhysicianInfor () throws InterruptedException {
	
	wait.until(ExpectedConditions.elementToBeClickable(requisitionNumber)).sendKeys(accessionprop.getProperty("requisitionNumber"));
	cancerType.sendKeys(accessionprop.getProperty("cancerType"));
	clientAccessionId.sendKeys(accessionprop.getProperty("clientAccessionId"));
	fedexTracking.sendKeys(accessionprop.getProperty("fedexTracking"));
	Thread.sleep(1000);
	Ordering_Location.sendKeys(accessionprop.getProperty("Ordering_Location"));
	Ordering_Location.sendKeys(Keys.TAB);
	Ordering_Provider.sendKeys(accessionprop.getProperty("Ordering_Provider"));
	Ordering_Provider.sendKeys(Keys.TAB);
	
}
	
public void addpatientInformation () {
	
	patientFN.sendKeys(accessionprop.getProperty("patientFN"));
	patientLN.sendKeys(accessionprop.getProperty("patientLN"));
	patientLN.sendKeys(Keys.TAB);
	
	patientDOB.sendKeys(Keys.CONTROL + "a");
	
//	for (int i=0;i<10;i++){
//		patientDOB.sendKeys(Keys.BACK_SPACE);
//	}
	
	patientDOB.sendKeys(accessionprop.getProperty("patientDOB"));
	patientDOB.sendKeys(Keys.TAB);
	Select Gender = new Select(patientGender);
	Gender.selectByVisibleText(accessionprop.getProperty("patientGender"));
	Select Country = new Select(patientCountry);
	Country.selectByVisibleText(accessionprop.getProperty("patientCountry"));
	patientAddress.sendKeys(accessionprop.getProperty("patientAddress"));
}

public void addInsurance () {
	
	
	//addInsurance.click();
	wait.until(ExpectedConditions.elementToBeClickable(addInsurance)).click();
	payorName.sendKeys(accessionprop.getProperty("payorName"));
	payorName.sendKeys(Keys.TAB);
	Select Relationship = new Select(patientRelationship);
	Relationship.selectByVisibleText("self");
	
}

public void addSpecimen () throws InterruptedException{


	wait.until(ExpectedConditions.elementToBeClickable(addSpecimen)).click();
	
	WebElement ExtSpecimenId = wait.until(ExpectedConditions.elementToBeClickable(externalSpecimenId));
	ExtSpecimenId.click();
	ExtSpecimenId.sendKeys(accessionprop.getProperty("externalSpecimenId"));
	Thread.sleep(2000);
	addSpecimenType.sendKeys(accessionprop.getProperty("specimenType"));
	addSpecimenType.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(addBodySite)).click();
	addBodySite.sendKeys(accessionprop.getProperty("bodySite"));
	addBodySite.sendKeys(Keys.ENTER);
	wait.until(ExpectedConditions.elementToBeClickable(addTransportType)).click();
	addTransportType.sendKeys(accessionprop.getProperty("transportType"));
	addTransportType.sendKeys(Keys.ENTER);
	addTransportType.sendKeys(Keys.TAB);
	
	addcollectionTime.sendKeys(Keys.CONTROL + "a");
	addcollectionTime.sendKeys(accessionprop.getProperty("collectionTime"));
	
	addcollectionTime.sendKeys(Keys.TAB);
	addReceivedDate.sendKeys(Keys.CONTROL + "a");
	addReceivedDate.sendKeys(accessionprop.getProperty("receivedDate"));
	addReceivedDate.sendKeys(Keys.TAB);
	addUnitOfMeasurement.sendKeys(accessionprop.getProperty("UnitOfMeasurement"));
	addUnitOfMeasurement.sendKeys(Keys.ENTER);
	 Thread.sleep(1000);
	//String totalTube = accessionprop.getProperty("totalTube");
	int totalTube = Integer.parseInt(accessionprop.getProperty("totalTube"));
	for (int i = 0; i < totalTube; i++) {
		addTubes.click();
			
    }
	
	List<WebElement> SpecimenTubesChild = SpecimenTubes.findElements(By.tagName("tr"));
	 for (int i = 0; i < SpecimenTubesChild.size(); i++) {
		 
		 WebElement tubesChildEle = SpecimenTubesChild.get(i);	
			WebElement tubeVolume = tubesChildEle.findElement((By.cssSelector("input[data-bind='value:TubeVolume']")));
			 tubeVolume.sendKeys(accessionprop.getProperty("tubeVolume"));
			WebElement tubeLotNumber = tubesChildEle.findElement(By.cssSelector("input[data-bind='value:TubeLotNumber"));
			tubeLotNumber.sendKeys(accessionprop.getProperty("tubeLotNumber"));
			WebElement tubeType = tubesChildEle.findElement(By.cssSelector("input[data-bind='value:TubeType']"));
			tubeType.sendKeys(accessionprop.getProperty("tubeType"));
     }

	 wait.until(ExpectedConditions.elementToBeClickable(saveSpecimen)).click();

	 
	WebElement TestAssign = wait.until(ExpectedConditions.visibilityOf(testassign));
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].click();", TestAssign);
	 Thread.sleep(2000);
}
	 public void addCTCFISHTest (){
		 
		 searchTests.sendKeys("CTC-FISH");
		 searchTests.sendKeys(Keys.ENTER);	
	 
		 String getTests = accessionprop.getProperty("fishTesst");
			String[] afterSplit = getTests.split(",");
			List<String> fixedLenghtList = Arrays.asList(afterSplit);
			ArrayList<String> CTCFISH_Tests = new ArrayList<String>(fixedLenghtList);
			int Total_CTCFISH_Tests = CTCFISH_Tests.size();
		        int P =1;
		        while(P<= Total_CTCFISH_Tests){
		        //String CurrentTest = CTCFISH_Tests[P-1];
		        	String CurrentTest = CTCFISH_Tests.get(P-1);
		        int availableTestCount = childTest.size();        
		        for (int i = 0; i < availableTestCount; i++) {
		            WebElement childTestElement = childTest.get(i);
		            String childTestName= childTestElement.getText();        
		            if(childTestName.equalsIgnoreCase(CurrentTest)){
		                   boolean checkboxstate =  driver.findElement(By.id("childTest"+i)).isEnabled();
		                    if (checkboxstate == true) {
		                        driver.findElement(By.id("childTest"+i)).click();
		                     }
		            }
		        }
		        P=P+1;
		        }
	 }
	 
	 
	 public void addBRAFTest () throws InterruptedException{
		 searchTests.sendKeys("BRAF");
		 searchTests.sendKeys(Keys.ENTER);
		 Thread.sleep(1000);
	 }
	 public void addKRASTest () throws InterruptedException{
		 searchTests.sendKeys("KRAS");
		 searchTests.sendKeys(Keys.ENTER);	
		 Thread.sleep(1000);
	 }
	 public void addMolEGFRTest () throws InterruptedException{
		 searchTests.sendKeys("MolEGFR");
		 searchTests.sendKeys(Keys.ENTER);
		 Thread.sleep(1000);
	 }
	 
	 public void addNRASest () throws InterruptedException{
		 searchTests.sendKeys("NRAS");
		 searchTests.sendKeys(Keys.ENTER);
		 Thread.sleep(1000);
	 }
	 
	 public void saveTest(){
		saveTestButton.click();
	 }
	      
	 public void assignProfessionalWork(){
		 ExplicitWait.waitUntilElementToBeClickable(assignProfessionalWork_Button).click();
		 ExplicitWait.waitUntilElementToBeClickable(assignProfessionalWork_Checkbox).click();
		 ExplicitWait.waitUntilElementToBeClickable(assignProfessionalWork_CloseButton).click();
		
	 }    
	 
	 


public void addICDCPTCodes () throws InterruptedException{
	
	 icd10Code.sendKeys(accessionprop.getProperty("icd10Code"));
	 icd10Code.sendKeys(Keys.ENTER);
	 cptCode.sendKeys(accessionprop.getProperty("cptCode"));
	 cptCode.sendKeys(Keys.ENTER);
	}

public void addClinicalInformation () throws InterruptedException{
	
	
	specialInstructions.sendKeys(accessionprop.getProperty("specialInstructions"));
	clinicalInformation.sendKeys(accessionprop.getProperty("clinicalInformation"));
	treatmentStatus.sendKeys(accessionprop.getProperty("treatmentStatus"));
	cancerState.sendKeys(accessionprop.getProperty("cancerState"));
	addComment.sendKeys(accessionprop.getProperty("addComment"));
	addComment.sendKeys(Keys.ENTER);
}
public void saveAccesion (){
	saveAccession.click();
}


}
