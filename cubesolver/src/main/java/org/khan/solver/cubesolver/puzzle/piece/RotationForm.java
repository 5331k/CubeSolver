package org.khan.solver.cubesolver.puzzle.piece;

public enum RotationForm {

	NORMAL,
	UPSIDEDOWN,
	LEFTTORIGHT;
	
	public static RotationForm findEnum(String str){
	    for(RotationForm v : values()){
	        if( v.name().equals(str)){
	            return v;
	        }
	    }
	    return null;
	}
}
