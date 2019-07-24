package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class CompletedWorklist extends BioceptBase{
	@FindBy(xpath ="//*[@id='dataTable_filter']/label/input")
	WebElement searchElement;
	
	@FindBy(css ="a[onclick*='CTC-FISH']")
	List <WebElement> testNumber;
	
	@FindBy(className ="bootbox-body")
	WebElement popUp;
	@FindBy(css ="button[class*='btn-success']")
	WebElement accecptButton;
	

	
	public CompletedWorklist(){
		PageFactory.initElements(driver, this);
	}
	
	public void search (String AccessionID, String TestName) throws InterruptedException{
		ExplicitWait.waitUntilElementToBeVisible(searchElement).sendKeys(AccessionID);
		Thread.sleep(2000);
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilAllElementToBeVisible(testNumber);
		for (int i=0;i< testNumber.size();i++){
			WebElement testNumberElement = testNumber.get(i);
			String testName = testNumberElement.getText();
			if(testName.contains(TestName)){	
				ExplicitWait.waitUntilElementToBeClickable(testNumberElement).click();
				break;
			}		
		}
	}
	
	public String confirmationMessage() throws InterruptedException{
	
		String message = ExplicitWait.waitUntilElementToBeVisible(popUp).getText();
		return message;
	}
	
	public void accecptConfirmation(){
		ExplicitWait.waitUntilElementToBeClickable(accecptButton).click();
		
	}
	
}
