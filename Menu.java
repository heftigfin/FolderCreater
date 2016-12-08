import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu{

	FileMenu fm = new FileMenu();

	private JFrame jm;
	private JComponent progBar = new JProgressBar(0,100); 
	private JButton fileDir;
	private JButton serverDir;
	private JPanel fileDirPanel;
	private JCheckBox subFoldersCheck;

	private JLabel msglabel;

	 public void show(){
	 	gui();
	 }

	 private void gui(){
	 	jm = new JFrame("FolderCreater");
	 	jm.setSize(300,300);
	 	jm.setLayout(new BorderLayout());
	 	jm.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    

	 	fileDirPanel = new JPanel();
	 	fileDirPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	 	fileDirPanel.setSize(300,150);
	 	fileDir = new JButton("Velg PDF mappe");
	 	fileDirPanel.add(fileDir);
	 	serverDir = new JButton("Velg destinasjon");
	 	fileDirPanel.add(serverDir);
		
		subFoldersCheck = new JCheckBox("Undermapper");


	 	jm.add(fileDirPanel, BorderLayout.PAGE_START);
	 	jm.add(subFoldersCheck, BorderLayout.PAGE_END);
	 	jm.add(progBar, BorderLayout.CENTER);

	 	jm.setVisible(true);
	 }


}