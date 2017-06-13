package com.reebrandogmail.inentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ResponseReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent msgIntent = new Intent(this, MyIntentService.class);
        msgIntent.putExtra(MyIntentService.PARAM_IN_MSG, "Agora: ");
        startService(msgIntent);
        registerReceiver();
    }

    public void registerReceiver(){
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }



    public class  ResponseReceiver extends BroadcastReceiver{

        public static final String ACTION_RESP = "com.reebrando.gmail.intent.action.RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            TextView result = (TextView) findViewById(R.id.tvResult);
            String text = intent.getStringExtra(MyIntentService.PARAM_OUT_MSG);
            result.setText(text);
        }
    }
}
