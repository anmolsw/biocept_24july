package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class MEMSProcessingActivity extends BioceptBase{
	
	@FindBy(css="select[data-bind*='options: $parent.IccTest()']")
	List<WebElement> iccTest;

	@FindBy(css="select[data-bind*='options: $parent.FishTest()']")
	List<WebElement> fishTest;
	
	public MEMSProcessingActivity(){
		PageFactory.initElements(driver, this);
	}
		
	public void selectICCTest(){
		try{
		ExplicitWait.waitUntilAllElementToBeVisible(iccTest);
		for(int i=0;i<iccTest.size();i++){
			WebElement Channel= iccTest.get(i);
			Select test = new Select(Channel);
			test.selectByIndex(i+1);	
		}
		}catch (Exception e){
			//e.printStackTrace();
		}
	}
	
	public void selectFISHTest(){
		try{
		for(int i=0;i<fishTest.size();i++){
			WebElement Channel= fishTest.get(i);
			Select test = new Select(Channel);
			test.selectByIndex(i+1);
			
		}
		}catch (Exception e){
			//e.printStackTrace();
		}
	}
	
}
