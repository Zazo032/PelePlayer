package com.czazo.peleplayer;

import java.util.ArrayList;
import java.util.List;

public class TimeSubject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int currentTime;

    TimeSubject (){
        currentTime = 0;
    }

    public int getCurrentTime(){
        return currentTime;
    };

    public void setCurrentTime(int state) {
        this.currentTime = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObservers(){
        for(Observer observer : observers) {
            observer.update();
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
