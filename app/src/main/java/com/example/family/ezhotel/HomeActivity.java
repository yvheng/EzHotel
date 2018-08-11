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
import com.example.family.ezhotel.NoticeBoard.NoticeBoardActivity;
import com.example.family.ezhotel.Payment.PaymentActivity;
import com.example.family.ezhotel.Reservation.ReservationActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            Room room2 = new Room("102","Family Room","Unavailable",150.00);
            Room room3 = new Room("103","Family Room","Unavailable",150.00);
            Room room4 = new Room("104","Family Room","Available",150.00);
            Room room5 = new Room("105","Family Room","Available",150.00);
            Room room6 = new Room("201","Deluxe Room","Unavailable",300.00);
            Room room7 = new Room("202","Deluxe Room","Unavailable",300.00);
            Room room8 = new Room("203","Deluxe Room","Unavailable",300.00);
            Room room9 = new Room("204","Deluxe Room","Available",300.00);
            Room room10 = new Room("205","Deluxe Room","Available",300.00);
            Room room11 = new Room("301","Executive Suite Room","Unavailable",450.00);
            Room room12 = new Room("302","Executive Suite Room","Unavailable",450.00);
            Room room13 = new Room("303","Executive Suite Room","Unavailable",450.00);
            Room room14 = new Room("304","Executive Suite Room","Available",450.00);
            Room room15 = new Room("305","Executive Suite Room","Available",450.00);
            Room room16 = new Room("401","VIP Room", "Unavailable", 1000.00);
            Room room17 = new Room("402","VIP Room", "Available", 1000.00);

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
                roomList.add(room11);
                roomList.add(room12);
                roomList.add(room13);
                roomList.add(room14);
                roomList.add(room15);
                roomList.add(room16);
                roomList.add(room17);
            }


           // String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String sdate1 = "2018-07-27 13:15:01";
            String sdate2 = "2018-07-28 14:55:13";
            String sdate3 = "2018-07-31 12:05:21";
            Date date1=Calendar.getInstance().getTime();
            Date date2=Calendar.getInstance().getTime();
            Date date3=Calendar.getInstance().getTime();
            try {

                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date1 = sd.parse(sdate1);
                date2 = sd.parse(sdate2);
                date3 = sd.parse(sdate3);
            }catch(java.text.ParseException p) {
                System.out.println(p.toString());
            }

            List<Reservation> reservationList = new ArrayList<>();
            Reservation reservation1 = new Reservation("10001","Lionel Messi", "900521-14-5514","012-1205173","S001","401","2018-07-21","2018-07-25","2018-07-27",1100.00,date1,"Checked-Out");
            Reservation reservation2 = new Reservation("10002","Thiago Mesei", "931104-14-5017","017-1295246","S002","202","2018-07-25","2018-07-26","2018-07-28",330.00,date2,"Checked-Out");
            Reservation reservation3 = new Reservation("10003","Sergio Messy", "730115-14-5619","013-7665134","S001","101","2018-07-28","2018-07-29","2018-07-31",165.00,date3,"Checked-Out");
            Reservation reservation4 = new Reservation("10004","Silver Pniesta", "790119-14-5619","013-4955132","S001","101","2018-08-02","2018-08-10","2018-08-11",0.0,"Checked-In");
            Reservation reservation5 = new Reservation("10005","Bronze Kniesta", "690330-14-5417","012-4355117","S001","202","2018-08-02","2018-08-12","2018-08-14",0.0,"Checked-In");
            Reservation reservation6 = new Reservation("10006","Golden Aniesta", "890121-14-5313","018-8755176","S002","303","2018-08-05","2018-08-13","2018-08-14",0.0,"Checked-In");
            Reservation reservation7 = new Reservation("10007","Andrea Iniesta", "910105-14-5014","019-6755147","S003","102","2018-08-06","2018-08-12","2018-08-14",0.0,"Checked-In");
            Reservation reservation8 = new Reservation("10008","Undrea Uneesta", "860222-14-5017","011-3255184","S003","201","2018-08-08","2018-08-13","2018-08-15",0.0,"Checked-In");
            Reservation reservation9 = new Reservation("10009","Jardu Elba", "770311-14-5517","019-1195343","S001","203","2018-08-10","2018-08-11","2018-08-15",0.0,"Reserved");
            Reservation reservation10 = new Reservation("10010","Jirde Ilba", "810415-14-5519","011-7195349","S001","103","2018-08-10","2018-08-14","2018-08-15",0.0,"Reserved");
            Reservation reservation11 = new Reservation("10011","Jordi Alba", "900111-14-5514","015-8195346","S003","301","2018-08-11","2018-08-14","2018-08-16",0.0,"Reserved");
            Reservation reservation12 = new Reservation("10012","Korbi Alda", "850217-14-5513","013-9195342","S003","401","2018-08-12","2018-08-14","2018-08-17",0.0,"Reserved");
            Reservation reservation13 = new Reservation("10013","Berdu Alra", "890321-14-5511","012-0195341","S002","302","2018-08-14","2018-08-15","2018-08-17",0.0,"Reserved");

            if(reservationList.isEmpty()){
                reservationList.add(reservation1);
                reservationList.add(reservation2);
                reservationList.add(reservation3);
                reservationList.add(reservation4);
                reservationList.add(reservation5);
                reservationList.add(reservation6);
                reservationList.add(reservation7);
                reservationList.add(reservation8);
                reservationList.add(reservation9);
                reservationList.add(reservation10);
                reservationList.add(reservation11);
                reservationList.add(reservation12);
                reservationList.add(reservation13);
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
    public void noticeBoard(View v) {

        Intent intent = new Intent(this, NoticeBoardActivity.class);
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
        }else if (id == R.id.nav_noticeboard) {
            intent = new Intent(this, NoticeBoardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
