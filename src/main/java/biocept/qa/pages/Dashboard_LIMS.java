package biocept.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;

public class Dashboard_LIMS extends BioceptBase {
	int allLinkCount;
	
	@FindBy(css ="a[data-bind='text: CustomPageName, attr: { href: CustomUrl}']")
	public List<WebElement> allDashboardLink;
	
public Dashboard_LIMS(){
	PageFactory.initElements(driver, this);
}

public int dashboardLinks(){
	allLinkCount = allDashboardLink.size();

    return allLinkCount;
}

public void clickOnPlaceNewOrder(String linkName){
	boolean found = false;
	 System.out.println("in");
    for (int i = 0; i < allDashboardLink.size(); i++) {

        WebElement link =  allDashboardLink.get(i);

        WebDriverWait wait=new WebDriverWait(driver, 30);
        String linktext = wait.until(ExpectedConditions.elementToBeClickable(link)).getText();
        	if (linktext.contains(linkName)) {
            found = true;
            link.click();
        }
        if (found) {
 
            break;   
        }  
    }
}


	

}
