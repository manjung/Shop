package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/18.
 */
//sim_x05 客戶銷售明細
public class custSale_dl
{
    private static final String TableName = "custSale_dl";

    public static final String CREATE = "CREATE TABLE " + TableName
            + " ( "
            + "Y01_UN     INTEGER  NOT NULL "                             //客戶序號
            + " , "
            + "Y05_UN     INTEGER  NOT NULL "                             //產品序號
            + " , "
            + "S17_UN      INTEGER  NOT NULL "                             //單位序號
            + " , "
            + "ODATE       VARCHAR(8) "                                     //訂單日期
            + " , "
            + "QTY           FLOAT "                                           //數量
            + " , "
            + "PRICE        FLOAT "                                           //單價
            + " , "
            + "AMOUNT    FLOAT "                                           //金額
            + " , "
            + "PRIMARY KEY ( Y01_UN , Y05_UN , S17_UN ) "
            + " ) ";

}
