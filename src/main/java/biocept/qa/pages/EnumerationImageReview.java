package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class EnumerationImageReview extends BioceptBase{

	
	@FindBy(css="span[data-bind='text:Channel.Name()']")
	List<WebElement> channel;
	
	@FindBy(id="txtbx")
	List<WebElement> txtbx;
	
	@FindBy(css="input[placeholder='']")
	List<WebElement> Initials;
	
	@FindBy(css="input[placeholder='Date Time']")
	List<WebElement> DateTime;
	
	@FindBy(css ="input[value='Complete']")
	WebElement completeButton;
	
	
	public EnumerationImageReview(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void enumerationAnalysis(){
		try{
		
		ExplicitWait.waitUntilAllElementToBeVisible(channel);
		for(int i=0;i<channel.size();i++){
			WebElement channelElement = channel.get(i);
			channelElement.click();	
			ExplicitWait.invisibilityOfLoader();
			for(int j=0;j<72;j++){
				WebElement Checkbox = txtbx.get(j);
				ExplicitWait.waitUntilElementToBeClickable(Checkbox).sendKeys(String.valueOf((int )(Math.random() * 10 + 1)));
				
			}
		}}catch (Exception e){
			//e.printStackTrace();
		}
	}
	
	public void enumerationInitials(){

		for(int k=0;k<Initials.size();k++){
			WebElement IniElement = Initials.get(k);
			IniElement.clear();
			IniElement.sendKeys(prop.getProperty("username"));
		}
	}
		public void enumerationDateTime(){
			for(int l=0;l<DateTime.size();l++){
				WebElement DateTimeElement = DateTime.get(l);
				DateTimeElement.click();
				DateTimeElement.sendKeys(Keys.TAB);
			}
		
	}
	
	

}
