package org.exam.singleton;

import lombok.Getter;

import java.util.Objects;

public final class SingletonPointState {

    private static SingletonPointState instance;

    @Getter
    private Boolean isTodayPointAvailable = true;

    public static SingletonPointState getInstance() {
        if(Objects.isNull(instance)) {
            synchronized(SingletonPointState.class) {
                instance = new SingletonPointState();
            }
        }
        return instance;
    }

    public synchronized void completeTodayPoint() {
        this.isTodayPointAvailable = false;
    }

    public synchronized void startTodayPoint() {
        this.isTodayPointAvailable = true;
    }

}
