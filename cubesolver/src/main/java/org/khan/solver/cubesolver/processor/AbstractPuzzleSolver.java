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
//		PuzzlePiece []pieceArray = new PuzzlePiece[6];
//		int j=0;
//		for(PuzzlePiece piece : pieces){
//			pieceArray[j] = piece;
//			j++;
//		}
//		List<List<PuzzlePiece>> possiblePermutaions = new ArrayList<List<PuzzlePiece>>();
//		permute(pieceArray,0,possiblePermutaions);
//		boolean cubeFound;
//		int size = pieces.size();
//		
//		for(List<PuzzlePiece> puzzlePieceList : possiblePermutaions){
//			PuzzlePiece assumedBottomPiece = puzzlePieceList.remove(0);
//			cubeFound = createCubes(assumedBottomPiece, puzzlePieceList, allcubes);
//			if(!allcubes && cubeFound)
//				break;
//		}
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
	
	 public void permute(PuzzlePiece[] a, int k, List<List<PuzzlePiece>> puzzlePieceCombinations) 
	    {
	        if (k == a.length) 
	        {
	        	List<PuzzlePiece> list = new ArrayList<PuzzlePiece>();
	            for (int i = 0; i < a.length; i++) 
	            {
	            	list.add(new PuzzlePiece(a[i]));
	                //System.out.print(" [" + a[i] + "] ");
	            }
	            puzzlePieceCombinations.add(list);
	        } 
	        else 
	        {
	            for (int i = k; i < a.length; i++) 
	            {
	            	PuzzlePiece temp = a[k];
	                a[k] = a[i];
	                a[i] = temp;
	 
	                permute(a, k + 1,puzzlePieceCombinations);
	 
	                temp = a[k];
	                a[k] = a[i];
	                a[i] = temp;
	            }
	        }
	    }
}
