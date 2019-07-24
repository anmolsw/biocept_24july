package biocept.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class TechOnlyProfessionalInterpretation extends BioceptBase{
	
	@FindBy(css="a[data-bind*='ReviewAndApprove']")
	WebElement reviewAndApproveTab;
	
	@FindBy(css="input[data-bind*=ReviewedCTCResult]")
	WebElement review;	
	
	@FindBy(css="button[data-bind*='ApproveCtcResults']")
	WebElement qcComplete;
	
	@FindBy(css="div[class='bootbox-body']")
	WebElement confirmationMessageEle;
	
	@FindBy(css="button[data-bb-handler='confirm']")
	WebElement okButtonEle;
	
	@FindBy(css="input[value='Go Back to Physician Portal']")
	WebElement goBacktoPhysicianPortalEle;
	
	@FindBy(css="input[data-bind*=Reviewed]")
	WebElement review_Clientportal;	
	
	@FindBy(css="button[data-bind*='click:SignOff")
	WebElement signOffEle;

	
	public TechOnlyProfessionalInterpretation(){
		PageFactory.initElements(driver, this);
	}
	
	public void fishReviewTab(){
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeClickable(reviewAndApproveTab).click();
	
	}
	
	public boolean qcCompleteButton(){
		return  qcComplete.isDisplayed();
	}
	
	public String qccompleteConfirmation() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		review.click();
		ExplicitWait.waitUntilElementToBeClickable(qcComplete).click();
		Thread.sleep(1000);
		return confirmationMessageEle.getText();
		
	}
	
	public void clickOnOkButton(){
		okButtonEle.click();
		
		
	}
	
	public boolean goBacktoPhysicianPortalButton(){
		
		return goBacktoPhysicianPortalEle.isDisplayed();
	}
	
	public void signOff(){
		review_Clientportal.click();
		ExplicitWait.waitUntilElementToBeClickable(signOffEle).click();
		
	}
	

}
