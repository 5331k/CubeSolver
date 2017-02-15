package org.khan.solver.cubesolver.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSideKey;

/**
 * 
 * @author SDK
 *
 */
public class ProcessingState {
	
	int depthLevel;
	PuzzlePiece pieceToCheck;
	PuzzlePiece bottomPiece;
	Cube cube;
	
	// list of compatible ids against each side of bottom piece 
	Map<String, List<PieceSideKey>> sidesCompatibleIdsMap;
	
	public ProcessingState(int depthLevel, Cube cube, PuzzlePiece pieceToCheck, PuzzlePiece bottomPiece){
		this.depthLevel = depthLevel;
		this.cube = cube;
		this.pieceToCheck = pieceToCheck;
		this.bottomPiece = bottomPiece;
		sidesCompatibleIdsMap = new HashMap<String, List<PieceSideKey>>();
	}

	public void addSideComptibleID(String sideID, PieceSideKey compatibleSideKey){
		if(sidesCompatibleIdsMap.containsKey(sideID)){
			List<PieceSideKey> sideKeyList = sidesCompatibleIdsMap.get(sideID);
			sideKeyList.add(compatibleSideKey);
			sidesCompatibleIdsMap.put(sideID, sideKeyList);
		}
		else
		{
			List<PieceSideKey> sideKeyList = new ArrayList<PieceSideKey>();
			sidesCompatibleIdsMap.put(sideID, sideKeyList);
		}
	}

	public void addSideComptibleID(String sideID, List<PieceSideKey> compatibleSideKeyList){
		sidesCompatibleIdsMap.put(sideID, compatibleSideKeyList);
	}
	
	/**
	 * Always return the first object
	 * 
	 * @param sideID
	 * @return
	 */
	public PieceSideKey getCompatibleSideKey(String sideID){
		if(!sidesCompatibleIdsMap.containsKey(sideID))
			return null;
		List< PieceSideKey> sideKeyList = sidesCompatibleIdsMap.get(sideID);
		if(sideKeyList.isEmpty())
			return null;
		return sideKeyList.remove(0);
	}
	
	public boolean isCompatibleSideKeyListEmpty(){
		return sidesCompatibleIdsMap.isEmpty();
	}
}
