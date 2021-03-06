package Rooms;

import Guest.Group;
import Guest.Guest;

public class Bedroom extends Room {

    protected RoomType roomType;
    protected Double pricePerNight;
    protected int roomNumber;
    protected boolean isOccupied;
    protected int nightsBooked;

    public Bedroom(RoomType roomtype, Double pricePerNight, int roomNumber, int capacity) {
        super(capacity);
        this.roomType = roomtype;
        this.pricePerNight = pricePerNight;
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.nightsBooked = 0;

    }

    public String getType() {
        return roomType.getType();
    }

    public void setType(RoomType type) {
        this.roomType = type;
    }

    public double getPrice() {
        return pricePerNight;
    }

    public void setPrice(Double price) {
        this.pricePerNight = price;
    }


    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getNightsBooked() {
        return nightsBooked;
    }

    public void setNightsBooked(int numberOfNights) {
        this.nightsBooked = numberOfNights;
    }

    public void addGroup(Group group){
        this.getGuestsInRoom().addAll(group.getGuestsList());
    }

    public void removeGuests() {
        this.getGuestsInRoom().clear();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void checkOutGuests() {
        for(Guest guest : guestsInRoom){
            guest.setCheckedInStatus(false);
        }
    }

}
