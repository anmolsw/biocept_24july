package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class CTCICCResultsSummary extends BioceptBase{


	@FindBy(css="div[data-bind='if: InstanceComponents().length > 0']")
	WebElement ctcResultSection;
	
	@FindBy(css="button[data-bind*='_ShowAccessionImagesPopUp']")
	WebElement chooseImages;
	
	@FindBy(css="button[data-bind*='_ShowAccessionImagesPopUp']")
	List<WebElement> iccChooseImages;
	
	@FindBy(css="span[data-bind*='text: Name']")
	WebElement selectImages;
	
	@FindBy(css="span[data-bind*='text: Name']")
	List<WebElement> iccSelectImages;
	
	@FindBy(xpath="//*[@id='modal_accessionImages']/div[3]/button[2]")
	WebElement close;
	
	@FindBy(xpath="//*[@id='modal_accessionImages']/div[3]/button[2]")
	List<WebElement> iccClose;
	
	@FindBy(css="div[class='modal-backdrop fade in']")
	WebElement fadeIn;
	
	@FindBy(css="div[data-bind='if: TestOrders().length > 0']")
	WebElement iccResultSection;
	
	@FindBy(css ="input[value='Complete']")
	WebElement ctcFishCompleteButtonElement;
	

	
	public CTCICCResultsSummary(){
		PageFactory.initElements(driver, this);
	}
	
	
	public void ctcResult (){
		WebElement optionsText = ctcResultSection.findElement(By.cssSelector("select[data-bind*='optionsText']"));
		ExplicitWait.waitUntilElementToBeVisible(optionsText);
		Select result = new Select(optionsText);
		result.selectByVisibleText("DETECTED");
	}
	
	public void ctcTestImage (){

		WebElement ctcImageButton = ctcResultSection.findElement(By.cssSelector("button[data-bind*='getImagesFromAzure']"));
		ctcImageButton.click();
		ExplicitWait.waitUntilElementToBeClickable(chooseImages).click();
		selectImages.click();
		close.click();
		
	}
	
	public void iccResult (){
		List<WebElement> optionsText = iccResultSection.findElements(By.cssSelector("select[data-bind*='optionsText']"));
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
	
	public void iccTestImage () {
		List<WebElement> iccImageButton = iccResultSection.findElements(By.cssSelector("button[data-bind*='getImagesFromAzure']"));
			
		if(iccImageButton.size()>0){
		try {
			for(int i=0;i<iccImageButton.size();i++){
				WebElement iccImageButtonElement = iccImageButton.get(i);
				ExplicitWait.waitUntilElementToBeClickable(iccImageButtonElement).click();
				//wait.until(ExpectedConditions.elementToBeClickable(iccImageButtonElement)).click();
				for(int j=i+1;j<iccChooseImages.size();j++){
					WebElement ChooseImagesElement = iccChooseImages.get(j);
					String CIValue= ExplicitWait.waitUntilElementToBeClickable(ChooseImagesElement).getText();	
					if(CIValue.equalsIgnoreCase("Choose Images"))
					{
						ChooseImagesElement.click();
						break;
					}}
					
					for( int k=0;k<iccSelectImages.size();k++){
						WebElement ICCSelectImageElement = iccSelectImages.get(k);
						boolean elementState = ICCSelectImageElement.isDisplayed();
						if(elementState==true){
							ICCSelectImageElement.click();
							break;
						}}
						for(int l=i+1;l<iccClose.size();l++){
							WebElement ICCCloseElement = iccClose.get(l);
							String CValue= ExplicitWait.waitUntilElementToBeClickable(ICCCloseElement).getText();
							if(CValue.equalsIgnoreCase("Close"))
							{
								ICCCloseElement.click();
								WebDriverWait wait_fadeIn=new WebDriverWait(driver, 05);
								wait_fadeIn.until(ExpectedConditions.invisibilityOf(fadeIn));
								
								break;
							}
								}	
									}
							
							}catch (Exception e){
							//e.printStackTrace();
						}
					}
				}
		

public void CompleteButton (){
	ExplicitWait.invisibilityOfLoader();
	//ExplicitWait.waitUntilElementToBeClickable(ctcFishCompleteButtonElement);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", ctcFishCompleteButtonElement);
//	WebDriverWait wait=new WebDriverWait(driver, 30);
//	wait.until(ExpectedConditions.elementToBeClickable(ctcFishCompleteButton));
//	ctcFishCompleteButton.click();
	
}
		
		
}
	


