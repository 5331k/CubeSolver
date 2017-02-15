package org.khan.solver.cubesolver.puzzle.piece.sides;

import java.util.Arrays;

/**
 * This class represents a side of a puzzle piece. Each piece object has side status and set
 * of values to indicate tabs and blanks in the side.
 * 
 * @author SDK
 *
 */
public class PieceSide {

	// id of parent piece to which it belongs
	private String parentID;
	// id of this side
	private String sideID;
	// values represent by this side, false for blanks and true for tabs
	protected boolean []sideValues;
	// side is connected or not
	protected boolean connected;
	// key to indicate the other side of connected piece
	protected PieceSideKey connectedSideKey;
	
	/**
	 * Copy constructor
	 * 
	 * @param objectToCOpy
	 */
	public PieceSide(PieceSide objectToCOpy){
		this.sideID = 	objectToCOpy.sideID;
		this.parentID = objectToCOpy.parentID;
		this.sideValues = Arrays.copyOf(objectToCOpy.sideValues, objectToCOpy.sideValues.length);
		this.connected = objectToCOpy.connected;
		if(objectToCOpy.connectedSideKey != null)
			this.connectedSideKey = new PieceSideKey(objectToCOpy.connectedSideKey);
		else
			this.connectedSideKey = null;
	}
	
	/**
	 * Default constructor
	 * 
	 * @param sideID
	 * @param parentID
	 * @param sideValues
	 */
	public PieceSide(String sideID, String parentID,boolean []sideValues){
		this.sideID = sideID;
		this.parentID = parentID;
		this.sideValues = sideValues;
		this.connected = false;
		this.connectedSideKey = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[] getSideValues() {
		return sideValues;
	}

	/**
	 * 
	 * @param sideValues
	 */
	public void setSideValues(boolean[] sideValues) {
		this.sideValues = sideValues;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * 
	 * @param connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * 
	 * @return get the connected side of object
	 */
	public PieceSideKey getConnectedSideKey() {
		return connectedSideKey;
	}

	/**
	 * set the key to which this side is connected
	 * 
	 * @param connectedSideKey
	 */
	public void setConnectedSideKey(PieceSideKey connectedSideKey) {
		this.connectedSideKey = connectedSideKey;
	}

	/**
	 * 
	 * @return get the id of parent piece
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * 
	 * @return get the id of side
	 */
	public String getSideID() {
		return sideID;
	}
}
