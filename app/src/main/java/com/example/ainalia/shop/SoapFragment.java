package com.example.ainalia.shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;




public class SoapFragment extends Fragment {



    private OnFragmentInteractionListener mListener;
    private MyReceiver receiver;
    private IntentFilter filter;
    private Context context;
    private Button button;
    public TextView text;


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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_soap, container, false);

        text = (TextView) v.findViewById(R.id.textViewSaop);
        button  = (Button) v.findViewById(R.id.button);
        context = getContext();
        receiver = new MyReceiver();
        filter = new IntentFilter();
        filter.addAction(MyReceiver.MY_BROADCAST_TAG);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                    Intent intent = new Intent(getActivity(),IntentServiceImpl.class);
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



    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    class MyReceiver extends BroadcastReceiver {

        public static final String MY_BROADCAST_TAG = "com.example.ainalia.shop";
        private String OutPutData;
        @Override
        public void onReceive(Context context, Intent intent) {

            String outputdata = intent.getStringExtra(IntentServiceImpl.TEXT_OUTPUT);
            setOutPutData(outputdata);
            text.setText(receiver.getOutPutData());
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
