package org.khan.solver.cubesolver.writer;

import java.util.HashMap;
import java.util.Map;

import org.khan.solver.cubesolver.Keywords;
import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;
import org.khan.solver.cubesolver.rotation.MatrixHelper;

public abstract class AbstractWriter implements PuzzlePieceWriter {

	public void writePuzzlePiece(PuzzlePiece piece) {
		boolean [][]matrix = piece.getTabsAndBlanksMatrix();
		Map<String,String> valueMap = getValueMap();
		printMatrix(matrix,piece.getPieceSize(), piece.getPieceSize(),valueMap);
	}

	public void writecube(Cube cube) {
		// create a huge puzzle piece in 2d matrix form
		int size = cube.getBottomface().getPieceSize();
		// three pieces in front row, where each piece is of size 5
		int cols = size*3;
		int rows = size*4;
		
		boolean [][]unfoldedCubeMat = new boolean[rows][cols];
		if(cube.getLeftface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getLeftface().getTabsAndBlanksMatrix(), 0, 0, size);
		if(cube.getBottomface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getBottomface().getTabsAndBlanksMatrix(), 0, size, size);
		if(cube.getRightface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getRightface().getTabsAndBlanksMatrix(), 0,size*2, size);
		if(cube.getFrontface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getFrontface().getTabsAndBlanksMatrix(), size, size, size);
		if(cube.getTopface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getTopface().getTabsAndBlanksMatrix(), size*2, size, size);
		if(cube.getBackface() != null)
			MatrixHelper.copyOneMatrixToAnother(unfoldedCubeMat, cube.getBackface().getTabsAndBlanksMatrix(), size*3, size, size);
		printMatrix(unfoldedCubeMat,rows,cols,getValueMap());
	}

	public Map<String, String> getValueMap() {
		Map<String,String> valueMap = new HashMap<String, String>();
		valueMap.put(Keywords.TRUE, "[]");
		valueMap.put(Keywords.FALSE, "  ");
		return valueMap;
	}


	public abstract void printMatrix(boolean [][] matrix, int rowSize, int colsize, Map<String,String> valueMap);
}