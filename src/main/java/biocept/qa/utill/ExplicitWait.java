package biocept.qa.utill;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;


public class ExplicitWait extends BioceptBase {
	
	
	public static void invisibilityOfLoader() {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderDiv")));
	}
	
	public static WebElement waitUntilElementToBeClickable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement Ele =wait.until(ExpectedConditions.elementToBeClickable(Element));
		return Ele;
	}

	public static  void waitUntilElementToBeInvisible(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOf(Element));
		
	}
	
	public static WebElement waitUntilElementToBeVisible(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement Ele =wait.until(ExpectedConditions.visibilityOf(Element));
		return Ele;
	}
	
	public static List<WebElement> waitUntilAllElementToBeVisible(List<WebElement> Element) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		List<WebElement> Ele= wait.until(ExpectedConditions.visibilityOfAllElements(Element));
		return Ele;
	}
}
