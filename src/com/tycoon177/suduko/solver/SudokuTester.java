package com.tycoon177.suduko.solver;

public class SudokuTester {
	public static void main(String[] args) {
		int[][] board = { { 5,7,0,0,1,0,0,0,0 },
						  { 0,6,0,0,9,0,5,0,1 },
						  { 0,0,1,0,0,5,0,0,0 }, 
						  { 0,2,7,0,0,8,0,0,0 }, 
						  { 8,3,0,0,0,0,0,1,9 },
						  { 0,0,0,1,0,0,2,5,0 }, 
						  { 0,0,0,5,0,0,4,0,0 }, 
						  { 2,0,6,0,8,0,0,9,0 },
						  { 0,0,0,0,6,0,0,2,3 } };
		SudokuGameBoard game = new SudokuGameBoard(board);
		game.solve();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
