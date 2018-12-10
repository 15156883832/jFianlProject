package com.sf.jfinal.qs.core.tools;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathKit {
    public static String getFileExtension(String filePath, boolean withDot) {
        String extension = FilenameUtils.getExtension(filePath);
        return "".equals(extension) ? null : (withDot ? "." + extension : extension);
    }

    public static String getFileName(String filePath) {
        return FilenameUtils.getName(filePath);
    }

    public static String getBareFileName(String filePath) {
        String fileName = FilenameUtils.getName(filePath);
        int dotIndex = fileName.indexOf(".");
        if (dotIndex > -1) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    public static String getFileDir(String filePath, boolean withLastSlash) {
        Path path = Paths.get(filePath).getParent();
        return path == null ? filePath : (withLastSlash ? path.toString() + "/" : path.toString());
    }

    public static String join(String base, String... path) {
        return StrKit.pathJoin(base, path);
    }

    public static String getWebRootPath() {
        return com.jfinal.kit.PathKit.getWebRootPath();
    }

    public static String getPublicDir(String... relPath) {
        return join(getWebRootPath(), relPath);
    }

    public static void ensureDir(String dir) {
        File d = new File(dir);
        if (d.isDirectory()) {
            return;
        }
        if (!d.exists()) {
            if (!d.mkdirs()) {
                throw new RuntimeException("cannot create dir: " + dir);
            }
            return;
        }

        throw new RuntimeException("cannot create dir: " + dir);
    }
}
