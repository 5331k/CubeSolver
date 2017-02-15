package org.khan.solver.cubesolver;

import java.util.ArrayList;
import java.util.List;

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
    	PuzzlePieceWriter pieceWriter = new PuzzlePieceConsoleWriter();
    	PuzzlePieceWriter fileWriter = new PuzzlePieceFileWriter();
    	
    	System.out.println("Input Pieces........................");
    	PuzzlePiece p1 = InputData.getPiece1();
    	printPiece(p1);
    	
    	
    	PuzzlePiece p2 = InputData.getPiece2();
    	printPiece(p2);
    	
     	
    	PuzzlePiece p3 = InputData.getPiece3();
    	printPiece(p3);
    	
     	
    	PuzzlePiece p4 = InputData.getPiece4();
    	printPiece(p4);
    	
    
    	PuzzlePiece p5 = InputData.getPiece5();
    	printPiece(p5);

        
        PuzzlePiece p6 = InputData.getPiece6();
        printPiece(p6);
        System.out.println("...............................................");
        
        List<PuzzlePiece> pieces = new ArrayList<PuzzlePiece>();
        pieces.add(p1);pieces.add(p2);pieces.add(p3);pieces.add(p4);pieces.add(p5);pieces.add(p6);
        AbstractPuzzleSolver puzzleSolver = new PuzzleSolver(new PieceCompatabilityChecker());
        puzzleSolver.solvePuzzle(pieces, false);
        List<Cube> cubeList = puzzleSolver.getCubeList();
        if(!cubeList.isEmpty()) {
        	Cube cube = puzzleSolver.getCubeList().get(0);
        	System.out.println("\nGenerated Cube in Unfolderd form......................");
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
  
}
