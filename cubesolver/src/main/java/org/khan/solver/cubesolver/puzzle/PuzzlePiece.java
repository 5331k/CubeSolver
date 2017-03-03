package org.khan.solver.cubesolver.puzzle;

import java.util.ArrayList;
import java.util.List;

import org.khan.solver.cubesolver.IDGenerator;
import org.khan.solver.cubesolver.Keywords;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSide;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSideKey;
import org.khan.solver.cubesolver.puzzle.piece.sides.SideNames;
import org.khan.solver.cubesolver.rotation.MatrixHelper;

/**
 * This class represents single piece of puzzle that comprises of four piece sides, representing corners of 
 * puzzle piece. Piece sides will play key role in joining puzzle piece to another. Joining four sides together
 * forms a complete puzzle piece in the form of 2d matrix.
 * 
 * Puzzle piece implements listener interface to keep itself updated for any updates.
 * 
 * @author SDK
 *
 */
public class PuzzlePiece {

	// Total number of sides in one single piece
	private final int NUMBER_OF_SIDES = 4;
	
	// Piece id to uniquely identify it
	private String uniqueID;
	// As, puzzle piece will always have equal sides, so one variable to represent size vertically/horizontally
	private int pieceSize;

	// Four sides of a puzzle piece
	protected PieceSide topSide;
	protected PieceSide bottomSide;
	protected PieceSide leftSide;
	protected PieceSide rightSide;
	
	// if the sides are updated to detect collision, initial matrix will be used for printing
	protected boolean [][] initialMatrix;
	
	/**
	 * Default constructor 
	 * 
	 * @param id unique id to represent puzzle piece
	 * @param values array of values representing filled tabs and empty blanks
	 * @param size size of puzzle piece sides
	 */
	public PuzzlePiece(boolean [][] values, int size){
		uniqueID = IDGenerator.getInstance().generateUniqueID();
		pieceSize = size;
		initAllSideObjects(values);
		initialMatrix = getTabsAndBlanksMatrix();
	}
	
	/**
	 * Copy constructor to create copy of this object
	 * 
	 * @param objectToCopy object of this class
	 */
	public PuzzlePiece(PuzzlePiece objectToCopy){
		uniqueID = objectToCopy.getUniqueID();
		pieceSize = objectToCopy.getPieceSize();
		topSide = new PieceSide(objectToCopy.topSide);
		bottomSide = new PieceSide(objectToCopy.bottomSide);
		leftSide = new PieceSide(objectToCopy.leftSide);
		rightSide = new PieceSide(objectToCopy.rightSide);
		initialMatrix = objectToCopy.initialMatrix;
	}
	

	/**
	 * Initialize all side objects
	 * 
	 * @param tabsAndBlanksMatrix  a 2d matrix that represents true for tab and false for a blank 
	 */
	private void initAllSideObjects(boolean [][] tabsAndBlanksMatrix){
		topSide = new PieceSide(SideNames.TOP.name(),this.getUniqueID(),MatrixHelper.getRowFrom2dMatrix(tabsAndBlanksMatrix, 0));
		bottomSide = new PieceSide( SideNames.BOTTOM.name(),this.getUniqueID(),MatrixHelper.getRowFrom2dMatrix(tabsAndBlanksMatrix, pieceSize-1));
		leftSide = new PieceSide( SideNames.LEFT.name(),this.getUniqueID(),MatrixHelper.getColumnFrom2dMatrix(tabsAndBlanksMatrix, 0));
		rightSide = new PieceSide( SideNames.RIGHT.name(),this.getUniqueID(),MatrixHelper.getColumnFrom2dMatrix(tabsAndBlanksMatrix, pieceSize -1));
	}
	
	/**
	 * Update values of all sides on the base of tabs and blanks matrix
	 * 
	 * @param tabsAndBlanksMatrix a 2d matrix that represents true for tab and false for a blank 
	 */
	public void updateSideValues(boolean [][] tabsAndBlanksMatrix) {
		topSide.setSideValues( MatrixHelper.getRowFrom2dMatrix(tabsAndBlanksMatrix, 0));
		bottomSide.setSideValues(MatrixHelper.getRowFrom2dMatrix(tabsAndBlanksMatrix, pieceSize-1));
		leftSide.setSideValues(MatrixHelper.getColumnFrom2dMatrix(tabsAndBlanksMatrix, 0));
		rightSide.setSideValues(MatrixHelper.getColumnFrom2dMatrix(tabsAndBlanksMatrix, pieceSize -1));
	}
	
