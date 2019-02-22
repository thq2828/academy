package com.academy.core.util;

import com.academy.core.pojo.User;

import java.io.*;

public class UploadPicUtil {
    private static final String ROOT_PIC_PATH = "/data/services/academy-noface-file";

    public static String upload(InputStream inputStream, String filePath, String fileType) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        long current = System.currentTimeMillis();
        String destination = ROOT_PIC_PATH + filePath + current + "." + fileType;
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = inputStream.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        inputStream.close();
        return filePath + current + "." + fileType;
    }

}
