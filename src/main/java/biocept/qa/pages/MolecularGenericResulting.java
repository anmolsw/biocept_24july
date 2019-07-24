package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class MolecularGenericResulting extends BioceptBase{
	
	@FindBy(css="td[data-bind*='TestName']")
	WebElement testName;
	@FindBy(css="lis_component_test_result[params*='Mol_Result']")
	WebElement result;
	
	@FindBy(css="lis_component_singleselect[params*='Mol_T790M_Result']")
	WebElement T790M_result;
	
	@FindBy(css="lis_component_singleselect[params*='Mol_L858R_Result']")
	WebElement L858R_result;
	
	@FindBy(css="lis_component_singleselect[params*='Mol_Del19_Result']")
	WebElement Del19_result;
	
	@FindBy(id="btn-Complete")
	WebElement molecularCompleteButton;
	
	public MolecularGenericResulting (){
		PageFactory.initElements(driver, this);
	}

	public String testName(){	
		return ExplicitWait.waitUntilElementToBeVisible(testName).getText();
			  	
	}
	
	
	public void braf_kras_nras_GenericUpdateResult() {	
		ExplicitWait.waitUntilElementToBeVisible(result);
		WebElement Select = result.findElement(By.tagName("select"));
		Select ResultValue = new Select(Select);
		ResultValue.selectByValue("DETECTED");
	
	}
	
	public void molEGFR_GenericUpdateResult() {	
		WebElement T7_Select = T790M_result.findElement(By.tagName("select"));
		ExplicitWait.waitUntilElementToBeVisible(T7_Select);
		Select T7_ResultValue = new Select(T7_Select);
		T7_ResultValue.selectByValue("DETECTED");
		
		WebElement L8_Select = L858R_result.findElement(By.tagName("select"));
		ExplicitWait.waitUntilElementToBeVisible(L8_Select);
		Select L8_ResultValue = new Select(L8_Select);
		L8_ResultValue.selectByValue("DETECTED");
		
		WebElement Del19_Select = Del19_result.findElement(By.tagName("select"));
		ExplicitWait.waitUntilElementToBeVisible(Del19_Select);
		Select Del19_ResultValue = new Select(Del19_Select);
		Del19_ResultValue.selectByValue("DETECTED");
	
	}
	
	
}