	public boolean[][] getInitialMatrix(){
		return initialMatrix;
	}
	
	/**
	 * Get number of sides in one signle piece
	 * 
	 * @return number of sides
	 */
	public int getNumberOfSides() {
		return NUMBER_OF_SIDES;
	}
	
	/**
	 * Get unique id of piece
	 * 
	 * @return
	 */
	public String getUniqueID() {
		return uniqueID;
	}
	
	/**
	 * Check if all sides in the piece are connected to other pieces in cube
	 * 
	 * @return true if all sides are connected else false
	 */
	public boolean areAllSidesConnected() {
		return topSide.isConnected() && bottomSide.isConnected() && leftSide.isConnected() && rightSide.isConnected();
	}

	/**
	 * Once any side of a piece is connected it won't be rotateable any more, function will tell
	 * if piece is rotate able or not
	 * 
	 * @return true if any single side is connected else false
	 */
	public boolean isRotateable() {
		return !topSide.isConnected() && !bottomSide.isConnected() && !leftSide.isConnected()
				&& !rightSide.isConnected();
	}

	/**
	 * Get piece size
	 * 
	 * @return piece size
	 */
	public int getPieceSize() {
		return pieceSize;
	}


	/**
	 * 
	 * @return top side of piece
	 */
	public PieceSide getTopSide() {
		return topSide;
	}

	/**
	 * Set top side of puzzle piece
	 * 
	 * @param topSide
	 */
	public void setTopSide(PieceSide topSide) {
		this.topSide = topSide;
	}

	/**
	 * 
	 * @return bottom side of piece
	 */
	public PieceSide getBottomSide() {
		return bottomSide;
	}

	/**
	 * Set bottom side of piece
	 * 
	 * @param bottomSide
	 */
	public void setBottomSide(PieceSide bottomSide) {
		this.bottomSide = bottomSide;
	}

	/**
	 * 
	 * @return left side of piece
	 */
	public PieceSide getLeftSide() {
		return leftSide;
	}

	/**
	 * Set left side of piece
	 * 
	 * @param leftSide
	 */
	public void setLeftSide(PieceSide leftSide) {
		this.leftSide = leftSide;
	}

	/**
	 * 
	 * @return right side of puzzle piece
	 */
	public PieceSide getRightSide() {
		return rightSide;
	}

	/**
	 * Set right side of puzzle piece
	 * 
	 * @param rightSide
	 */
	public void setRightSide(PieceSide rightSide) {
		this.rightSide = rightSide;
	}

	/**
	 * Create tabs and blanks matrix to represent tabs and blanks in a puzzle piece
	 * via using boolean values. Coreners of puzzle piece are created via respective 
	 * piece side, the middle part in every piece comprises of tabs.
	 * 
	 * @return 2d boolean matrix
	 */
	public boolean[][] getTabsAndBlanksMatrix() {
		boolean [][]tabsAndBlanksMatrix = new boolean[pieceSize][pieceSize];
		// initialize whole mat with true
		for(int i=0;i<pieceSize;i++){
			for(int j=0;j<pieceSize;j++){
				tabsAndBlanksMatrix[i][j] = true;
			}
		}
		// now copy actual side values in array
		MatrixHelper.copyRowIn2dMat(tabsAndBlanksMatrix, this.topSide.getSideValues(), 0);
		MatrixHelper.copyRowIn2dMat(tabsAndBlanksMatrix, this.bottomSide.getSideValues(), pieceSize-1);
		MatrixHelper.copyColIn2dMat(tabsAndBlanksMatrix, this.leftSide.getSideValues(), 0);
		MatrixHelper.copyColIn2dMat(tabsAndBlanksMatrix, this.rightSide.getSideValues(), pieceSize-1);
		return tabsAndBlanksMatrix;
	}

