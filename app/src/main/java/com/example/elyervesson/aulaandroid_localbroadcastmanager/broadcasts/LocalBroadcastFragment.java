package com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.fragments.FragmentThread;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.services.ServiceTest;

/**
 * Created by elyervesson on 11/05/17.
 */

// So responde a mensagens internas, então não temos o risco de mensagens irem para outras aplicações
public class LocalBroadcastFragment extends BroadcastReceiver {
    private FragmentThread fragment;

    public LocalBroadcastFragment(FragmentThread fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra(FragmentThread.MENSAGEM_KEY);
        fragment.logMensagem(mensagem);
    }
}
