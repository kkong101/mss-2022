package org.exam.scheduler;


import lombok.extern.slf4j.Slf4j;
import org.exam.singleton.SingletonPointState;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class PointTask {

    @Scheduled(cron="0/60 * * * * ?")
    public void initializationTodayPoint() {
        SingletonPointState singletonPointState = SingletonPointState.getInstance();
        singletonPointState.completeTodayPoint();
        System.out.println("60 => " + singletonPointState.getIsTodayPointAvailable());
    }

    @Scheduled(cron="0/30 * * * * ?")
    public void initializationTodayPoint2() {
        SingletonPointState singletonPointState = SingletonPointState.getInstance();
        singletonPointState.startTodayPoint();
        System.out.println("30 => " +singletonPointState.getIsTodayPointAvailable());
    }

}
