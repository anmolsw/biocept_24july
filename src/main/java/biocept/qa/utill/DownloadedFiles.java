package biocept.qa.utill;

import java.io.File;

public class DownloadedFiles {
	
	static boolean found;
	
	public static void delete (String FileName)
	{
		String homePath = System.getenv("USERPROFILE");
			  File dir = new File(homePath + "\\Downloads");
			  File[] dirContents = dir.listFiles();
			  for (int i = 0; i < dirContents.length; i++) {
			      if (dirContents[i].getName().contains(FileName)) {	    	  
			          dirContents[i].delete();
			      }
			          }
			  
	}
	
	public static boolean verify (String FileName)
	{
		String homePath = System.getenv("USERPROFILE");
			  File dir = new File(homePath + "\\Downloads");
			  File[] dirContents = dir.listFiles();
			   found = false;
			  for (int i = 0; i < dirContents.length; i++) {
				  
			      if (dirContents[i].getName().contains(FileName)!=true) {	
			    	  found = true;
			    	  
			      }if(found){
			    	  break;
			    	    
			      }
			          }
			return found;
			  
	}
}
