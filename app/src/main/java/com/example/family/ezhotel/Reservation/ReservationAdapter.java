package com.example.family.ezhotel.Reservation;

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
import java.util.Random;


/**
 * Created by LKS on 30/7/2018.
 */

public class ReservationAdapter  extends ArrayAdapter<Reservation> {

    public ReservationAdapter(Activity context, int resource, List<Reservation> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tViewReservationID, tViewCustName, tViewCIC,tViewCPhone, tViewStaffID,tViewRoomType,tViewReservationDate;

        Reservation reservation = getItem(position);

        LayoutInflater inflater  = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.reservation_record, parent, false);



        tViewReservationID = (TextView)rowView.findViewById(R.id.tViewReservationID);
        tViewCustName = (TextView)rowView.findViewById(R.id.tViewCustName);
        tViewCIC = (TextView)rowView.findViewById(R.id.tViewCIC);
        tViewCPhone = (TextView)rowView.findViewById(R.id.tViewCPhone);
        tViewStaffID = (TextView)rowView.findViewById(R.id.tViewStaffID);
        tViewRoomType = (TextView)rowView.findViewById(R.id.tViewRoomType);
        tViewReservationDate = (TextView)rowView.findViewById(R.id.tViewReservationDate);

        tViewReservationID.setText(tViewReservationID.getText() +" " + reservation.getReservationID());
        tViewCustName.setText(tViewCustName.getText() +" " + reservation.getCustName());
        tViewCIC.setText(tViewCIC.getText() +" " + reservation.getCustICNo());
        tViewCPhone.setText(tViewCPhone.getText() +" " + reservation.getCustPhoneNo());
        tViewStaffID.setText(tViewStaffID.getText() +" " + reservation.getStaffID());
        tViewReservationDate.setText(tViewReservationDate.getText() + " "+ reservation.getReservationDate() );
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
