package com.model;

import java.io.Serializable;

/* 這隻 JavaBean 把 資料庫 Customer table 一筆資料封裝起來。 */
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pk = 0;
    private String cName = "";
    private int cAge = 0;
    private String cType = "";
    private String cShow = "";

    public CustomerBean() {
        super();
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcAge() {
        return cAge;
    }

    public void setcAge(int cAge) {
        this.cAge = cAge;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcShow() {
        return cShow;
    }

    public void setcShow(String cShow) {
        this.cShow = cShow;
    }

}
