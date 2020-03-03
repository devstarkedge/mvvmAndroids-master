package com.app.fitv1.ProjectUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (isNetworkAvailable(context)) {
//                internet connected
//                refresh all
            }
            else {
//                internet disconnected
            }
        }
    }

    public boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager cm          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

//    need manifest entry also :
//     <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
//    <receiver
//    android:name=".receivers.NetworkReceiver">
//    <intent-filter>
//        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
//    </intent-filter>
//      </receiver>

}