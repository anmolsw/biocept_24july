package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class MolecularProfessionalInterpretation extends BioceptBase{
	@FindBy(css="td[data-bind*='Mutation-Name']")
	WebElement testName;
	@FindBy(css="lis_component_overall_result[params*='Mol_OverAll_Result']")
	WebElement result;

	
	public MolecularProfessionalInterpretation (){
		PageFactory.initElements(driver, this);
	}

	public String testName(){	
		return ExplicitWait.waitUntilElementToBeVisible(testName).getText();	  	
	}
	
	
	public void piUpdateResult() throws InterruptedException{	
		Thread.sleep(3000);
		WebElement Select = result.findElement(By.tagName("select"));
		ExplicitWait.waitUntilElementToBeVisible(Select);
		Select ResultValue = new Select(Select);
		ResultValue.selectByValue("DETECTED");
	
	}

}
