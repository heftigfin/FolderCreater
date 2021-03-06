import java.io.*;
import javax.swing.*;

public class Move{
 	

	public void start(File folder){

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Flytte til server?", "Message", dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			System.out.println(FolderCreater.fc.getServer().getPath());
			int dialogButton2 = JOptionPane.YES_NO_OPTION;
			int dialogResult2 = JOptionPane.showConfirmDialog(null, checkNrOfFiles(folder) +" PDF filer i " +checknrOfFolders(folder) + " mapper vil bli flyttet. OK?", "Message", dialogButton2);
			if(dialogResult2 == JOptionPane.YES_OPTION){
				copyFolder(folder, FolderCreater.fc.getServer());

			} 
		}
	}

	//lag en midlertidig mappe før du overfører og sletter.
	public void copyFolder(File source, File dest){

		if(source.getPath().toLowerCase().equals(FolderCreater.fc.getServer().getPath())){
     		JOptionPane.showMessageDialog(null, "Samme filbane som Server.", "Error", JOptionPane.ERROR_MESSAGE);
     		System.exit(0);
		}
		if(source.isDirectory()){
			
			if(!dest.exists()){
				dest.mkdirs();
			}

			 String files[] = source.list();
       		 for (String file : files){

          	 	File srcFile = new File(source, file);
           		File destFile = new File(dest, file);
           		copyFolder(srcFile, destFile);
				
			}
   		}
   		
 		else if(checkIfPdf(source.getPath())){

        		InputStream in = null;
        		OutputStream out = null;

       		 try{

       		 	in = new FileInputStream(source);       		

       		 	while(checkFileExist(dest)){
       		 		dest = checkExtension(dest.getPath());
       		 		}

       		 		out = new FileOutputStream(dest);
				
          	  	byte[] buffer = new byte[1024];

           		int length;
            	while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            	out.close();
    			in.close();
        }
        	catch (Exception e){
           	 	try{
                in.close();
           		}
          	  	catch (IOException e1){
                e1.printStackTrace();
          		}

            	try{
                out.close();
           		}
            	catch (IOException e1){
                e1.printStackTrace();
         		}
      		}
		}


	}

	public void deleteOriginal(File source){

		File[] index = source.listFiles();

		for(File file : index){
			if(file.isDirectory()){
				deleteOriginal(file);
			}
		file.delete();
		}   
	}
	
	public boolean checkFileExist(File f){

		boolean check = f.exists();

		return check;
	}

	public File checkExtension(String dest){

		String fileWOutEx = new String();
		int extPos = 0;
		int stNr = 0;
		int endNr = 0;
		boolean hasExtensionCounter = false;

		for(int i = dest.length()-1; i>0; i--){
			if (dest.charAt(i)=='.') {
					extPos = i;
       		 }
       		 if(dest.charAt(i)=='('){	
       		 	stNr = i;
       		 }
       		 if (dest.charAt(i)==')') {
       		 	hasExtensionCounter = true;
       		 	endNr = i;
       		 }
  
		}
		

        if(hasExtensionCounter){

        	int counterInc = Integer.parseInt(dest.substring(stNr+1, endNr)) + 1;
        	String extCounter = Integer.toString(counterInc);
        	fileWOutEx = new StringBuilder(dest).replace(stNr,endNr, "(" + extCounter).toString();

        } else fileWOutEx = new StringBuilder(dest).insert(extPos, " (1)").toString();


		return new File(fileWOutEx);
	}

	public boolean checkIfPdf(String dest){
			
			boolean pdfCheck = false;

			if(dest.substring((dest.length()-3), dest.length()).toLowerCase().equals("pdf")){
				pdfCheck = true;
			}
		return pdfCheck;
	}

	private int checkNrOfFiles(File folder){
		int pdfNrCounter = 0;
		//tell mapper også!
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {

			if(file.isDirectory()){
				pdfNrCounter += checkNrOfFiles(file);
   		 	}
   		 	else if (checkIfPdf(file.getPath())) {
    			pdfNrCounter++;
   		 	}
   		 }

		return pdfNrCounter;
	}
	
	private int checknrOfFolders(File folder){
		int folderNrCounter = 0;
		File[] listOfFiles = folder.listFiles();
		folderNrCounter++;

		for (File file : listOfFiles) {
			folderNrCounter += checkNrOfFiles(file);

 
   		 }
		return folderNrCounter;
	}
}