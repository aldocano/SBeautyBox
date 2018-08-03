package com.dc.beautybox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView web_view_content_main_1;
    private boolean doubleBackToExitPressedOnce;
    private SwipeRefreshLayout swipe_refresh_layout_main_1;
    private CoordinatorLayout coordinatorLayout;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        
        
        setSupportActionBar(toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
        if (getString(R.string.AD_MOB_ENABLED).equals("false")){
            mAdView.setVisibility(View.GONE);
        }
        
        initView();
        initAction();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initView(){

        web_view_content_main_1= findViewById(R.id.web_view_content_main_1);
        swipe_refresh_layout_main_1= findViewById(R.id.swipe_refresh_layout_main_1);
        // register class containing methods to be exposed to JavaScript
        swipe_refresh_layout_main_1.setRefreshing(true);
        web_view_content_main_1.setWebViewClient(new WebViewClient());
        web_view_content_main_1.getSettings().setJavaScriptEnabled(true);
        web_view_content_main_1.loadUrl(getString(R.string.url_web_site));

    }
    private void initAction(){
        web_view_content_main_1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                MainActivity.this.setTitle(view.getTitle());
                swipe_refresh_layout_main_1.setRefreshing(false);
            }
        });
        swipe_refresh_layout_main_1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh_layout_main_1.setRefreshing(true);
                web_view_content_main_1.reload();

            }
        });
        coordinatorLayout= findViewById(R.id.CoordinatorLayout);

    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            web_view_content_main_1.goBack();
           // super.onBackPressed();
        }

        Snackbar.make(coordinatorLayout, getString(R.string.back_message), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_aboute) {
            Intent intent= new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent= new Intent(MainActivity.this,ContactActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            String shareBody = getString(R.string.app_name)+" "+getString(R.string.url_app_google_play);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,  getString(R.string.app_name));
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
