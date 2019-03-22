package sudoku;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Sudoku s = new Sudoku();
		long end =  System.currentTimeMillis();
		
		System.out.println("Time: " + ((double)(end - start)/1000) + " ms");
		System.out.println(s.print());
		
		CSVhandler.exportSolutionCSV(s.getSudoku(), false);
		CSVhandler.exportNormalCSV(s.getSudoku(), false);
	}
}
