package com.lucas.demo.controller.testcontroller;

import com.lucas.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Lucasfen
 * @Date 2021/08/16
 */
@Component
public class TestSchedule {

    @Autowired
    private ScheduleService scheduleService;

    @Scheduled(cron = "* * */1 * * ?")
    public void push() {
//        scheduleService.doTask();
    }
}
