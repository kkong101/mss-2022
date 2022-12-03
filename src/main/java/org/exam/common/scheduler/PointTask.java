package org.exam.common.scheduler;


import lombok.extern.slf4j.Slf4j;
import org.exam.common.singleton.SingletonPointState;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class PointTask {

    @Scheduled(cron="0 0 0 * * *")
    public void initializationTodayPoint() {
        SingletonPointState singletonPointState = SingletonPointState.getInstance();
        singletonPointState.startTodayPoint();
        if(singletonPointState.getIsTodayPointAvailable())
            log.info("START TODAY ATTENDANCE ##");
    }

}
