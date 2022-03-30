package wm;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.event.*;

public class WarMaker extends JPanel {
    private JLabel jcomp3;
    private JTextArea jcomp4;
    private JFileChooser chooser;
    private String choosertitle;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JTextField urlPomFolder;
    private JTextField mvnaction;
    
    
    public WarMaker() {
        jcomp3 = new JLabel ("Mvn Action");
        jcomp3.setFont(new Font("Verdana Pro Cond Black", Font.BOLD, 14));
        jcomp3.setBackground(SystemColor.activeCaption);
        jcomp3.setHorizontalAlignment(SwingConstants.CENTER);
        jcomp4 = new JTextArea (5, 5);
        jcomp4.setForeground(Color.WHITE);
        jcomp4.setBackground(Color.BLACK);
      
        JScrollPane scroll = new JScrollPane (jcomp4, 
        		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scroll.setBounds(15, 103, 725, 284);
        
        //adjust size and set layout
        setPreferredSize (new Dimension(752, 397));
        setLayout (null);
        
        ButtonGroup group = new ButtonGroup();
        add (jcomp3);
        add (scroll);
        jcomp3.setBounds (10, 6, 199, 25);
        jcomp4.setBounds (15, 103, 725, 284);
        
        TextAreaOutputStream taos = new TextAreaOutputStream( jcomp4, 60 );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );
        
        
        btnNewButton = new JButton("Esegui");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		disabilitaElementi();
        				
        		jcomp4.setText("");
        		String urlPom = urlPomFolder.getText();
        		String action = mvnaction.getText();
        		group.getSelection();
        		if((!action.isEmpty()) && (!urlPom.isEmpty())){
        			System.out.println("Cartella di lavoro selezionata:  "+urlPom);
        			Executer.MavenCliMethod(action, urlPom);
        		}
        		
        		abilitaElementi();
        	}

        	private void disabilitaElementi() {
				jcomp4.setEditable(false);
				mvnaction.setEditable(false);
				btnNewButton.setEnabled(false);
			}
        	
        	private void abilitaElementi() {
				jcomp4.setEditable(true);
				mvnaction.setEditable(true);
				btnNewButton.setEnabled(true);
			}
        	
        	
        });
        btnNewButton.setBounds(318, 72, 85, 21);
        add(btnNewButton);
            
            btnNewButton_1 = new JButton("Choose Pom Folder");
            btnNewButton_1.setFont(new Font("Verdana Pro Black", Font.BOLD, 12));
            btnNewButton_1.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {       		
            		choosePomFolder(e);         		
            	}
            });
            btnNewButton_1.setBounds(10, 41, 199, 21);
            add(btnNewButton_1);
            
            urlPomFolder = new JTextField();
            urlPomFolder.setName("pomfolder");
            urlPomFolder.setBounds(219, 42, 521, 19);
            add(urlPomFolder);
            urlPomFolder.setColumns(10);
            
            mvnaction = new JTextField();
            mvnaction.setBounds(219, 11, 521, 19);
            add(mvnaction);
            mvnaction.setColumns(10);
            

    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("WarMaker");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Main2());
        frame.pack();
        frame.setVisible (true);
    }
    
    public void choosePomFolder(ActionEvent e) {            
        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
          //System.out.println("getCurrentDirectory(): " 
           //  +  chooser.getCurrentDirectory());
         // System.out.println("getSelectedFile() : " 
         //    +  chooser.getSelectedFile());
          
          urlPomFolder.setText(chooser.getSelectedFile().getPath());
          
       } else {
          System.out.println("No Selection ");
          }
     }
}
