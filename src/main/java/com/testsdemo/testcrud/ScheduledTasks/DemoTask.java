package com.testsdemo.testcrud.ScheduledTasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//https://medium.com/@bectorhimanshu/how-to-create-a-scheduled-task-using-a-cron-job-in-spring-boot-a1987e679d60
@Component
public class DemoTask {

    @Scheduled(cron =  "*/10 * * * * *")
    public  void execute(){
        System.out.println("**********************[ this is cron every 10 seconds ]**********************");
    }
}
