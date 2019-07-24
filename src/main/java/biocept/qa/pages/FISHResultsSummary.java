package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class FISHResultsSummary extends BioceptBase{
	

	@FindBy(css="input[data-bind*= 'value:$parent.ShowComponentsValue']")
	List<WebElement> testComponenet;
	
	@FindBy(css="div[data-bind='if: TestOrders().length > 0']")
	WebElement fishResultSection;
	
	@FindBy(css="button[data-bind*='_ShowAccessionImagesPopUp']")
	List<WebElement> fishChooseImages;
	
	@FindBy(css="span[data-bind*='text: Name']")
	List<WebElement> fishSelectImages;
	
	@FindBy(xpath="//*[@id='modal_accessionImages']/div[3]/button[2]")
	List<WebElement> fishClose;
	
	public FISHResultsSummary(){
		PageFactory.initElements(driver, this);
	}
	

	
	public void fishTestComponenet (){
		for (int i=0;i<testComponenet.size();i++){
			WebElement componentElement = testComponenet.get(i);
			componentElement.sendKeys(String.valueOf((int )(Math.random() * 10 + 1)));
		}
		
	}
	
	public void fishResult (){
		List<WebElement> optionsText = fishResultSection.findElements(By.cssSelector("select[data-bind*='optionsText']"));
		if(optionsText.size()>0){
			try {
			for(int i=0;i<optionsText.size();i++){
				WebElement resultElement = optionsText.get(i);
				Select result = new Select(resultElement);
				result.selectByIndex(i);
			}
			}catch (Exception e){
				//e.printStackTrace();
			}
		}
	}
	
	public void fishTestImage (){
		
		
		List<WebElement> fishImageButton = fishResultSection.findElements(By.cssSelector("button[data-bind*='getImagesFromAzure']"));
			
		if(fishImageButton.size()>0){
			
		try {
			for(int i=0;i<fishImageButton.size();i++){
				WebElement fishImageButtonElement = fishImageButton.get(i);
				ExplicitWait.waitUntilElementToBeClickable(fishImageButtonElement).click();
				
				for(int j=i;j<fishChooseImages.size();j++){
					WebElement ChooseImagesElement = fishChooseImages.get(j);
					
					String CIValue= ExplicitWait.waitUntilElementToBeClickable(ChooseImagesElement).getText();		
					if(CIValue.equalsIgnoreCase("Choose Images"))
					{
						ChooseImagesElement.click();
						break;
					}}
					for( int k=0;k<fishSelectImages.size();k++){
						WebElement FISHSelectImageElement = fishSelectImages.get(k);
						boolean elementState = FISHSelectImageElement.isDisplayed();
						if(elementState==true){
							FISHSelectImageElement.click();	
							break;
						}}					
						for(int l=i;l<fishClose.size();l++){
							WebElement FISHCloseElement = fishClose.get(l);
							String CValue= ExplicitWait.waitUntilElementToBeClickable(FISHCloseElement).getText();
							if(CValue.equalsIgnoreCase("Close"))
							{
								FISHCloseElement.click();
								WebDriverWait wait=new WebDriverWait(driver, 30);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='modal-backdrop fade in']")));
								break;
							}}							
							
						}}catch (Exception e){
							//e.printStackTrace();
						}	
				}
		
		}
}
