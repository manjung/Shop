package com.example.ainalia.shop;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.ainalia.shop.IntentServiceImpl.*;


public class SoapFragment extends Fragment {



    private OnFragmentInteractionListener mListener;
    private MyReceiver receiver;
    private IntentFilter filter;
    private Context context;
    private Button button;
    private TextView text;
    private ProgressBar progressBar;


    public static SoapFragment newInstance() {
        SoapFragment fragment = new  SoapFragment();

        return fragment;
    }

    public SoapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        receiver = new MyReceiver();
        filter = new IntentFilter();
        filter.addAction(MyReceiver.MY_BROADCAST_TAG);
        filter.addAction(MyReceiver.MY_INTENTERROR_TAG);
        filter.addAction(MyReceiver.MY_PROGRESSBAR_TAG);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_soap, container, false);

        text = (TextView) v.findViewById(R.id.textViewSaop);
        button  = (Button) v.findViewById(R.id.button);
        progressBar=(ProgressBar)v.findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), IntentServiceImpl.class);
                getActivity().startService(intent);

            }
        });

        return v;
    }




    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public void onDestroy() {
        super.onDestroy();

    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    class MyReceiver extends BroadcastReceiver {

        public static final String MY_BROADCAST_TAG = "com.example.ainalia.shop.finish";
        public static final String MY_PROGRESSBAR_TAG = "com.example.ainalia.shop.progressbar";
        public static final String MY_INTENTERROR_TAG = "com.example.ainalia.shop.intenterror";
        private String OutPutData;
        @Override
        public void onReceive(Context context, Intent intent) {

            if(MY_PROGRESSBAR_TAG.equals(intent.getAction()))
            {
                Bundle bundle = intent.getExtras();
                int time = bundle.getInt(PROGRESSPAR);
                //String outputdata = bundle.getString(TEXT_OUTPUT);
                String outputdata = Integer.toString(time)+"%";
                setOutPutData(outputdata);
                text.setText(receiver.getOutPutData());
                progressBar.setProgress(time);


            }else if(MY_BROADCAST_TAG.equals(intent.getAction()))
            {
                String outputdata = intent.getStringExtra(TEXT_OUTPUT);
                setOutPutData(outputdata);
                text.setText(receiver.getOutPutData());
                progressBar.setProgress(0);

            }else if(MY_INTENTERROR_TAG.equals(intent.getAction()))
            {


            }

        }

        public String getOutPutData()
        {
            return OutPutData;
        }

        private void setOutPutData(String s)
        {
            this.OutPutData = s;
        }

}


}
