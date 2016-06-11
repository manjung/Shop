package com.example.ainalia.shop.sqlite_table;

/**
 * Created by Ainalia on 2016/3/18.
 */

//sim_x01 客戶主檔
public class customer_dl
{
    private static final String TableName = "customer_dl";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + TableName
            + " ( "
            + "Y01_UN   INTEGER PRIMARY KEY NOT NULL"             //客戶序號
            + " , "
            + "N              VARCHAR(8) NOT NULL"                       //客戶編號
            + " , "
            + "NAM         VARCHAR(20)"                                 //客戶名稱
            + " , "
            + "ADDR        VARCHAR(100)"                               //公司地址
            + " , "
            + "TEL           VARCHAR(20)"                                //電話
            + " , "
            + "FAX           VARCHAR(20)"                                //傳真
            + " , "
            + "COMAN      VARCHAR(20)"                               //聯絡人
            + " , "
            + "EMAIL        VARCHAR(40)"                               //E-mail
            + " , "
            + "MEMO         VARCHAR(60)"                              //備註
            + " ); ";

}