	/**
	 * This function rotates puzzle piece to align the two connected sides, As, the side to which this piece is connected
	 * will remain static and rotating this piece to adjust properly. The rotation is required to make sure values in puzzle
	 * piece are in the form that represents a 3d view of cube.
	 * 
	 * Explanation: if I have two pieces P1 and P2, Now Left side of P2 can be joined to Top side of P1, So to make a join I 
	 * will rotate a piece P2 and join it to the top of P1, the other sides of P1 are also effected. To have this real time
	 * effect on the puzzle piece after rotation, we need to update side values that are done by this function.
	 * 
	 * @param sideToAlign side of this piece which is connected to the other piece
	 * @param connectedSideID side of some other piece which is connected by this piece
	 * @return side which should be presented by this piece after required rotation
	 */
	public String rotatePuzzlePieceToAlign(SideNames sideToAlign, String connectedSideID){
		// Align sides, so values are updated and next joins should be done on updated values
		boolean [][]tabsAndBlanksMatrix = this.getTabsAndBlanksMatrix();
		switch(SideNames.findEnum(connectedSideID)){
		case TOP:
			//The side which will be aligned to the top side of bottom piece must have its aligned side at bottom
			if(sideToAlign == SideNames.LEFT)
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.ANTI_CLOCK_WISE, pieceSize);
			else if(sideToAlign == SideNames.RIGHT) {
				tabsAndBlanksMatrix = MatrixHelper.rotateLeftToRigh(tabsAndBlanksMatrix, pieceSize);
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.ANTI_CLOCK_WISE, pieceSize);
			}
			else if(sideToAlign == SideNames.TOP)
				tabsAndBlanksMatrix = MatrixHelper.rotateUpSideDown(tabsAndBlanksMatrix,pieceSize);
			updateSideValues(tabsAndBlanksMatrix);
			return SideNames.BOTTOM.name();
		case BOTTOM:
			//The side which will be aligned to the bottom side of bottom piece must have its aligned side at top
			if(sideToAlign == SideNames.LEFT) {
				tabsAndBlanksMatrix = MatrixHelper.rotateLeftToRigh(tabsAndBlanksMatrix, pieceSize);
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.ANTI_CLOCK_WISE, pieceSize);
			}
			else if(sideToAlign == SideNames.RIGHT) 
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.ANTI_CLOCK_WISE, pieceSize);
			else if(sideToAlign == SideNames.BOTTOM)
				tabsAndBlanksMatrix = MatrixHelper.rotateUpSideDown(tabsAndBlanksMatrix,pieceSize);
			updateSideValues(tabsAndBlanksMatrix);
			return SideNames.TOP.name();
		case LEFT:
			//The side which will be aligned to the left side of bottom piece must have its aligned side at right
			if(sideToAlign == SideNames.LEFT)
				tabsAndBlanksMatrix = MatrixHelper.rotateLeftToRigh(tabsAndBlanksMatrix, pieceSize);
			else if(sideToAlign == SideNames.TOP)
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.CLOCK_WISE, pieceSize);
			else if(sideToAlign == SideNames.BOTTOM) {
				tabsAndBlanksMatrix = MatrixHelper.rotateUpSideDown(tabsAndBlanksMatrix,pieceSize);
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.CLOCK_WISE, pieceSize);
			}
			updateSideValues(tabsAndBlanksMatrix);
			return SideNames.RIGHT.name();
		case RIGHT:
			//The side which will be aligned to the left side of bottom piece must have its aligned side at left
			if(sideToAlign == SideNames.BOTTOM)
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.CLOCK_WISE, pieceSize);
			else if(sideToAlign == SideNames.RIGHT)
				tabsAndBlanksMatrix = MatrixHelper.rotateLeftToRigh(tabsAndBlanksMatrix, pieceSize);
			else if(sideToAlign == SideNames.TOP) {
				tabsAndBlanksMatrix = MatrixHelper.rotateUpSideDown(tabsAndBlanksMatrix,pieceSize);
				tabsAndBlanksMatrix = MatrixHelper.rotateMatrixCorners(tabsAndBlanksMatrix, 1, Keywords.CLOCK_WISE, pieceSize);
			}
			updateSideValues(tabsAndBlanksMatrix);
			return SideNames.LEFT.name();
		default:
			break;
		}
		return connectedSideID;
	}
	
	/**
	 * Get only those sides which are not yet connected
	 * 
	 * @return list of free piece side objects 
	 */
	public List<PieceSide> getFreeSides(){
		List<PieceSide> freeSides = new ArrayList<PieceSide>();
		if(!topSide.isConnected())
			freeSides.add(topSide);
		else if(!bottomSide.isConnected())
			freeSides.add(bottomSide);
		else if(!leftSide.isConnected())
			freeSides.add(leftSide);
		else if(!rightSide.isConnected())
			freeSides.add(rightSide);
		
		return freeSides;
	}

	/**
	 * Get the respective side of piece based on piece id
	 * 
	 * @param id puzzle piece id a string value
	 * @return repective piece side object
	 */
	public PieceSide getSideByID(String id){
		if(topSide.getSideID().equals(id))
			return topSide;
		else if(bottomSide.getSideID().equals(id))
			return bottomSide;
		else if(leftSide.getSideID().equals(id))
			return leftSide;
		else if(rightSide.getSideID().equals(id))
			return rightSide;
		return null;
	}
	
	/**
	 * Get piece side by index
	 * 
	 * @param index integer value in range 0-3
	 * @return piece side object
	 */
	public PieceSide getSideByIndex(int index){
		if(index >= NUMBER_OF_SIDES || index < 0)
			throw new IllegalArgumentException();
		switch(index){
		case 0:
			return topSide;
		case 1:
			return rightSide;
		case 2:
			return leftSide;
		case 3:
			return bottomSide;
		default:
			return null;
		}
	}
	
	/**
	 * Get the connected side of this piece for the given side.
	 * 
	 * @param sideID
	 * @return
	 */
	public String getConnectedSideWithThisSide(String sideID){
		for(int i=0;i<NUMBER_OF_SIDES;i++){
			PieceSide side = getSideByIndex(i);
			if(side.getConnectedSideKey().getSideID().equals(sideID))
				return side.getSideID();
		}
		return "";
	}
	
	public void updateStatus(boolean [][]tabsAndBlanksMatrix, String sideID, PieceSideKey connectedSideKey){
		updateSideValues(tabsAndBlanksMatrix);
		PieceSide side = this.getSideByID(sideID);
		side.setConnected(true);
		side.setConnectedSideKey(connectedSideKey);
	}
	
	/**
	 * Updating one side has effect on other sides, so we handle all the cases
	 */
	public void synchronizeSide(PieceSide updatedSide, boolean []updateValues){
		updatedSide.setSideValues(updateValues);
		if(updatedSide.getSideID().equals(SideNames.BOTTOM.name())){
			// update corenrs of left and right side, because they are shared with bottom side
			leftSide.getSideValues()[pieceSize - 1] = updatedSide.getSideValues()[0];
			rightSide.getSideValues()[pieceSize - 1] = updatedSide.getSideValues()[pieceSize-1];
		}
		else if(updatedSide.getSideID().equals(SideNames.TOP.name())){
			// update corenrs of left and right side, because they are shared with top side
			leftSide.getSideValues()[0] = updatedSide.getSideValues()[0];
			rightSide.getSideValues()[0] = updatedSide.getSideValues()[pieceSize-1];
		}
		else if(updatedSide.getSideID().equals(SideNames.LEFT.name())){
			// update corenrs of top and bottom side, because they are shared with left side
			topSide.getSideValues()[0] = updatedSide.getSideValues()[0];
			bottomSide.getSideValues()[0] = updatedSide.getSideValues()[pieceSize-1];
		}
		else if(updatedSide.getSideID().equals(SideNames.RIGHT.name())){
			// update corenrs of top and bottom side, because they are shared with right side
			topSide.getSideValues()[pieceSize-1] = updatedSide.getSideValues()[0];
			bottomSide.getSideValues()[pieceSize-1] = updatedSide.getSideValues()[pieceSize-1];
		}
	}
	
}
