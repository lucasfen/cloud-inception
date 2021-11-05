package com.lucas.demo.dto.thread;

/**
 * @author Lucasfen
 * @Date 2021/08/05
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread " + Thread.currentThread().getName());
    }
}
