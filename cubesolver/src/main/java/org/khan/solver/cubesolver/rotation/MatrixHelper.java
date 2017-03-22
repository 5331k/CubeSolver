package org.khan.solver.cubesolver.rotation;

import org.khan.solver.cubesolver.Keywords;

/**
 * Utility class, which provide different functions to be applied on 2d boolean matrix. Functions are defined. As each puzzle 
 * can be represented by a 2d matrix, different functions can be used to apply different effects on puzzle piece via updating 
 * matrix values.
 * 
 * @author SDK
 *
 */
public class MatrixHelper {

	/**
	 * Copy a given source matrix to the given destination matrix. 
	 * 
	 * @param srcMat destination matrix
	 * @param matToCopy matrix to copy
	 * @param startRow starting row in destination matrix 
	 * @param startCol starting column in destination matrix
	 * @param size size of the matrix to be copied
	 */
	public static void copyOneMatrixToAnother(boolean[][] srcMat, boolean [][]matToCopy, int startRow,int startCol, int size){
		int row = startRow;
		int col = startCol;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				srcMat[row][col] = matToCopy[i][j];
				col++;
			}
			col = startCol;
			row++;
		}
	}
	
	/**
	 * Rotate matrix in the vertical direction, 90 degrees from upside down
	 * 
	 * @param inputMatrix matrix to be rotated
	 * @param size size of matrix
	 * @return rotated matrix
	 */
	public static boolean[][] rotateUpSideDown(boolean[][] inputMatrix, int size) {
		boolean[][] newMatrix = new boolean[size][size];
		int row = size - 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMatrix[i][j] = inputMatrix[row][j];
			}
			row = row - 1;
		}
		return newMatrix;
	}
	

	/**
	 * Rotate matrix in 180 degree horizontally
	 * 
	 * @param inputMatrix matrix to be rotated
	 * @param size size of matrix
	 * @return rotated matrix
	 */
	public static boolean[][] rotateLeftToRigh(boolean[][] inputMatrix, int size) {
		boolean[][] newMatrix = new boolean[size][size];
		int col = size - 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMatrix[i][j] = inputMatrix[i][col];
				col = col - 1;
			}
			col = size - 1;
		}
		return newMatrix;
	}

	/**
	 * Create a copy of matrix 
	 * 
	 * @param inputMatrix matrix to be rotated
	 * @param size size of matrix
	 * @return output copied matrix
	 */
	public static boolean[][] getMatrixCopy(boolean[][] inputMatrix, int size) {
		boolean[][] newMatrix = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMatrix[i][j] = inputMatrix[i][j];
			}
		}
		return newMatrix;
	}

	/**
	 * Get a desired column from a 2d matrix
	 * 
	 * @param mat input matrix
	 * @param colIndex column index
	 * @return column values in an array
	 */
	public static boolean[] getColumnFrom2dMatrix(boolean[][] mat, int colIndex) {
		boolean[] column = new boolean[mat.length];
		for (int i = 0; i < mat.length; i++) {
			column[i] = mat[i][colIndex];
		}
		return column;
	}

	/**
	 * Get a desired form a 2d matrix
	 * 
	 * @param mat input matrix
	 * @param rowIndex row index
	 * @return row values in an array
	 */
	public static boolean[] getRowFrom2dMatrix(boolean[][] mat, int rowIndex) {
		boolean[] row = new boolean[mat.length];
		for (int i = 0; i < mat.length; i++) {
			row[i] = mat[rowIndex][i];
		}
		return row;
	}

	/**
	 * Update a column in a 2d matrix
	 * 
	 * @param mat matrix to update
	 * @param colIndex column index to update
	 * @param values values to be updated
	 */
	public static void updateColumnIn2dMatrix(boolean[][] mat, int colIndex, boolean[] values) {
		for (int i = 0; i < mat.length; i++) {
			mat[i][colIndex] = values[i];
		}
	}

	/**
	 * Update a row in a 2d matrix
	 * 
	 * @param mat matrix to update
	 * @param rowIndex row index
	 * @param values values to be updated
	 */
	public void updateRowIn2dMatrix(boolean[][] mat, int rowIndex, boolean[] values) {
		for (int i = 0; i < mat.length; i++) {
			mat[rowIndex][i] = values[i];
		}
	}

	/**
	 * Rotate the corners of matrix 
	 * 
	 * @param mat matrix to be rotated
	 * @param steps number of times to rotate
	 * @param direction clockwise or anti-clockwise
	 * @param size size of the input matrix
	 * @return rotated matrix
	 */
	public static boolean[][] rotateMatrixCorners(boolean[][] mat, int steps, String direction, int size) {
		boolean[][] newMat = null;
		for (int i = 0; i < steps; i++) {
			if (direction == Keywords.CLOCK_WISE) {
				newMat = rotateCornersClockWise(mat, size);
			} else if (direction == Keywords.ANTI_CLOCK_WISE) {
				newMat = rotateCornersAntiClockWise(mat, size);
			}
		}

		return newMat;
	}

	/**
	 * Rotate the corners of matrix in clockwise manners
	 * 
	 * @param mat matrix to be rotated
	 * @param size size of matrix
	 * @return rotated matrix
	 */
	public static boolean[][] rotateCornersClockWise(boolean[][] mat, int size) {
		boolean[][] newMat = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMat[i][j] = mat[i][j];
			}
		}

		copyRowToColum(mat, newMat, 0, size - 1, size);
		copyColumnToRow(mat, newMat, size - 1, size - 1, size);
		reverseRow(newMat, size - 1, size);
		copyRowToColum(mat, newMat, size - 1, 0, size);
		copyColumnToRow(mat, newMat, 0, 0, size);
		reverseRow(newMat, 0, size);
		return newMat;
	}

	/**
	 * Rotate the corenrs of matrix in anti-clockwise manners
	 * 
	 * @param mat input matrix
	 * @param size size of matrix
	 * @return rotated matrix
	 */
	public static boolean[][] rotateCornersAntiClockWise(boolean[][] mat, int size) {
		boolean[][] newMat = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				newMat[i][j] = mat[i][j];
			}
		}

		copyRowToColum(mat, newMat, 0, 0, size);
		reverseCol(newMat, 0, size);
		copyColumnToRow(mat, newMat, 0, size - 1, size);
		copyRowToColum(mat, newMat, size - 1, size - 1, size);
		reverseCol(newMat, size - 1, size);
		copyColumnToRow(mat, newMat, size - 1, 0, size);

		return newMat;
	}

	/**
	 * Reverse the column in the given matrix
	 * 
	 * @param mat input matrix
	 * @param colIndex index of the column to be reversed
	 * @param rowLength length of the row in the input matrix
	 */
	public static void reverseCol(boolean[][] mat, int colIndex, int rowLength) {
		int lastIndex = rowLength - 1;
		for (int row = 0; row < rowLength / 2; row++) {
			boolean temp = mat[row][colIndex];
			mat[row][colIndex] = mat[lastIndex][colIndex];
			mat[lastIndex][colIndex] = temp;
			lastIndex--;
		}
	}

	/**
	 * Reverse the row in the given matrix
	 * 
	 * @param mat input matrix
	 * @param rowIndex index of row to be reversed
	 * @param colLength length of the column
	 */
	public static void reverseRow(boolean[][] mat, int rowIndex, int colLength) {
		int lastIndex = colLength - 1;
		for (int col = 0; col < colLength / 2; col++) {
			boolean temp = mat[rowIndex][col];
			mat[rowIndex][col] = mat[rowIndex][lastIndex];
			mat[rowIndex][lastIndex] = temp;
			lastIndex--;
		}
	}

	/**
	 * Copy specific row of matrix to a specifc column in matrix
	 * 
	 * @param toCopy matrix having row to be copied
	 * @param dest destination matrix
	 * @param rowIndex row index to be copied
	 * @param inCol column index in destination matrix
	 * @param colLength length of column
	 */
	public static void copyRowToColum(boolean[][] toCopy, boolean[][] dest, int rowIndex, int inCol, int colLength) {
		for (int j = 0; j < colLength; j++) {
			dest[j][inCol] = toCopy[rowIndex][j];
		}
	}

	/**
	 * Copy specific column of matrix to a specific row in matrix
	 * 
	 * @param toCopy matrix having column to be copied
	 * @param dest destination matrix
	 * @param colIndex column index
	 * @param row row index in destination matrix
	 * @param rowLength length of row
	 */
	public static void copyColumnToRow(boolean[][] toCopy, boolean[][] dest, int colIndex, int row, int rowLength) {
		for (int i = 0; i < rowLength; i++) {
			dest[row][i] = toCopy[i][colIndex];
		}
	}
	
	/**
	 * Copy row in a 2d matrix
	 * 
	 * @param mat input matrix
	 * @param values values to copy
	 * @param rowIndex index of row
	 */
	public static void copyRowIn2dMat(boolean[][] mat, boolean[]values, int rowIndex){
		for(int col=0;col<values.length;col++){
			mat[rowIndex][col] = values[col];
		}
	}
	
	/**
	 * Copy column in a 2d matrix
	 * 
	 * @param mat input matrix
	 * @param values values to copy
	 * @param colIndex index of column
	 */
	public static void copyColIn2dMat(boolean[][] mat, boolean[]values, int colIndex){
		for(int row=0;row<values.length;row++){
			mat[row][colIndex] = values[row];
		}
	}
	
	public static boolean[] reverseArray(boolean []values){
		boolean[]newArray = new boolean[values.length];
		int index = 0;
		for(int i=values.length-1;i>=0;i--){
			newArray[index] = values[i];
			index++;
		}
		return newArray;
	}
}
