package biocept.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class OrderHistory extends BioceptBase{
	
	@FindBy(tagName="h3")
	WebElement title;
	
	@FindBy(css="input[aria-controls='dataTable']")
	WebElement searchElement;
	
	@FindBy(css="button[onclick*='RedirectUser']")
	WebElement techOnlyProfessionalInterpretationEle ;
	
	
	
	
	public OrderHistory(){
		PageFactory.initElements(driver, this);
	}
	
	public String pageTitle(){
		return title.getText();
	}
	
	public void search (String AccessionID){
		ExplicitWait.waitUntilElementToBeVisible(searchElement).sendKeys(AccessionID);
		
	}
	
	public boolean techOnlyProfessionalInterpretationButton(){
		return techOnlyProfessionalInterpretationEle.isDisplayed();
	}
	
	public void clickOnTechOnlyProfessionalInterpretationButton(){
		ExplicitWait.waitUntilElementToBeVisible(techOnlyProfessionalInterpretationEle).click();
		
		 ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(1));
		    ExplicitWait.invisibilityOfLoader();
	}
	
	
	
}
