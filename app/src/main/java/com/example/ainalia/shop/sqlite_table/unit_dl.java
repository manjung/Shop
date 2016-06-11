package com.example.ainalia.shop.sqlite_table;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ainalia on 2016/3/17.
 */

//sim_x03 單位主檔
public class unit_dl
{
    private static final String TableName = "unit_dl";

    private static final String column1 = "S17_UN";
    private static final String column2 = "N";
    private static final String column3 = "NAM";

    private static String value1;
    private static String value2;
    private static String value3;

    public static final String CREATE =
            "CREATE TABLE IF NOT EXISTS " + TableName
            + " ( "
            + column1 +" "
            + "INTEGER PRIMARY KEY NOT NULL"                     //單位序號
            + " , "
            + column2 +" "
            + "VARCHAR(4) NOT NULL"                          //單位編號
            + " , "
            + column3 +" "
            + "VARCHAR(20)"                                    //單位名稱
            + " );";


     public static void setValue(String v1,String v2,String v3)
     {
         value1 = v1;
         value2 = v2;
         value3 = v3;

     }

    public static void INSERT(SQLiteDatabase db,String v1,String v2,String v3)
    {
        setValue(v1,v2,v3);
        db.execSQL(INSERT());

    }

    private static String INSERT()
    {
        String INSERT = "INSERT OR REPLACE INTO " + TableName
                + " ("
                + column1
                + ","
                + column2
                + ","
                + column3
                + ") "
                + " VALUES"
                + " ("
                + getValue1()
                + ",'"
                + getValue2()
                + "','"
                + getValue3()
                + "');";

        return INSERT;
    }

    protected static String getValue1()
    {
        return value1;
    }

    protected static String getValue2()
    {
        return value2;
    }
    protected static String getValue3()
    {
        return value3;
    }


}
