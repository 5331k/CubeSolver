package org.khan.solver.cubesolver;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;

/**
 * Encoding In put matrix in boolean form: "[]" represents true and " " represents false
 * 
 * @author SDK
 *
 */
public class InputData {

	static int pieceSize = 5;

	
	public class BlueCube {
			/*		[]    
				  [][][]  
				[][][][][] 
				  [][][]  
					[] */     
		public static PuzzlePiece getPiece5() {
			boolean[][] piece = { { false, false, true, false, false }, { false, true, true, true, false },
					{ true, true, true, true, true }, { false, true, true, true, false },
					{ false, false, true, false, false } };
			return new PuzzlePiece( piece, pieceSize);
		}

				/*	 []  
				   [][][][]
				 [][][][] 
				   [][][][]
					 []  */
		public static PuzzlePiece getPiece2() {
			boolean[][] piece = { { false, false, true, false, false }, { false, true, true, true, true },
					{ true, true, true, true, false }, { false, true, true, true, true },
					{ false, false, true, false, false } };
			return new PuzzlePiece(piece, pieceSize);
		}

				/*	[]  []  
				  [][][][] 
					[][][][] 
				  [][][][] 
				  [][]  []*/
		public static PuzzlePiece getPiece3() {
			boolean[][] piece = { { false, true, false, true, false }, { true, true, true, true, false },
					{ false, true, true, true, true }, { true, true, true, true, false },
					{ true, true, false, true, false } };

			return new PuzzlePiece(piece, pieceSize);
		}

				/*	[]  []    
				  [][][][][]  
					[][][]  
				  [][][][][]  
				  []  []    */
		public static PuzzlePiece getPiece4() {

			boolean[][] piece = { { false, true, false, true, false }, { true, true, true, true, true },
					{ false, true, true, true, false }, { true, true, true, true, true },
					{ true, false, true, false, false } };

			return new PuzzlePiece(piece, pieceSize);
		}

		/*   []  []  []   
			 [][][][][]  
			   [][][] 
			 [][][][][] 
			 []  []  [] */ 
		public static PuzzlePiece getPiece1() {
			boolean[][] piece = { { true, false, true, false, true }, { true, true, true, true, true },
					{ false, true, true, true, false }, { true, true, true, true, true },
					{ true, false, true, false, true } };

			return new PuzzlePiece(piece, pieceSize);
		}
		
				/*	[]  [] 
					[][][][]
				  [][][][]  
					[][][][]
				  [][]  [][]*/

		public static PuzzlePiece getPiece6() {
			boolean[][] piece = { { false, true, false, true, false }, { false, true, true, true, true },
					{ true, true, true, true, false }, { false, true, true, true, true },
					{ true, true, false, true, true } };
			return new PuzzlePiece(piece, pieceSize);
		}
	}

	 
	public class RedCube {
			/*		[]    
				  [][][]  
				[][][][][] 
				  [][][]  
					[] */     
			public static PuzzlePiece getPiece1() {
	boolean[][] piece = { { false, false, false, true, true }, { false, true, true, true, false },
			{ true, true, true, true, true }, { false, true, true, true, false },
			{ false, true, false, true, true } };
	return new PuzzlePiece( piece, pieceSize);
		}

				/*	 []  
				   [][][][]
				 [][][][] 
				   [][][][]
					 []  */
			public static PuzzlePiece getPiece2() {
		boolean[][] piece = { { false, true, false, true, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, true, false, false, false } };
		return new PuzzlePiece(piece, pieceSize);
		}

				/*	[]  []  
				  [][][][] 
					[][][][] 
				  [][][][] 
				  [][]  []*/
		public static PuzzlePiece getPiece3() {
		boolean[][] piece = { { false, true, true, false, true }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, false, true, true } };

		return new PuzzlePiece(piece, pieceSize);
	}
		}

				/*	[]  []    
				  [][][][][]  
					[][][]  
				  [][][][][]  
				  []  []    */
		public static PuzzlePiece getPiece4() {

		boolean[][] piece = { { false, false, true, false, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}

		/*   []  []  []   
			 [][][][][]  
			   [][][] 
			 [][][][][] 
			 []  []  [] */ 
		public static PuzzlePiece getPiece5() {
		boolean[][] piece = { { false, false, true, true, false }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}
		
				/*	[]  [] 
					[][][][]
				  [][][][]  
					[][][][]
				  [][]  [][]*/

	public static PuzzlePiece getPiece6() {
		boolean[][] piece = { { false, true, true, false, false }, { false, true, true, true, false },
				{ true, true, true, true, true }, { false, true, true, true, false },
				{ true, true, false, true, true } };
		return new PuzzlePiece(piece, pieceSize);
	}
	}
 
	 
	/**
	 * 
	 * @param mat
	 * @return
	 */
	public static boolean[][] convertStringMatrixToBoolean(String[][] mat) {
		boolean[][] newMat = new boolean[pieceSize][pieceSize];
		for (int i = 0; i < pieceSize; i++) {
			for (int j = 0; j < pieceSize; j++) {
				if (mat[i][j] == "[]")
					newMat[i][j] = true;
				else
					newMat[i][j] = false;
			}
		}
		return newMat;
	}
}
