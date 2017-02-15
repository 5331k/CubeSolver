package org.khan.solver.cubesolver.puzzle.piece.sides;

/**
 * Enum to represent sides of a puzzle piece
 * 
 * @author SDK
 *
 */
public enum SideNames {
	TOP,
	BOTTOM,
	LEFT,
	RIGHT;
	
	/**
	 * T -> R -> B -> L - > T
	 * Function returns the next adjacent side to a given side in
	 * clockwise direction.
	 * 
	 * @param sideName enum object
	 * @return value of enum object
	 */
	public static String getNextAdjacentSideClockWise(SideNames sideName){
		switch(sideName){
		case TOP:
			return RIGHT.name();
		case BOTTOM:
			return LEFT.name();
		case LEFT:
			return TOP.name();
		case RIGHT:
			return BOTTOM.name();
		default:
			break;
		}
		return null;
	}
	
	/**
	 *  T <- R <- B <- L <- T
	 * Function returns the next adjacent side to a given side in
	 * anti-clockwise direction.
	 * 
	 * @param sideName enum object
	 * @return value of enum object
	 */
	public static String getNextAdjacentSideAntiClockWise(SideNames sideName) {
		switch(sideName){
		case TOP:
			return LEFT.name();
		case BOTTOM:
			return RIGHT.name();
		case LEFT:
			return BOTTOM.name();
		case RIGHT:
			return TOP.name();
		default:
			break;
		}
		return null;
	}
	
	/**
	 * Find enum object on the base of string value
	 * 
	 * @param str value
	 * @return enum object
	 */
	public static SideNames findEnum(String str){
	    for(SideNames v : values()){
	        if( v.name().equals(str)){
	            return v;
	        }
	    }
	    return null;
	}
}
