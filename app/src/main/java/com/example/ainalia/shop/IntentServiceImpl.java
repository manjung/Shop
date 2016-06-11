package com.example.ainalia.shop;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.ainalia.shop.sqlite_table.unit_dl;

import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceImpl extends IntentService {

    public static final String   TEXT_OUTPUT = "OutPut"                            ;
    public static final String   PROGRESSPAR = "Time"                              ;
    private 					 SQLiteDatabase 				db						;
    private 					 DbHelper 						DbHelper				;
    private String dataString;
    private String[] sourceString;
    boolean isStop=false;


    public IntentServiceImpl() {
        super("IntentServiceImpl");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(SoapFragment.MyReceiver.MY_BROADCAST_TAG);
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_OUTPUT, "下載中....");
        //bundle.putInt(PROGRESSPAR, 50);
        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);

        CallSoap call = new CallSoap();

        bundle.putString(TEXT_OUTPUT, "下載完成！");
        //bundle.putInt(PROGRESSPAR, 50);
        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);
        DbHelper = new DbHelper(this);
        db = DbHelper.getWritableDatabase();

        sourceString = call.CallHellow();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        bundle.putString(TEXT_OUTPUT, "開始寫入資料庫！");
        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);

        Intent prograssIntent = new Intent();
        prograssIntent.setAction(SoapFragment.MyReceiver.MY_PROGRESSBAR_TAG);

        for( int i=0 ; i < sourceString.length ; i++ )
        {
            int bar =(int)(((float)i/sourceString.length)*100);

            bundle.putInt(PROGRESSPAR, bar);
            prograssIntent.putExtras(bundle);
            localBroadcastManager.sendBroadcast(prograssIntent);


            String[] splite = sourceString[i].split(",");
            for( int j=0 ; j< splite.length ; j++ )
            {
               unit_dl.INSERT(db, splite[0], splite[1], splite[2]);
            }

        }


        dataString=sourceString[0]+"\n";

        for(int i=1; i < sourceString.length ; i++)
        {
            dataString = dataString+sourceString[i]+"\n";
        }

        //SQLiteDatabase db = Connector.getDatabase();


        db.close();
        exportDB();


        bundle.putString(TEXT_OUTPUT, dataString);
        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        bundle.putString(TEXT_OUTPUT,"寫入資料庫結束！");
        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);


        /*new Thread(){
            public void run(){

                int i=0;
                while(!isStop){
                    i++;
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(SoapFragment.MyReceiver.MY_PROGRESSBAR_TAG);
                    Bundle bundle = new Bundle();
                    bundle.putInt(PROGRESSPAR, i);
                    broadcastIntent.putExtras(bundle);
                    localBroadcastManager.sendBroadcast(broadcastIntent);
                    isStop = false;
                    if( i== 100)
                    {
                        Intent dataIntent = new Intent();
                        dataIntent.setAction(SoapFragment.MyReceiver.MY_BROADCAST_TAG);
                        dataIntent.putExtra(TEXT_OUTPUT, dataString);
                        localBroadcastManager.sendBroadcast(dataIntent);
                        isStop = true;

                    }


                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/


    }

    @Override
    public void onDestroy() {
        isStop=false;
        super.onDestroy();

    }

    //製作備份
    private void exportDB(){

        File sd = Environment.getExternalStoragePublicDirectory("MYDB");
        File data = Environment.getDataDirectory();

        if(sd.exists() == false)
        {
            sd.mkdirs();
        }

        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.example.ainalia.shop" +"/databases/"+DbHelper.DB_NAME;
        String backupDBPath = DbHelper.DB_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);

        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();

        } catch (FileNotFoundException e) {
            Log.i("CopyDBException", e.getMessage());
        } catch(IOException e) {
            Log.i("CopyDBException", e.getMessage());
        }
    }


}


