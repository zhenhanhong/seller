package cn.rfatx.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

/**
 * 生成二维码
 */
public class TwoDimensionCodeImage
        implements QRCodeImage {
    BufferedImage bufImg;

    public TwoDimensionCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    public int getHeight() {
        return this.bufImg.getHeight();
    }

    public int getPixel(int x, int y) {
        return this.bufImg.getRGB(x, y);
    }

    public int getWidth() {
        return this.bufImg.getWidth();
    }
}
