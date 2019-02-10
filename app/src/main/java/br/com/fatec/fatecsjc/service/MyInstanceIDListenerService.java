package br.com.fatec.fatecsjc.service;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyInstanceIDListenerService extends FirebaseMessagingService {

    private static final String TOKENTAG = "TOKEN *** TOKEN";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TOKENTAG, " ************************************************************************************************************************************************ NOVO TOKEN");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TOKENTAG, " ************************************************************************************************************************************************ MENSAGEM RECEBIDA");
    }


}


//public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//
//    private static final String TAG = "MyAndroidFCMIIDService";
//
////    @Override
////    public void onNewTokenRefresh() {
////
////        //Get token
////        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
////        //Log token
////        Log.d(TAG, "Refreshed token: " + refreshedToken);
////    }
