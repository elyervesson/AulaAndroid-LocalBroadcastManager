package com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.MainActivity;

/**
 * Created by elyervesson on 11/05/17.
 */

public class LocalBroadcastMainActivity extends BroadcastReceiver {

    private MainActivity mainActivity;

    public LocalBroadcastMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra(MainActivity.MENSAGEM_KEY);
        mainActivity.logMensagem(mensagem);
    }
}
