package com.commplc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line")
    private String line;

    @Column(name = "status")
    private String status;

    @Column(name = "speed")
    private String speed;

    @Column(name = "counterout")
    private String counterOut;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "time")
    private String time;

    @Column(name = "date")
    private String date;

    @Column(name = "shift")
    private String shift;

    @Column(name = "target")
    private String target;

    public HistoryEntity(String line, String status, String speed, String counterOut, String runtime, String time, String date, String shift) {
        this.line = line;
        this.status = status;
        this.speed = speed;
        this.counterOut = counterOut;
        this.runtime = runtime;
        this.time = time;
        this.date = date;
        this.shift = shift;
    }

    public HistoryEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCounterOut() {
        return counterOut;
    }

    public void setCounterOut(String counterOut) {
        this.counterOut = counterOut;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() {
        return date;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
