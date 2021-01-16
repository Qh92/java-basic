package com.qinh.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-01-16-15:06
 */
public class FileUtilsTest {

    public static void main(String[] args) {
        File srcFile = new File("io\\01.jpg");
        File destFile = new File("io\\0111.jpg");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
