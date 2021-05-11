package com.shpp.p2p.cs.yparfeniuk.assignment6.tm;

import acm.util.ErrorException;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ToneMatrix extends SimpleProgram {
    private ToneMatrixDisplay display = new ToneMatrixDisplay();
    private static final int MATRIX_SIZE = ToneMatrixConstants.size();

    public void init() {
        this.add(this.display);
        this.add(new JButton("Clear"), "South");
        this.add(new JButton("Save Matrix"), "South");
        this.add(new JButton("Load Matrix"), "South");
        this.addActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            this.display.clear();
        } else if (e.getActionCommand().equals("Save Matrix")) {
            this.saveMatrix();
        } else if (e.getActionCommand().equals("Load Matrix")) {
            this.loadMatrix();
        }

    }

    private String extensionOf(File filename) {
        int lastDot = filename.getName().lastIndexOf(46);
        return lastDot == -1 ? "" : filename.getName().substring(lastDot);
    }

    private JFileChooser getFileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            public boolean accept(File filename) {
                return filename.isDirectory() || ToneMatrix.this.extensionOf(filename).equals(".matrix");
            }

            public String getDescription() {
                return ".matrix Files";
            }
        });
        fc.setCurrentDirectory(new File("matrices"));
        return fc;
    }

    private void saveMatrix() {
        JFileChooser fc = this.getFileChooser();
        if (fc.showSaveDialog(this) == 0) {
            String filename = fc.getSelectedFile().getAbsolutePath();
            if (this.extensionOf(fc.getSelectedFile()).equals("")) {
                filename = filename + ".matrix";
            }

            try {
                this.writeMatrixToFile(this.display.getMatrix(), filename);
            } catch (ErrorException var4) {
                this.getDialog().showErrorMessage("Error saving file: " + var4.getMessage());
            }
        }

    }

    private void loadMatrix() {
        JFileChooser fc = this.getFileChooser();
        if (fc.showOpenDialog(this) == 0) {
            try {
                this.display.setMatrix(this.readMatrixFromFile(fc.getSelectedFile().getAbsolutePath()));
            } catch (ErrorException var3) {
                this.getDialog().showErrorMessage("Error loading file: " + var3.getMessage());
            }
        }

    }

    public void writeMatrixToFile(boolean[][] toneMatrix, String fileName) {
        try {
            PrintWriter e = new PrintWriter(new FileWriter(fileName));

            for (int row = 0; row < MATRIX_SIZE; ++row) {
                for (int col = 0; col < MATRIX_SIZE; ++col) {
                    e.println(toneMatrix[row][col]);
                }
            }

            e.close();
        } catch (IOException var6) {
            throw new ErrorException(var6);
        }
    }

    public boolean[][] readMatrixFromFile(String fileName) {
        try {
            boolean[][] e = new boolean[MATRIX_SIZE][MATRIX_SIZE];
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            for (int row = 0; row < MATRIX_SIZE; ++row) {
                for (int col = 0; col < MATRIX_SIZE; ++col) {
                    e[row][col] = Boolean.parseBoolean(br.readLine());
                }
            }

            br.close();
            return e;
        } catch (IOException var6) {
            throw new ErrorException(var6);
        }
    }

    private void validate(double[] clip) {
        double[] var6 = clip;
        int var5 = clip.length;

        for (int var4 = 0; var4 < var5; ++var4) {
            double value = var6[var4];
            if (Double.isNaN(value)) {
                throw new RuntimeException("Division by zero.");
            }

            if (value < -1.0D || value > 1.0D) {
                throw new RuntimeException("Intensity out of range (did you forget to normalize?)");
            }
        }

    }

    public void run() {
        double[][] samples = ToneMatrixWaveGenerator.getSamples();

        try {
            while (true) {
                for (int e = 0; e < MATRIX_SIZE; ++e) {
                    boolean[][] matrix = this.display.getMatrix();
                    this.display.highlightColumn(e);
                    double[] toPlay = ToneMatrixLogic.matrixToMusic(matrix, e, samples);
                    this.validate(toPlay);
                    StdAudio.play(toPlay);
                }
            }
        } catch (RuntimeException var5) {
            this.getDialog().showErrorMessage("Something went wrong: " + var5.getMessage());
            System.exit(-1);
        }
    }
}
