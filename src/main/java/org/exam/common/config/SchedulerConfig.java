package org.exam.common.config;


import org.exam.common.scheduler.PointTask;
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
