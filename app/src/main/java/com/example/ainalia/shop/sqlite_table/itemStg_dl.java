package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/19.
 */
//sim_x07 產品單位庫存
public class itemStg_dl
{
    private static final String TableName = "itemStg_dl";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TableName
            + " ( "
            + "Y_N   INTEGER PRIMARY KEY NOT NULL"              //單位序號
            + " , "
            + "N           VARCHAR(4) NOT NULL"                          //單位編號
            + " , "
            + "NAM      VARCHAR(20)"                                    //單位名稱
            + " );";

}
