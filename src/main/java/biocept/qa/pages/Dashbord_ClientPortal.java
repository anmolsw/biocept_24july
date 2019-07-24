package biocept.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;

public class Dashbord_ClientPortal extends BioceptBase{
	
	@FindBy(css="div[class='button-box view-report']")
	WebElement viewReport;
	
	
	public Dashbord_ClientPortal(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnViewReport(){
		viewReport.click();
	}
}
