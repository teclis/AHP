package es.org.israel.gui.comparitions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.ejml.simple.SimpleMatrix;

import es.org.israel.gui.PanelCenterMatrix;
import es.org.israel.gui.PanelWarning;
import es.org.israel.matrix.ahp.Ahpmatrix;

public class Comparematrix extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4645713339291373448L;
	
	private PanelCenterMatrix pcm;
	private JButton calc;
	private PanelWarning warning;
	
	private ArrayList<Double> collectedInputs;
	
	private int id;
	private int numElements;
	
	private Ahpmatrix ahp;

	public Comparematrix(int id, int numElements) {
		super("Matrix Comparing Panel");

		super.setLayout(new BorderLayout());
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(400, 200));
		super.pack();
		super.setVisible(true);
		
		calc = new JButton("Calculate");
		pcm = new PanelCenterMatrix(numElements);
		warning = new PanelWarning("Comparition matrix #"+ id);
		collectedInputs = new ArrayList<>();
		ahp = new Ahpmatrix(new ArrayList<Double>());
		
		this.id = id;
		this.numElements = numElements;

		super.add(calc, BorderLayout.NORTH);
		super.add(pcm, BorderLayout.CENTER);
		super.add(warning, BorderLayout.SOUTH);
		
		calc.addActionListener(this);
	}

	public int getId() {
		return id;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("hola soy el panel " + getId());
		ArrayList<JTextField> inputs = pcm.getInputs();
		
		if (checksInputs(inputs)) {
			setCollectedInputs(inputs);
			ahp = new Ahpmatrix(collectedInputs);
			if (ahp.isConsistent()) {
				warning.setNewText("RC is ok!, go ahead");
			} else {
				warning.setNewText("RC is poor, please review your inputs");
			}
		}
	}

	private void setCollectedInputs(ArrayList<JTextField> inputs) {
		collectedInputs.clear();
		for (JTextField jTextField : inputs) {
			Double value = null;
			String uInput = jTextField.getText();
			if (uInput.startsWith("1/")) {						
				value = 1/Double.parseDouble(uInput.replaceAll("1/", ""));
			} else {
				value = Double.parseDouble(uInput);
			}
			
			collectedInputs.add(value);
		}
	}

	public ArrayList<Double> getCollectedInputs() {
		return collectedInputs;
	}

	private boolean checksInputs(ArrayList<JTextField> inputs) {
		boolean isOK = true;
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
					SwingUtilities.updateComponentTreeUI(this);
					isOK = false;
				}
			} catch (NumberFormatException e2) {
				System.err.println("The number entered is not a Number");
				warning.setNewText("please, check your inputs!");
				SwingUtilities.updateComponentTreeUI(this);
				isOK = false;
			}
		}
		
		return isOK;
	}
}
