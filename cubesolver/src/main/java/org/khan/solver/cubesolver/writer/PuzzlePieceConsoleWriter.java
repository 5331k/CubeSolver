package org.khan.solver.cubesolver.writer;


import java.util.Map;

/**
 * This class implements required functions to display puzzle piece and cube on console
 * 
 * @author SDK
 *
 */
public class PuzzlePieceConsoleWriter extends AbstractWriter {
	
	
	@Override
	public void printMatrix(boolean[][] matrix, int rowSize, int colsize, Map<String, String> valueMap) {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colsize; j++) {
				Boolean val = matrix[i][j];
				System.out.print(valueMap.get(val.toString()));
			}
			System.out.println();
		}
		
	}

}
