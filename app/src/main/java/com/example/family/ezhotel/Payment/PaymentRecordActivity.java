package com.example.family.ezhotel.Payment;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.family.ezhotel.CheckIO.CICOAdapter;
import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PaymentRecordActivity extends AppCompatActivity {
    private ListView listViewPayment;
    private List<Reservation> reservationList;
    private List<Reservation> validReservationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_record);


        validReservationList = new ArrayList<>();
        //Get lists from Shared Preference
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        reservationList = new Gson().fromJson(reservationJSONString, type2);

        listViewPayment = (ListView) findViewById(R.id.listViewPayment);

        for(int i=0;i<reservationList.size();i++){
            if(reservationList.get(i).getPaymentAmount()>0) {
                validReservationList.add(reservationList.get(i));
            }


        }
        final PaymentAdapter paymentAdapter = new PaymentAdapter(this,R.layout.activity_payment_record,validReservationList);
        listViewPayment.setAdapter(paymentAdapter);

    }
}
