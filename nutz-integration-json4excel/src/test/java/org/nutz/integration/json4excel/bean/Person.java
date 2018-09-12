package org.nutz.integration.json4excel.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.integration.json4excel.J4EColumnType;
import org.nutz.integration.json4excel.annotation.J4EDefine;
import org.nutz.integration.json4excel.annotation.J4EName;

@J4EName("人员")
@Table("t_person")
public class Person {

    @J4EName("姓名")
    @Name
    private String name;

    @J4EName("年龄")
    private int age;

    private Date birthday;

    @J4EName("收入")
    @J4EDefine(type = J4EColumnType.NUMERIC, precision = 5)
    @ColDefine(type = ColType.FLOAT, width = 20, precision = 5)
    private double income;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
