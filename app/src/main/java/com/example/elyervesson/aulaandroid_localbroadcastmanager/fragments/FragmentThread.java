package com.example.elyervesson.aulaandroid_localbroadcastmanager.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts.LocalBroadcastFragment;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.domain.ClasseDominio;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.services.ServiceTest;


/**
 * Created by elyervesson on 11/05/17.
 */

// Utilizamos o nome thread porque vamos envocar a outra entidade via mensagem broadcast dentro de
// uma thread secundaria
public class FragmentThread extends Fragment {
    public static final String FILTRO_KEY = "FragmentThread_KEY";
    public static final String MENSAGEM_KEY = "FragmentThread_MENSAGEM_KEY";

    // Caso seja necessario usar o fragment by tag
    public static final String KEY = "FragmentThread";

    private LocalBroadcastFragment broadcast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        broadcast = new LocalBroadcastFragment(this);
        IntentFilter intentFilter = new IntentFilter(FILTRO_KEY);
        LocalBroadcastManager
                .getInstance(getActivity())
                .registerReceiver(broadcast, intentFilter); //O contexto tem que ser de um componente android
    }

    //Desvincular o receiver para que ele possa ser coletado pelo garbage colector e não ter problema de vasamento de memoria
    @Override
    public void onDestroy(){
        super.onDestroy();
        LocalBroadcastManager
                .getInstance(getActivity())
                .unregisterReceiver(broadcast);
    }

    public void logMensagem(String mensagem){
        final String mensagemConcatenada = mensagem + "ClasseDominio: Mensagem OK, <br>";
        new Thread(){
            @Override
            public void run() {
                super.run();

                Intent intent = new Intent(ClasseDominio.FILTRO_KEY);
                intent.putExtra(ClasseDominio.MENSAGEM_KEY, mensagemConcatenada);
                LocalBroadcastManager
                        .getInstance(getActivity())
                        .sendBroadcast(intent); //sendBroadcast é assincrono
            }
        }.start();
    }
}
