package sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CSVhandler {

    public static void exportSolutionCSV(Sudoku sudoku, boolean printOut) {
        int[][] array = sudoku.getArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(array[i][j]).append(";");
            }
            sb.append("\r\n");
        }

        try {
            String msg = writeToFile(new JFileChooser().getFileSystemView().getDefaultDirectory().toString(), "sudoku_solution" + sudoku.getSeed(), sb.toString());

            if (printOut) {
                System.out.println(msg);
            } else {
                JOptionPane.showMessageDialog(null, msg);
            }
        }
        catch (Exception e) {
            if (printOut) {
                e.printStackTrace();
            } else {
                JOptionPane.showMessageDialog(null, "File could not be saved! \n Errormessage: " + e.getMessage());
            }
        }
    }

    public static void exportNormalCSV(Sudoku sudoku, boolean printOut) {
        int[][] array = sudoku.getArray();
        StringBuilder sb = new StringBuilder();
        Double r = getRandom();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (r > 1 && r < 7) {
                    sb.append(0).append(";");
                } else {
                    sb.append(array[i][j]).append(";");
                }
                r = getRandom();
            }
            sb.append("\r\n");
        }

        try {
            String msg = writeToFile(new JFileChooser().getFileSystemView().getDefaultDirectory().toString(), "sudoku_solvable" + sudoku.getSeed(), sb.toString());

            if (printOut) {
                System.out.println(msg);
            } else {
                JOptionPane.showMessageDialog(null, msg);
            }
        }
        catch (Exception e) {
            if (printOut) {
                e.printStackTrace();
            } else {
                JOptionPane.showMessageDialog(null, "File could not be saved! \n Errormessage: " + e.getMessage());
            }
        }
    }

    private static String writeToFile(String path, String filename, String input) throws Exception {
        File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + ".csv");
        if (file.exists() && JOptionPane.showConfirmDialog(null, "Do you want to delete existing sudoku files?") == JOptionPane.OK_OPTION) {
            deleteExistingFiles(path, filename);
        }
        else {
            int i = 1;
            while (file.exists()) {
                file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + "_" + i + ".csv");
                i++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(input);
        bw.close();

        return "File saved here: " + file.getAbsolutePath();
    }

    private static void deleteExistingFiles(String path, String filename) {
        File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + ".csv");
        int i = 1;
        while (file.exists()) {
            file.delete();
            file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\" + filename + "_" + i + ".csv");
            i++;
        }
    }

    private static double getRandom() {
        double multi = new Random().nextInt(11);
        double r = new Random().nextDouble();

        return r * multi;
    }
}
