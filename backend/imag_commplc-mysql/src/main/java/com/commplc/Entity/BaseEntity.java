package com.commplc.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

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
    private String target = "0";

    @Column(name = "available")
    private String available;

    @Column(name = "performance")
    private String performance;

    @Column(name = "quanlity")
    private String quanlity;

    @Column(name = "oee")
    private String oee;

    @Column(name = "downtime")
    private String downtime;

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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

    public String getOee() {
        return oee;
    }

    public void setOee(String oee) {
        this.oee = oee;
    }

    public String getDowntime() {
        return downtime;
    }

    public void setDowntime(String downtime) {
        this.downtime = downtime;
    }

    
}
