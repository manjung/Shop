package com.example.ainalia.shop.table;

/**
 * Created by Ainalia on 2016/3/9.
 */
public class unit_dl
{

    private int id;             // primary key
    private int s17_um;        //單位序號
    private String nam;        //單位編號
    private String n;           //單位名稱

    public int getS17_um() {
        return s17_um;
    }

    public String getNam() {
        return nam;
    }

    public String getN() {
        return n;
    }

    public int getId() {
        return id;
    }

    public void setS17_um(int s17_um) {
        this.s17_um = s17_um;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public void setN(String n) {
        this.n = n;
    }

    public void setId(int id) {
        this.id = id;
    }
}
