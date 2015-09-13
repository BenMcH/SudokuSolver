package com.tycoon177.suduko.solver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SudokuCell {
	private int value;
	private ArrayList<Integer> possibleValues;
	private int row;
	private int column;
	private int hBoxIndex;
	private int vBoxIndex;

	private SudokuGameBoard board;

	private SudokuCell(SudokuGameBoard board) {
		value = 0;
		possibleValues = new ArrayList<Integer>();
		possibleValues = SudokuListUtils.getPossibleSudokuValues();
		row = 0;
		column = 0;
		this.board = board;
	}

	public SudokuCell(int row, int col, int value, SudokuGameBoard board) {
		this(board);
		this.row = row;
		this.column = col;
		vBoxIndex = row / 3;
		hBoxIndex = col / 3;
		if (value > -1 && value < 10) {
			possibleValues.removeAll(possibleValues);
			this.value = value;
		} else {
			throw new IndexOutOfBoundsException(
					"Sudoku tiles must have a value of 1-9 (0 is acceptable for unsolved tiles)");
		}
	}

	public List<Integer> getPossibleValues() {
		return possibleValues;
	}

	public int getValue() {
		return value;
	}

	public void removePossibleValue(int possibleValue) {
		possibleValues.remove((Integer) possibleValue);
	}

	public boolean isPossibleValue(int value) {
		return possibleValues.contains(value);
	}

	public boolean solve() {
		if (possibleValues.size() == 1) {
			value = possibleValues.remove(0);
		}
		return possibleValues.size() == 0;
	}

	public void crosshatch() {
		if (isSolved()) {
			return;
		}

		ArrayList<SudokuCell> row = board.getRow(this.row);
		ArrayList<SudokuCell> col = board.getColumn(column);
		ArrayList<SudokuCell> box = board.getBox(hBoxIndex, vBoxIndex);
		ArrayList<Integer> possible = SudokuListUtils.getPossibleSudokuValues();
		row.addAll(col);
		row.addAll(box);
		for (SudokuCell cell : row)

		{
			possible.remove((Integer) cell.getValue());
		}
		if (possible.size() == 1)

		{
			this.value = possible.remove(0);
			this.possibleValues.clear();
		}

	}

	public boolean isSolved() {
		return value != 0;
	}

	public void removePossibleValues(ArrayList<Integer> union) {
		Iterator<Integer> unionIterator = union.iterator();
		while (unionIterator.hasNext()) {
			possibleValues.remove(unionIterator.next());
		}
	}

	@Override
	public String toString() {
		return getValue() + " ";
	}

}
