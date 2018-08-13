package com.example.family.ezhotel.CheckIO;

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

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {
    private SharedPreferences appSharedPrefs;
    private List<Reservation> reservationList;
    private List<Room> roomList;

    private Spinner coSprReservation;
    private EditText coEtCName, coEtCIC, coEtCPhone,coEtRoomType,coEtCheckOutDate;
    private String tempRoomId;
    private boolean isPaymentMade=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        //Get lists from Shared Preference
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String roomJSONString = appSharedPrefs.getString("KEY_ROOM", null);
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type1 = new TypeToken<List<Room>>() {}.getType();
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        roomList = new Gson().fromJson(roomJSONString, type1);
        reservationList = new Gson().fromJson(reservationJSONString, type2);

        coSprReservation = (Spinner) findViewById(R.id.coSprReservation);
        coEtCName = (EditText) findViewById(R.id.coEtCName);
        coEtCIC = (EditText) findViewById(R.id.coEtCIC);
        coEtCPhone = (EditText) findViewById(R.id.coEtCPhone);
        coEtRoomType = (EditText) findViewById(R.id.coEtRoomType);
        coEtCheckOutDate = (EditText) findViewById(R.id.coEtCheckOutDate);

        coEtCName.setEnabled(false);
        coEtCIC.setEnabled(false);
        coEtCPhone.setEnabled(false);
        coEtRoomType.setEnabled(false);
        coEtCheckOutDate.setEnabled(false);

        final List<Reservation> reservedList = new ArrayList<Reservation>();
        for(int i =0;i<reservationList.size();i++){
            if(reservationList.get(i).getStatus().equals("Checked-In"))
                reservedList.add(reservationList.get(i));
        }

        Reservation spinnerHeader = new Reservation();
        spinnerHeader.setReservationID("Select Reservation");
        if(!reservedList.get(0).getReservationID().equals("Select Reservation"))
            reservedList.add(0,spinnerHeader);


        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_spinner_item, reservedList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coSprReservation.setAdapter(adapter);

        coSprReservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("Select Reservation")){

                }else{
                    String reservationID = parent.getItemAtPosition(position).toString();
                    int tempRoomID = parent.getSelectedItemPosition();
                    for(int i=0;i<reservationList.size();i++){
                        if(reservationID.equals(reservationList.get(i).getReservationID())  && reservationList.get(i).getRoomID().equals(reservedList.get(tempRoomID).getRoomID())){
                            int roomID = Integer.parseInt(reservationList.get(i).getRoomID());

                            if(roomID> 100 && roomID < 200){
                                coEtRoomType.setText("Family Room");
                            }else if(roomID> 200 && roomID < 300){
                                coEtRoomType.setText("Deluxe Room");
                            }else if(roomID> 300 && roomID < 400){
                                coEtRoomType.setText("Executive Suite Room");
                            }else if(roomID> 400){
                                coEtRoomType.setText("VIP Room");
                            }

                            tempRoomId = reservationList.get(i).getRoomID();
                            coEtCName.setText(reservationList.get(i).getCustName());
                            coEtCIC.setText(reservationList.get(i).getCustICNo());
                            coEtCPhone.setText(reservationList.get(i).getCustPhoneNo());
                            coEtCheckOutDate.setText(reservationList.get(i).getCheckOutDate());

                            reservationList.get(i).setStatus("Checked-Out");
                            if(reservationList.get(i).getPaymentAmount()>0.0)
                                isPaymentMade = true;
                            else
                                isPaymentMade = false;
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
    public void checkOut(View v) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        if(!date.equals(String.valueOf(coEtCheckOutDate.getText()))){
            Toast.makeText(getApplicationContext(), "Unable to check-out, check-out date does not match with today's date.", Toast.LENGTH_SHORT).show();
        }else if(isPaymentMade == false){
            Toast.makeText(getApplicationContext(), "Unable to check-out, please make the payment before check-out.", Toast.LENGTH_SHORT).show();
        } else{



            for(int i =0;i<roomList.size();i++){
               if(tempRoomId.equals(roomList.get(i).getRoomID()))
                   roomList.get(i).setRoomStatus("Available");
            }
            //Save list to shared preference
            String roomJSONString = new Gson().toJson(roomList);
            String reservationJSONString = new Gson().toJson(reservationList);

            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_ROOM", roomJSONString);
            editor.putString("KEY_RESERVATION", reservationJSONString);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Successfully checked-out.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CheckOutActivity.class);
            startActivity(intent);
        }



    }
}
