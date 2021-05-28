package com.lucas.util.inputstream;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

/**
 * @author Lucasfen
 * @Date 2021/05/27
 */
public class InputUtils {

    public static InputStream getInputStream1(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            return classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStream2(String path) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
