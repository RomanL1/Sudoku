package sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CSVhandler {
	
	public static void exportSolutionCSV(int[][] array, boolean printOut) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(array[i][j] + ";");
			}
			sb.append("\r\n");
		}

		try {
			String msg = writeToFile(new JFileChooser().getFileSystemView().getDefaultDirectory().toString(), "sudoku_solution", sb.toString());
			
			if (printOut) {
				System.out.println(msg);
			} else {
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			if (printOut) {
				e.printStackTrace();
			} else {
				JOptionPane.showMessageDialog(null, "File could not be saved! \n Errormessage: " + e.getMessage());
			}
		}
	}

	public static void exportNormalCSV(int[][] array, boolean printOut) {
		StringBuilder sb = new StringBuilder();
		Double r = getRandom();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (r > 0 && r < 8) {
					sb.append(array[i][j] + ";");
				} else {
					sb.append(0 + ";");
				}
				r = getRandom();
			}
			sb.append("\r\n");
		}

	}

	private static String writeToFile(String path, String filename, String input) throws Exception {
		File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + ".csv");
		int i = 1;
		while (file.exists()) {
			file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + "_" + i + ".csv");
			i++;
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		bw.write(input);
		bw.close();

		return "File saved here: " + file.getAbsolutePath();
	}

	private static double getRandom() {
		double multi = new Random().nextInt(11);
		double r = new Random().nextDouble();

		return r * multi;
	}
}
