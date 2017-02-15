package org.khan.solver.cubesolver.writer;


import java.util.Map;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;

public interface PuzzlePieceWriter {

	/**
	 * Write a puzzle piece in console, file or in any other required channel.
	 * 
	 * @param piece puzzle piece
	 */
	public void writePuzzlePiece(PuzzlePiece piece);
	
	/**
	 * Write a complete cube in the specified format.
	 * 
	 * @param cube
	 */
	public void writecube(Cube cube);

	/**
	 * Function will provide a required mapping for each value in puzzle piece, if required
	 * 
	 * @return map for providing mapping for each puzzle piece 
	 */
	public Map<String,String> getValueMap();
}
