package com.shpp.p2p.cs.yparfeniuk.assignment6.sg;

import acm.graphics.GCanvas;
import acm.graphics.GImage;

import java.awt.Dimension;

public class SteganographyPictureCanvas extends GCanvas implements SteganographyConstants {
    private GImage image;

    public SteganographyPictureCanvas() {
        this.setPreferredSize(new Dimension(400, 300));
    }

    public void setImage(GImage newImage) {
        if (this.image != null) {
            this.remove(this.image);
        }

        this.image = newImage;
        if (this.image != null) {
            this.image.setBounds(0.0D, 0.0D, (double) this.getWidth(), (double) this.getHeight());
            this.add(this.image);
        }

    }

    public GImage getImage() {
        return this.image;
    }
}
