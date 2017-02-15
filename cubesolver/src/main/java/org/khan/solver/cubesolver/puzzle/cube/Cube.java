package org.khan.solver.cubesolver.puzzle.cube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.khan.solver.cubesolver.Keywords;
import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSide;
import org.khan.solver.cubesolver.puzzle.piece.sides.PieceSideKey;
import org.khan.solver.cubesolver.puzzle.piece.sides.SideNames;

/**
 * This class represents a cube having all the required objects. A complete cube is represented
 * by six puzzle pieces joined together. Each puzzle piece is assigned to a specific position in
 * a cube. Class also provides function to get sides of different pieces which needed to be joined
 * to make a complete cube. 
 * 
 * @author SDK
 *
 */
public class Cube {

	// cube bottom face
	private PuzzlePiece bottomface;
	// left face of cube
	private PuzzlePiece leftface;
	// right face of cube
	private PuzzlePiece rightface;
	// front face of cube
	private PuzzlePiece frontface;
	// top face of cube
	private PuzzlePiece topface;
	// bakc face of cube
	private PuzzlePiece backface;
	// map referencing same objects of cube to provide easy access to different faces of cube based on id 
	private Map<String, PuzzlePiece> puzzlePieceMap;
	
	/**
	 * Copy constructor 
	 * 
	 * @param objectToCopy
	 */
	public Cube(Cube objectToCopy) { 
		puzzlePieceMap = new HashMap<String, PuzzlePiece>();
		if(objectToCopy.bottomface != null)
			setBottomface(new PuzzlePiece(objectToCopy.bottomface));
		if(objectToCopy.leftface != null)
			setLeftface(new PuzzlePiece(objectToCopy.leftface));
		if(objectToCopy.rightface != null)
			setRightface(new PuzzlePiece(objectToCopy.rightface));
		if(objectToCopy.frontface != null)
			setFrontface(new PuzzlePiece(objectToCopy.frontface));
		if(objectToCopy.topface != null)
			setTopface(new PuzzlePiece(objectToCopy.topface));
		if(objectToCopy.backface != null)
			setBackface(new PuzzlePiece(objectToCopy.backface));
	}
	
