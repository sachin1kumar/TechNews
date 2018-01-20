package com.tech.ashort.short_technews;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by sachin on 4/1/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String TAG = MyFirebaseMessagingService.class.getName().toString();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e(TAG,"onMessageReceived:called");

    }
}
