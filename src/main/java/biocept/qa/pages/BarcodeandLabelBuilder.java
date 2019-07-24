package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.DownloadedFiles;
import biocept.qa.utill.ExplicitWait;

public class BarcodeandLabelBuilder extends BioceptBase{
	
	@FindBy(tagName ="h4")
	WebElement patientName;
	
	@FindBy(css ="span[data-bind='text: $root.Accession().AccessionNumber']")
	WebElement accessionID;
	
	@FindBy(css ="button[data-bind*='CTC-FISH Summary Report Doc']")
	WebElement summaryReport;
	
	@FindBy(css ="button[data-bind*='CTC-FISH Score Sheet']")
	WebElement scoreSheet;
	
	@FindBy(css ="button[data-bind*='CTC-FISH Sample Traveler']")
	WebElement sampleTraveler;
	
	@FindBy(css ="button[data-bind*='long']")
	WebElement generate_LongLabel;
	
	@FindBy(css ="button[data-bind*='small']")
	WebElement generate_SmallLabel;
		
	@FindBy(css ="button[data-bind*='molecular']")
	WebElement generate_MolecularLabel;
	
	public BarcodeandLabelBuilder(){
		PageFactory.initElements(driver, this);
	}
	
	public String patientName(){
		String PatientName = patientName.getText();
		return PatientName;
	}
	
	public String getAccessionId(){
		String AccessionID = accessionID.getText();
		return AccessionID;
		
		
	}
	
	public boolean isSummaryReportAvailable(){
	
		return summaryReport.isDisplayed();
	}
	
	public void click_On_SummaryReporButton(){
		DownloadedFiles.delete("CTC-FISH_Summary_Report");
		ExplicitWait.waitUntilElementToBeClickable(summaryReport).click();
		
	}	
	
	public void click_On_ScoreSheetButton(){
		
		DownloadedFiles.delete("CTC-FISH_Score_Sheet");
		ExplicitWait.waitUntilElementToBeClickable(scoreSheet).click();
		
	}
	
	public void click_On_SampleTravelerButton(){

		DownloadedFiles.delete("CTC-FISH_Sample_Traveler");
		ExplicitWait.waitUntilElementToBeClickable(sampleTraveler).click();
	}
	
	public void click_On_Generate_LongLabelButton(){
		
		ExplicitWait.waitUntilElementToBeClickable(generate_LongLabel).click();
		
	}	
	
	public void click_On_Generate_SmallLabelButton(){
		ExplicitWait.waitUntilElementToBeClickable(generate_SmallLabel).click();
		
	}
	
	public void click_On_Generate_MolecularLabelButton(){
		ExplicitWait.waitUntilElementToBeClickable(generate_MolecularLabel).click();
	}


	public void wait_Until_SampleTraveler_ToBeClickable() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//boolean available= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-bind*='CTC-FISH Sample Traveler']"))).isDisplayed();
	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-bind*='CTC-FISH Sample Traveler']")));
	Thread.sleep(6000);
		
	}
	
	public void wait_Until_Generate_MolecularLabelButton_ToBeAvailable() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//boolean available= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-bind*='CTC-FISH Sample Traveler']"))).isDisplayed();
	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-bind*='molecular']")));
	Thread.sleep(4000);
		
	}

}
