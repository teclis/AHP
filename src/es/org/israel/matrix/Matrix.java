package es.org.israel.matrix;

import org.ejml.simple.SimpleMatrix;

public class Matrix {
	private SimpleMatrix matrix;
	private double consistencyIndex;
	
	public Matrix() {
		matrix = new SimpleMatrix();
	}

	public Matrix(SimpleMatrix matrix) {
		super();
		this.matrix = matrix;
	}

	public boolean isReciprocal() {
		boolean rtn = true;
		
		for (int i = 0; i < matrix.numCols(); i++) 
			for (int j = 0; j < matrix.numRows(); j++) {				
				if (matrix.get(i, j)*matrix.get(j, i) != 1.0D) {
					rtn = false;
				}
			}		
		//System.out.println("matrix is reciprocal" + matrix);
		return rtn;
	}
	
	public double consistencyIndex(SimpleMatrix normXmatrix, SimpleMatrix weightMatrix) {
		double ci = 0.0;
		double cr = 0.0;
		double lambdaMax = 0.0;
		//Saaty is giving other values, these are accepted for use matrix large than n > 10
		double ri = (1.98 * (weightMatrix.getNumElements() - 2) / weightMatrix.getNumElements());
		
		for (int i = 0; i < weightMatrix.getNumElements(); i++) {
			lambdaMax += normXmatrix.extractVector(false, i).elementSum() * weightMatrix.get(i);
			//System.out.println("e:" + normXmatrix.extractVector(false, i).elementSum() + " w: " + weightMatrix.get(i));
		}		
		//System.out.println("lambdaMax: " + lambdaMax);
		ci = (lambdaMax-weightMatrix.getNumElements())/(weightMatrix.getNumElements()-1);
		cr = ci / ri;
		
		consistencyIndex = cr;
		
		return cr;
	}
	
	public boolean isConsistent() {
		boolean check = true;
		if (consistencyIndex > 0.1) {
			check = false;
		}
		
		return check;
	}

	public void setMatrix(SimpleMatrix matrix) {
		this.matrix = matrix;
	}
}
