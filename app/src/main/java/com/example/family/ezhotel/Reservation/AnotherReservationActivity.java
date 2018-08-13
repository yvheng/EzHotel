package com.example.family.ezhotel.Reservation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AnotherReservationActivity extends AppCompatActivity {
    private Button btnYes, btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_reservation);

        btnYes = (Button) findViewById(R.id.btnYes);
        btnNo = (Button) findViewById(R.id.btnNo);
    }


    public void yes(View v){
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String savedReservationJSON = appSharedPrefs.getString("KEY_SAVEDRESERVATION", null);
        Reservation savedReservation = new Gson().fromJson(savedReservationJSON, Reservation.class);

        savedReservation.setPaymentAmount(-10.0);
        String savedReservationJSONString = new Gson().toJson(savedReservation);
        SharedPreferences.Editor editor = appSharedPrefs.edit();
        editor.putString("KEY_SAVEDRESERVATION",savedReservationJSONString);
        editor.commit();

        Intent intent = new Intent(this, AddReservationActivity.class);
        startActivity(intent);
    }

    public void no(View v){
        Intent intent = new Intent(this, ReservationActivity.class);
        startActivity(intent);
    }
}
