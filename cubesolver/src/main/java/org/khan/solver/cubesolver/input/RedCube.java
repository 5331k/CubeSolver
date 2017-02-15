package org.khan.solver.cubesolver.input;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;

public class RedCube {

	static int pieceSize = 5;
	public static PuzzlePiece getPiece1() {
		boolean[][] piece = { { false, false, false, true, true }, { false, true, true, true, false },
				{ true, true, true, true, true }, { false, true, true, true, false },
				{ false, true, false, true, true } };
		return new PuzzlePiece(piece, pieceSize);
	}

	public static PuzzlePiece getPiece2() {
		boolean[][] piece = { { false, true, false, true, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, true, false, false, false } };
		return new PuzzlePiece(piece, pieceSize);
	}

	public static PuzzlePiece getPiece3() {
		boolean[][] piece = { { false, true, true, false, true }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, false, true, true } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public static PuzzlePiece getPiece4() {

		boolean[][] piece = { { false, false, true, false, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public static PuzzlePiece getPiece5() {
		boolean[][] piece = { { false, false, true, true, false }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public static PuzzlePiece getPiece6() {
		boolean[][] piece = { { false, true, true, false, false }, { false, true, true, true, false },
				{ true, true, true, true, true }, { false, true, true, true, false },
				{ true, true, false, true, true } };
		return new PuzzlePiece(piece, pieceSize);
	}

}