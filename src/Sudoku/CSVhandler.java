package Sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CSVhandler {
	public static void exportCSV(int[][] array, boolean printOut) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(array[i][j] + ";");
			}
			sb.append("\r\n");
		}

		try {
			File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()  + "\\sudoku.csv");
			int i = 1;
			while (file.exists()) {
				file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\sudoku_ " + i + ".csv");
				i++;
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			bw.write(sb.toString());
			bw.close();

			String msg = "File saved under: " + file.getAbsolutePath();
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
}
