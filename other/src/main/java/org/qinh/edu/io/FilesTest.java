package org.qinh.edu.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author Qh
 * @version 1.0
 * @date 2021-08-26-19:43
 */
public class FilesTest {

    @Test
    public void t1() throws IOException {
        String file = "D:\\temp\\FilesTest.java";
        Path source = Paths.get(file);
        Path target = Paths.get("D:\\temp\\success");
        //复制文件
        Files.copy(source, target.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        //读取文件
        Files.list(Paths.get("D:\\temp" )).forEach(p -> {
            if (!Files.isDirectory(p)){
                try {
                    Files.lines(p, StandardCharsets.UTF_8).forEach(System.out::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
        });
    }
}
