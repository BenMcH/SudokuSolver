package com.tycoon177.suduko.solver;

import java.util.ArrayList;
import java.util.Iterator;

public class SudokuListUtils {
	final static ArrayList<Integer> possibleNumbers = new ArrayList<>();

	static{
		for(int i = 1; i <= SudokuGameBoard.SUDOKU_BOARD_SIZE; i++){
			possibleNumbers.add(i);
		}
	}

	public static ArrayList<Integer> getCommon(ArrayList<Integer> first, ArrayList<Integer> second) {
		ArrayList<Integer> common = new ArrayList<>();
		Iterator<Integer> iterator = first.iterator();

		while (iterator.hasNext()) {
			int num = iterator.next();
			if (!second.contains(num) && num != 0) {
				common.add(num);
			}
		}
		return common;
	}

	public static ArrayList<Integer> missing(ArrayList<Integer> fullset, ArrayList<Integer> subset) {
		fullset.removeAll(subset);
		return fullset;
	}
	
	public static ArrayList<Integer> missing(ArrayList<Integer> subset){
		return missing(getPossibleSudokuValues(), subset);
	}
	
	public static ArrayList<Integer> getPossibleSudokuValues(){
		return new ArrayList<Integer>(possibleNumbers);
	}
}
