package Sudoku;

import java.util.Random;

public class Sudoku {
	int[][] sudoku;

	public Sudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}

	public Sudoku() {
		this.sudoku = generateArray();
	}

	public int[][] getSudoku() {
		return sudoku;
	}

	public void setSudoku(int[][] sudoku) {
		this.sudoku = sudoku;
	}

	public int[][] generateArray() {
		int count = 0;
		int[][] array = new int[9][9];
		array = fillAllWithZeros();

		boolean b;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int r = getRandom();
				while (b = allContainsN(array, j, i, r)) {
					r = getRandom();
					count++;
					if (count > 50) {
						count = 0;
						array = fillRowWithZeros(array, i);
						i--;
						break;
					}
				}
				if (!b) {
					array[i][j] = r;
					count = 0;
				}  else {
					break;
				}
			}
		}

		return array;
	}

	public String print() {
		StringBuilder str = new StringBuilder();
		int countCol = 0;
		int countRow = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				str.append("[" + sudoku[i][j] + "]");
				countCol++;
				if (countCol > 2) {
					if (j != 8)
						str.append(" | ");
					countCol = 0;
				}
			}
			str.append("\n");
			countRow++;
			if (countRow > 2) {
				if (i != 8)
					str.append("----------|-----------|-----------\n");
				countRow = 0;
			}
		}

		return str.toString();
	}

	private boolean allContainsN(int[][] array, int col, int row, int n) {
		return rowContainsN(array, col, n) || colContainsN(array, row, n) || squareContainsN(array, col, row, n);
	}

	private boolean rowContainsN(int[][] array, int col, int n) {
		for (int i = 0; i < 9; i++) {
			if (array[i][col] == n)
				return true;
		}

		return false;
	}

	private boolean colContainsN(int[][] array, int row, int n) {
		for (int i = 0; i < 9; i++) {
			if (array[row][i] == n)
				return true;
		}

		return false;
	}

	private boolean squareContainsN(int[][] array, int col, int row, int n) {
		int minRow = 0, maxRow = 0, minCol = 0, maxCol = 0;

		switch (col) {
		case 0:
		case 1:
		case 2:
			minCol = 0;
			maxCol = 2;
			break;
		case 3:
		case 4:
		case 5:
			minCol = 3;
			maxCol = 5;
			break;
		case 6:
		case 7:
		case 8:
			minCol = 6;
			maxCol = 8;
			break;
		default:
			System.exit(0);
		}

		switch (row) {
		case 0:
		case 1:
		case 2:
			minRow = 0;
			maxRow = 2;
			break;
		case 3:
		case 4:
		case 5:
			minRow = 3;
			maxRow = 5;
			break;
		case 6:
		case 7:
		case 8:
			minRow = 6;
			maxRow = 8;
			break;
		default:
			System.exit(0);
		}

		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if (array[i][j] == n)
					return true;
			}
		}

		return false;
	}

	private int[][] fillAllWithZeros() {
		int array[][] = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				array[i][j] = 0;
			}
		}
		return array;
	}

	private int[][] fillRowWithZeros(int[][] array, int row) {
		for (int i = 0; i < 9; i++) {
			array[row][i] = 0;
		}
		return array;
	}

	private int[][] fillColWithZeros(int[][] array, int col) {
		for (int i = 0; i < 9; i++) {
			array[i][col] = 0;
		}
		return array;
	}

	private int getRandom() {
		int r = 0;
		while (r == 0) {
			r = new Random().nextInt(10);
		}
		return r;
	}

}
