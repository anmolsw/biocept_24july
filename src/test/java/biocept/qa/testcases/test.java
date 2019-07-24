package biocept.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class test {
	public static Properties prop;
	public static void main(String[] args) {
		
		
//		LocalDate today = LocalDate.now();
//		System.out.println("today+++" + today);
//		System.out.println(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(today));
//		LocalDate yesterday = today.minusDays(1);
//		System.out.println("yesterday+++" + yesterday);
		
		
		
		// TODO Auto-generated method stub
		
		try{
			 prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/biocept/qa/config/config.properties");
			///Biocept/src/main/java/biocept/qa/config/config.properties
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		
		

	}

}
