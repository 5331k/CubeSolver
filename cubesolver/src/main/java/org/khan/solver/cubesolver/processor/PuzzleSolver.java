package org.khan.solver.cubesolver.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.khan.solver.cubesolver.puzzle.CompatabilityChecker;
import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;
import org.khan.solver.cubesolver.puzzle.piece.RotationForm;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSide;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSideKey;
import org.khan.solver.cubesolver.puzzle.piece.sides.SideNames;
import org.khan.solver.cubesolver.rotation.MatrixHelper;

/**
 * Puzzle solver class that implements the main algorithm, inspired by depth first search.
 * Class also act as a mediator to update puzzle piece objects and cube object. An alternative approach
 * would be to use observer pattern, however it will be a little more over kill, Using this class as mediator
 * to update puzzle piece objects and cube when it is required.
 * 
 * Currently, implemented algorithm finds only one unique cube and returns, however flexible enough to be changed
 * for finding all the cubes
 * 
 * @author SDK
 *
 */
public class PuzzleSolver extends AbstractPuzzleSolver {

	public PuzzleSolver(CompatabilityChecker compatabilityChecker) {
		super(compatabilityChecker);
	}

	/**
	 * Main algorithm that works in a depth first manner, it has following main intutive steps.
	 * 
	 * 1: Make bottom piece as a fixed piece (try all other pieces to join at each side of bottom pieces).
	 * 2: Take a piece out of list.
	 * 3: Before taking any decision, create a processing state, saving current bottom piece, piece taken from list, cube state and the list of
	 *    possible options that can be taken.
	 * 4: Now take a decision of joining a piece at possible side of bottom piece.
	 * 5: Verify decision, by checking if it gets fit in with already connected sides in cube.
	 * 6: If decision is verified, then
	 * 7: check if all sides of bottom piece are well joined, if yes then check the only left piece as a top piece. if it fits
	 *    as a top piece, then cube is complete else 
	 * 8: If step 7 is not done then, store the processing state in stack, update cube state and move forward, start from step 2 again. 
	 * 9: If decision is not verified then try all possible combinations one by one, if found do step 6, otherwise pop the processing state
	 * from stack and try a new decision, Do step 6.
	 */
	@Override
	public boolean createCubes(PuzzlePiece fixedPiece, List<PuzzlePiece> pieces, boolean allCubes) {
		Cube cube = new Cube();
		// initialize depth level
		int depthLevel = 0;
		// to allow/disallow backtracking
		boolean backTrack = false;
		// piece to be joined in cube
		PuzzlePiece pieceUnderObservation = null;
		// current processing states
		ProcessingState processingState = null;
		// stack to hold processing states
		Stack<ProcessingState> processingStateStack = new Stack<ProcessingState>();
		// make bottom piece a fixed piece
		cube.setBottomface(fixedPiece);
		// try to make a cube until 
		boolean  cubeComplete = false;
		while (cubeComplete == false) {
			// otherwise get from stack
			if (backTrack == false) {
				// always get the first piece
				pieceUnderObservation = new PuzzlePiece(pieces.get(depthLevel));
				// create a processing state, with all possible combinations
				processingState = createProcessingState(depthLevel, cube, pieceUnderObservation, fixedPiece);
			}
			else{
				// keep old compatabilities map but copy all other objects before they get changed
				ProcessingState newProcessingState = new ProcessingState(depthLevel, new Cube(cube), new PuzzlePiece(pieceUnderObservation),new PuzzlePiece(fixedPiece));
				newProcessingState.sidesCompatibleIdsMap = processingState.sidesCompatibleIdsMap;
				processingState = newProcessingState;
			}
			
			boolean verified = false, failure = false;
			// check each side one by one
			for (int i = 0; i < fixedPiece.getNumberOfSides(); i++) {
				// get side to check
				PieceSide sideToCheck = fixedPiece.getSideByIndex(i);
				// check until found one side which is vierifed
				while (processingState.isCompatibleSideKeyListEmpty() == false) {
					PieceSideKey compatibleSideKey = processingState.getCompatibleSideKey(sideToCheck.getSideID());
					if (compatibleSideKey == null)
						break;
					if( verifyCompatibleSideKeyAndPerformUpdate(cube, fixedPiece, pieceUnderObservation, SideNames.findEnum(sideToCheck.getSideID()),compatibleSideKey,false)) {
						cube.setBottomface(fixedPiece);
						verified = true;
						// successful decision save state, move forward.
						depthLevel++;
						processingStateStack.push(processingState);
						backTrack = false;
						
						if(fixedPiece.areAllSidesConnected()){
							if(checkTopPieceAndPerformUpdate(cube,pieces.get(depthLevel)) == true) {
								cubesList.add(new Cube(cube));
								cubeComplete = true;
							}
							else
								failure = true;
						}
						// break inner loop
						break;
					}
				}
				// break the outer loop
				if(verified == true)
					break;
			}

			// decide to move forward or backward
			 if(verified == false || failure) {
				if(processingStateStack.isEmpty())
					break;
				depthLevel--;
				backTrack = true;
				processingState = processingStateStack.pop();
				cube  = processingState.cube;
				pieceUnderObservation = processingState.pieceToCheck;
				fixedPiece = processingState.bottomPiece;
			}
		}
		
		return cubeComplete;
	}

