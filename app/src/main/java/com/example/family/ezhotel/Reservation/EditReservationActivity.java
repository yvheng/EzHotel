package com.example.family.ezhotel.Reservation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


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

public class EditReservationActivity extends AppCompatActivity {


    private SharedPreferences appSharedPrefs;
    private List<Room> roomList;
    private List<Reservation> reservationList;
    private Button buttonCIDate,buttonCODate;
    private Spinner sprStaff, sprRoom,sprReservation;
    private EditText etCName, etCIC, etCPhone,etReservationDate,etCheckInDate, etCheckOutDate;
    //For validation of checkout Date
    private int checkInDom, checkInMonth;
    //To trace the arrayList position
    private int reservationListPos=0;
    //To get the old reservationID
    private String oldReservationID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);

        //Get lists from Shared Preference
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String roomJSONString = appSharedPrefs.getString("KEY_ROOM", null);
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type1 = new TypeToken<List<Room>>() {}.getType();
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        roomList = new Gson().fromJson(roomJSONString, type1);
        reservationList = new Gson().fromJson(reservationJSONString, type2);

        buttonCIDate = (Button) findViewById(R.id.buttonCIDate);
        buttonCODate = (Button) findViewById(R.id.buttonCODate);
        sprStaff = (Spinner) findViewById(R.id.sprStaff);
        sprRoom = (Spinner) findViewById(R.id.sprRoom);
        sprReservation = (Spinner) findViewById(R.id.sprReservation);


        etCName = (EditText) findViewById(R.id.etCName);
        etCIC = (EditText) findViewById(R.id.etCIC);
        etCPhone = (EditText) findViewById(R.id.etCPhone);
        etReservationDate = (EditText) findViewById(R.id.etReservationDate);
        etCheckInDate = (EditText) findViewById(R.id.etCheckInDate);
        etCheckOutDate = (EditText) findViewById(R.id.etCheckOutDate);

//      String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//      editTextReservationDate.setText(date);
        etReservationDate.setEnabled(false);
        etCheckInDate.setEnabled(false);
        etCheckOutDate.setEnabled(false);
        staffIDSpinner();
        roomIDSpinner();
