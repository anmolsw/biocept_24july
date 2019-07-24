package biocept.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class CellSepBatching extends BioceptBase {
	

	@FindBy(css="input[data-bind*='value: ComponentValue']")
	WebElement cellSepInitials;
	
	
	public CellSepBatching(){
		PageFactory.initElements(driver, this);
	}

	public void cellSepInitials(){
		ExplicitWait.waitUntilElementToBeVisible(cellSepInitials).sendKeys(prop.getProperty("username"));
		
	}
	

}
