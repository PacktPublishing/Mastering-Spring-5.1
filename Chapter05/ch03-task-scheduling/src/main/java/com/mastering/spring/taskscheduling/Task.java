package com.mastering.spring.taskscheduling;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    @Scheduled(fixedRate = 10000)
    //fixedDelay=10000
    //initialDelay=20000
    //cron="*/5 * * * * MON-FRI") //CRON EXPRESSION
    public void execute() {
        log.info("The time is now {}", new Date());
    }
}