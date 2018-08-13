package com.example.family.ezhotel.CheckIO;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.family.ezhotel.Model.Reservation;
import com.example.family.ezhotel.R;

import java.util.List;

/**
 * Created by LKS on 30/7/2018.
 */

public class CICOAdapter  extends ArrayAdapter<Reservation> {


    public CICOAdapter(Activity context, int resource, List<Reservation> list) {
        super(context, resource, list);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tViewReservationID, tViewCustName, tViewCIC,tViewCPhone, tViewRoomType,tViewCheckInDate,tViewCheckOutDate,tViewStatusCICO,textViewRoomID;

        Reservation reservation = getItem(position);

        LayoutInflater inflater  = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cico_record, parent, false);


        textViewRoomID = (TextView)rowView.findViewById(R.id.textViewRoomID);
        tViewReservationID = (TextView)rowView.findViewById(R.id.tViewReservationID);
        tViewCustName = (TextView)rowView.findViewById(R.id.tViewCustName);
        tViewCIC = (TextView)rowView.findViewById(R.id.tViewCIC);
        tViewCPhone = (TextView)rowView.findViewById(R.id.tViewCPhone);
        tViewRoomType = (TextView)rowView.findViewById(R.id.tViewRoomType);
        tViewCheckInDate = (TextView)rowView.findViewById(R.id.tViewCheckInDate);
        tViewCheckOutDate = (TextView)rowView.findViewById(R.id.tViewCheckOutDate);
        tViewStatusCICO = (TextView) rowView.findViewById(R.id.tViewStatusCICO);


        textViewRoomID.setText(textViewRoomID.getText() +" " + reservation.getRoomID());
        tViewReservationID.setText(tViewReservationID.getText() +" " + reservation.getReservationID());
        tViewCustName.setText(tViewCustName.getText() +" " + reservation.getCustName());
        tViewCIC.setText(tViewCIC.getText() +" " + reservation.getCustICNo());
        tViewCPhone.setText(tViewCPhone.getText() +" " + reservation.getCustPhoneNo());
        tViewCheckInDate.setText(tViewCheckInDate.getText() +" " + reservation.getCheckInDate());
        tViewCheckOutDate.setText(tViewCheckOutDate.getText() +" " + reservation.getCheckOutDate());
        tViewStatusCICO.setText(tViewStatusCICO.getText() +" " + reservation.getStatus());



        int roomID = Integer.parseInt(reservation.getRoomID());

        String roomType = "";
        if(roomID> 100 && roomID < 200){
            roomType="Family Room";
        }else if(roomID> 200 && roomID < 300){
            roomType="Deluxe Room";
        }else if(roomID> 300 && roomID < 400){
            roomType="Executive Suite Room";
        }else if(roomID> 400){
            roomType="VIP Room";
        }
        tViewRoomType.setText(tViewRoomType.getText() +" " + roomType);


        return rowView;
    }

}
