package com.example.ainalia.shop;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import org.xmlpull.v1.XmlPullParser;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ainalia on 2016/2/3.
 */
/*public class DownloadSoapTask  extends AsyncTask<String, Integer, Integer> {
    @Override
    protected Integer doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

}*/

public abstract class DownloadSoapTask  extends Service {

    protected abstract void onHandleIntent(Intent intent);


    private boolean m_bRedelivery;
    private ConcurrentHashMap<Intent, MyAsyncTask> m_mapIntent2AsyncTask;

    public DownloadSoapTask()
    {
        m_mapIntent2AsyncTask = new ConcurrentHashMap<Intent, MyAsyncTask>(32);

    }

    public void setIntentRedelivery(boolean enabled)
    {
        m_bRedelivery = enabled;
    }

    public void cancel()
    {
        for (MyAsyncTask task: m_mapIntent2AsyncTask.values()) {
            task.cancel(true);
        }
        m_mapIntent2AsyncTask.clear();
        stopSelf();
    }

    @Override public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        if (m_mapIntent2AsyncTask.containsKey(intent)) {
            return;
        }

        MyAsyncTask task = new MyAsyncTask();
        m_mapIntent2AsyncTask.put(intent, task);
        task.execute(intent);
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId)
    {
        onStart(intent, startId);
        return m_bRedelivery ? START_REDELIVER_INTENT : START_NOT_STICKY;
    }

    @Override public IBinder onBind(Intent intent)
    {
        return null;
    }


    private class MyAsyncTask extends AsyncTask<Intent, Void, Void> {
        protected Void doInBackground(Intent... its)
        {
            final int nCount = its.length;
            for (int i = 0; i < nCount; i++) {
                Intent it = its[i];

                m_mapIntent2AsyncTask.remove(it);
                onHandleIntent(it);
            }
            return null;
        }

        @Override protected void
        onPostExecute(Void result)
        {
            if (m_mapIntent2AsyncTask.isEmpty())
                stopSelf();
        }

        @Override protected void
        onCancelled()
        {
            if (m_mapIntent2AsyncTask.isEmpty())
                stopSelf();
        }
    }
}
