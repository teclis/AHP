package es.org.israel.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelWarning extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2851824375415691360L;
	
	private JLabel label;

	public PanelWarning(String msg) {
		label = new JLabel(msg);
		
		super.add(label);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setNewText(String msg) {
		label.setText(msg);
	}
}
