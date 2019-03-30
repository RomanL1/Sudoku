package sudoku;

import java.util.Random;

public class Sudoku {
	private int[][] sudoku;
  private final long seed;

  public Sudoku() {
    this(System.nanoTime());
  }

	public Sudoku(long seed) {
	  this.seed = seed;
	  Random rd = new Random(seed);

	  int count = 0;
    this.sudoku = new int[9][9];

    boolean b;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int r = rd.nextInt(9) + 1;
        while (b = this.allContainsN(j, i, r)) {
          r = rd.nextInt(9) + 1;
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

	public int[][] getArray() { return this.sudoku; }

  /**
   * Returns the seed with which the sudoku was generated.
   * @return the seed
   */
  public long getSeed() { return this.seed; }

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
}
