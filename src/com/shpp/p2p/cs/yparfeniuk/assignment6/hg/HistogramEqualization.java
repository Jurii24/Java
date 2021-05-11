package com.shpp.p2p.cs.yparfeniuk.assignment6.hg;

import acm.graphics.GImage;
import acm.util.ErrorException;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class HistogramEqualization extends WindowProgram implements ComponentListener {
    private GImage displayImage;
    private GImage masterImage;
    private JButton toggleButton;
    private static final String[] LOAD_IMAGE_EXTENSIONS = new String[]{".png", ".bmp", ".wbmp", ".jpg", ".gif", ".jpeg"};

    public void init() {
        this.add(new JButton("Choose Image"), "South");
        this.add(this.toggleButton = new JButton("Equalize"), "South");
        this.setIsEqualize(true);
        this.addComponentListener(this);
        this.addActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Choose Image")) {
            this.chooseImage();
        } else if (e.getActionCommand().equals("Equalize")) {
            this.equalizeImage();
        } else if (e.getActionCommand().equals("Original")) {
            this.restoreImage();
        }

    }

    private void redrawAll() {
        if (this.displayImage != null) {
            this.displayImage.setBounds(0.0D, 0.0D, (double) this.getWidth(), (double) this.getHeight());
        }

    }

    private void computeEqualizedImage() {
        assert this.displayImage != null;

        int[][] pixels = HistogramEqualizationImageTransforms.toGrayscale(this.displayImage).getPixelArray();
        int[][] intensities = HistogramEqualizationImageTransforms.imageToLuminances(pixels);
        int[][] result = HistogramEqualizationLogic.equalize(intensities);
        if (this.confirmResult(result, pixels)) {
            for (int row = 0; row < result.length; ++row) {
                for (int col = 0; col < result[row].length; ++col) {
                    int intensity = result[row][col];
                    result[row][col] = GImage.createRGBPixel(intensity, intensity, intensity);
                }
            }

            this.setImage(new GImage(result));
        }

    }

    private boolean confirmResult(int[][] result, int[][] pixels) {
        if (result == null) {
            this.getDialog().showErrorMessage("Resulting image was null.");
            return false;
        } else if (result.length != pixels.length) {
            this.getDialog().showErrorMessage("Resulting image has the wrong number of rows.");
            return false;
        } else {
            for (int i = 0; i < result.length; ++i) {
                if (result[i] == null) {
                    this.getDialog().showErrorMessage("Resulting image contains a null row.");
                    return false;
                }

                if (result[i].length != pixels[i].length) {
                    this.getDialog().showErrorMessage("Resulting image has the wrong number of columns.");
                    return false;
                }

                for (int j = 0; j < result[i].length; ++j) {
                    if (result[i][j] < 0 || result[i][j] >= 256) {
                        this.getDialog().showErrorMessage("Luminance out of range: " + result[i][j]);
                        return false;
                    }
                }
            }

            return true;
        }
    }

    private void setIsEqualize(boolean isEqualize) {
        this.toggleButton.setText(isEqualize ? "Equalize" : "Original");
    }

    private void setImage(GImage image) {
        if (this.displayImage != null) {
            this.remove(this.displayImage);
        }

        this.displayImage = image;
        if (image != null) {
            this.displayImage.setBounds(0.0D, 0.0D, 0.0D, 0.0D);
            this.add(this.displayImage);
        }

        this.redrawAll();
    }

    private String extensionOf(File filename) {
        int lastDot = filename.getName().lastIndexOf(46);
        return lastDot == -1 ? "" : filename.getName().substring(lastDot);
    }

    private JFileChooser getFileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            public boolean accept(File filename) {
                return filename.isDirectory() || Arrays.asList(HistogramEqualization.LOAD_IMAGE_EXTENSIONS).contains(HistogramEqualization.this.extensionOf(filename));
            }

            public String getDescription() {
                return "Image files";
            }
        });
        fc.setCurrentDirectory(new File("histogram/"));
        return fc;
    }

    private void chooseImage() {
        JFileChooser fc = this.getFileChooser();
        if (fc.showOpenDialog(this) == 0) {
            try {
                this.masterImage = new GImage(fc.getSelectedFile().getAbsolutePath());
                this.setImage(this.masterImage);
                this.setIsEqualize(true);
            } catch (ErrorException var3) {
                this.getDialog().showErrorMessage("Error loading file: " + var3.getMessage());
            }
        }

    }

    private void equalizeImage() {
        if (this.displayImage == null) {
            this.getDialog().showErrorMessage("No image selected.");
        } else {
            this.computeEqualizedImage();
            this.setIsEqualize(false);
        }

    }

    private void restoreImage() {
        this.setImage(this.masterImage);
        this.setIsEqualize(true);
    }

    public void componentResized(ComponentEvent arg0) {
        this.redrawAll();
    }

    public void componentHidden(ComponentEvent arg0) {
    }

    public void componentMoved(ComponentEvent arg0) {
    }

    public void componentShown(ComponentEvent arg0) {
    }
}