	/**
	 * Check if given piece can be joined as a top piece in the cube
	 * 
	 * @param cube
	 * @param pieceToCheck
	 * @return
	 */
	protected boolean checkTopPieceAndPerformUpdate(Cube cube,PuzzlePiece pieceToCheck){
		// get free sides to check compatability for top face
		List<PieceSide> freeSides = cube.getFreeSidesInCompleteCubeExceptThisPiece(pieceToCheck.getUniqueID());
		for(PieceSide sideToCheck : freeSides) {
			// All this pieces are are already fixed, so no more rotation allowed
			List<PieceSideKey> compatibleSideKeyList = new ArrayList<PieceSideKey>();
			compatibleSideKeyList.addAll(createCompatibleSideKeyList(pieceToCheck, sideToCheck));
			for(PieceSideKey compatibleSideKey : compatibleSideKeyList){
				if (compatibleSideKey != null) {
					if( verifyCompatibleSideKeyAndPerformUpdate(cube,cube.getPieceByID(sideToCheck.getParentID()), pieceToCheck,SideNames.findEnum(sideToCheck.getSideID()),compatibleSideKey,true)) {
						// now finally rotate top face to align its connected side with bottom of front face, so, we
						// don't need to do any extra work while printing in unfolded form.
						String sideid = pieceToCheck.getConnectedSideWithThisSide(SideNames.BOTTOM.name());
						pieceToCheck.rotatePuzzlePieceToAlign(SideNames.findEnum(sideid),SideNames.BOTTOM.name());
						cube.setTopface(pieceToCheck);
						return  true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Rotate puzzle piece in the identified direction
	 * 
	 * @param piece
	 * @param rotation
	 */
	protected PuzzlePiece getRotatedPiece(PuzzlePiece piece, RotationForm rotation){
		PuzzlePiece rotatedPiece = new PuzzlePiece(piece);
		boolean [][]tabsAndBlanksMatrix ;
		switch(rotation){
		case NORMAL:
			break;
		case LEFTTORIGHT:
			tabsAndBlanksMatrix = MatrixHelper.rotateLeftToRigh(rotatedPiece.getTabsAndBlanksMatrix(), piece.getPieceSize());
			rotatedPiece.updateSideValues(tabsAndBlanksMatrix);
			break;
		case UPSIDEDOWN:
			tabsAndBlanksMatrix = MatrixHelper.rotateUpSideDown(rotatedPiece.getTabsAndBlanksMatrix(), piece.getPieceSize());
			rotatedPiece.updateSideValues(tabsAndBlanksMatrix);
			break;
		default:
			break;
		}
		return rotatedPiece;
	}
	
	/**
	 * Verify that the decision is possible to take by checking the compatability with the sides of other adjacent pieces
	 * already connected in cube
	 * 
	 * @param cube instance of cube, will be updated, if verification is true
	 * @param srcPiece piece with whom other piece is required to be connected
	 * @param joinedPiece fresh piece that is required to be joined with source piece and need to be added in cube
	 * @param toConnectedSide the side of srcPiece that is compatible with joinedPiece
	 * @param compatibleSideKey the compatible side information of joined piece
	 * @param topPiece flag to indicate if joinedPiece is a top piece or not
	 * @return
	 */
	protected boolean verifyCompatibleSideKeyAndPerformUpdate(Cube cube, PuzzlePiece srcPiece, PuzzlePiece joinedPiece, SideNames toConnectedSide, PieceSideKey compatibleSideKey, boolean topPiece) {
		boolean update = true;
		// get the rotated piece and update side direction, after rotation
		PuzzlePiece rotatedPiece= getRotatedPiece(joinedPiece, RotationForm.findEnum(compatibleSideKey.getRotationForm()));
		String alignedID = rotatedPiece.rotatePuzzlePieceToAlign(SideNames.findEnum(compatibleSideKey.getSideID()),toConnectedSide.name());
		compatibleSideKey.setSideID(alignedID);
		// If sides are compatible then check the adjacent pieces in cube, if they are okay with the new join, and there is no conflict
		Map<PieceSide,String> requiredSideConnectedMap = cube.getConnectedSides(srcPiece, toConnectedSide, SideNames.findEnum(compatibleSideKey.getSideID()),topPiece);
		update = checkAdjacentSides(requiredSideConnectedMap, rotatedPiece);
		// if everything is fine then finally perform the update
		if (update)
			performUpdate(cube, srcPiece, joinedPiece, rotatedPiece, toConnectedSide, compatibleSideKey, requiredSideConnectedMap, topPiece);
		return update;
	}
	
	/**
	 * Update all the objects 
	 * @param cube
	 * @param srcPiece
	 * @param joinedPiece
	 * @param rotatedPiece
	 * @param toConnectedSide
	 * @param compatibleSideKey
	 * @param requiredSideConnectedMap
	 */
	protected void performUpdate(Cube cube, PuzzlePiece srcPiece, PuzzlePiece joinedPiece, PuzzlePiece rotatedPiece, SideNames toConnectedSide, PieceSideKey compatibleSideKey, Map<PieceSide,String> requiredSideConnectedMap, boolean topPiece){
		joinedPiece.updateStatus(rotatedPiece.getTabsAndBlanksMatrix(), compatibleSideKey.getSideID(),new PieceSideKey(srcPiece.getUniqueID(), toConnectedSide.name()));
		srcPiece.updateStatus(srcPiece.getTabsAndBlanksMatrix(), toConnectedSide.name(), new PieceSideKey(compatibleSideKey));
		if(topPiece == false)
			cube.addPiece(toConnectedSide, joinedPiece);
		for(PieceSide sideOfAdjacentPiece : requiredSideConnectedMap.keySet()){
			String sideToMatchID = requiredSideConnectedMap.get(sideOfAdjacentPiece);
			PuzzlePiece piece = cube.getPieceByID(sideOfAdjacentPiece.getParentID());
			piece.updateStatus(piece.getTabsAndBlanksMatrix(), sideOfAdjacentPiece.getSideID(), new PieceSideKey(joinedPiece.getUniqueID(),sideToMatchID) );
			joinedPiece.updateStatus(joinedPiece.getTabsAndBlanksMatrix(), sideToMatchID, new PieceSideKey(piece.getUniqueID(),sideOfAdjacentPiece.getSideID()));
		}
	}


	/**
	 * Check in the given piece side matched to the required side of joined piece.
	 * 
	 * @param requiredSideConnectedMap
	 * @param joinedPiece
	 * @return true if all sides in the given map compatible with the given sides of joined piece
	 */
	protected boolean checkAdjacentSides(Map<PieceSide,String> requiredSideConnectedMap, PuzzlePiece joinedPiece){
		boolean update = true;
		for(PieceSide sideOfAdjacentPiece : requiredSideConnectedMap.keySet()){
			String sideToMatchID = requiredSideConnectedMap.get(sideOfAdjacentPiece);
			PieceSide sideToMatch = joinedPiece.getSideByID(sideToMatchID);
			if(!compatabilityChecker.areSidesCompatible(sideOfAdjacentPiece, sideToMatch)) {
				update = false;
				break;
			}
		}
		return update;
	}
	
	/**
	 * 
	 * @param depthLevel
	 * @param cube
	 * @param pieceToCheck
	 * @param bottomPiece
	 * @return
	 */
	protected ProcessingState createProcessingState(int depthLevel, Cube cube, PuzzlePiece pieceToCheck,
			PuzzlePiece bottomPiece) {
		// create processing state
		ProcessingState state = new ProcessingState(depthLevel, new Cube(cube), new PuzzlePiece(pieceToCheck),
				new PuzzlePiece(bottomPiece));
		// check all possible combinations of this piece with source piece
		for (int i = 0; i < bottomPiece.getNumberOfSides(); i++) {
			//List<PieceSideKey> compatibleSideKeyList = new ArrayList<PieceSideKey>();
			PieceSide sideToCheck = bottomPiece.getSideByIndex(i);
			state.addSideComptibleID(sideToCheck.getSideID(), createCompatibleSideKeyList(pieceToCheck,sideToCheck));
		}
		return state;
	}
	
	/**
	 * Each piece can bo joined with other piece in three different forms, check all the forms
	 * and compute possible sides that are compatible.
	 * 
	 * @param pieceToCheck
	 * @param sideToCheck
	 * @return
	 */
	protected List<PieceSideKey> createCompatibleSideKeyList(PuzzlePiece pieceUnderObservation, PieceSide sideToCheck){
		List<PieceSideKey> compatibleSideKeyList = new ArrayList<PieceSideKey>();
		compatibleSideKeyList.addAll(checkInNormalForm(pieceUnderObservation, sideToCheck));
		compatibleSideKeyList.addAll(checkInLeftToRightRotatedForm(pieceUnderObservation, sideToCheck));
		compatibleSideKeyList.addAll(checkInUpsideDownRotatedForm(pieceUnderObservation, sideToCheck));
		return compatibleSideKeyList;
	}
	
	/**
	 * Check piece under observation without any rotation and compute the list of compatible side keys
	 * 
	 * @param srcPiece
	 * @param sideToCheck
	 * @return
	 */
	protected List<PieceSideKey> checkInNormalForm(PuzzlePiece srcPiece, PieceSide sideToCheck) {
		List<String> sideIDs = compatabilityChecker.getCompatibleSideIDs(srcPiece, sideToCheck);
		return createPieceSideKeyList(sideIDs, srcPiece.getUniqueID(), RotationForm.NORMAL.name());
	}
	
	/**
	 * Check piece under observation after rotating left to right and compute the list of compatible side keys
	 * 
	 * @param srcPiece
	 * @param sideToCheck
	 * @return
	 */
	protected List<PieceSideKey> checkInLeftToRightRotatedForm(PuzzlePiece srcPiece, PieceSide sideToCheck) {
		PuzzlePiece rotatedPiece =new PuzzlePiece(srcPiece);
		boolean[][] tabsAndBlanks = rotatedPiece.getTabsAndBlanksMatrix();
		tabsAndBlanks = MatrixHelper.rotateLeftToRigh(tabsAndBlanks, rotatedPiece.getPieceSize());
		rotatedPiece.updateSideValues(tabsAndBlanks);
		List<String> sideIDs = compatabilityChecker.getCompatibleSideIDs(rotatedPiece, sideToCheck);
		return createPieceSideKeyList(sideIDs, rotatedPiece.getUniqueID(), RotationForm.LEFTTORIGHT.name());
	}

	/**
	 * Check piece under observation after rotating upside down and compute the list of compatible side keys
	 *  
	 * @param srcPiece
	 * @param sideToCheck
	 * @return
	 */
	protected List<PieceSideKey> checkInUpsideDownRotatedForm(PuzzlePiece srcPiece, PieceSide sideToCheck) {
		PuzzlePiece rotatedPiece =new PuzzlePiece(srcPiece);
		boolean[][] tabsAndBlanks = rotatedPiece.getTabsAndBlanksMatrix();
		tabsAndBlanks = MatrixHelper.rotateUpSideDown(tabsAndBlanks, rotatedPiece.getPieceSize());
		rotatedPiece.updateSideValues(tabsAndBlanks);
		List<String> sideIDs = compatabilityChecker.getCompatibleSideIDs(rotatedPiece, sideToCheck);
		return createPieceSideKeyList(sideIDs, rotatedPiece.getUniqueID(), RotationForm.UPSIDEDOWN.name());
	}
	
	/**
	 * Create a list of piece side keys from the given list of side ids
	 * 
	 * @param sideIDs
	 * @param parentID
	 * @param rotation
	 * @return
	 */
	protected List<PieceSideKey> createPieceSideKeyList(List<String> sideIDs, String parentID, String rotation) {
		List<PieceSideKey> compatibleSideKeyList = new ArrayList<PieceSideKey>();
		for (String sideid : sideIDs) {
			PieceSideKey sideKey = new PieceSideKey(parentID, sideid);
			sideKey.setRotationForm(rotation);
			compatibleSideKeyList.add(sideKey);
		}
		return compatibleSideKeyList;
	}
	
}
