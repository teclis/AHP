package es.org.israel.gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCenterMatrix extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903187391582835598L;
	
	ArrayList<JTextField> inputs;
	
	public PanelCenterMatrix(int iTotalMxN) {
		super.setLayout(new GridLayout(0, iTotalMxN));
		inputs = new ArrayList<JTextField>();
		for (int i = 0; i < Math.pow(iTotalMxN, 2); i++) { //a matrix is a MxN, so i = MxN therefore ixi
			if (i % (iTotalMxN + 1) == 0.0D) {//i % (iTotalMxN + 1) collecting elements from diagonal matrix
				JTextField a = new JTextField("1", 3);
				a.setEditable(false);
				inputs.add(a);
			} else {
				inputs.add(new JTextField("3", 3));
			}
			
			
		}
		
		for (JTextField jTextField : inputs) {
			super.add(jTextField);
		}
	}

	public ArrayList<JTextField> getInputs() {
		return inputs;
	}
}
