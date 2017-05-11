package com.example.elyervesson.aulaandroid_localbroadcastmanager.domain;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.MainActivity;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts.LocalBroadcastClasseDominio;

/**
 * Created by elyervesson on 11/05/17.
 */

// Esta classe é para simular que conseguimos passar mensagens do local broadcast dentro das classes de dominio
public class ClasseDominio {
    public static final String FILTRO_KEY = "ClasseDominio_KEY";
    public static final String MENSAGEM_KEY = "ClasseDominio_MENSAGEM_KEY";

    private Context context;
    private LocalBroadcastClasseDominio broadcast;

    public ClasseDominio(Context context) {
        this.context = context;

        broadcast = new LocalBroadcastClasseDominio(this);
        IntentFilter intentFilter = new IntentFilter(FILTRO_KEY);
        LocalBroadcastManager
                .getInstance(context)
                .registerReceiver(broadcast, intentFilter); //O contexto tem que ser de um componente android
    }

    public void logMensagem(String mensagem){
        mensagem += "MainActivity: Mensagem OK, <br>";

        Intent intent = new Intent(MainActivity.FILTRO_KEY);
        intent.putExtra(MainActivity.MENSAGEM_KEY, mensagem);
        LocalBroadcastManager
                .getInstance(context)
                .sendBroadcast(intent); //sendBroadcast é assincrono
    }

    public void Destroy() {
        LocalBroadcastManager
                .getInstance(context)
                .unregisterReceiver(broadcast);
    }

}
