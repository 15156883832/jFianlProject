package com.sf.jfinal.qs.core.tools;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.codec.Base64;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author gaols
 */
public class ImageKit {
    /**
     * 创建图片的缩略图。
     *
     * @param size     final thumbnail size
     * @param destPath thumbnail store path
     */
    public static void generateThumbnail(BufferedImage image, int size, String destPath) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        int cropSize = Math.min(width, height);
        Thumbnails.of(image)
                .sourceRegion(Positions.CENTER, cropSize, cropSize)
                .size(size, size)
                .toFile(destPath);
    }

    /**
     * 获取文件的base64形式的编码。
     */
    public static String encodeToBase64(File image) throws IOException {
        byte[] imageBytes = IOUtils.toByteArray(image.toURI());
        return Base64.encodeToString(imageBytes);
    }

    /**
     * 获取文件的base64形式的编码。
     */
    public static String encodeToBase64(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            return Base64.encodeToString(imageBytes);
        }
    }

    /**
     * @param image   image to be compressed
     * @param quality 0 - 1.0
     */
    public static BufferedImage compress(File image, double scale, double quality) throws IOException {
        return Thumbnails.of(image)
                .outputQuality(quality)
                .scale(scale)
                .asBufferedImage();
    }

    /**
     * @param image   image to be compressed
     * @param quality 0 - 1.0
     */
    public static BufferedImage compress(BufferedImage image, double scale, double quality) throws IOException {
        return Thumbnails.of(image)
                .outputQuality(quality)
                .scale(scale)
                .asBufferedImage();
    }

    /**
     * @param image   image to be compressed
     * @param quality 0 - 1.0
     */
    public static void compress(File image, File destFile, double scale, double quality) throws IOException {
        Thumbnails.of(image)
                .outputQuality(quality)
                .scale(scale)
                .toFile(destFile);
    }

    /**
     * 图片灰度化处理。
     *
     * @param in 待处理图片
     */
    public static BufferedImage grayScale(BufferedImage in) {
        BufferedImage image = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image.getGraphics();
        g.drawImage(in, 0, 0, null);
        g.dispose();
        return image;
    }

    /**
     * Gets image dimensions for given file.
     *
     * @param image image file
     * @return dimensions of image
     * @throws IOException if the file is not a known image
     */
    public static Dimension getImageDimension(File image) throws IOException {
        int pos = image.getName().lastIndexOf(".");
        if (pos == -1) {
            throw new RuntimeException(String.format("No extension for: %s", image.getAbsolutePath()));
        }

        String suffix = image.getName().substring(pos + 1);
        Iterator<ImageReader> iterator = ImageIO.getImageReadersBySuffix(suffix);
        if (iterator.hasNext()) {
            ImageReader reader = iterator.next();
            try {
                reader.setInput(new FileImageInputStream(image));
                int minIndex = reader.getMinIndex();
                return new Dimension(reader.getWidth(minIndex), reader.getHeight(minIndex));
            } finally {
                reader.dispose();
            }
        }

        throw new IOException(String.format("No image found: %s", image.getAbsolutePath()));
    }
}
