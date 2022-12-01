package com.peng.bean;

/**
 * @Author Peng
 * @Date 2022/3/31 15:25
 * @Version 1.0
 */

public class StuBindID {
    private int id;
    private String qqnum;
    private String name;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StuBindID{" +
                "id=" + id +
                ", qqnum='" + qqnum + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
