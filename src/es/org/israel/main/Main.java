/**
 * 
 */
package es.org.israel.main;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import es.org.israel.gui.MainGUI;

/**
 * This software requires java version 1.7 (this is due to ejml library)
 * 
 * @author Win7
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemChecks sys = new SystemChecks();
		if (!sys.isJavaVersionSupported()) {
			System.err.println("java version unsupported");
			System.exit(1);
		} else { //checks are OK, running the program
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	UIManager.put("swing.boldMetal", Boolean.FALSE);
	                MainGUI gui = new MainGUI();
	            }
	        });
		}
	}
}
