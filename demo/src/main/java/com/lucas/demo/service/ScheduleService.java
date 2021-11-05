package com.lucas.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

/**
 * @author Lucasfen
 * @Date 2021/08/16
 */
@Service
public class ScheduleService {

    public void doTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("now:" + LocalDateTime.now());
    }
}
