package com.wave.model;

import javax.persistence.*;

/**
 * Created by Json on 2016/11/18.
 */
@Entity
public class NewsTag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length = 20)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
