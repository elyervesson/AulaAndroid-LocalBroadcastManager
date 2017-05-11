package com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.domain.ClasseDominio;

/**
 * Created by elyervesson on 11/05/17.
 */

public class LocalBroadcastClasseDominio extends BroadcastReceiver {
    private ClasseDominio classeDominio;

    public LocalBroadcastClasseDominio(ClasseDominio classeDominio) {
        this.classeDominio = classeDominio;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra(ClasseDominio.MENSAGEM_KEY);
        classeDominio.logMensagem(mensagem);
    }
}
