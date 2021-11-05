package com.lucas.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lucas.demo.dto.User;
import com.lucas.demo.utils.ListPartitionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * @author Lucasfen
 * @Date 2021/06/30
 */
@Service
public class ConcurrentService {

    public Future<String> asyncTask1() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask1 complete");
        return new AsyncResult<>("asyncTask1 complete");
    }

    public Future<String> asyncTask2() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask2 complete");
        return new AsyncResult<>("asyncTask2 complete");
    }

    public Future<String> asyncTask3() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask3 complete");
        return new AsyncResult<>("asyncTask3 complete");
    }

    public void asyncSimpleTask1() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask1 complete");
        //return new AsyncResult<>("asyncTask1 complete");
    }

    public void asyncSimpleTask2() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask2 complete");
        //return new AsyncResult<>("asyncTask2 complete");
    }

    public void asyncSimpleTask3() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": asyncTask3 complete");
        //return new AsyncResult<>("asyncTask3 complete");
    }

    @Autowired
    private UserService userService;

    @Async
    public void async1() {
        for (int i = 0; i < 100; i++) {
            async2();
        }
    }

    @Async
    public void async2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
