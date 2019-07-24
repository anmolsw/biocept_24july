package biocept.qa.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class UploadMacroFile extends BioceptBase {

	String new_name;
	String old_name;
	@FindBy(css ="input[name='files[]']")
	WebElement addFiles;
	
	@FindBy(tagName="h2")
	WebElement page_title;
	
	public UploadMacroFile(){
		PageFactory.initElements(driver, this);
	}
	public  String pageTitle (){
		System.err.println("seq image in");
		
		ExplicitWait.invisibilityOfLoader();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		  String title = wait.until(ExpectedConditions.visibilityOf(page_title)).getText();
		return title;
		
	}
	
	public void updateMacro(String AccessionID, String TestName) throws IOException{
		
		File file = new File(System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Macro&SequencingFile/"+TestName+".txt");		
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String str1  = new String(data, "UTF-8");
		String[] row = str1.split("\r\n");
		String[] newFileContent = new String[row.length];
		newFileContent[0] = row[0];
		for(int i = 1; i< row.length ; i++) {
		String currentLineText = row[i];
		String[] currentLine = currentLineText.split("\t");
		if(currentLine.length > 1) {
		currentLine[0] = AccessionID;
		}
		newFileContent[i] = String.join("\t", currentLine);
		}
		for(int i = 0; i< newFileContent.length ; i++) {
				//System.out.println(newFileContent[i]);
		}
		File file1 = new File(System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Macro&SequencingFile/"+TestName+".txt");
		FileWriter fw = new FileWriter(file1);
		fw.write(String.join("\r\n", newFileContent));
		fw.close();
	}
	
	public void uploadMacro (String TestName){
		
		addFiles.sendKeys(System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Macro&SequencingFile/"+TestName+".txt");
		ExplicitWait.invisibilityOfLoader();
		
	}
	
	public void updateSequencingImage(String AccessionID, String Initials ) throws InterruptedException{
		
		new_name= System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Macro&SequencingFile/"+AccessionID+"_"+Initials+"_"+AccessionID+"KVG.jpg";
		
		old_name = System.getProperty("user.dir")+"/src/main/java/biocept/qa/testdata/Macro&SequencingFile/19-00106_B_19-00106KVG.jpg";
		File oldfile = new File(old_name);
		File newfile = new File (new_name);
		oldfile.renameTo(newfile);

		

		
	}
	
	public void reupdateSequencingImage(){
		
		File oldfile = new File(new_name);
		File newfile = new File (old_name);
		oldfile.renameTo(newfile);
	
		
	}
	
	public void uploadSequencingImage () throws InterruptedException {
		
		addFiles.sendKeys(new_name);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progress-bar-success")));
		Thread.sleep(6000);
	
		
	}
	
	
	

}
