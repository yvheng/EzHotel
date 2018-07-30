package com.example.family.ezhotel.Reservation;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ReservationRecordActivity extends AppCompatActivity {
    private ListView reservationListView;
    private List<Reservation> reservationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_record);

        //Get lists from Shared Preference
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        reservationList = new Gson().fromJson(reservationJSONString, type2);

        reservationListView = (ListView) findViewById(R.id.listViewReservation);

        final ReservationAdapter reservationAdapter = new ReservationAdapter(this,R.layout.activity_reservation_record,reservationList);
        reservationListView.setAdapter(reservationAdapter);
    }
}
