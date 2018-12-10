package com.skrymer.qrbuilder;

import com.skrymer.qrbuilder.decorator.QRCodeDecorator;
import com.skrymer.qrbuilder.exception.CouldNotCreateQRCodeException;
import com.skrymer.qrbuilder.exception.UnreadableDataException;

import java.io.File;
import java.io.IOException;

/**
 * A builder to create QRCodes
 */
public interface QRCBuilder<T> {

    /**
     * Syntactic sugar - qrbuilder.newQRCode().with....
     *
     */
    public QRCBuilder<T> newQRCode();

    /**
     * Syntactic sugar - qrbuilder.newQRCode().with().and().with()
     *
     */
    public QRCBuilder<T> and();

    /**
     * Should the generated QRCode be verified after creation
     *
     */
    public QRCBuilder<T> doVerify(Boolean doVerify);

    /**
     * Adds the specified data to the QRCode
     *
     */
    public QRCBuilder<T> withData(String data);

    /**
     * Sets the size for the generated QRCode
     *
     * @throws com.skrymer.qrbuilder.exception.InvalidSizeException If width or height <= 0 a InvalidSizeException is thrown
     */
    public QRCBuilder<T> withSize(Integer width, Integer height);

    /**
     * Adds the given decorator. Decoration is applied after the qrcode has been generated
     * <p>
     * If more than one decorator is added, the order on which they are called will be FIFO style
     *
     */
    public QRCBuilder<T> decorate(QRCodeDecorator<T> decorator);

    /**
     * Creates the new QRCode image with the specified attributes
     *
     * @return The new QRCode image
     * @throws CouldNotCreateQRCodeException If the QRCode could not be generated a CouldNotCreateQRCodeException is thrown
     * @throws UnreadableDataException       If the data contained in the QRCode is not the same as the data specified or the data is unreadable
     */
    public T toImage() throws CouldNotCreateQRCodeException, UnreadableDataException;

    /**
     * @param fileName   - the absolute file name
     * @param fileFormat - the image type (png, jpg, gif)
     * @return The generated image file
     * @throws CouldNotCreateQRCodeException If the QRCode could not be generated a CouldNotCreateQRCodeException is thrown
     * @throws UnreadableDataException       If the data contained in the QRCode is not the same as the data specified or the data is unreadable
     */
    public File toFile(String fileName, String fileFormat) throws CouldNotCreateQRCodeException, UnreadableDataException, IOException;
}
