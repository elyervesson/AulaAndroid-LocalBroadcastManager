package com.example.elyervesson.aulaandroid_localbroadcastmanager.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts.LocalBroadcastServiceTest;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.fragments.FragmentThread;


/**
 * Created by elyervesson on 11/05/17.
 */

public class ServiceTest extends Service {
    public static final String FILTRO_KEY = "ServiceTest_KEY";
    public static final String MENSAGEM_KEY = "ServiceTest_MENSAGEM_KEY";

    private LocalBroadcastServiceTest broadcast;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcast = new LocalBroadcastServiceTest(this);
        IntentFilter intentFilter = new IntentFilter(FILTRO_KEY);
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(broadcast, intentFilter); //O contexto tem que ser de um componente android
    }

    //Desvincular o receiver para que ele possa ser coletado pelo garbage colector e não ter problema de vasamento de memoria
    @Override
    public void onDestroy(){
        super.onDestroy();
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(broadcast);
    }

    public void logMensagem(String mensagem){
        mensagem += "FragmentThread: Mensagem OK, <br>";

        Intent intent = new Intent(FragmentThread.FILTRO_KEY);
        intent.putExtra(FragmentThread.MENSAGEM_KEY, mensagem);
        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(intent);
    }

}
