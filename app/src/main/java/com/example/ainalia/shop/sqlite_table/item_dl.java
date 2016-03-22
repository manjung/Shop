package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/18.
 */
//sim_x02 產品主檔
public class item_dl
{
    private static final String TableName = "item_dl";

    public static final String CREATE = "CREATE TABLE " + TableName
            + " ( "
            + "Y05_UN  INTEGER PRIMARY KEY NOT NULL"             //產品序號
            + " , "
            + "N           VARCHAR(30) NOT NULL"                        //產品編號
            + " , "
            + "NAM      VARCHAR(40)"                                   //產品名稱
            + " , "
            + "S17_UN  INTEGER"                                        //單位序號
            + " , "
            + "NSTG     FLOAT"                                           //庫存量
            + " ) ";

}
