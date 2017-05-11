package com.example.elyervesson.aulaandroid_localbroadcastmanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.elyervesson.aulaandroid_localbroadcastmanager.broadcasts.LocalBroadcastMainActivity;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.domain.ClasseDominio;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.fragments.FragmentThread;
import com.example.elyervesson.aulaandroid_localbroadcastmanager.services.ServiceTest;

public class MainActivity extends AppCompatActivity {
    public static final String FILTRO_KEY = "MainActivity_KEY";
    public static final String MENSAGEM_KEY = "MainActivity_MENSAGEM_KEY";

    private LocalBroadcastMainActivity broadcastMainActivity;
    private ClasseDominio classeDominio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* SERVICE */
        Intent intent = new Intent(this, ServiceTest.class);
        startService(intent);

        /* FRAGMENT */
        if ( savedInstanceState == null) {
            FragmentThread fragmentThread = new FragmentThread();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(fragmentThread, FragmentThread.KEY);
            ft.commit();
        }

        /* DOMAIN */
        classeDominio = new ClasseDominio(this);

        broadcastMainActivity = new LocalBroadcastMainActivity(this);
        IntentFilter intentFilter = new IntentFilter(FILTRO_KEY);
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(broadcastMainActivity, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(this, ServiceTest.class);
        stopService(intent);

        classeDominio.Destroy();

        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(broadcastMainActivity);
    }

    public void cicloMensagem(View view) {
        Intent intent = new Intent(ServiceTest.FILTRO_KEY);
        intent.putExtra(ServiceTest.MENSAGEM_KEY, "ServiceTest: Mensagem OK, <br>");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent); //sendBroadcast é assincrono
    }

    public void logMensagem(String mensagem){
        TextView tv = (TextView) findViewById(R.id.tv_conteudo);
        tv.setText(Html.fromHtml(mensagem));
    }
}
