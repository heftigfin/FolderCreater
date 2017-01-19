import java.io.*;

public class FolderAndMoveFile{

	Move m = new Move();

	public void start(File folder){
	File[] listOfFiles = folder.listFiles();
	String destFolder = new String();

		for (File file : listOfFiles) {
			if(file.isDirectory() && FolderCreater.fc.getSubFoldCheck()){
				start(file);
			}
   			else if (file.isFile()) {
   				if(m.checkIfPdf(file.getPath())){
		
   					destFolder = isNumeric(file.getName());
   					createFolder(file.getParent(), destFolder);
   					file.renameTo(new File(file.getParent() + "/" + destFolder + "/" + file.getName()));

   					}
   				}
			}
		}

	public String isNumeric(String s) {
	    int len = s.length();
	    String fName = new String();
	    for (int i = 0; i < len; ++i) {
	        if (Character.isDigit(s.charAt(i))) {
	        	fName = fName + s.charAt(i);
	        } else break;
	    }
		return fName;
	
		}
		public void createFolder(String f, String s){
	
			new File(f +"/"+s).mkdirs();
	}
}