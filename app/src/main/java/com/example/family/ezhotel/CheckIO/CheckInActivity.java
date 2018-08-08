package com.example.family.ezhotel.CheckIO;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.R;
import com.example.family.ezhotel.Reservation.EditReservationActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckInActivity extends AppCompatActivity {

    private SharedPreferences appSharedPrefs;
    private List<Reservation> reservationList;

    private Spinner ciSprReservation;
    private EditText ciEtCName, ciEtCIC, ciEtCPhone,ciEtRoomType,ciEtCheckInDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        //Get lists from Shared Preference
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();
        reservationList = new Gson().fromJson(reservationJSONString, type2);


        ciSprReservation = (Spinner) findViewById(R.id.ciSprReservation);
        ciEtCName = (EditText) findViewById(R.id.ciEtCName);
        ciEtCIC = (EditText) findViewById(R.id.ciEtCIC);
        ciEtCPhone = (EditText) findViewById(R.id.ciEtCPhone);
        ciEtRoomType = (EditText) findViewById(R.id.ciEtRoomType);
        ciEtCheckInDate = (EditText) findViewById(R.id.ciEtCheckInDate);

        ciEtCName.setEnabled(false);
        ciEtCIC.setEnabled(false);
        ciEtCPhone.setEnabled(false);
        ciEtRoomType.setEnabled(false);
        ciEtCheckInDate.setEnabled(false);

        List<Reservation> reservedList = new ArrayList<Reservation>();
        for(int i =0;i<reservationList.size();i++){
            if(reservationList.get(i).getStatus().equals("Reserved"))
                reservedList.add(reservationList.get(i));
        }

        Reservation spinnerHeader = new Reservation();
        spinnerHeader.setReservationID("Select Reservation");
        if(!reservedList.get(0).getReservationID().equals("Select Reservation"))
            reservedList.add(0,spinnerHeader);


        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_spinner_item, reservedList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciSprReservation.setAdapter(adapter);

        ciSprReservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("Select Reservation")){

                }else{
                    String reservationID = parent.getItemAtPosition(position).toString();
                    for(int i=0;i<reservationList.size();i++){
                        if(reservationID.equals(reservationList.get(i).getReservationID())){
                            int roomID = Integer.parseInt(reservationList.get(i).getRoomID());

                            if(roomID> 100 && roomID < 200){
                                ciEtRoomType.setText("Family Room");
                            }else if(roomID> 200 && roomID < 300){
                                ciEtRoomType.setText("Deluxe Room");
                            }else if(roomID> 300 && roomID < 400){
                                ciEtRoomType.setText("Executive Suite Room");
                            }else if(roomID> 400){
                                ciEtRoomType.setText("VIP Room");
                            }


                            ciEtCName.setText(reservationList.get(i).getCustName());
                            ciEtCIC.setText(reservationList.get(i).getCustICNo());
                            ciEtCPhone.setText(reservationList.get(i).getCustPhoneNo());
                            ciEtCheckInDate.setText(reservationList.get(i).getCheckInDate());

                            reservationList.get(i).setStatus("Checked-In");
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
    public void checkIn(View v) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        if(date.equals(String.valueOf(ciEtCheckInDate.getText()))){


            //Save list to shared preference
            String reservationJSONString = new Gson().toJson(reservationList);
            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_RESERVATION", reservationJSONString);
            editor.commit();



            Toast.makeText(getApplicationContext(), "Successfully checked-in.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CheckInActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), "Unable to check-in, check-in date does not match with today's date.", Toast.LENGTH_SHORT).show();
        }



    }
}
