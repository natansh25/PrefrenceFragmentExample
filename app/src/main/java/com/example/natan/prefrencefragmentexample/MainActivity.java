package com.example.natan.prefrencefragmentexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.txt_view);
        setUpSharedPref();


    }

    void setUpSharedPref()
    {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        show(sharedPreferences.getBoolean("chk_pref",false));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }




    void show(boolean str)
    {

            if ((str)) {
                txt.setVisibility(View.VISIBLE);
            }
            else
            {
                txt.setVisibility(View.INVISIBLE);
            }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Toast.makeText(this, "huaala", Toast.LENGTH_SHORT).show();
        if(key.equals("chk_pref"))
        {
            show(sharedPreferences.getBoolean("chk_pref",false));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister VisualizerActivity as an OnPreferenceChangedListener to avoid any memory leaks.
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
