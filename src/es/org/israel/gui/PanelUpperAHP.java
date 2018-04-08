/**
 * 
 */
package es.org.israel.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Win7
 *
 */
public class PanelUpperAHP extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 29083085712983938L;
	
	private JTextField textField; 
	private JButton buttonMatrix;
	private JButton buttonCalc;
	private JButton buttonAddPairwiseMatrix;

	public PanelUpperAHP() {
		textField = new JTextField(10);
		buttonMatrix = new JButton("Add");
		buttonCalc  = new JButton("Calculate");
		buttonAddPairwiseMatrix  = new JButton("new Matrix");
		
		super.add(textField);
		super.add(buttonMatrix);
		super.add(buttonCalc);
		super.add(buttonAddPairwiseMatrix);
	}

	public JButton getButtonMatrix() {
		return buttonMatrix;
	}

	public JButton getButtonCalc() {
		return buttonCalc;
	}
	
	public JButton getButtonAddPairwiseMatrix() {
		return buttonAddPairwiseMatrix;
	}
	
	
	
	public String getTextField() {
		return textField.getText();
	}
}
