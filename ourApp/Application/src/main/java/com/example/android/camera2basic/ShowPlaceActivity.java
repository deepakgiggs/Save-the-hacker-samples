package com.example.android.camera2basic;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ShowPlaceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place_details);
        Bundle extras = getIntent().getExtras();

        String strBusiness = extras.getString("name");
        String strCategory = extras.getString("category");
        String strRating = extras.getString("rating");
        String strPhone = extras.getString("phone");
        String strUrl = extras.getString("url");


        TextView tx_business = (TextView)findViewById(R.id.business_name);
        tx_business.setText(strBusiness);
        TextView tx_category = (TextView)findViewById(R.id.business_category);
        tx_category.setText(strCategory);
//        TextView tx_address = (TextView)findViewById(R.id.business_address);
//        tx_address.setText("");
        TextView tx_ratings = (TextView)findViewById(R.id.business_ratings);
        tx_ratings.setText(strRating);
        TextView tx_contact = (TextView)findViewById(R.id.business_contact);
        tx_contact.setText(strPhone);
        TextView tx_url = (TextView)findViewById(R.id.business_url);
        tx_url.setText(strUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
