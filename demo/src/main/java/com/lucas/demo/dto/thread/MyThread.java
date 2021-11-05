package com.lucas.demo.dto.thread;

/**
 * @author Lucasfen
 * @Date 2021/08/05
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("i am a child thread, thread name:" + Thread.currentThread().getName());
    }
}
