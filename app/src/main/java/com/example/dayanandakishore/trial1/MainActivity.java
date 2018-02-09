package com.example.dayanandakishore.trial1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Intent intent = new Intent(MainActivity.this, MyService.class);
       startService(intent);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar a = getSupportActionBar();
        if(a != null) {
            a.setLogo(R.mipmap.cepstrum);

            a.setDisplayUseLogoEnabled(true);
            a.setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {/*Inflate the menu options using the newly created menu file*/
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.main_menu,menu);
       return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.setting:
                Toast.makeText(MainActivity.this, "Settings icon is selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.dayanandakishore.trial1.MainActivity_settings");
                startActivity(intent);

            case R.id.about :


            default:
                return super.onOptionsItemSelected(item);
        }


    }



    }


