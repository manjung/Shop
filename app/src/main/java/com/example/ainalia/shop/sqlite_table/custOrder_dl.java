package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/18.
 */
//sim_x06 客戶下單記錄
public class custOrder_dl
{
    private static final String TableName = "custOrder_dl";

    public static final String CREATE = "CREATE TABLE " + TableName
            + " ( "
            + "Y01_UN     INTEGER  NOT NULL "                             //客戶序號
            + " , "
            + "Y05_UN     INTEGER  NOT NULL "                             //產品序號
            + " , "
            + "S17_UN      INTEGER  NOT NULL "                             //單位序號
            + " , "
            + "SDATE       VARCHAR(8) "                                     //下單日期
            + " , "
            + "STIME       VARCHAR(6)  NOT NULL"                          //下單時間
            + " , "
            + "QTY           FLOAT "                                           //數量
            + " , "
            + "PRICE        FLOAT "                                           //單價
            + " , "
            + "AMOUNT    FLOAT "                                           //金額
            + " , "
            + "MEMO        VARCHAR(60) "                                   //備註
            + " , "
            + "TRA           CHAR(1) "                                         //拋磚
            + " , "
            + "PRIMARY KEY ( Y01_UN , Y05_UN , S17_UN ) "
            + " ) ";


}
