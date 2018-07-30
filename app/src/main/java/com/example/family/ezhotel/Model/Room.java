package com.example.family.ezhotel.Model;

/**
 * Created by LKS on 26/7/2018.
 */

public class Room {
    private String RoomID,RoomType,RoomStatus;
    private double RoomPrice;

    public Room(){

    }

    public Room(String RoomID, String RoomType, String RoomStatus, double RoomPrice){
        this.RoomID = RoomID;
        this.RoomType = RoomType;
        this.RoomStatus = RoomStatus;
        this.RoomPrice = RoomPrice;

    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getRoomStatus() {
        return RoomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        RoomStatus = roomStatus;
    }

    public double getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        RoomPrice = roomPrice;
    }

    public String toString(){
        return RoomType;
    }
}
