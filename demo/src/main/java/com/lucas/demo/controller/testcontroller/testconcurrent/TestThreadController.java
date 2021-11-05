package com.lucas.demo.controller.testcontroller.testconcurrent;

import java.util.concurrent.FutureTask;

import com.lucas.demo.dto.thread.MyCallable;
import com.lucas.demo.dto.thread.MyRunnable;
import com.lucas.demo.dto.thread.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/07/14
 */
@RequestMapping("/test/threadPool")
@RestController
public class TestThreadController {

    @PostMapping("/create/thread/1")
    public void testCreateThread1() {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(Thread.currentThread().getName());
    }

    @PostMapping("/create/thread/2")
    public void testCreateThread2() {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "child-thread-1").start();
        new Thread(myRunnable, "child-thread-2").start();
        System.out.println(Thread.currentThread().getName());
    }

    @PostMapping("/create/thread/3")
    public void testCreateThread3() {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask, "child-thread-1").start();
        new Thread(futureTask, "child-thread-2").start();
        try {
            String result = futureTask.get();
            System.out.println(result + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    @PostMapping("/create/thread/4")
    public void testCreateThread4() {

    }
}
