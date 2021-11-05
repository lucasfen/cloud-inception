package com.lucas.demo.dto.thread;

import java.util.concurrent.Callable;

/**
 * @author Lucasfen
 * @Date 2021/08/05
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello";
    }
}
