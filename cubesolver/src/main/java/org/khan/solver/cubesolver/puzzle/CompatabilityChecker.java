package org.khan.solver.cubesolver.puzzle;

import java.util.List;

import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSide;

/**
 * 
 * @author SDK
 *
 */
public interface CompatabilityChecker {

	/**
	 * Check if two sides are compatible to get joined together.
	 * 
	 * @param side1 PieceSide object
	 * @param side2 PieceSide object
	 * @return true if compatible else false
	 */
	boolean areSidesCompatible(PieceSide side1, PieceSide side2);

	/**
	 * Check if the given side values are compatible with each other or not
	 * 
	 * @param side1Values boolean array
	 * @param side2Values boolean array
	 * @return true if compatible else false
	 */
	boolean areValuesCompatible(boolean[] side1Values, boolean[] side2Values);
	
	/**
	 * Check all the possible compatible sides in the given puzzle piece and return their ids
	 * 
	 * @param srcPiece Puzzle piece to be checked
	 * @param sideToCheck PieceSide which needs to be compatible with other sides of the given piece
	 * @return list of piece ids
	 */
	public List<String> getCompatibleSideIDs(PuzzlePiece srcPiece, PieceSide sideToCheck);

}