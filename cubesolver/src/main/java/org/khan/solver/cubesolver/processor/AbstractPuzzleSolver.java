package org.khan.solver.cubesolver.processor;

import java.util.ArrayList;
import java.util.List;

import org.khan.solver.cubesolver.puzzle.CompatabilityChecker;
import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;

/**
 * Abstract puzzle solver class to act as a template for cube solving algorithm, Wil ease
 * the task to add more algorithms if required in future.
 * 
 * @author SDK
 *
 */
public abstract class AbstractPuzzleSolver implements Strategy{

	// compatability checker
	protected CompatabilityChecker compatabilityChecker;
	// list to save cubes
	protected List<Cube> cubesList;
	
	public AbstractPuzzleSolver(CompatabilityChecker compatabilityChecker){
		this.compatabilityChecker = compatabilityChecker;
		cubesList = new ArrayList<Cube>();
	}
	
	/**
	 * Try different combinations to find cubes, Everytime start with a new cube assuming it as 
	 * bottom face.
	 */
	public void solvePuzzle(List<PuzzlePiece> pieces, boolean allcubes) {
		// trying every cube as a fix bottom cube, to try different possible combinations
		boolean cubeFound;
		int size = pieces.size();
		for(int i=0;i<size;i++) {
			List<PuzzlePiece> restOfPieces = getCopyOfList(pieces);
			PuzzlePiece assumedBottomPiece = restOfPieces.remove(i);
			cubeFound = createCubes(assumedBottomPiece, restOfPieces, allcubes);
			if(!allcubes && cubeFound)
				break;
		}
	}
	
	/**
	 * 
	 * @param pieces
	 * @return
	 */
	private List<PuzzlePiece> getCopyOfList(List<PuzzlePiece> pieces){
		List<PuzzlePiece> piecesList = new ArrayList<PuzzlePiece>();
		for(PuzzlePiece piece : pieces){
			PuzzlePiece copyPiece = new PuzzlePiece(piece);
			piecesList.add(copyPiece);
		}
		return piecesList;
	}
	
	/**
	 * Method to be implemented that will contain main logic to traverse pieces and find cubes
	 * 
	 * @param bottomPiece
	 * @param pieces
	 * @param allcubes
	 * @return
	 */
	public abstract boolean createCubes(PuzzlePiece bottomPiece, List<PuzzlePiece> pieces, boolean allcubes);
	
	/**
	 * 
	 * @return
	 */
	public List<Cube> getCubeList(){
		return cubesList;
	}
}
