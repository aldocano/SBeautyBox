package com.dc.beautybox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.dc.beautybox.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ProgressBar progressBar= (ProgressBar) findViewById(R.id.intro_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimaryLight), android.graphics.PorterDuff.Mode.SRC_IN);
        getSupportActionBar().hide();
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // If you want to modify a view in your Activity
                IntroActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(IntroActivity.this,MainActivity.class));
                        finish();
                    }
                });
            }
        }, 3000);
    }
}
