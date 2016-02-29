package com.example.ainalia.shop;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
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
    private String dataString;

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
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(SoapFragment.MyReceiver.MY_BROADCAST_TAG);
        broadcastIntent.putExtra(TEXT_OUTPUT, dataString);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(broadcastIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}


