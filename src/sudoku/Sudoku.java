package sudoku;

import java.util.Random;

public class Sudoku {
	private int[][] sudoku;

	public Sudoku() {
	  int count = 0;
    this.sudoku = new int[9][9];

    boolean b;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int r = Sudoku.getRandom();
        while (b = this.allContainsN(j, i, r)) {
          r = Sudoku.getRandom();
          count++;
          if (count > 50) {
            count = 0;
            this.fillRowWithZeros(i);
            i--;
            break;
          }
        }
        if (!b) {
          this.sudoku[i][j] = r;
          count = 0;
        }  else {
          break;
        }
      }
    }

	}

	public int[][] getArray() {
		return this.sudoku;
	}

	@Override public String toString() {
		StringBuilder str = new StringBuilder();
		int countCol = 0;
		int countRow = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				str.append("[" + this.sudoku[i][j] + "]");
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

	private boolean allContainsN(int col, int row, int n) {
		return this.rowContainsN(col, n) || this.colContainsN(row, n) || this.squareContainsN(col, row, n);
	}

	private boolean rowContainsN(int col, int n) {
		for (int i = 0; i < 9; i++) {
			if (this.sudoku[i][col] == n)
				return true;
		}
		return false;
	}

	private boolean colContainsN(int row, int n) {
		for (int i = 0; i < 9; i++) {
			if (this.sudoku[row][i] == n)
				return true;
		}
		return false;
	}

	private boolean squareContainsN(int col, int row, int n) {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (this.sudoku[row / 3 * 3 + i][col / 3 * 3 + j] == n)
					return true;
			}
		}
		return false;
	}

	private void fillRowWithZeros(int row) {
		this.sudoku[row] = new int[9];
	}

	/**
	 * Returns a random int between 1 and 9.
	 * @return
	 */
	private static int getRandom() {
		return new Random().nextInt(9) + 1;
	}

}
