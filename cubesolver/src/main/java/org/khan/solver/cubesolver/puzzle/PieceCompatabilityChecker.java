package org.khan.solver.cubesolver.puzzle;


import java.util.ArrayList;
import java.util.List;

import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSide;

/**
 * Class implementing compatability checker interface to provide required business logic for piece compatabilities 
 * 
 * @author SDK
 *
 */
public class PieceCompatabilityChecker implements CompatabilityChecker {
	
	/**
	 * Tow sides are compatible if they are not already connected and have compatible values
	 */
	public boolean areSidesCompatible(PieceSide side1, PieceSide side2){
		boolean sidesCompatible = true;
		if(side1.isConnected() || side2.isConnected())
			sidesCompatible = false;
		else{
			return areValuesCompatible(side1.getSideValues(),side2.getSideValues());
		}
		return sidesCompatible;
	}
	
	/**
	 * Two side values are compatible if they don't have any tabs/true at same position
	 */
	public boolean areValuesCompatible(boolean []side1Values, boolean[]side2Values){
		boolean sidesCompatible = true;
		if(side1Values.length != side2Values.length)
			sidesCompatible = false;
		else {
			// two blocks on same position
			for (int i = 0; i < side1Values.length; i++) {
				if(side1Values[i] == false && side2Values[i] == false && i != 0 && i!= side1Values.length-1) {
					sidesCompatible = false;
					break ;
				}
				else if (side1Values[i] == true && side2Values[i] == true) {
					sidesCompatible = false;
					break;
				}
			}
		}
		return sidesCompatible;
	}

	/**
	 * Create a list of compatible sides in a puzzle piece against the given puzzle piece
	 */
	public List<String> getCompatibleSideIDs(PuzzlePiece srcPiece, PieceSide sideToCheck){
		List<String> compatibleSideIDs = new ArrayList<String>();
		for(int i=0;i<srcPiece.getNumberOfSides();i++){
			PieceSide srcSide = srcPiece.getSideByIndex(i);
			if(areSidesCompatible(srcSide, sideToCheck))
				compatibleSideIDs.add(srcSide.getSideID());
		}
		return compatibleSideIDs;
	}
}
