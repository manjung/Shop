package com.example.ainalia.shop.sqlite_table;



/**
 * Created by Ainalia on 2016/3/17.
 */

//sim_x03 單位主檔
public class unit_dl
{
    private static final String TableName = "unit_dl";

    public static final String CREATE = "CREATE TABLE " + TableName
            + " ( "
            + "S17_UN   INTEGER PRIMARY KEY NOT NULL"              //單位序號
            + " , "
            + "N           VARCHAR(4) NOT NULL"                          //單位編號
            + " , "
            + "NAM      VARCHAR(20)"                                    //單位名稱
            + " ) ";


}
