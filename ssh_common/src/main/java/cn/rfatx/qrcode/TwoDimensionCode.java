package cn.rfatx.qrcode;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 二维码管理
 */
public class TwoDimensionCode {
    public void encoderQRCode(String content, String imgPath) {
        encoderQRCode(content, imgPath, "png", 7);
    }

    public void encoderQRCode(String content, OutputStream output) {
        encoderQRCode(content, output, "png", 7);
    }

    public void encoderBigQRCode(String content, OutputStream output, int size) {
        encoderQRCode(content, output, "png", size);
    }

    public void encoderQRCode(String content, String imgPath, String imgType) {
        encoderQRCode(content, imgPath, imgType, 7);
    }

    public void encoderQRCode(String content, OutputStream output, String imgType) {
        encoderQRCode(content, output, imgType, 7);
    }

    public void encoderQRCode(String content, String imgPath, String imgType, int size) {
        try {
            BufferedImage bufImg = qRCodeCommon(content, imgType, size);
            File imgFile = new File(imgPath);

            ImageIO.write(bufImg, imgType, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encoderQRCode(String content, OutputStream output, String imgType, int size) {
        try {
            BufferedImage bufImg = qRCodeCommon(content, imgType, size);

            ImageIO.write(bufImg, imgType, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage qRCodeCommon(String content, String imgType, int size) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();

            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');

            qrcodeHandler.setQrcodeVersion(size);

            byte[] contentBytes = content.getBytes("utf-8");

            int imgSize = 67 + 12 * (size - 1);
            bufImg = new BufferedImage(imgSize, imgSize, 1);
            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            gs.setColor(Color.BLACK);

            int pixoff = 2;

            if ((contentBytes.length > 0) && (contentBytes.length < 800)) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++)
                        // rf-author START TODO if (codeOut[j][i] != 0)
                        if (codeOut[j][i] != false)
                            // rf-author END
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                }
            } else {
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            gs.dispose();
            bufImg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImg;
    }

    public String decoderQRCode(String imgPath) {
        File imageFile = new File(imgPath);
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(imageFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
        } catch (IOException | DecodingFailedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return content;
    }

    public String decoderQRCode(InputStream input) {
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(input);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new TwoDimensionCodeImage(bufImg)), "utf-8");
        } catch (IOException | DecodingFailedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return content;
    }
}
