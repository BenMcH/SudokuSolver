package com.tycoon177.suduko.solver;

import java.util.ArrayList;

public class SudokuGameBoard {
	private SudokuCell[][] board;
	//private int fieldsAdded = 0, oldFieldsAdded;;
	public static final int SUDOKU_BOARD_SIZE = 9;
	public SudokuGameBoard(int[][] board) {
		this.board = new SudokuCell[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				this.board[i][j] = new SudokuCell(i, j, board[i][j], this);
			}
		}
	}

	public ArrayList<SudokuCell> getColumn(int colIndex) {
		if (colIndex >= 9 || colIndex < 0) {
			throw new IndexOutOfBoundsException("Suduko only has 9 columns.");
		}
		ArrayList<SudokuCell> column = new ArrayList<SudokuCell>();
		for (int i = 0; i < SUDOKU_BOARD_SIZE; i++) {
			column.add(board[i][colIndex]);
		}
		return column;
	}

	public ArrayList<SudokuCell> getRow(int rowIndex) {
		if (rowIndex >= 9 || rowIndex < 0) {
			throw new IndexOutOfBoundsException("Suduko only has 9 rows.");
		}
		ArrayList<SudokuCell> row = new ArrayList<SudokuCell>();
		for (int i = 0; i < SUDOKU_BOARD_SIZE; i++) {
			row.add(board[rowIndex][i]);
		}
		return row;
	}
	
	public ArrayList<SudokuCell> getUnsolvedInBox(int hBoxIndex, int vBoxIndex){
		ArrayList<SudokuCell> unsolved = new ArrayList<>();
		ArrayList<SudokuCell> box = getBox(hBoxIndex, vBoxIndex);
		for(SudokuCell cell : box){
			if(!cell.isSolved()){
				unsolved.add(cell);
			}
		}
		return unsolved;
	}

	public ArrayList<SudokuCell> getBox(int hBoxIndex, int vBoxIndex) {
		int hLoc = hBoxIndex * 3;
		int vLoc = vBoxIndex * 3;
		ArrayList<SudokuCell> box = new ArrayList<SudokuCell>();
		for (int i = hLoc; i < hLoc + 3; i++) {
			for (int j = vLoc; j < vLoc + 3; j++) {
				box.add(board[j - vLoc][i - hLoc]);
			}
		}
		return box;
	}

	public boolean isSolved() {
		for (SudokuCell[] row : board) {
			for (SudokuCell cell : row) {
				if (!cell.isSolved())
					return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				builder.append(board[i][j]);
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void solve(){
		while(!isSolved()){
			for(SudokuCell[] row : board){
				for(SudokuCell cell : row){
					cell.crosshatch();
				}
			}
			System.out.println(this.toString());
		}
	}

}
