package org.khan.solver.cubesolver;

import java.util.ArrayList;
import java.util.List;

import org.khan.solver.cubesolver.input.BlueCube;
import org.khan.solver.cubesolver.input.RedCube;
import org.khan.solver.cubesolver.processor.AbstractPuzzleSolver;
import org.khan.solver.cubesolver.processor.PuzzleSolver;
import org.khan.solver.cubesolver.puzzle.PieceCompatabilityChecker;
import org.khan.solver.cubesolver.puzzle.PuzzlePiece;
import org.khan.solver.cubesolver.puzzle.cube.Cube;
import org.khan.solver.cubesolver.writer.PuzzlePieceConsoleWriter;
import org.khan.solver.cubesolver.writer.PuzzlePieceFileWriter;
import org.khan.solver.cubesolver.writer.PuzzlePieceWriter;

/**
 * Cube Solver
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	createAndPrintCube(getRedPieces(), Keywords.RED);
    	createAndPrintCube(getBluePieces(), Keywords.BLUE);
    }
    
    public static void createAndPrintCube(List<PuzzlePiece> pieces, String color ){
    	PuzzlePieceWriter pieceWriter = new PuzzlePieceConsoleWriter();
    	PuzzlePieceWriter fileWriter = new PuzzlePieceFileWriter(color);
        AbstractPuzzleSolver puzzleSolver = new PuzzleSolver(new PieceCompatabilityChecker());
        
        puzzleSolver.solvePuzzle(pieces, false);
        List<Cube> cubeList = puzzleSolver.getCubeList();
        if(!cubeList.isEmpty()) {
        	Cube cube = puzzleSolver.getCubeList().get(0);
        	System.out.println("\n" + color +" generated Cube in Unfolderd form......................");
        	pieceWriter.writecube(cube);
        	fileWriter.writecube(cube);
        }
        else
        	System.out.println("\nCube not found, Invalid pieces......................");
    }
    
    public static void printPiece(PuzzlePiece piece) {
        PuzzlePieceWriter pieceWriter = new PuzzlePieceConsoleWriter();
    		 pieceWriter.writePuzzlePiece(piece);
    		 
    }
    
    public static List<PuzzlePiece> getRedPieces(){

    	System.out.println("\nRed Cube Input Pieces........................");
    	PuzzlePiece p1 = RedCube.getPiece1();
    	printPiece(p1);
    	System.out.println("");
    	
    	PuzzlePiece p2 = RedCube.getPiece2();
    	printPiece(p2);
    	System.out.println("");
     	
    	PuzzlePiece p3 = RedCube.getPiece3();
    	printPiece(p3);
    	
    	System.out.println("");
    	PuzzlePiece p4 = RedCube.getPiece4();
    	printPiece(p4);
    	System.out.println("");
    
    	PuzzlePiece p5 = RedCube.getPiece5();
    	printPiece(p5);
    	System.out.println("");
        
        PuzzlePiece p6 = RedCube.getPiece6();
        printPiece(p6);
        System.out.println("...............................................");
        
        List<PuzzlePiece> pieces = new ArrayList<PuzzlePiece>();
        pieces.add(p1);pieces.add(p2);pieces.add(p3);pieces.add(p6);pieces.add(p5);pieces.add(p4);
        
        return pieces;
    }
    
    public static List<PuzzlePiece> getBluePieces(){

    	System.out.println("\nBlue Cube Input Pieces........................");
    	PuzzlePiece p1 = BlueCube.getPiece1();
    	printPiece(p1);
    	System.out.println("");
    	
    	PuzzlePiece p2 = BlueCube.getPiece2();
    	printPiece(p2);
    	System.out.println("");
     	
    	PuzzlePiece p3 = BlueCube.getPiece3();
    	printPiece(p3);
    	
    	System.out.println("");
    	PuzzlePiece p4 = BlueCube.getPiece4();
    	printPiece(p4);
    	System.out.println("");
    
    	PuzzlePiece p5 = BlueCube.getPiece5();
    	printPiece(p5);
    	System.out.println("");
        
        PuzzlePiece p6 = BlueCube.getPiece6();
        printPiece(p6);
        System.out.println("...............................................");
        
        List<PuzzlePiece> pieces = new ArrayList<PuzzlePiece>();
        pieces.add(p1);pieces.add(p2);pieces.add(p3);pieces.add(p6);pieces.add(p5);pieces.add(p4);
        
        return pieces;
    }
  
}
