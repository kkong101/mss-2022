package org.exam.common.singleton;

import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingletonPointState {

    private static SingletonPointState instance;

    private AtomicBoolean isTodayPointAvailable = new AtomicBoolean(true);

    public static SingletonPointState getInstance() {
        if(Objects.isNull(instance)) {
            synchronized(SingletonPointState.class) {
                instance = new SingletonPointState();
            }
        }
        return instance;
    }

    public void completeTodayPoint() {
        this.isTodayPointAvailable.getAndSet(false);
    }

    public void startTodayPoint() {
        this.isTodayPointAvailable.getAndSet(true);
    }

    public Boolean getIsTodayPointAvailable() {
        return this.isTodayPointAvailable.get();
    }

}
