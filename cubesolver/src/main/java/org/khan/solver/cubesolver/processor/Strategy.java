package org.khan.solver.cubesolver.processor;

import java.util.List;

import org.khan.solver.cubesolver.puzzle.PuzzlePiece;

public interface Strategy {

	/**
	 * Solve puzzle to find all unique cubes or just the first one found.
	 * 
	 * @param pieces
	 * @param allcubes
	 */
	public void solvePuzzle(List<PuzzlePiece> pieces, boolean allcubes);
}
