package com.commplc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usedtime")
public class UsedTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usedtime")
    private String usedTime;

    public UsedTimeEntity() {

    }

    public UsedTimeEntity(long id, String usedTime) {
        this.id = id;
        this.usedTime = usedTime;
    }

    public Long getId() {
        return id;
    }

    public String getUsedTime() {
        return this.usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }
    
}
