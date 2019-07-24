package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class LeftMenuItems extends BioceptBase{
	@FindBy(id="expand")
	WebElement expandIcon;
		
	public LeftMenuItems (){
		
		PageFactory.initElements(driver, this);
	}	
	public void leftMenuSelection(String Parent, String Child ) throws InterruptedException{
		ExplicitWait.waitUntilElementToBeClickable(expandIcon).click();
		Thread.sleep(2000);

		WebElement ParentElement = driver.findElement(By.linkText(Parent));
		ExplicitWait.waitUntilElementToBeClickable(ParentElement).click();
		WebElement ChildElement = driver.findElement(By.linkText(Child));
		ExplicitWait.waitUntilElementToBeClickable(ChildElement).click();
		
		//driver.findElement(By.linkText(Child)).click();
		
	}

}
