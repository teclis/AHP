package es.org.israel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import es.org.israel.gui.comparitions.Comparematrix;
import es.org.israel.matrix.ahp.Ahpmatrix;

/**
 * 
 */

/**
 * @author Win7
 *
 */
public class MainGUI implements ActionListener {
	private JFrame frame;
	private JPanel main;
	private PanelUpperAHP ahp;
	private PanelCenterMatrix pcm;
	private PanelWarning warning;
	
	private ArrayList<Double> inputs;
	private Integer number;
	
	private int counterComparitionMatrix;

	public MainGUI() {
		frame = new JFrame("AHP Panel");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 200));
		frame.pack();
		frame.setVisible(true);

		addComponents();
		
		counterComparitionMatrix = 0;
	}

	private void addComponents() {		
		ahp = new PanelUpperAHP();
		warning = new PanelWarning("      ");

		main = new JPanel();
		main.setLayout(new BorderLayout());
		main.add(ahp, BorderLayout.NORTH);
		main.add(warning, BorderLayout.SOUTH);
		frame.add(main);

		JButton b = ahp.getButtonMatrix();
		b.addActionListener(this);

		JButton c = ahp.getButtonCalc();
		c.addActionListener(this);
		
		JButton a = ahp.getButtonAddPairwiseMatrix();
		a.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add")) {
			try {
				number = new Integer(ahp.getTextField());
				if (main.getComponentCount() > 2) {//El panel de entrada de arriba y el de texto de abajo
					main.remove(pcm);
				}
				System.out.println("c: " + main.getComponentCount()); 
				pcm = new PanelCenterMatrix(number);
				main.add(pcm);
				SwingUtilities.updateComponentTreeUI(frame);
			} catch (NumberFormatException e2) {
				System.err.println("The number entered is not a Number");
				number = 0;
			}
			System.out.println(number);
		}
		ArrayList<Double> collectedInputs = new ArrayList<>();
		if (e.getActionCommand().equals("Calculate")) {
			ArrayList<JTextField> inputs = pcm.getInputs();
			
			for (JTextField jTextField : inputs) {
				try {
					//collecting user's inputs from GUI
					Double value = null;
					String uInput = jTextField.getText();
					if (uInput.startsWith("1/")) {						
						value = 1/Double.parseDouble(uInput.replaceAll("1/", ""));
					} else {
						value = Double.parseDouble(uInput);
					}
					
					collectedInputs.add(value);
					//checks for user's input
					if (value < 0 || value > 9) {
						System.err.println("The number entered is not between 0 and 9");
						warning.setNewText("invalid number input range");
						SwingUtilities.updateComponentTreeUI(frame);
					}
					
				} catch (NumberFormatException e2) {
					System.err.println("The number entered is not a Number");
					warning.setNewText("please, check your inputs!");
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}
		}
		if (e.getActionCommand().equals("new Matrix")) {
			Comparematrix c = new Comparematrix(++counterComparitionMatrix, Integer.parseInt(ahp.getTextField()));
		}
		//checks for user's input
		System.out.println("collectedInputs: " + collectedInputs.size());
		System.out.println(e);
		if (collectedInputs.size() == Math.pow(number, 3)) {//every time that you add new GUI element then you need update this!
			setInputs(collectedInputs);
			Ahpmatrix ahpmatrix = new Ahpmatrix(collectedInputs);
			if (ahpmatrix.isConsistent()) {
				warning.setNewText("RC is ok!, go ahead");
			} else {
				warning.setNewText("RC is poor, please review your inputs");
			}
		}
	}
	
	public ArrayList<Double> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Double> inputs) {
		this.inputs = inputs;
	}
}