//
//
//        String roomid = roomList.get(1).getRoomID();
//        String roomstatus = roomList.get(1).getRoomStatus();
//        textViewMessage1.setText(roomid +" " + roomList.get(3).getRoomID() +" " + String.valueOf(reservationList.size()));
//        testViewMessage2.setText(roomstatus +" " + roomList.get(3).getRoomStatus() +" " + String.valueOf(roomList.size()));
//
//
//        //Save list to shared preference
////        roomList.get(0).setRoomID("101");
////        roomList.get(0).setRoomStatus("Available");
////        roomList.get(1).setRoomStatus("Available");
//        String roomJSONString1 = new Gson().toJson(roomList);
//        String reservationJSONString1 = new Gson().toJson(reservationList);
//
//        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
//        SharedPreferences.Editor editor = appSharedPrefs.edit();
//        editor.putString("KEY_ROOM", roomJSONString1);
//        editor.putString("KEY_RESERVATION", reservationJSONString1);
//        editor.commit();

        //DatePicker for checkIn Date
        buttonCIDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                Calendar c = Calendar.getInstance();
                final int m = c.get(Calendar.MONTH);
                final int dom = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditReservationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if(month+1< m+1  || ( (month+1== m+1)&&(day < dom)) ) {
                                    Toast.makeText(getApplicationContext(), "Invalid Check-In Date", Toast.LENGTH_SHORT).show();
                                    etCheckInDate.setText("");
                                }
                                else{
                                    checkInMonth = month+1;
                                    checkInDom = day;
                                    etCheckInDate.setText(year + "-" + (month+1) + "-" + day  );
                                }

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        //DatePicker for checkOut Date
        buttonCODate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);




                DatePickerDialog datePickerDialog = new DatePickerDialog(EditReservationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if(month+1< checkInMonth  || ( (month+1== checkInMonth)&&(day <= checkInDom)) ) {
                                    Toast.makeText(getApplicationContext(), "Invalid Check-Out Date", Toast.LENGTH_SHORT).show();
                                    etCheckOutDate.setText("");
                                }
                                else{
                                    etCheckOutDate.setText(year + "-" + (month+1) + "-" + day  );
                                }

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });


        Reservation spinnerHeader = new Reservation();
        spinnerHeader.setReservationID("Select Reservation");
        if(!reservationList.get(0).getReservationID().equals("Select Reservation"))
        reservationList.add(0,spinnerHeader);

        ArrayAdapter<Reservation> adapter = new ArrayAdapter<Reservation>(this, android.R.layout.simple_spinner_item, reservationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprReservation.setAdapter(adapter);

        sprReservation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("Select Reservation")){

                }else{
                    String reservationID = parent.getItemAtPosition(position).toString();
                    for(int i=0;i<reservationList.size();i++){
                        if(reservationID.equals(reservationList.get(i).getReservationID())){
                            int roomID = Integer.parseInt(reservationList.get(i).getRoomID());

                            if(roomID> 100 && roomID < 200){
                                sprRoom.setSelection(0);
                            }else if(roomID> 200 && roomID < 300){
                                sprRoom.setSelection(1);
                            }else if(roomID> 300 && roomID < 400){
                                sprRoom.setSelection(2);
                            }else if(roomID> 400){
                                sprRoom.setSelection(3);
                            }

                            sprStaff.setSelection(getIndex(sprStaff, reservationList.get(i).getStaffID()));
                            etCName.setText(reservationList.get(i).getCustName());
                            etCIC.setText(reservationList.get(i).getCustICNo());
                            etCPhone.setText(reservationList.get(i).getCustPhoneNo());
                            etReservationDate.setText(reservationList.get(i).getReservationDate());
                            etCheckInDate.setText(reservationList.get(i).getCheckInDate());
                            etCheckOutDate.setText(reservationList.get(i).getCheckOutDate());
                            reservationListPos = i;
                            oldReservationID=reservationList.get(i).getReservationID();
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
    public void staffIDSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.staff_arrays, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner
        sprStaff.setAdapter(adapter);

    }

    public void roomIDSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roomType_arrays, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sprRoom.setAdapter(adapter);
    }

    //To get the value of spinner
    public int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    public void editReservation(View v){


        String custName = String.valueOf(etCName.getText());
        String custIC = String.valueOf(etCIC.getText());
        String custPhone = String.valueOf(etCPhone.getText());
        String staffId = sprStaff.getSelectedItem().toString();
        String roomType = sprRoom.getSelectedItem().toString();
        String roomID="";
        String reservationDate = String.valueOf(etReservationDate.getText());
        String checkInDate = String.valueOf(etCheckInDate.getText());
        String checkOutDate = String.valueOf(etCheckOutDate.getText());
        String status = "Reserved";

        for(int i=0; i< roomList.size();i++){
            if(roomList.get(i).getRoomType().equals(roomType) && roomList.get(i).getRoomStatus().equals("Available")){
                roomID = roomList.get(i).getRoomID();
                roomList.get(i).setRoomStatus("Unavailable");
                break;

            }
        }

        //Successful added reservation
        if(custName.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Customer Name.", Toast.LENGTH_SHORT).show();
        }else if(custIC.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Customer IC Number.", Toast.LENGTH_SHORT).show();
        }else if(custPhone.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Customer Phone Number.", Toast.LENGTH_SHORT).show();
        }else if(checkInDate.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Check-In Date.", Toast.LENGTH_SHORT).show();
        }else if(checkOutDate.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter Check-Out Date.", Toast.LENGTH_SHORT).show();
        }else if(roomID.equals("")){
            Toast.makeText(getApplicationContext(), "Room Unavailable, please select other room type." + String.valueOf(roomList.size()), Toast.LENGTH_SHORT).show();
        }
        else{
            Reservation newReservation = new Reservation(oldReservationID,custName,custIC,custPhone,staffId,roomID,reservationDate,checkInDate,checkOutDate,status);
            reservationList.set(reservationListPos,newReservation);

            //Save list to shared preference
            String roomJSONString = new Gson().toJson(roomList);
            String reservationJSONString = new Gson().toJson(reservationList);

            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_ROOM", roomJSONString);
            editor.putString("KEY_RESERVATION", reservationJSONString);
            editor.commit();


            Toast.makeText(getApplicationContext(),"The reservation has been edited." , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EditReservationActivity.class);
            startActivity(intent);
        }

    }





}
