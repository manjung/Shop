package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/17.
 */
//sim_x04 產品單位對照
public class itemUnit_dl
{
    private static final String TableName = "itemUnit_dl ";

    public static final String CREATE = "CREATE TABLE " + TableName
            + " ( "
            + "Y05_UN   INTEGER  NOT NULL "                             //產品序號
            + " , "
            + "S17_UN    INTEGER  NOT NULL "                            //原始單位
            + " , "
            + "S17_UN1  INTEGER  NOT NULL "                            //轉換單位
            + " , "
            + "SQTY       FLOAT "                                          //數量
            + " , "
            + "PRIMARY KEY ( Y05_UN , S17_UN , S17_UN1 ) "
            + " ) ";
}
