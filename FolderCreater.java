import javax.swing.*;
import java.io.*;
import java.awt.*;

public class FolderCreater{

	public static File server = new File("m:/SCANNET");
	
	public static void main(String[] args) {
	
		Menu me = new Menu();
		me.show();
		//fm.initiate();

	}

}

class FileMenu{

	FolderAndMoveFile s = new FolderAndMoveFile();
	Move m = new Move();

	public void initiate(){
		JFileChooser f = new JFileChooser();
     	f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 

     	int result = f.showSaveDialog(f);
     	if(result==JFileChooser.APPROVE_OPTION){
    		f.setCurrentDirectory(f.getCurrentDirectory());
     		}else if(result==JFileChooser.CANCEL_OPTION){
     			System.exit(0);
     		}else if(f.getSelectedFile() == null) System.exit(0);


     	



     	if(f.getSelectedFile().getPath().toLowerCase().equals(FolderCreater.server.getPath())){
     	JOptionPane.showMessageDialog(null, "Samme filbane som Server.", "Error", JOptionPane.ERROR_MESSAGE);
     	System.exit(0);
}
		s.start(f.getSelectedFile());
		m.start(f.getSelectedFile());

	}


}