	/**
	 * Default constructor
	 */
	public Cube() {
		bottomface = null;
		leftface = null;
		rightface = null;
		frontface = null;
		topface = null;
		backface = null;
		puzzlePieceMap = new HashMap<String, PuzzlePiece>();
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getBottomface() {
		return bottomface;
	}
	
	/**
	 * 
	 * @param bottomface
	 */
	public void setBottomface(PuzzlePiece bottomface) {
		this.bottomface = bottomface;
		puzzlePieceMap.put(bottomface.getUniqueID(), bottomface);
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getLeftface() {
		return leftface;
	}
	
	/**
	 * 
	 * @param leftface
	 */
	public void setLeftface(PuzzlePiece leftface) {
		this.leftface = leftface;
		puzzlePieceMap.put(leftface.getUniqueID(), leftface);
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getRightface() {
		return rightface;
	}
	
	/**
	 * 
	 * @param rightface
	 */
	public void setRightface(PuzzlePiece rightface) {
		this.rightface = rightface;
		puzzlePieceMap.put(rightface.getUniqueID(), rightface);
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getFrontface() {
		return frontface;
	}
	
	/**
	 * 
	 * @param frontface
	 */
	public void setFrontface(PuzzlePiece frontface) {
		this.frontface = frontface;
		puzzlePieceMap.put(frontface.getUniqueID(), frontface);
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getTopface() {
		return topface;
	}
	
	/**
	 * 
	 * @param topface
	 */
	public void setTopface(PuzzlePiece topface) {
		this.topface = topface;
		puzzlePieceMap.put(topface.getUniqueID(), topface);
	}
	
	/**
	 * 
	 * @return
	 */
	public PuzzlePiece getBackface() {
		return backface;
	}
	
	/**
	 * 
	 * @param backface
	 */
	public void setBackface(PuzzlePiece backface) {
		this.backface = backface;
		puzzlePieceMap.put(backface.getUniqueID(), backface);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public PuzzlePiece getPieceByID(String id){
		return puzzlePieceMap.get(id);
	}
	
	/**
	 * This function will check which side is recently connected to which and then will return 
	 * a map providing instructions that which other sides should also conform to the side of
	 * connected piece to have smooth joining.
	 * 
	 * Example If Piece1 is connected to Top side then it will check left and right side of
	 * cube, if there are pieces on left and right side then it will provide the respective
	 * side of those pieces that must conform to the respective side of recently connected
	 * piece.
	 * 
	 * Note: This function works, assuming a cube has valid BottomFace
	 * 
	 * @param connectedSideName
	 * @param connectedSide
	 * @return
	 */
	/**
	 * This function will check which side is recently connected to the under observation piece and then will return 
	 * a map providing instructions that which other sides should also conform to the side of
	 * connected piece to have smooth joining.
	 * 
	 * Example If Piece1 is connected to Top side then it will check left and right side of
	 * cube, if there are pieces on left and right side then it will provide the respective
	 * side of those pieces that must conform to the respective side of recently connected
	 * piece.
	 * 
	 * @param pieceToObserve puzzle piece around which sides will be observed
	 * @param sideOfObservingPiece connected side of piece under observation
	 * @param connectedSide the side of other piece which is connected to side of piece under observation
	 * @param top flag to indicate if its a top side, then we have to check all four sides of already connected pieces
	 * @return
	 */
	public Map<PieceSide,String>  getConnectedSides(PuzzlePiece pieceToObserve,SideNames sideOfObservingPiece, SideNames connectedSide, boolean top){
		Map<PieceSide,String> requiredConnectedSidesMap = new HashMap<PieceSide, String>();
		switch(sideOfObservingPiece){
		case TOP:
			// check left side but in anti-clock wise manner
			checkLeftSidePiece(requiredConnectedSidesMap,pieceToObserve, connectedSide, Keywords.ANTI_CLOCK_WISE);
			// check right side but in clock wise manner
			checkRightSidePiece(requiredConnectedSidesMap,pieceToObserve, connectedSide, Keywords.CLOCK_WISE);
			// if its a top piece then check the last left side, where it needs to be joined
			if(top) {
				String sideToJoin = getMissingSide(requiredConnectedSidesMap,connectedSide);
				checkTopPieceSide(requiredConnectedSidesMap,getFrontface().getUniqueID(), SideNames.BOTTOM.name(),sideToJoin);
			}
			break;
		case BOTTOM:
			// check left side but in clock wise manner
			checkLeftSidePiece(requiredConnectedSidesMap, pieceToObserve,connectedSide, Keywords.CLOCK_WISE);
			// check right side but in anti-clock wise manner
			checkRightSidePiece(requiredConnectedSidesMap,pieceToObserve, connectedSide, Keywords.ANTI_CLOCK_WISE);
			if(top) {
				String sideToJoin = getMissingSide(requiredConnectedSidesMap,connectedSide);
				if(getBackface() == null)
					System.out.println("");
				checkTopPieceSide(requiredConnectedSidesMap,getBackface().getUniqueID(), SideNames.TOP.name(),sideToJoin);
			}
			break;
		case LEFT:
			// check top side but in clock wise manner
			checkTopSidePiece(requiredConnectedSidesMap, pieceToObserve,connectedSide, Keywords.CLOCK_WISE);
			// check bottom side but in anti-clock wise manner
			checkBottomSidePiece(requiredConnectedSidesMap, pieceToObserve,connectedSide, Keywords.ANTI_CLOCK_WISE);
			if(top) {
				String sideToJoin = getMissingSide(requiredConnectedSidesMap,connectedSide);
				checkTopPieceSide(requiredConnectedSidesMap,getRightface().getUniqueID(), SideNames.RIGHT.name(),sideToJoin);
			}
			break;
		case RIGHT:
			// check top side but in anti-clock wise manner
			checkTopSidePiece(requiredConnectedSidesMap,pieceToObserve, connectedSide, Keywords.ANTI_CLOCK_WISE);
			// check bottom side but in anti-clock wise manner
			checkBottomSidePiece(requiredConnectedSidesMap,pieceToObserve, connectedSide, Keywords.CLOCK_WISE);
			if(top) {
				String sideToJoin = getMissingSide(requiredConnectedSidesMap,connectedSide);
				checkTopPieceSide(requiredConnectedSidesMap,getLeftface().getUniqueID(), SideNames.LEFT.name(),sideToJoin);
			}
			break;
		default:
			break;
		}	
	
		return requiredConnectedSidesMap;
	}
	
	/**
	 * For a join to be valid, a piece must have conformity with the sides of 
	 * already joined pieces, This function checks that conformity and update the map.
	 * 
	 * @param requiredConnectedSidesMap
	 * @param sideToCheck
	 * @param direction
	 * @param connectedSide
	 */
	private void updateRequiredSidesMapForValidJoin(Map<PieceSide,String> requiredConnectedSidesMap, PieceSide sideToCheck,SideNames connectedSide,String direction){
		// get the respective puzzle piece from side
		PieceSideKey sideKey = sideToCheck.getConnectedSideKey();
		PuzzlePiece puzzlePiece = puzzlePieceMap.get(sideKey.getPieceID());
		if(puzzlePiece == null)
			return;
		// get the direction to check the side of already connected piece
		String nextPieceDirection;
		if(direction == Keywords.CLOCK_WISE)
			nextPieceDirection	 = SideNames.getNextAdjacentSideClockWise(SideNames.findEnum(sideKey.getSideID()));
		else
			nextPieceDirection = SideNames.getNextAdjacentSideAntiClockWise(SideNames.findEnum(sideKey.getSideID())); 
		// finally get the side of already connected piece to be matched
		PieceSide sideToMatch = puzzlePiece.getSideByID(nextPieceDirection);
		if(!sideToMatch.isConnected())
			if(direction == Keywords.CLOCK_WISE)
				requiredConnectedSidesMap.put(sideToMatch, SideNames.getNextAdjacentSideAntiClockWise(connectedSide));
			else
				requiredConnectedSidesMap.put(sideToMatch, SideNames.getNextAdjacentSideClockWise(connectedSide));
	}
	
	/**
	 * Check the let side of piece under observation and get the required sides that should also be connected.
	 * 
	 * @param requiredConnectedSidesMap
	 * @param pieceToObserve
	 * @param connectedSide
	 * @param direction
	 */
	public void checkLeftSidePiece(	Map<PieceSide,String> requiredConnectedSidesMap, PuzzlePiece pieceToObserve, SideNames connectedSide, String direction){
		if(pieceToObserve.getLeftSide().isConnected()) {
			updateRequiredSidesMapForValidJoin(requiredConnectedSidesMap,pieceToObserve.getLeftSide(),connectedSide,direction);
		}
	}
	
	/**
	 * Check the right side of piece under observation and get the required sides that should also be connected.
	 *  
	 * @param requiredConnectedSidesMap
	 * @param pieceToObserve
	 * @param connectedSide
	 * @param direction
	 */
	public void checkRightSidePiece(Map<PieceSide,String> requiredConnectedSidesMap, PuzzlePiece pieceToObserve,  SideNames connectedSide, String direction){
		if(pieceToObserve.getRightSide().isConnected()) {
			updateRequiredSidesMapForValidJoin(requiredConnectedSidesMap,pieceToObserve.getRightSide(),connectedSide,direction);
		}
	}
	
	/**
	 * Check the top side of piece under observation and get the required sides that should also be connected. 
	 * 
	 * @param requiredConnectedSidesMap
	 * @param pieceToObserve
	 * @param connectedSide
	 * @param direction
	 */
	public void checkTopSidePiece(Map<PieceSide,String> requiredConnectedSidesMap, PuzzlePiece pieceToObserve, SideNames connectedSide, String direction){
		if(pieceToObserve.getTopSide().isConnected()) {
			updateRequiredSidesMapForValidJoin(requiredConnectedSidesMap,pieceToObserve.getTopSide(),connectedSide,direction);
		}
	}
	
	/**
	 * Check the bottom side of piece under observation and get the required sides that should also be connected.
	 * 
	 * @param requiredConnectedSidesMap
	 * @param pieceToObserve
	 * @param connectedSide
	 * @param direction
	 */
	public void checkBottomSidePiece(Map<PieceSide,String> requiredConnectedSidesMap, PuzzlePiece pieceToObserve, SideNames connectedSide, String direction){
		if(pieceToObserve.getBottomSide().isConnected()) {
			updateRequiredSidesMapForValidJoin(requiredConnectedSidesMap,pieceToObserve.getBottomSide(),connectedSide,direction);
		}
	}
	
	/**
	 * Add piece in the cube based on the side of bottom piece to which new pieces is joined.
	 * If the piece is not joined to any of the side of the bottom piece, then its a top piece
	 * 
	 * @param connectedSideName side of bottom piece to which it is connected
	 * @param piece newly joined puzzle piece
	 */
	public void addPiece(SideNames connectedSideName, PuzzlePiece piece ){
		switch(connectedSideName){
		case TOP:
			setBackface(piece);
			break;
		case BOTTOM:
			setFrontface(piece);
			break;
		case LEFT:
			setLeftface(piece);
			break;
		case RIGHT:
			setRightface(piece);
			break;
		default:
			setTopface(piece);
			break;
		}
	}
	
	/**
	 * Find our the possible missing side for a puzzle piece
	 * 
	 * @param requiredConnectedSidesMap map containing piece sides that must be joined
	 * @param connectedSide side of piece which is already connected
	 * @return name of missing side
	 */
	private String getMissingSide(Map<PieceSide,String> requiredConnectedSidesMap,SideNames connectedSide){
		Collection<String> coll = requiredConnectedSidesMap.values();
		List<String> list = new ArrayList<String>();
		list.addAll( coll);
		list.add(connectedSide.name());
		if(!list.contains(SideNames.TOP.name()))
			return SideNames.TOP.name();
		else if(!list.contains(SideNames.BOTTOM.name()))
			return SideNames.BOTTOM.name();
		else if(!list.contains(SideNames.LEFT.name()))
			return SideNames.LEFT.name();
		else if(!list.contains(SideNames.RIGHT.name()))
			return SideNames.RIGHT.name();
		else 
			return null;
	}

	/**
	 * Check top piece side
	 * 
	 * @param requiredConnectedSidesMap
	 * @param cubePieceID
	 * @param cubePieceSide
	 * @param topPieceSide
	 */
	private void checkTopPieceSide(Map<PieceSide,String> requiredConnectedSidesMap, String cubePieceID, String cubePieceSide, String topPieceSide){
		PuzzlePiece piece = getPieceByID(cubePieceID);
		requiredConnectedSidesMap.put(piece.getSideByID(cubePieceSide), topPieceSide);
	}
	
	public List<PieceSide> getFreeSidesInCompleteCubeExceptThisPiece(String pieceID){
		List<PieceSide> freeSides = new ArrayList<PieceSide>();
		for(String key: puzzlePieceMap.keySet()){
			if(!key.equals(pieceID))
				freeSides.addAll(puzzlePieceMap.get(key).getFreeSides());
		}
		return freeSides;
	}
	
}
