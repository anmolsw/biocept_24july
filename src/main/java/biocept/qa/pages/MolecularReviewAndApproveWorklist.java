package biocept.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class MolecularReviewAndApproveWorklist extends BioceptBase{
	
	
	@FindBy(className ="fa-check")
	WebElement review;
	
	@FindBy(css ="button[data-bind*='SignOff']")
	WebElement signOff;
	
	@FindBy(xpath ="//*[@id='panel-default']/div[1]/strong")
	WebElement reportPreview;
	
	
	public  MolecularReviewAndApproveWorklist(){
		PageFactory.initElements(driver, this);
	}
	
	public void reportSignoff(){
		ExplicitWait.invisibilityOfLoader();
		try{
		ExplicitWait.waitUntilElementToBeVisible(reportPreview);
		ExplicitWait.waitUntilElementToBeVisible(review);
		ExplicitWait.waitUntilElementToBeClickable(review);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", review);
		ExplicitWait.waitUntilElementToBeClickable(signOff).click();
		
		
		}catch(StaleElementReferenceException e){
			
			ExplicitWait.waitUntilElementToBeVisible(reportPreview);
			ExplicitWait.waitUntilElementToBeVisible(review);
			ExplicitWait.waitUntilElementToBeClickable(review);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", review);
			ExplicitWait.waitUntilElementToBeClickable(signOff).click();
			}catch(Exception  e){
				
			}
		}
	

}
