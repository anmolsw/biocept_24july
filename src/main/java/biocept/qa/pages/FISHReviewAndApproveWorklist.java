package biocept.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class FISHReviewAndApproveWorklist extends BioceptBase {
	
	String message;
	
	@FindBy(id="titleHeader")
	WebElement titleHeader;
	
	@FindBy(css ="input[type='search']")
	WebElement searchElement;
	
	@FindBy(xpath ="//*[@id='queueResults']/tbody/tr[1]/td[9]/a")
	WebElement testNameElement;
	
	@FindBy(tagName ="tbody")
	WebElement table;	
	
	@FindBy(xpath ="//*[@id='panel-default']/div[1]/strong")
	WebElement reportPreviewElement;
	
	
	@FindBy(className ="fa-check")
	WebElement review;
	
	@FindBy(css ="button[data-bind*='SignOff']")
	WebElement signoff_Element;
	
	
	@FindBy(css ="h4[id='myModalLabel']")
	WebElement popUpMessageEle;

	
	@FindBy(css ="select[data-bind*='ReportVersionTypes()']")
	WebElement reportVersionEle;

	@FindBy(css ="textarea[placeholder='Enter a Reason...']")
	WebElement resonElement;
	
	@FindBy(css ="button[class='btn btn-primary']")
	WebElement saveButton;
	

	
	
	public  FISHReviewAndApproveWorklist(){
		PageFactory.initElements(driver, this);
	}
	

	
	public void search (String AccessionID, String TestName) throws InterruptedException{
		ExplicitWait.waitUntilElementToBeVisible(searchElement).sendKeys(AccessionID);
		Thread.sleep(2000);
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeVisible(testNameElement).click();
		
}
	
	
	public void reportSignoff(){
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeVisible(reportPreviewElement);
		ExplicitWait.waitUntilElementToBeVisible(review);
		ExplicitWait.waitUntilElementToBeClickable(review).click();
		ExplicitWait.waitUntilElementToBeVisible(signoff_Element).click();
		

		}
	
	public String reportVersionSelectionPopup(){

		ExplicitWait.waitUntilElementToBeVisible(popUpMessageEle);
		String message = popUpMessageEle.getText();
		return message;
		
		}
	
	public void reportVersionType(String Type){
		try{
		Select version = new Select (reportVersionEle);
		version.selectByVisibleText(Type);
		}catch(Exception e)
		{
			}
		}
	
	public void reason(String Reason){
		 resonElement.sendKeys(Reason);
		}
	
	
	public void clickOnSaveChanges(){
		saveButton.click();
		}
//	public String distributionConfirmationMessage(){
//		
//		ExplicitWait.invisibilityOfLoader();
//		message=ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
//		System.out.println("Message 1+++"+ message);
//		while(message!="Automated distribution has been triggered for this Report"){
//			String message1 = ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
//			System.out.println("Message 2+++"+ message1);
//			message = message.replace(message1, message);
//			if(message1.contains("Automated distribution has been triggered for this Report")){
//				
//			}
//		}
//		return message;
//		
//	}
}
