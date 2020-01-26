package com.nessotech.moneygment.codesnippet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // assumes WordService is a registered service
        //Intent intent = new Intent(context, WordService.class);
        context.startService(intent);
    }
}
