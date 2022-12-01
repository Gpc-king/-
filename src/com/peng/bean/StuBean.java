package com.peng.bean;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author Peng
 * @Date 2022/3/30 15:25
 * @Version 1.0
 */

public class StuBean {
    private int id;
    private String qqNum;
    private String name;
    private String password;
    private Date birth;
    private String sex;
    private String phone;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirth(Date birth){
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }

    public int getAge() {
        // 获取当前时间
        Calendar currTime = Calendar.getInstance();
        // 获取生日
        Calendar births = Calendar.getInstance();
        births.setTime(this.birth);
        // 年龄 = 当前年 - 出生年
        int age = currTime.get(Calendar.YEAR) - births.get(Calendar.YEAR);
        // 如果当前月份小于出生月份: age-1
        // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
        int currMonth = currTime.get(Calendar.MONTH);
        int currDay = currTime.get(Calendar.DAY_OF_MONTH);
        int bornMonth = births.get(Calendar.MONTH);
        int bornDay = births.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        return age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        if(this.type == 0)
            return "管理员";
        else
            return "用户";
    }

    public int getTypes() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StuBean{" +
                "id=" + id +
                ", qqNum='" + qqNum + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birth='" + birth + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }
}
