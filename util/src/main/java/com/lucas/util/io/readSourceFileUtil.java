package com.lucas.util.io;

import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

/**
 * @author: lucasfen
 * @create: 2021/02/02
 */
public class readSourceFileUtil {

    public InputStream readFileFromResource() throws Exception {
        return new ClassPathResource("test/demo.txt").getInputStream();
    }

    public InputStream readFileFromSys() throws Exception {
        return new FileInputStream("C:\\Users\\Administrator\\Desktop\\demo.txt");
    }
}
