package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class BioviewScanData extends BioceptBase{
							 
		@FindBy(css="span[data-bind='text:Channel.Name()']")
		List<WebElement> channel;
		@FindBy(css="input[type='checkbox']")
		List<WebElement> checkbox;
		@FindBy(css="input[class*='txt-chk']")
		List<WebElement> txtkbox;
		@FindBy(css="input[placeholder='Date Time']")
		WebElement dateTime;
	
		
		public BioviewScanData(){
			PageFactory.initElements(driver, this);
		}
		
		public void bioviewData() {

			for(int i=0;i<channel.size();i++){
				WebElement channelElement = channel.get(i);
				ExplicitWait.waitUntilElementToBeVisible(channelElement).click();	
				ExplicitWait.invisibilityOfLoader();
				for(int j=0;j<checkbox.size();j++){
					WebElement Checkbox = checkbox.get(j);
					ExplicitWait.waitUntilElementToBeClickable(Checkbox).click();
				}
					for(int k=0;k<txtkbox.size();k++){
						WebElement Txtkbox = txtkbox.get(k);
						if(k<=5){						
							Txtkbox.sendKeys(String.valueOf((int )(Math.random() * 10 + 1)));	
						}
						else if(k==6){
							Txtkbox.sendKeys(prop.getProperty("username"));
						}
						else if(k==7){
							Txtkbox.sendKeys(String.valueOf((int )(Math.random() * 10 + 1)));
						}	
				}	
					dateTime.click();
			}
		}	

}
