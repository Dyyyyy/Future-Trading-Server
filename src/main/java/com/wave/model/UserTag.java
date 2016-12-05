package com.wave.model;

import javax.persistence.*;

/**
 * Created by Json on 2016/12/2.
 */
@Entity
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String tag_name;

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getId() {

        return id;
    }
}
