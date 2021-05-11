package com.shpp.p2p.cs.yparfeniuk.assignment6.sg;

import acm.graphics.GImage;
import acm.gui.TableLayout;
import acm.util.ErrorException;
import acm.util.MediaTools;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class Steganography extends SimpleProgram implements SteganographyConstants {
    private static final int HGAP = 5;
    private static final int VGAP = 5;
    public static final int APPLICATION_WIDTH = 900;
    private static final String[] SAVE_IMAGE_EXTENSIONS = new String[]{".png", ".bmp", ".wbmp"};
    private static final String[] LOAD_IMAGE_EXTENSIONS = new String[]{".png", ".bmp", ".wbmp", ".jpg", ".gif", ".jpeg"};
    private SteganographyDrawingCanvas drawing = new SteganographyDrawingCanvas();
    private SteganographyPictureCanvas picture = new SteganographyPictureCanvas();

    public void init() {
        MediaTools.setCachingEnabled(false);
        this.setLayout(new TableLayout(3, 2, 5, 5));
        this.add(new JLabel("Secret Drawing"));
        this.add(new JLabel("Master Image"));
        this.add(this.drawing);
        this.add(this.picture);
        this.add(new JButton("Clear Drawing"));
        this.add(new JButton("Choose Image"));
        this.add(new JButton("Hide Message"), "South");
        this.add(new JButton("Recover Message"), "South");
        this.addActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear Drawing")) {
            this.drawing.clear();
        } else if (e.getActionCommand().equals("Choose Image")) {
            this.chooseImage();
        } else if (e.getActionCommand().equals("Hide Message")) {
            this.hideMessage();
        } else if (e.getActionCommand().equals("Recover Message")) {
            this.recoverMessage();
        }

    }

    public class SteganographyFileFilter extends FileFilter {
        // $FF: synthetic field
        final Steganography this$0;
        // $FF: synthetic field
        private final String[] val$extensions;
        // $FF: synthetic field
        private final String val$description;

        SteganographyFileFilter(Steganography var1, String[] var2, String var3) {
            this.this$0 = var1;
            this.val$extensions = var2;
            this.val$description = var3;
        }

        public boolean accept(File filename) {
            return filename.isDirectory() || Arrays.asList(this.val$extensions).contains(Steganography.access$0(this.this$0, filename));
        }

        public String getDescription() {
            return this.val$description;
        }
    }


    private JFileChooser getFileChooser(String[] extensions, String description) {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new SteganographyFileFilter(this, extensions, description));
        fc.setCurrentDirectory(new File("steganography"));
        return fc;
    }

    private void chooseImage() {
        JFileChooser fc = this.getFileChooser(LOAD_IMAGE_EXTENSIONS, "Image files");
        if (fc.showOpenDialog(this) == 0) {
            try {
                this.picture.setImage(new GImage(fc.getSelectedFile().getAbsolutePath()));
            } catch (ErrorException var3) {
                this.getDialog().showErrorMessage("Could not open that image.");
            }
        }

    }

    private void hideMessage() {
        try {
            GImage e = SteganographyLogic.hideMessage(this.toBooleanArray(this.getImage(this.drawing)), this.getImage(this.picture));
            if (e == null) {
                throw new NullPointerException("Image is null.");
            }

            this.picture.setImage(e);
            if (this.getDialog().readBoolean("Message hidden.  Do you want to save?", "Yes", "No")) {
                JFileChooser fc = this.getFileChooser(SAVE_IMAGE_EXTENSIONS, ".png, .bmp, and .wbmp files");
                if (fc.showSaveDialog(this) == 0) {
                    this.saveImage(this.picture.getImage(), fc.getSelectedFile());
                }
            }
        } catch (Exception var3) {
            this.getDialog().showErrorMessage("An error occurred: " + var3.getMessage());
        }

    }

    private void recoverMessage() {
        try {
            this.drawing.setImage(this.toGImage(SteganographyLogic.findMessage(this.getImage(this.picture))));
        } catch (Exception var2) {
            this.getDialog().showErrorMessage("An error occurred: " + var2.getMessage());
        }

    }

    private boolean[][] toBooleanArray(GImage image) {
        int[][] pixels = image.getPixelArray();
        int numRows = pixels.length;
        int numCols = pixels[0].length;
        int whitePixel = GImage.createRGBPixel(255, 255, 255);
        boolean[][] result = new boolean[numRows][numCols];

        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                result[row][col] = pixels[row][col] != whitePixel;
            }
        }

        return result;
    }

    private GImage toGImage(boolean[][] pixels) {
        if (pixels == null) {
            throw new NullPointerException("Returned array is null.");
        } else if (pixels.length != 300) {
            throw new IllegalArgumentException("Incorrect size for returned array.");
        } else {
            for (int result = 0; result < pixels.length; ++result) {
                if (pixels[result] == null) {
                    throw new IllegalArgumentException("Inner array is null.");
                }

                if (pixels[result].length != 400) {
                    throw new IllegalArgumentException("Incorrect size for returned array.");
                }
            }

            int[][] var5 = new int[300][400];

            for (int row = 0; row < pixels.length; ++row) {
                for (int col = 0; col < pixels[row].length; ++col) {
                    var5[row][col] = pixels[row][col] ? GImage.createRGBPixel(0, 0, 0) : GImage.createRGBPixel(255, 255, 255);
                }
            }

            return new GImage(var5);
        }
    }

    private String extensionOf(File filename) {
        int lastDot = filename.getName().lastIndexOf(46);
        return lastDot == -1 ? "" : filename.getName().substring(lastDot);
    }

    private boolean isLegalExtension(String extension) {
        String[] var5 = SAVE_IMAGE_EXTENSIONS;
        int var4 = SAVE_IMAGE_EXTENSIONS.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            String legal = var5[var3];
            if (legal.equals(extension)) {
                return true;
            }
        }

        return false;
    }

    private BufferedImage getBufferedImageFromImage(Image img) {
        img = (new ImageIcon(img)).getImage();
        BufferedImage bufferedImage = new BufferedImage(400, 300, 1);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(img, 0, 0, (ImageObserver) null);
        g.dispose();
        return bufferedImage;
    }

    private void saveImage(GImage image, File file) {
        try {
            int e = file.getName().lastIndexOf(46);
            if (e == -1) {
                file = new File(file.getAbsolutePath() + ".png");
                System.out.println(file);
            }

            String extension = this.extensionOf(file.getAbsoluteFile());
            if (!this.isLegalExtension(extension)) {
                throw new ErrorException("Unsupported file format.");
            }

            BufferedImage bufferedImage = this.getBufferedImageFromImage(MediaTools.createImage(image.getPixelArray()));
            ImageIO.write(bufferedImage, extension.substring(1), file);
        } catch (ErrorException var6) {
            this.getDialog().showErrorMessage(var6.getMessage());
        } catch (IOException var7) {
            this.getDialog().showErrorMessage("An error occurred saving the image.");
        }

    }

    private GImage getImage(Component c) {
        BufferedImage bi = new BufferedImage(400, 300, 1);
        Graphics2D g = bi.createGraphics();
        c.paint(g);
        return new GImage(bi);
    }

    // $FF: synthetic method
    static String access$0(Steganography var0, File var1) {
        return var0.extensionOf(var1);
    }
}
