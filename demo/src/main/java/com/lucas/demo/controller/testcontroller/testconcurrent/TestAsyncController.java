package com.lucas.demo.controller.testcontroller.testconcurrent;

import java.util.concurrent.Future;

import com.lucas.demo.service.ConcurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/06/28
 */
@RequestMapping("/test/concurrent")
@RestController
public class TestAsyncController {

    @Autowired
    private ConcurrentService concurrentService;

    public static String status = "async tasks are not triggered.";

    public static Future<String> task1;
    public static Future<String> task2;
    public static Future<String> task3;

    @PostMapping("/simpleAsync")
    public String testSimpleConcurrent() {
        long startTimeStamp = System.currentTimeMillis();
        concurrentService.asyncSimpleTask1();
        concurrentService.asyncSimpleTask2();
        concurrentService.asyncSimpleTask3();
        long endTimeStamp = System.currentTimeMillis();
        String message = "async tasks are triggered successfully, duration: " + (endTimeStamp - startTimeStamp) + " ms";
        System.out.println(message);
        return message;
    }

    @PostMapping("/async")
    public String testConcurrent() {
        long startTimeStamp = System.currentTimeMillis();
        task1 = concurrentService.asyncTask1();
        task2 = concurrentService.asyncTask2();
        task3 = concurrentService.asyncTask3();
        long endTimeStamp = System.currentTimeMillis();
        String message = "async tasks are triggered successfully, duration: " + (endTimeStamp - startTimeStamp) + " ms";
        System.out.println(message);
        return message;
    }

    @PostMapping("/task/status")
    public String getTasksStatus() {
        assert task1 != null;
        if (task1.isDone() && task2.isDone() && task3.isDone()) {
            status = "async tasks are done.";
        }
        return status;
    }

    @PostMapping("/task/status/{taskId}")
    public Boolean getTaskStatus(@PathVariable(name = "taskId") int taskId) {
        boolean taskStatus = false;
        switch (taskId) {
            case 1:
                taskStatus = task1.isDone();
                break;
            case 2:
                taskStatus = task2.isDone();
                break;
            case 3:
                taskStatus = task3.isDone();
                break;
            default:
                break;
        }
        return taskStatus;
    }

    @PostMapping("/multi/async")
    public void testAsync() {
        concurrentService.async1();
    }
}
