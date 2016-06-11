package com.example.ainalia.shop;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Ainalia on 2016/2/3.
 */
public class CallSoap {

    private static final String NAMESPACE = "http://yuancheng/sim/webservice"; //Web Services命名空間


    private static final String SOAP_ACTIONS_SEARCH = "http://yuancheng/sim/webservice/Get_Customer"; //Web Services命名空間+函數名稱
    private static final String Callmethod_SEARCH = "Get_Customer"; //要呼叫的函數名稱
    private static final String URL_SEARCH = "http://test.yctec.com.tw/misws/service.asmx"; //Web Services的網址

    private static final String SOAP_ACTION_DOWNLAND = "http://yuancheng/sim/webservice/Unit_dl"; //Web Services命名空間+函數名稱
    private static final String Callmethod_DOWNLAND = "Unit_dl"; //要呼叫的函數名稱
    private static final String URL_DOWNLAND = "http://simws.dingyan.com.tw/service.asmx"; //Web Services的網址


    public CallSoap()
    {

    }

    //無輸入參數，下載。
    public String[] CallHellow()
    {

        String[] Get_method ;


        try{

            // add paramaters and values

            SoapObject request1 = new SoapObject(NAMESPACE, Callmethod_DOWNLAND);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;

            envelope.setOutputSoapObject(request1);



            //Web method call

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_DOWNLAND);

            androidHttpTransport.debug = true;


            androidHttpTransport.call(SOAP_ACTION_DOWNLAND, envelope);



            //get the response

            SoapObject result= (SoapObject)envelope.getResponse();

            int num = result.getPropertyCount()-1;   //去掉"anyType{}"
            //int acount = result.getAttributeCount();

            if(num >= 0)
            {
                Get_method = new String[num];

                for(int i=0;i<Get_method.length;i++)
                {
                    Get_method[i]=result.getProperty(i).toString();
                }


            }else
            {
                Get_method = new String[1];
                Get_method[0]="無資料！";

            }


        }catch (Exception e){

            Get_method = new String[1];
            Get_method[0]=e.getMessage(); //將錯誤訊息傳回

        }

        return Get_method; //傳回字串

    }

    //有輸入參數，下載。
    public String[] getUnit(String s) {

        String[] Get_method ;

        try{

            // add paramaters and values

            SoapObject request1 = new SoapObject(NAMESPACE, Callmethod_SEARCH);

            request1.addProperty("as_customer_n", s);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.bodyOut = request1;

            envelope.dotNet = true;

            envelope.setOutputSoapObject(request1);



            //Web method call

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL_SEARCH);

            androidHttpTransport.debug = true;

            androidHttpTransport.call(SOAP_ACTIONS_SEARCH, envelope);

            //get the response
            SoapObject result= (SoapObject)envelope.bodyIn;
            int num = result.getPropertyCount();
            Log.v("NUMOK", Integer.toString(num));
            if (num >= 0)
            {
                Get_method = new String[num];

                for(int i=0;i<Get_method.length;i++)
                {
                    Get_method[i]=result.getProperty(i).toString();
                    Log.v("OK", Get_method[i]);
                }


            }else
            {
                Get_method = new String[1];
                Get_method[0]="無資料！";

            }


        }catch (Exception e){

            Get_method = new String[1];
            Get_method[0]=e.getMessage(); //將錯誤訊息傳回

        }

        return Get_method; //傳回字串


    }




}
