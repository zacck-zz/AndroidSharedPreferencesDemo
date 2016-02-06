package com.zacck.androidsharedpreferencesdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    TextView tvUserLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUserLanguage = (TextView)findViewById(R.id.tvLanguage);

        //Create a shared prefs var
        //setup
        mSharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getting the data back
        String userLanguage = mSharedPreferences.getString("userLanguage","");
        if(userLanguage.equals(""))
        {
            tvUserLanguage.setText("Please set your User Language");
        }
        else
        {
            tvUserLanguage.setText(userLanguage);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.add)
        {
            //Log.i(getPackageName(), "Action bar Add was tapped");
            setLanguage(MainActivity.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLanguage(Context c)
    {
        //creating an alert Dialog
        new AlertDialog.Builder(c)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Please Choose A Language")
                .setMessage("This App is available in English and Spanish Choose one")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //to put data into shared prefs
                        mSharedPreferences.edit().putString("userLanguage","English").apply();

                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //to put data into shared prefs
                        mSharedPreferences.edit().putString("userLanguage","Spanish").apply();
                    }
                })
                .show();
    }
}
