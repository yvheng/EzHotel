package com.example.family.ezhotel.Model;

import java.util.Date;

/**
 * Created by LKS on 26/7/2018.
 */

public class Reservation {
    private String ReservationID,CustName, CustICNo, CustPhoneNo, StaffID,RoomID, ReservationDate, CheckInDate, CheckOutDate, Status;
    private double PaymentAmount;
    private Date PaymentDateTime;

    public Reservation(){

    }

    public Reservation(String ReservationID, String CustName,String CustICNo,String CustPhoneNo, String StaffID, String RoomID, String ReservationDate, String CheckInDate, String CheckOutDate,double PaymentAmount,Date PaymentDateTime ,String Status){
        this.ReservationID = ReservationID;
        this.CustName = CustName;
        this.CustICNo = CustICNo;
        this.CustPhoneNo = CustPhoneNo;
        this.StaffID = StaffID;
        this.RoomID = RoomID;
        this.ReservationDate = ReservationDate;
        this.CheckInDate = CheckInDate;
        this.CheckOutDate = CheckOutDate;
        this.PaymentAmount= PaymentAmount;
        this.PaymentDateTime= PaymentDateTime;
        this.Status = Status;
    }
    public Reservation(String ReservationID, String CustName,String CustICNo,String CustPhoneNo, String StaffID, String RoomID, String ReservationDate, String CheckInDate, String CheckOutDate, double PaymentAmount,String Status){

        this.ReservationID = ReservationID;
        this.CustName = CustName;
        this.CustICNo = CustICNo;
        this.CustPhoneNo = CustPhoneNo;
        this.StaffID = StaffID;
        this.RoomID = RoomID;
        this.ReservationDate = ReservationDate;
        this.CheckInDate = CheckInDate;
        this.CheckOutDate = CheckOutDate;
        this.PaymentAmount=PaymentAmount;
        this.Status = Status;

    }

    public String getReservationID() {
        return ReservationID;
    }

    public void setReservationID(String reservationID) {
        ReservationID = reservationID;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustICNo() {
        return CustICNo;
    }

    public void setCustICNo(String custICNo) {
        CustICNo = custICNo;
    }

    public String getCustPhoneNo() {
        return CustPhoneNo;
    }

    public void setCustPhoneNo(String custPhoneNo) {
        CustPhoneNo = custPhoneNo;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(String reservationDate) {
        ReservationDate = reservationDate;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public double getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public Date getPaymentDateTime() {
        return PaymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        PaymentDateTime = paymentDateTime;
    }

    public String getStatus() {return Status;}

    public void setStatus(String status) {Status = status;}

    public String toString(){
        return ReservationID;
    }
}
