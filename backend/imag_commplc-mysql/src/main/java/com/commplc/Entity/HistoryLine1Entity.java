package com.commplc.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "historyline1")
public class HistoryLine1Entity extends BaseEntity {

    public HistoryLine1Entity(String line, String status, String speed, String counterOut, String runtime, String time, String date, String shift) {
        this.setLine(line);
        this.setStatus(status);
        this.setSpeed(speed);
        this.setCounterOut(counterOut);
        this.setRuntime(runtime);
        this.setTime(time);
        this.setDate(date);
        this.setShift(shift);
    }

    public HistoryLine1Entity() {

    }
}
