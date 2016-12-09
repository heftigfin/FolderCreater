import java.io.*;

public class FolderCreater{

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
}


