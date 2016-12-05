package com.wave.model;

import javax.persistence.*;

/**
 * Created by Json on 2016/12/2.
 */
@Entity
public class TradingTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(length = 100)
    private String time;
}
