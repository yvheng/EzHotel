package com.example.family.ezhotel.Payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.family.ezhotel.CheckIO.CheckOutActivity;
import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MakePaymentActivity extends AppCompatActivity {

    private SharedPreferences appSharedPrefs;
    private List<Reservation> reservationList;
    private double paymentAmountForUpdate =0.0;
    private Spinner pySprReservation;
    private EditText pyEtCName, pyEtCIC, pyEtCPhone,pyEtRoomType,pyEtServiceCharge,pyEtPaymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        //Get lists from Shared Preference
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();
        reservationList = new Gson().fromJson(reservationJSONString, type2);


        pySprReservation = (Spinner) findViewById(R.id.pySprReservation);
        pyEtCName = (EditText) findViewById(R.id.pyEtCName);
        pyEtCIC = (EditText) findViewById(R.id.pyEtCIC);
        pyEtCPhone = (EditText) findViewById(R.id.pyEtCPhone);
        pyEtRoomType = (EditText) findViewById(R.id.pyEtRoomType);
        pyEtServiceCharge = (EditText) findViewById(R.id.pyEtServiceCharge);
        pyEtPaymentAmount = (EditText) findViewById(R.id.pyEtPaymentAmount);

        pyEtCName.setEnabled(false);
        pyEtCIC.setEnabled(false);
        pyEtCPhone.setEnabled(false);
        pyEtRoomType.setEnabled(false);
        pyEtServiceCharge.setEnabled(false);
        pyEtPaymentAmount.setEnabled(false);

        List<Reservation> reservedList = new ArrayList<Reservation>();
        for(int i =0;i<reservationList.size();i++){
            if(!(reservationList.get(i).getPaymentAmount()>0) )
                reservedList.add(reservationList.get(i));
        }

        Reservation spinnerHeader = new Reservation();
        spinnerHeader.setReservationID("Select Reservation");
        if(!reservedList.get(0).getReservationID().equals("Select Reservation"))
            reservedList.add(0,spinnerHeader);


        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_spinner_item, reservedList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pySprReservation.setAdapter(adapter);

        pySprReservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("Select Reservation")){

                }else{
                    String reservationID = parent.getItemAtPosition(position).toString();
                    for(int i=0;i<reservationList.size();i++){
                        if(reservationID.equals(reservationList.get(i).getReservationID())){
                            int roomID = Integer.parseInt(reservationList.get(i).getRoomID());
                            double roomPrice=0.0;
                            double serviceCharge=0.0;

                            if(roomID> 100 && roomID < 200){
                                pyEtRoomType.setText("Family Room");
                                roomPrice = 150.0;
                            }else if(roomID> 200 && roomID < 300){
                                pyEtRoomType.setText("Deluxe Room");
                                roomPrice = 300.0;
                            }else if(roomID> 300 && roomID < 400){
                                pyEtRoomType.setText("Executive Suite Room");
                                roomPrice = 450.0;
                            }else if(roomID> 400){
                                pyEtRoomType.setText("VIP Room");
                                roomPrice = 1000.0;
                            }


                            pyEtCName.setText(reservationList.get(i).getCustName());
                            pyEtCIC.setText(reservationList.get(i).getCustICNo());
                            pyEtCPhone.setText(reservationList.get(i).getCustPhoneNo());

                            serviceCharge = roomPrice*0.1;

                            pyEtServiceCharge.setText("RM"+String.valueOf(serviceCharge) +"0");
                            pyEtPaymentAmount.setText("RM"+String.valueOf(roomPrice+serviceCharge) +"0");
                            reservationList.get(i).setPaymentAmount(roomPrice+serviceCharge);
                            reservationList.get(i).setPaymentDateTime(Calendar.getInstance().getTime());
                            break;
                        }


                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
    }

    public void makePayments(View v) {

            //Save list to shared preference
            String reservationJSONString = new Gson().toJson(reservationList);

            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_RESERVATION", reservationJSONString);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Payment has been made successfully.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MakePaymentActivity.class);
            startActivity(intent);
        }
}
