package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class FISHReloAnalysis extends BioceptBase{


	@FindBy(css="input[data-bind*='value: ComponentValue']")
	WebElement fishReloInitials;

	
	@FindBy(css="input[data-bind*='FileUploadInput']")
	WebElement uploadCSV;
	
	public FISHReloAnalysis(){
		PageFactory.initElements(driver, this);
	}
	

	public void fishReloInitials(){
		
		fishReloInitials.sendKeys(prop.getProperty("username"));	
	}
	
	
	public void uploadCSVFile() throws InterruptedException{
		
		WebElement TestName = driver.findElement(By.cssSelector("span[data-bind='text:ChannelTestDisplayName']"));
		ExplicitWait.waitUntilElementToBeVisible(TestName);
		
		uploadCSV.sendKeys(System.getProperty("user.dir") + "/src/main/java/biocept/qa/testdata/a.csv");
		Thread.sleep(5000);
		
		

}

}
