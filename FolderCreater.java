import java.io.*;

public class FolderCreater{
	
 	public static FolderCreater fc = new FolderCreater();
 	private boolean subFoldCheck = false;
	private File server = new File("m:/SCANNET");
	
	public static void main(String[] args) {
	
		FileMenu me = new FileMenu();


		me.show();

	}

	public File getServer(){
		return server;
	}
	public void setServer(File f){
		server = f;
	}
	public boolean getSubFoldCheck(){
		return subFoldCheck;
	}
	public void setSubFoldCheck(boolean value){
		subFoldCheck = value;
	}
}