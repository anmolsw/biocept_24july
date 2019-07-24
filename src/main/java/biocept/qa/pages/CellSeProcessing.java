package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;


public class CellSeProcessing extends BioceptBase{

	@FindBy(css ="tbody[data-bind='foreach: BloodTubes()']")
	WebElement bloodTubesTable;
	@FindBy(css ="input[value='Complete']")
	WebElement completeButton;

	@FindBy(tagName="h3")
	WebElement activityName;

	public CellSeProcessing(){
		PageFactory.initElements(driver, this);
	}
	
	public  String pageTitle (){
		ExplicitWait.invisibilityOfLoader();
		return activityName.getText();
	}
	
	public void selectLeucosepTube(){	
		ExplicitWait.waitUntilElementToBeVisible(bloodTubesTable);
		List<WebElement> allRow = bloodTubesTable.findElements(By.tagName("tr"));
		for (int i=0;i<allRow.size();i++ ){
			WebElement row = allRow.get(i);
			WebElement leucosepTubeColumn = row.findElement(By.tagName("select"));
			Select leucosepTube = new Select(leucosepTubeColumn);
			leucosepTube.selectByIndex(i);
			WebElement Comments = row.findElement(By.tagName("input"));
			Comments.sendKeys("Testing");		
		}
	}

}
