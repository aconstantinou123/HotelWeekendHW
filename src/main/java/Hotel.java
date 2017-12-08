import Guest.Group;
import Rooms.*;

import java.util.ArrayList;

public class Hotel {

    private String hotelName;
    private ArrayList<Bedroom> bedrooms;
    private ArrayList<Room> otherRooms;
    private Double moneyMade;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.bedrooms = new ArrayList<Bedroom>();
        this.otherRooms = new ArrayList<Room>();
        this.moneyMade = 0.0;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNumberofRooms() {
        return bedrooms.size();
    }

    public Double getMoneyMade() {
        return moneyMade;
    }

    public void addBedRoom(Bedroom bedroom) {
        this.bedrooms.add(bedroom);
    }

    public void removeBedRoom(Bedroom bedroom) {
        this.bedrooms.remove(bedroom);
    }

    public void checkIn(Bedroom bedroom, Group group) {
        if (group.enoughMoneyForRoom(bedroom) == true &&
                group.checkCapacity(bedroom) == true &&
                 bedroom.getIsOccupied() == false) {
                    bedroom.addGroup(group);
                    group.deductMoney(bedroom);
                    bedroom.setIsOccupied(true);
                    group.checkInAllGuests();
                    moneyMade += bedroom.getPrice();
                    group.removeAll();
        }
    }

    public void checkOut(Bedroom bedroom, Group group) {
        if (bedroom.getIsOccupied() == true){
            bedroom.checkOutGuests();
            bedroom.removeGuests();
            bedroom.setIsOccupied(false);

        }
    }

    public int totalNumberOfGuests() {
        int guests = 0;
        for (Bedroom bedroom : bedrooms){
            guests += bedroom.numberOfGuestsInRoom();
        }
        return guests;
    }

    public String allRoomInfo() {
        ArrayList<String> guestNames = new ArrayList<>();
        for (Bedroom bedroom : bedrooms) {
            if (bedroom.getIsOccupied() == true) {
                guestNames.add(String.format("Room %s is currently occupied by %s people - %s"  + "\n", bedroom.getRoomNumber(),
                       bedroom.numberOfGuestsInRoom(), bedroom.getGuestNames()));
            }
            else {guestNames.add(String.format("Room %s is currently unoccupied" + "\n", bedroom.getRoomNumber()));

            }
        }
        String result = String.join("", guestNames);
        return result;
    }
}
