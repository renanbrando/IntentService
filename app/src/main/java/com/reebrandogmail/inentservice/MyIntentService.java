package com.reebrandogmail.inentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;


/**
 * Created by logonrm on 12/06/2017.
 */

public class MyIntentService extends IntentService{

    public static final String PARAM_IN_MSG = "input_message";
    public static final String PARAM_OUT_MSG = "output_message";

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(3000);
        String resultText = String.format(msg + " " + "MM/dd/yy h:mmaa", System.currentTimeMillis());

        Intent broadCastIntent = new Intent();
        broadCastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        broadCastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadCastIntent.putExtra(PARAM_OUT_MSG, resultText);
        sendBroadcast(broadCastIntent);
    }
}

