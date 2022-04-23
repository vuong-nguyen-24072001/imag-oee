package com.commplc.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "historyline1")
public class HistoryLine1Entity extends BaseEntity {

    public HistoryLine1Entity(String line, String status, String speed, String counterOut, String runtime, String time,
                       String date, String shift, String target, String downtime, String speedStandard, String available, String performance,
                       String quanlity, String oee) {
        this.setLine(line);
        this.setStatus(status);
        this.setSpeed(speed);
        this.setCounterOut(counterOut);
        this.setRuntime(runtime);
        this.setTime(time);
        this.setDate(date);
        this.setShift(shift);
        this.setTarget(target);
        this.setDowntime(downtime);
        this.setSpeedStandard(speedStandard);
        this.setAvailable(available);
        this.setPerformance(performance);
        this.setQuanlity(quanlity);
        this.setOee(oee);
    }

    public HistoryLine1Entity() {

    }
}
