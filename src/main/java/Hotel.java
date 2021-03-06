import Guest.Group;
import Guest.Guest;
import Rooms.*;

import java.util.ArrayList;

public class Hotel {

    private String hotelName;
    private ArrayList<Bedroom> bedrooms;
    private ArrayList<ConferenceRoom> conferenceRooms;
    private ArrayList<Restaurant> restaurants;
    private Double moneyMade;
    private UI ui;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.bedrooms = new ArrayList<Bedroom>();
        this.conferenceRooms = new ArrayList<ConferenceRoom>();
        this.restaurants = new ArrayList<Restaurant>();
        this.moneyMade = 0.0;
        this.ui = new UI();
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

    public void addConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRooms.add(conferenceRoom);
    }

    public ArrayList<ConferenceRoom> getConferenceRooms() {
        return this.conferenceRooms;
    }

    public void removeBedRoom(Bedroom bedroom) {
        this.bedrooms.remove(bedroom);
    }

    public void checkIn(Bedroom bedroom, Group group, int numberOfNights) {
        if (group.enoughMoneyForRoom(bedroom, numberOfNights) == true &&
                group.checkCapacity(bedroom) == true &&
                 bedroom.getIsOccupied() == false) {
                    bedroom.addGroup(group);
                    group.deductMoney(bedroom, numberOfNights);
                    bedroom.setIsOccupied(true);
                    group.checkInAllGuests();
                    bedroom.setNightsBooked(numberOfNights);
                    moneyMade += (bedroom.getPrice() * numberOfNights);
                    group.removeAll();
        }
    }

    public void checkOut(Bedroom bedroom) {
        if (bedroom.getIsOccupied() == true){
            bedroom.checkOutGuests();
            bedroom.removeGuests();
            bedroom.setNightsBooked(0);
            bedroom.setIsOccupied(false);

        }
    }

    public void bookConferenceRoom(ConferenceRoom conferenceRoom, Group group, String use) {
        if(group.enoughMoneyForConferenceRoom(conferenceRoom) == true &&
                group.getGuestsList().size() <= conferenceRoom.getCapacity() &&
                conferenceRoom.getIsInUse() == false){
            conferenceRoom.addGroup(group);
            group.deductMoneyConferenceRoom(conferenceRoom);
            conferenceRoom.setCurrentUse(use);
            conferenceRoom.setIsInUse(true);
            this.moneyMade += conferenceRoom.getPrice();
            group.removeAll();
        }

    }


    public void leaveConferenceRoom(ConferenceRoom conferenceRoom){
        if(conferenceRoom.getIsInUse() == true){
            conferenceRoom.removeGuests();
            conferenceRoom.setCurrentUse("None");
            conferenceRoom.setIsInUse(false);
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
                guestNames.add(String.format("Room %s is currently occupied by %s people for %s night(s)" + "\n"
                                + "Guest names: %s"  + "\n\n", bedroom.getRoomNumber(),
                       bedroom.numberOfGuestsInRoom(), bedroom.getNightsBooked(), bedroom.getGuestNames()));
            }
            else {guestNames.add(String.format("Room %s is currently unoccupied" + "\n\n", bedroom.getRoomNumber()));

            }
        }
        String result = String.join("", guestNames);
        return result;

    }

    public String unoccupiedRooms() {
        ArrayList<String> unoccupiedRooms = new ArrayList<>();
        for (Bedroom bedroom : bedrooms) {
           if (bedroom.getIsOccupied() == false){
               unoccupiedRooms.add(String.format("Room %s - %s" + "\n", bedroom.getRoomNumber(), bedroom.getType()));
           }
        }
        String result = String.join("", unoccupiedRooms);
        return result;
    }

    public Bedroom findRoom(int roomNumber) {
        ArrayList<Bedroom> foundRoom = new ArrayList<>();
        for(Bedroom bedroom : bedrooms) {
            if(roomNumber == bedroom.getRoomNumber()) {
                foundRoom.add(bedroom);
            }
        }
        if (foundRoom.isEmpty()) {
            System.out.println("Room not found!");
        }
        return foundRoom.get(0);
    }

    public String viewConferenceRoomActivity() {
        String result = new String();
        for (ConferenceRoom conferenceRoom : conferenceRooms) {
            if(conferenceRoom.getIsInUse() == true){
                result = String.format("The conference room is currently being used by %s guests for a %s ",
                        conferenceRoom.getGuestsInRoom().size(), conferenceRoom.getCurrentUse());
            }
            else{
                result = "The conference room is currently empty";
            }
        }
        return result;
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public int getNumberofRestaurants() {
        return restaurants.size();
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void removeRestaurant(Restaurant restaurant) {
       this.restaurants.remove(restaurant);

    }

    public void bookRestaurant(Group group, Restaurant restaurant, int tables) {
        if (group.getGuestsList().size() < restaurant.getCapacity()){
            restaurant.getGuestsInRoom().addAll(group.getGuestsList());
            restaurant.setTablesBooked(tables);
        }
    }

    public void cancelTable(Group group, Restaurant restaurant, int tables) { {
        for (Guest guest : group.getGuestsList()){
                if (restaurant.getGuestsInRoom().contains(guest)) {
                        restaurant.getGuestsInRoom().remove(guest);
                }
            }

        }
        restaurant.cancelTablesBooked(tables);
    }

    public String viewRestaurant() {
        String result = new String();
        for (Restaurant restaurant : restaurants){
            result = String.format("The restaurant currently has %s table(s) booked. The food being served this evening is %s",
                    restaurant.getTablesBooked(), restaurant.getCurrentMenu());
        }
        return result;
    }

}
