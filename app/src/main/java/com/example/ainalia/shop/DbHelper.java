package com.example.ainalia.shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ainalia.shop.sqlite_table.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Ainalia on 2016/3/17.
 */
public class DbHelper extends SQLiteOpenHelper {

    protected static final String DB_NAME = "dataBD.db";
    private static final int DB_VERSION = 1;

    private List<String> CreateTable = new ArrayList<>(Arrays.asList(
            customer_dl.CREATE, custOrder_dl.CREATE,custSale_dl.CREATE,item_dl.CREATE
            ,itemStg_dl.CREATE,itemUnit_dl.CREATE,unit_dl.CREATE));



    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    //建立所需要的資料表，並初始化表格中的資料
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*String sqlUser = "create table users(user_id text, user_name text, email text)";*/

        for(int i=0;i< CreateTable.size();i++)
            db.execSQL(CreateTable.get(i));


    }

    //升級資料庫(目前以拋棄現有資料庫重新建立為方法，較不佳)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    //降級資料庫(目前以拋棄現有資料庫重新建立為方法，較不佳)
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {

        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    /*
     *  rawQuery:開頭的方法是將 select語法以及所需的參數，以字串 (sql) 和字串陣列 (selectionArgs) 的方式傳入。
     *  相較於 query 方法將 select 語法拆解成固定的輸入參數，
     *  rawQuery 方法在使用上彈性比較大 (無論 select 語法多複雜，全部寫在一個 sql中)
     *  因此，使用上也比較普遍。
     */
    public Cursor getAll(SQLiteDatabase db,String tableName) {

        return db.rawQuery("SELECT  * FROM "+tableName, null);
    }

    /*
     * query 開頭的方法可以接受多個參數，每一個參數可以對應到 SQLite select 語法中的某些值，
     * 如：表格名稱 (table)、欄位名稱 (columns)、查詢條件 (selection)、查詢條件的值 (selectionArgs)、
     * 欄位群組 (groupBy)、排序方式 (orderBy) 以及回傳資料的筆數限制 (limit) 等。
     */

    public Cursor getAllTwo(SQLiteDatabase db,String tableName) {
        return db.query(
                true,
                tableName,									    // 資料表名稱
                new String[] {"user_id","user_name","email"},	// 欄位名稱
                null, 											// WHERE
                null, 											// WHERE 的參數
                null, 											// GROUP BY
                null, 											// HAVING
                null,  											// ORDOR BY
                null											// limit
        );
    }

    // 取得一筆紀錄
    public Cursor get(SQLiteDatabase db,long rowId) throws SQLException {
        Cursor cursor = db.query(true,
                "table_name",									// 資料表名稱
                new String[] {"user_id", "user_name", "email"},	// 欄位名稱
                "user_id=" + rowId,								// WHERE
                null, 											// WHERE 的參數
                null, 											// GROUP BY
                null,											// HAVING
                null, 											// ORDOR BY
                null  											// 限制回傳的rows數量
        );

        // 注意：不寫會出錯
        if (cursor != null) {
            cursor.moveToFirst();						//將指標移到第一筆資料
        }
        return cursor;
    }

    public long insertUsers(SQLiteDatabase db,String id,String name, String value,String tableName)
    {
        ContentValues args = new ContentValues();
        args.put("user_id", id);
        args.put("user_name", name);
        args.put("email", value);
        db.insert(tableName, null, args);

        return db.insert(tableName, null, args);
    }

    public int delete(SQLiteDatabase db,long rowId)
    {
        return db.delete("table_name",					// 資料表名稱
                "user_id=" + rowId,								// WHERE
                null											// WHERE的參數
        );
    }

    public int update(SQLiteDatabase db,long rowId, String value)
    {
        ContentValues args = new ContentValues();
        args.put("email", value);

        return db.update("table_name",					//資料表名稱
                args,											//VALUE
                "user_id=" + rowId,								//WHERE
                null											//WHERE的參數
        );
    }
}
