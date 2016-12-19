package com.wave.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.swing.text.StringContent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Json on 2016/11/14.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(length = 20)
    private String nickname;

    @Column(length = 10)
    private String last_name;

    @Column(length = 10)
    private String first_name;

    private String password;

    private Boolean enabled;

    private int age;

    @Column(length = 50)
    private String portrait_url;

    private int sex;

    @Column(length = 20,unique = true)
    private String phone_number;

    @Column(length = 50,unique = true)
    private String email;

    private float account_balance;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = TradeRecord.class, mappedBy = "user")
    private List<TradeRecord> records=new ArrayList<TradeRecord>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = UserContract.class, mappedBy = "user")
    private List<UserContract> contracts=new ArrayList<UserContract>();

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = UserTag.class)
    private List<UserTag> tags=new ArrayList<UserTag>();

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<UserTag> getTags() {
        return tags;
    }

    public void setTags(List<UserTag> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {

        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPortrait_url() {
        return portrait_url;
    }

    public void setPortrait_url(String portrait_url) {
        this.portrait_url = portrait_url;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public List<UserContract> getContracts() {
        return contracts;
    }

    public void setContracts(List<UserContract> contracts) {
        this.contracts = contracts;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(float account_balance) {
        this.account_balance = account_balance;
    }

    public List<TradeRecord> getRecords() {
        return records;
    }

    public void setRecords(List<TradeRecord> records) {
        this.records = records;
    }
}
