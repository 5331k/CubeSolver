package org.khan.solver.cubesolver.input;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;

public class RedCube implements CubeInputPieces {

	static int pieceSize = 5;

	public PuzzlePiece getPiece1() {
		boolean[][] piece = { { false, false, false, true, true }, { false, true, true, true, false },
				{ true, true, true, true, true }, { false, true, true, true, false },
				{ false, true, false, true, true } };
		return new PuzzlePiece(piece, pieceSize);
	}

	public PuzzlePiece getPiece2() {
		boolean[][] piece = { { false, true, false, true, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, true, false, false, false } };
		return new PuzzlePiece(piece, pieceSize);
	}

	public PuzzlePiece getPiece3() {
		boolean[][] piece = { { false, true, true, false, true }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, false, true, true } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public PuzzlePiece getPiece4() {

		boolean[][] piece = { { false, false, true, false, false }, { true, true, true, true, false },
				{ false, true, true, true, true }, { true, true, true, true, false },
				{ false, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public PuzzlePiece getPiece5() {
		boolean[][] piece = { { false, false, true, true, false }, { true, true, true, true, true },
				{ false, true, true, true, false }, { true, true, true, true, true },
				{ true, false, true, false, false } };

		return new PuzzlePiece(piece, pieceSize);
	}

	public PuzzlePiece getPiece6() {
		boolean[][] piece = { { false, true, true, false, false }, { false, true, true, true, false },
				{ true, true, true, true, true }, { false, true, true, true, false },
				{ true, true, false, true, true } };
		return new PuzzlePiece(piece, pieceSize);
	}

}