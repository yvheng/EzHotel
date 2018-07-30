package com.example.family.ezhotel;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.family.ezhotel.CheckIO.CheckIOActivity;
import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.Payment.PaymentActivity;
import com.example.family.ezhotel.Reservation.ReservationActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // private TextView textViewMessage;
    private SharedPreferences appSharedPrefs;
    private Boolean firstTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        //Get lists from Shared Preference
//        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
//        String checkJSONString = appSharedPrefs.getString("KEY_ROOM", null);
//        Type type1 = new TypeToken<List<Room>>() {}.getType();
//        List<Room> roomListCheck = new Gson().fromJson(checkJSONString, type1);
//
//        //if list is empty, add data to shared preference (for first time launch only)
//        if(roomListCheck.size()==0){

        if(isFirstTime()){
            //Hardcode data to shared preference
            List<Room> roomList = new ArrayList<>();
            Room room1 = new Room("101","Family Room","Unavailable",150.00);
            Room room2 = new Room("102","Family Room","Available",150.00);
            Room room3 = new Room("103","Family Room","Available",150.00);
            Room room4 = new Room("201","Deluxe Room","Unavailable",300.00);
            Room room5 = new Room("202","Deluxe Room","Available",300.00);
            Room room6 = new Room("203","Deluxe Room","Available",300.00);
            Room room7 = new Room("301","Executive Suite Room","Available",450.00);
            Room room8 = new Room("302","Executive Suite Room","Available",450.00);
            Room room9 = new Room("303","Executive Suite Room","Available",450.00);
            Room room10 = new Room("401","VIP Room", "Available", 1000.00);

            if(roomList.isEmpty()){
                roomList.add(room1);
                roomList.add(room2);
                roomList.add(room3);
                roomList.add(room4);
                roomList.add(room5);
                roomList.add(room6);
                roomList.add(room7);
                roomList.add(room8);
                roomList.add(room9);
                roomList.add(room10);
            }


            String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            List<Reservation> reservationList = new ArrayList<>();
            Reservation reservation1 = new Reservation("10001","Lionel Messi", "900521-14-5514","012-1205173","S001","401","2018-07-21","2018-07-29","2018-07-31",1100.00,Calendar.getInstance().getTime(),"Paid");
            Reservation reservation2 = new Reservation("10002","Andrea Iniesta", "890119-14-5517","017-8755173","S001","101","2018-07-29","2018-08-10","2018-08-12","Checked-In");
            Reservation reservation3 = new Reservation("10003","Jordi Alba", "910311-14-5517","019-1195343","S002","201","2018-08-01","2018-08-25","2018-08-27","Reserved");

            if(reservationList.isEmpty()){
                reservationList.add(reservation1);
                reservationList.add(reservation2);
                reservationList.add(reservation3);
            }


            String roomJSONString = new Gson().toJson(roomList);
            String reservationJSONString = new Gson().toJson(reservationList);

            appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_ROOM", roomJSONString);
            editor.putString("KEY_RESERVATION", reservationJSONString);

            editor.commit();
        }

//        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
//        Type type1 = new TypeToken<List<Room>>() {}.getType();
//        Type type2 = new TypeToken<List<Reservation>>() {}.getType();
//
//        List<Room> roomList = new Gson().fromJson(roomJSONString, type1);
//        List<Reservation> reservationList = new Gson().fromJson(reservationJSONString, type2);

//        textViewMessage = (TextView) findViewById(R.id.txtViewHome);
//        textViewMessage.setText("123" + roomList.get(0).getRoomID() +" " + roomList.get(1).getRoomID() +" " + reservationList.get(0).getReservationID());



    }
    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
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

    public void logOut(View v) {

        Toast.makeText(getApplicationContext(), "Successfully logged out.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void reservation(View v) {

        Intent intent = new Intent(this, ReservationActivity.class);
        startActivity(intent);
    }

    public void cico(View v) {

        Intent intent = new Intent(this, CheckIOActivity.class);
        startActivity(intent);
    }

    public void payment(View v) {

        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_reservation) {
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
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
