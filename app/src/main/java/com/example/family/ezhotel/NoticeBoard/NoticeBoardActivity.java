package com.example.family.ezhotel.NoticeBoard;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.family.ezhotel.CheckIO.CheckIOActivity;
import com.example.family.ezhotel.HomeActivity;
import com.example.family.ezhotel.LoginActivity;
import com.example.family.ezhotel.Payment.PaymentActivity;
import com.example.family.ezhotel.R;
import com.example.family.ezhotel.Reservation.ReservationActivity;


public class NoticeBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtViewNotice;
    private Boolean firstTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_noticeboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtViewNotice = (TextView) findViewById(R.id.txtViewNotice);
        String text = String.valueOf(txtViewNotice.getText());

        if(isFirstTime()){


            SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
            editor.putString("KEY_NOTICE", text);
            editor.apply();
        }else{

            SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            String noticeString = prefs.getString("KEY_NOTICE", "No value");

            txtViewNotice.setText(noticeString);
        }





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.default_activity, menu);
        return true;
    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime1", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime1", false);
                editor.commit();
            }
        }
        return firstTime;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")


    public void editNotice(View v) {

        Intent intent = new Intent(this, EditNoticeActivity.class);
        startActivity(intent);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_home) {
            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_reservation) {
            intent = new Intent(this, ReservationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_checkIn) {
            intent = new Intent(this, CheckIOActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_checkOut) {
            intent = new Intent(this, CheckIOActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_payment) {
            intent = new Intent(this, PaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_noticeboard) {

        } else if (id == R.id.nav_logout) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
