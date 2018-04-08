package es.org.israel.matrix.ahp;

import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

import es.org.israel.matrix.Matrix;

public class Ahpmatrix extends Matrix {
	private SimpleMatrix matrix;
	private SimpleMatrix normalizedMatrix;
	private SimpleMatrix weightMatrix;

	public Ahpmatrix(ArrayList<Double> input) {
		Double sqrt = Math.sqrt(input.size());
		matrix = new SimpleMatrix(sqrt.intValue(), sqrt.intValue());
		super.setMatrix(matrix);		

		for (int i = 0; i < sqrt.intValue(); i++) {
			for (int j = 0; j < sqrt.intValue(); j++) {
				//System.out.println("i: " + i + " j: " + j + " val: " + input.get(sqrt.intValue() * i  + j));
				matrix.set(i, j, input.get(sqrt.intValue() * i  + j));
			}
		}
		super.isReciprocal();
		buildNormalizedMatrix();
		buildWeightMatrix();
		super.consistencyIndex(normalizedMatrix.mult(matrix), weightMatrix);
	}

	public void buildNormalizedMatrix() {
		double[][] column = new double[1][matrix.numCols()];
		double[][] sum = new double[matrix.numRows()][matrix.numCols()];
		normalizedMatrix = new SimpleMatrix(matrix.numRows(), matrix.numCols());
		
		for (int i = 0; i < matrix.numCols(); i++) {
			SimpleMatrix vector = matrix.extractVector(false, i);
			sum[0][i] = vector.elementSum();
			for (int j = 0; j < vector.getNumElements(); j++) {
				column[0][j] = vector.get(j)/sum[0][i];
			}
			normalizedMatrix.setColumn(i, 0, column[0]);
		}
		System.out.println("normalizedMatrix: " + normalizedMatrix);
	}
	
	public void buildWeightMatrix() {
		weightMatrix = new SimpleMatrix(1, matrix.numRows());
		for (int i = 0; i < normalizedMatrix.numCols(); i++) {
			SimpleMatrix vector = normalizedMatrix.extractVector(true, i);
			weightMatrix.setColumn(i, 0, vector.elementSum() / vector.getNumElements());
		}
		System.out.println("weightMatrix: " + weightMatrix);
	}

	public SimpleMatrix getMatrix() {
		return matrix;
	}

	public SimpleMatrix getWeightMatrix() {
		return weightMatrix;
	}
}
