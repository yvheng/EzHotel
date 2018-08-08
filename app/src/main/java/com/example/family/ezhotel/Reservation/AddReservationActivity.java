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

import com.example.family.ezhotel.HomeActivity;
import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.Model.Room;
import com.example.family.ezhotel.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddReservationActivity extends AppCompatActivity  {
    private Button btnCIDate,btnCODate, btnAddReservation;
    private Spinner spnStaffID, spnRoomID;
    private EditText editTextCName, editTextCIC, editTextCPhone,editTextReservationDate,editTextCIDate, editTextCODate;
    private List<Room> roomList;
    private List<Reservation> reservationList;
    //For validation of checkout Date
    private int checkInDom, checkInMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);

        btnCIDate = (Button) findViewById(R.id.btnCIDate);
        btnCODate = (Button) findViewById(R.id.btnCODate);
        btnAddReservation = (Button) findViewById(R.id.btnAddReservation);
        spnStaffID = (Spinner) findViewById(R.id.spnStaffID);

        editTextCName = (EditText) findViewById(R.id.editTextCName);
        editTextCIC = (EditText) findViewById(R.id.editTextCIC);
        editTextCPhone = (EditText) findViewById(R.id.editTextCPhone);
        editTextReservationDate = (EditText) findViewById(R.id.editTextReservationDate);
        editTextCIDate = (EditText) findViewById(R.id.editTextCIDate);
        editTextCODate = (EditText) findViewById(R.id.editTextCODate);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        editTextReservationDate.setText(date);
        editTextReservationDate.setEnabled(false);
        editTextCIDate.setEnabled(false);
        editTextCODate.setEnabled(false);

        //Get lists from Shared Preference
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String roomJSONString = appSharedPrefs.getString("KEY_ROOM", null);
        String reservationJSONString = appSharedPrefs.getString("KEY_RESERVATION", null);
        Type type1 = new TypeToken<List<Room>>() {}.getType();
        Type type2 = new TypeToken<List<Reservation>>() {}.getType();

        roomList = new Gson().fromJson(roomJSONString, type1);
        reservationList = new Gson().fromJson(reservationJSONString, type2);

        //call spinner method to set values
        roomIDSpinner();
        staffIDSpinner();

      //DatePicker for checkIn Date
        btnCIDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                Calendar c = Calendar.getInstance();
                final int m = c.get(Calendar.MONTH);
                final int dom = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddReservationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if(month+1< m+1  || ( (month+1== m+1)&&(day < dom)) ) {
                                    Toast.makeText(getApplicationContext(), "Invalid Check-In Date", Toast.LENGTH_SHORT).show();
                                    editTextCIDate.setText("");
                                }
                                else{
                                    checkInMonth = month+1;
                                    checkInDom = day;
                                    editTextCIDate.setText(String.format("%d-%02d-%02d",year, month+1, day));
                                }

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        //DatePicker for checkOut Date
        btnCODate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);




                DatePickerDialog datePickerDialog = new DatePickerDialog(AddReservationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if(month+1< checkInMonth  || ( (month+1== checkInMonth)&&(day <= checkInDom)) ) {
                                    Toast.makeText(getApplicationContext(), "Invalid Check-Out Date", Toast.LENGTH_SHORT).show();
                                    editTextCODate.setText("");
                                }
                                else{
                                    editTextCODate.setText(String.format("%d-%02d-%02d",year, month+1, day));
                                }

                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }
    public void staffIDSpinner(){

        spnStaffID = (Spinner) findViewById(R.id.spnStaffID);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.staff_arrays, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spnStaffID.setAdapter(adapter);
    }

    public void roomIDSpinner(){

        spnRoomID = (Spinner) findViewById(R.id.spnRoomID);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roomType_arrays, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spnRoomID.setAdapter(adapter);
    }

       public void addReservation(View v){

        int reservationId = reservationList.size()+10001;
        String custName = String.valueOf(editTextCName.getText());
        String custIC = String.valueOf(editTextCIC.getText());
        String custPhone = String.valueOf(editTextCPhone.getText());
        String staffId = spnStaffID.getSelectedItem().toString();
        String roomType = spnRoomID.getSelectedItem().toString();
        String roomID="";
        String reservationDate = String.valueOf(editTextReservationDate.getText());
        String checkInDate = String.valueOf(editTextCIDate.getText());
        String checkOutDate = String.valueOf(editTextCODate.getText());
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
            Toast.makeText(getApplicationContext(), "Room Unavailable, please select other room type." , Toast.LENGTH_SHORT).show();
        }
        else{
            Reservation newReservation = new Reservation(String.valueOf(reservationId),custName,custIC,custPhone,staffId,roomID,reservationDate,checkInDate,checkOutDate,0.0,status);
            reservationList.add(newReservation);

            //Save list to shared preference
            String roomJSONString = new Gson().toJson(roomList);
            String reservationJSONString = new Gson().toJson(reservationList);

            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
            SharedPreferences.Editor editor = appSharedPrefs.edit();
            editor.putString("KEY_ROOM", roomJSONString);
            editor.putString("KEY_RESERVATION", reservationJSONString);
            editor.commit();


            Toast.makeText(getApplicationContext(),"Reservation is added successfully." , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddReservationActivity.class);
            startActivity(intent);
        }

    }
}
