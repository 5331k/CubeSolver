package org.khan.solver.cubesolver.puzzle.piece.sides;

import org.khan.solver.cubesolver.puzzle.piece.RotationForm;

/**
 * An object which represents the necessary information to indicate the side of connected puzzle piece
 * 
 * @author SDK
 *
 */
public class PieceSideKey {

	// id of puzzle piece
	private String pieceID;
	// id og piece side
	private String sideID;
	// to indicate rotation of piece
	private String rotationForm;
	// constant value
	private final String KEY_INDEX_SEP = ":";
	
	/**
	 * Copy constructor
	 * 
	 * @param objectToCopy
	 */
	public PieceSideKey(PieceSideKey objectToCopy){
		pieceID = objectToCopy.pieceID;
		sideID = objectToCopy.sideID;
	}
	
	/**
	 * DEfault constructor
	 * 
	 * @param pieceID
	 * @param sideID
	 */
	public PieceSideKey(String pieceID,String sideID){
		this.pieceID = pieceID;
		this.sideID = sideID;
		this.rotationForm = RotationForm.NORMAL.name();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRotationForm() {
		return rotationForm;
	}

	/**
	 * 
	 * @param rotationForm
	 */
	public void setRotationForm(String rotationForm) {
		this.rotationForm = rotationForm;
	}
	
	public void setSideID(String id){
		sideID = id;
	}
	
	public String getSideID(){
		return sideID;
	}
	
	public String getPieceID(){
		return pieceID;
	}

	public void setPieceID(String pieceID) {
		this.pieceID = pieceID;
	}

	@Override
	public String toString() {
		return pieceID + KEY_INDEX_SEP + sideID;
	}
	
	@Override
	public int hashCode()
	{
	    return pieceID.hashCode() + sideID.hashCode();
	} 
	
	@Override
	public boolean equals(Object o)
	{
		PieceSideKey otherObject = (PieceSideKey) o;
		if( (this.pieceID.equals(otherObject.pieceID)) && (this.sideID.equals(otherObject.sideID )))
			return true;
		else
			return false;
	}
}
