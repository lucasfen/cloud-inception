package com.lucas.demo.utils;

import java.util.Random;

/**
 * @author Lucasfen
 * @Date 2021/05/09
 */
public class RandomUtils {

    private static final String NUMBER_LETTER = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final Random rand = new Random();
    public static String getRandomSixNum() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < 6; i++) {
            stringBuilder.append(NUMBER_LETTER.charAt(rand.nextInt(NUMBER_LETTER.length())));
        }
        return stringBuilder.toString();
    }
}
