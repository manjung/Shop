package com.example.ainalia.shop;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceImpl extends IntentService {

    public static final String TEXT_OUTPUT = "OutPut";
    public static final String PROGRESSPAR = "Time";
    private String dataString;
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

        CallSoap call = new CallSoap();
        dataString = call.CallHellow();
        final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        new Thread(){
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

                    Log.i("TAG",String.valueOf(i));
                    Log.i("TAG", String.valueOf(isStop));
                    
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }

    @Override
    public void onDestroy() {
        isStop=false;
        super.onDestroy();

    }
}


