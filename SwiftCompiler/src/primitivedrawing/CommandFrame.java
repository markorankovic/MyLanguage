package primitivedrawing;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This class is a frame which holds the command line interface.
 * @author marko
 *
 */

public class CommandFrame extends PrimitiveFrame {

	private static final long serialVersionUID = 1L;
	
	public DrawingCommandProcessor dcp;
		
	CommandTextArea commandList = new CommandTextArea(false, this);
	
	CommandTextArea commandEntry = new CommandTextArea(true, this);
	
	public CommandFrame() {
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
				
		this.dcp = new DrawingCommandProcessor((DrawingContext) new DrawingGraphics());
		
		add((DrawingGraphics)dcp.dc);
		add(commandEntry);
		add(commandList);
		dcp.commandList = commandList;
		createMenuBar();
		
        pack();
	}
	
	
	
	/**
	 * Opens a dialog for opening a file to be sent to the command processor.
	 */
	
	public void openFile() {
	    JFileChooser chooser = new JFileChooser();
        int returnValue = chooser.showOpenDialog( null ) ;
        if (returnValue == JFileChooser.APPROVE_OPTION ) {
        	File file = chooser.getSelectedFile();
        	dcp.loadCommandsFromFile(file);
        }
	}
	
	/**
	 * Opens a dialog for saving a file to be sent to the command processor.
	 */
	
	public void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(this);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
		    dcp.saveCommandsToFile(fileToSave);
		    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		}	
	}
	
	// Creates the menu bar for opening and/or saving a file.
    private void createMenuBar() {
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        
        var openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener((event) -> openFile());
        
        var saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener((event) -> saveFile());


        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

}