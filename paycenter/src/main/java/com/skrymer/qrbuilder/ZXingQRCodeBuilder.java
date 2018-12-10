package com.skrymer.qrbuilder;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.skrymer.qrbuilder.decorator.QRCodeDecorator;
import com.skrymer.qrbuilder.exception.CouldNotCreateQRCodeException;
import com.skrymer.qrbuilder.exception.InvalidSizeException;
import com.skrymer.qrbuilder.exception.UnreadableDataException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.skrymer.qrbuilder.util.SyntacticSugar.throwIllegalArgumentExceptionIfEmpty;

/**
 * QRCBuilder implementation using the ZXing library to generate a qrCode as a  BufferedImage
 * <p>
 * see https://github.com/zxing/zxing
 */
public class ZXingQRCodeBuilder implements QRCBuilder<BufferedImage> {
    private String data;
    private Boolean verify;
    private Integer width, height;
    private List<QRCodeDecorator<BufferedImage>> decorators;

    public ZXingQRCodeBuilder() {
        verify = true;
    }

    public QRCBuilder<BufferedImage> newQRCode() {
        return this;
    }

    public QRCBuilder<BufferedImage> and() {
        return this;
    }

    public QRCBuilder<BufferedImage> doVerify(Boolean doVerify) {
        this.verify = doVerify;
        return this;
    }

    public QRCBuilder<BufferedImage> withData(String data) {
        this.data = data;
        return this;
    }

    public QRCBuilder<BufferedImage> withSize(Integer width, Integer height) {
        validateSize(width, height);

        this.width = width;
        this.height = height;
        return this;
    }

    public QRCBuilder<BufferedImage> decorate(QRCodeDecorator<BufferedImage> decorator) {
        if (decorators == null) {
            decorators = new LinkedList<>();
        }

        decorators.add(decorator);
        return this;
    }

    public BufferedImage toImage() throws CouldNotCreateQRCodeException, UnreadableDataException {
        BufferedImage qrcode = encode();
        qrcode = decorate(qrcode);
        verifyQRCode(qrcode);
        return qrcode;
    }

    public File toFile(String fileName, String fileFormat) throws CouldNotCreateQRCodeException, UnreadableDataException, IOException {
        throwIllegalArgumentExceptionIfEmpty(fileName, "fileName");
        throwIllegalArgumentExceptionIfEmpty(fileFormat, "fileFormat");

        File imageFile = new File(fileName);
        ImageIO.write(toImage(), fileFormat, imageFile);
        return imageFile;
    }

    private void validateSize(Integer width, Integer height) {
        if (width <= 0) {
            throw new InvalidSizeException("Width is to small should be > 0 is " + width);
        }

        if (height <= 0) {
            throw new InvalidSizeException("Height is to small should be > 0 is " + height);
        }
    }

    private void verifyQRCode(BufferedImage qrcode) {
        if (!verify) {
            return;
        }

        try {
            Result readData = readData(qrcode);
            if (!readData.getText().equals(this.data)) {
                throw new UnreadableDataException("The data contained in the qrCode is not as expected: " + this.data + " actual: " + readData);
            }
        } catch (ReaderException ex) {
            throw new UnreadableDataException("The data contained in the qrCode is not readable", ex);
        }
    }

    private Result readData(BufferedImage qrcode) throws ReaderException {
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(qrcode)));
        return new QRCodeReader().decode(bitmap, getDecodeHints());
    }

    private Map<EncodeHintType, Object> getEncodeHints() {
        Map<EncodeHintType, Object> encodeHints = new HashMap<EncodeHintType, Object>();
        encodeHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        encodeHints.put(EncodeHintType.CHARACTER_SET, "utf8");
        encodeHints.put(EncodeHintType.MARGIN, 0);
        return encodeHints;
    }

    private Map<DecodeHintType, Object> getDecodeHints() {
        Map<DecodeHintType, Object> decodeHints = new HashMap<DecodeHintType, Object>();
        decodeHints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        decodeHints.put(DecodeHintType.CHARACTER_SET, "utf8");
        return decodeHints;
    }

    private BufferedImage encode() {
        BufferedImage qrcode;

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, this.width, this.height, getEncodeHints());
            qrcode = MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            throw new CouldNotCreateQRCodeException("QRCode could not be generated", e.getCause());
        }

        return qrcode;
    }

    private BufferedImage decorate(BufferedImage qrcode) {
        if (decorators != null) {
            for (QRCodeDecorator<BufferedImage> decorator : decorators) {
                qrcode = decorator.decorate(qrcode);
            }
        }

        return qrcode;
    }
}
