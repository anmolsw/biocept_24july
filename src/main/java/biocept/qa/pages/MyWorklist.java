package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.DownloadedFiles;
import biocept.qa.utill.ExplicitWait;

public class MyWorklist extends BioceptBase {
	
	String ActivityStatus;
	String ActivityCurrentStatus; 
	String Status;
	
	
	@FindBy(id="titleHeader")
	WebElement titleHeader;
	
	@FindBy(xpath ="//*[@id='dataTable_filter']/label/input")
	WebElement searchElement;
	
	@FindBy(tagName ="tbody")
	WebElement table;	
	
	@FindBy(css ="a[href*='/Orders/Accession/']")
	List <WebElement> accessionID;
	
	@FindBy(css ="a[href*='testinstancelable']")
	List <WebElement> testNumber;
	
	@FindBy(tagName ="td")
	List <WebElement> activityStatus;
	
	@FindBy(css ="td:nth-child(12)")
	List <WebElement> lOS_Column;
	
	@FindBy(id ="cbselectallPlasma")
	WebElement selectallPlasma;
	
	@FindBy(id ="btn-Plasma-Export")
	WebElement plasmaCsvButton;
	
	public  MyWorklist(){
		PageFactory.initElements(driver, this);
	}
	
	public String title (){
		ExplicitWait.invisibilityOfLoader();
		return  titleHeader.getText();
	}
	
	public void selectallPlasmaCheckbox(){
		
	}
	
	
	public void search (String AccessionID, String TestName) throws InterruptedException{
		ExplicitWait.waitUntilElementToBeVisible(searchElement).sendKeys(AccessionID);
		Thread.sleep(2000);
		ExplicitWait.invisibilityOfLoader();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(testNumber));
		for (int i=0;i< testNumber.size();i++){
			WebElement testNumberElement = testNumber.get(i);
			String testName = testNumberElement.getText();
			if(testName.contains(TestName)){	
				ExplicitWait.waitUntilElementToBeClickable(testNumberElement).click();
				break;
			}
					
		}
	}
	
	
	public String molecularActivityStatus (String AccessionID, String ActivityName) throws InterruptedException{
		ExplicitWait.waitUntilElementToBeVisible(searchElement).sendKeys(AccessionID);
		Thread.sleep(2000);
		ExplicitWait.invisibilityOfLoader();
		boolean found= false;
		for (int i=0;i< activityStatus.size();i++){
			WebElement testNumberElement = activityStatus.get(i);
			ActivityCurrentStatus = testNumberElement.getText();
			if(ActivityCurrentStatus.contains(ActivityName)){
				
				found = true;
			
			}if(found){
				break;
		}
		}return ActivityCurrentStatus;
		
	}
		
			
	public void molecularSearch (String AccessionID, String TestName, String ActivityName, String ActivityStatus ) throws InterruptedException{

		Status = molecularActivityStatus(AccessionID, ActivityName);
		if(Status.equalsIgnoreCase(ActivityStatus)){
			searchElement.clear();
			ExplicitWait.invisibilityOfLoader();
			search(AccessionID, TestName);
		}
		else {
		while(Status!=ActivityStatus){
			driver.navigate().refresh();
			ExplicitWait.invisibilityOfLoader();
			String Status1 = molecularActivityStatus(AccessionID, ActivityName);
			Status = Status.replace(Status1, Status);
			if(Status1.equalsIgnoreCase(ActivityStatus)){
				searchElement.clear();
				ExplicitWait.invisibilityOfLoader();
				search(AccessionID, TestName);
				break;
			}
		}
	}
		
		
	}
	
	
	public boolean accesionType (String AccessionID, String Value) throws InterruptedException{

		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(accessionID));
		boolean found= false;
		for (int i=0;i< accessionID.size();i++){
			WebElement accessionElement = accessionID.get(i);
			String accession = accessionElement.getText();
			for (int j=0;j< lOS_Column.size();j++){
				WebElement losElement = lOS_Column.get(j);
				String los = losElement.getText();
				if(accession.equalsIgnoreCase(AccessionID) && los.equalsIgnoreCase(Value)){
					found = true;
				}
			
			}if(found){
			break;
			}
	}
		return found;
	
	}

	public void click_On_PlasmaCSVButton(){
		DownloadedFiles.delete("HamiltonPlasmaCsv");
		ExplicitWait.waitUntilElementToBeClickable(selectallPlasma).click();
		ExplicitWait.waitUntilElementToBeClickable(plasmaCsvButton).click();
	
	}
}
