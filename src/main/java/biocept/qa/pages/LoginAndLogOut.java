package biocept.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class LoginAndLogOut extends BioceptBase {
	
	@FindBy(id="username")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="btn-Login")
	WebElement loginButton;
	
	@FindBy(id="logOut")
	WebElement logOutButton;
	
	@FindBy(id="welcomename")
	WebElement welomename;
	
	@FindBy(id="logout")
	WebElement logOutButton_Clientportal;
	
public LoginAndLogOut(){
	PageFactory.initElements(driver, this);
}



public void LIMSlogin(){
	
	driver.get(prop.getProperty("LIMSurl"));
	ExplicitWait.waitUntilElementToBeClickable(username).sendKeys(prop.getProperty("username"));
	password.sendKeys(prop.getProperty("password"));
	ExplicitWait.waitUntilElementToBeClickable(loginButton).click();
	
}

public void LIMSLogOut(){
	ExplicitWait.waitUntilElementToBeVisible(logOutButton);
	ExplicitWait.waitUntilElementToBeClickable(logOutButton).click();
}

	




public void ClientPortalLogin(){
	
	driver.get(prop.getProperty("PhysicianPortal"));
	ExplicitWait.waitUntilElementToBeClickable(username).sendKeys(prop.getProperty("PhysicianUserName"));
	password.sendKeys(prop.getProperty("PhysicianPassword"));
	ExplicitWait.waitUntilElementToBeClickable(loginButton).click();
	
}

public void ClientPortalLogOut(){
	
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", welomename);
	executor.executeScript("arguments[0].click();", logOutButton_Clientportal);
//	welomename.click();
//	logOutButton_Clientportal.click();
	
	
}

public String validateLoginPageTitle(){
	
return driver.getTitle();
}



}
