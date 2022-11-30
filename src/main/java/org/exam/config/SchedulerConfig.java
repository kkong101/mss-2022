package org.exam.config;


import org.exam.scheduler.PointTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public PointTask pointTask() {
        return new PointTask();
    }

}
