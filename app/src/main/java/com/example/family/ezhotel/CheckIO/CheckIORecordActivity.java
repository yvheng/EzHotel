package com.example.family.ezhotel.CheckIO;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.R;
import com.example.family.ezhotel.Reservation.ReservationAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckIORecordActivity extends AppCompatActivity {
    private ListView listViewCICO;
    private List<Reservation> reservationList;
   private List<Reservation> cicoReservationList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_iorecord);

        //Get lists from Shared Preference
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        reservationList = new Gson().fromJson(reservationJSONString, type2);

        listViewCICO = (ListView) findViewById(R.id.listViewCICO);

        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getStatus().equals("Checked-In") || reservationList.get(i).getStatus().equals("Checked-Out") ) {
                cicoReservationList.add(reservationList.get(i));
            }
        }


            final CICOAdapter cicoAdapter = new CICOAdapter(this, R.layout.activity_reservation_record, cicoReservationList);
            listViewCICO.setAdapter(cicoAdapter);




    }
}
