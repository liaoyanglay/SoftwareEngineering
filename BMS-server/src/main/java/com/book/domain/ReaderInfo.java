package com.book.domain;

import java.io.Serializable;
import java.util.Date;

public class ReaderInfo implements Serializable{

    private int readerId;       //读者ID
    private String name;        //姓名
    private String sex;         //性别
    private Date birth;         //出生日期
    private String address;     //住址
    private String telcode;     //电话

    public void setName(String name) {
        this.name = name;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public String getName() {
        return name;
    }

    public int getReaderId() {
        return readerId;
    }

    public Date getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public String getTelcode() {
        return telcode;
    }
}
