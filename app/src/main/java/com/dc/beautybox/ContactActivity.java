package com.dc.beautybox;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setTitle(getString(R.string.nav_contact));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void BrowseEmail(View view){
        TextView textView = (TextView) view;

        Intent emailIntent;
        emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+textView.getText().toString()));

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {

        }
    }

    public void Browse(View view){
        TextView textView = (TextView) view;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(textView.getText().toString()));
        startActivity(browserIntent);
    }

    public void Call (View view) {
        TextView textView = (TextView) view;
        Intent intent = new Intent (Intent.ACTION_DIAL, Uri.fromParts ("tel", textView.getText().toString (), null));
        startActivity (intent);
    }
}
