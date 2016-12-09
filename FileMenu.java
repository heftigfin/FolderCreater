import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class FileMenu{

     private FolderAndMoveFile s = new FolderAndMoveFile();
     private File pdfDir;
     private Move m = new Move();
     private JFileChooser fold = new JFileChooser();
     private JFileChooser ser = new JFileChooser();
     //Frame, button and panel declaration
     private JFrame jm;
     private JPanel fileDirPanel;
     private JPanel initiatePanel;
     private JPanel initSubfoldPanel;
     private JComponent progBar = new JProgressBar(0,100); 
     private JButton fileDir;
     private JButton serverDir;
     private JButton initFolderCreate;
     private JButton initMoveToServ;
     private JCheckBox subFoldersCheck;

      public void show(){
          gui();
      }

      private void gui(){
          //main frame
          jm = new JFrame("FolderCreator");
          jm.setSize(320,450);
          jm.setLayout(new BorderLayout());
          jm.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });   
          //PDF,Server and init button panel
          initSubfoldPanel = new JPanel();
          initSubfoldPanel.setLayout(new BorderLayout());
          initSubfoldPanel.setSize(350,70);
          //PDF and Server button panel
          fileDirPanel = new JPanel();
          fileDirPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
          fileDirPanel.setSize(300,50);
          //PDF folder button
          fileDir = new JButton("Velg PDF mappe");
          fileDir.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
               initiatePDFFolder();
          }});
          fileDirPanel.add(fileDir);
          //server button
          serverDir = new JButton("Velg destinasjon");
          serverDir.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
               initiateServerFolder();
          }});
          fileDirPanel.add(serverDir);

          initiatePanel =  new JPanel();     
          initiatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
          initiatePanel.setSize(300,50);
          //Start button
          initFolderCreate = new JButton("Start");
          initFolderCreate.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                    if(subFoldersCheck.isSelected()){
                         s.start(fold.getSelectedFile());
                         m.start(fold.getSelectedFile());}
                    else{System.out.println("NO");}

          }});          
          initiatePanel.add(initFolderCreate, BorderLayout.LINE_START);

          //Exit("Avslutt") button
          initMoveToServ = new JButton("Avslutt");
          initMoveToServ.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
               System.exit(0);
          }}); 
          initiatePanel.add(initMoveToServ, BorderLayout.LINE_END);

          //Subfolder button
          subFoldersCheck = new JCheckBox("Undermapper");
          //Add panels
          initSubfoldPanel.add(initiatePanel, BorderLayout.PAGE_START);
          initSubfoldPanel.add(subFoldersCheck, BorderLayout.PAGE_END);
          jm.add(initSubfoldPanel,BorderLayout.PAGE_END);
          jm.add(fileDirPanel, BorderLayout.PAGE_START);
          jm.add(progBar, BorderLayout.CENTER);

          jm.setVisible(true);
      }



	private void initiatePDFFolder(){
          
     	fold.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
     	int result = fold.showSaveDialog(fold);
     	if(result==JFileChooser.APPROVE_OPTION){
               if(fold.getSelectedFile().getPath().toLowerCase().equals(FolderCreater.fc.getServer().getPath().toLowerCase())){
               JOptionPane.showMessageDialog(null, "Samme filbane som Server.", "Error", JOptionPane.ERROR_MESSAGE);
                 } else pdfDir = fold.getCurrentDirectory();

     		}


	}
     private void initiateServerFolder(){

          ser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 

          int result = ser.showSaveDialog(fold);
          if(result==JFileChooser.APPROVE_OPTION){
               FolderCreater.fc.setServer(ser.getSelectedFile());
          }
     }
}