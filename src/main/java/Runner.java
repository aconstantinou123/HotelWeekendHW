import Guest.Group;
import Guest.Guest;
import Rooms.*;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    private Hotel hotel;

    public Runner (Hotel hotel) {
        this.hotel = hotel;
    }

    public static void main(String[] args) {

        Hotel hotel2 = new Hotel("Club Tropicana");
        Bedroom bedroom1 = new Bedroom(RoomType.SINGLE, 80.0, 101, 2);
        Bedroom bedroom2 = new Bedroom(RoomType.DOUBLE, 200.0, 102, 2);
        Bedroom bedroom3 = new Bedroom(RoomType.PENTHOUSE, 300.0, 103, 4);
        Bedroom bedroom4 = new Bedroom(RoomType.SINGLE, 300.0, 104, 2);
        Bedroom bedroom5 = new Bedroom(RoomType.DOUBLE, 300.0, 105, 2);
        ConferenceRoom conferenceRoom1 = new ConferenceRoom(20, 200.0, "None");
        Restaurant restaurant1 = new Restaurant(20, "Mexican");
        Guest guest1 = new Guest("Lacey", 300.0);
        Guest guest2 = new Guest("Alex", 120.0);
        Guest guest3 = new Guest("Bill", 220.0);
        Guest guest4 = new Guest("Steve", 120.0);
        Guest guest5 = new Guest("Jenny", 300.0);
        Guest guest6 = new Guest("Sarah", 300.0);
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        hotel2.addBedRoom(bedroom1);
        hotel2.addBedRoom(bedroom2);
        hotel2.addBedRoom(bedroom3);
        hotel2.addBedRoom(bedroom4);
        hotel2.addBedRoom(bedroom5);
        hotel2.addConferenceRoom(conferenceRoom1);
        hotel2.addRestaurant(restaurant1);
        group2.addGuest(guest1);
        group2.addGuest(guest2);
        group3.addGuest(guest3);
        group3.addGuest(guest4);
        group4.addGuest(guest5);
        group4.addGuest(guest6);

        hotel2.checkIn(bedroom1, group2, 1);
        hotel2.checkIn(bedroom2, group3, 1);
        hotel2.checkIn(bedroom3, group4, 1);

        Runner runner = new Runner(hotel2);
        runner.run();


    }

    public void run(){
        String choice;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.println("Welcome to " + hotel.getHotelName() + " Admin Services");
            System.out.println("Menu Options:\n" +
                    "1. View all Rooms\n" +
                    "2. View Unoccupied Rooms\n" +
                    "3. View Number of Guests in Hotel\n" +
                    "4. Total money paid in\n" +
                    "5. Check-in guests\n" +
                    "6. Check-out guests\n" +
                    "7. View Conference Room information\n" +
                    "8. Book Conference Room\n" +
                    "9. Reset Conference Room\n" +
                    "10. Check Restaurant\n" +
                    "11. Book a Table\n" +
                    "Q. Quit program\n");
            String input = scanner.nextLine().toLowerCase();
            choice = input;
            switch (choice) {
                case "1":
                    System.out.println(hotel.allRoomInfo());
                    System.out.println("Press any key return to the main menu");
                    String input2 = scanner.nextLine();
                    choice = input2.toLowerCase();
                    break;
                case "2":
                    System.out.println(hotel.unoccupiedRooms());
                    System.out.println("Press any key return to the main menu");
                    String input3 = scanner.nextLine();
                    choice = input3.toLowerCase();
                    break;
                case "3":
                    System.out.println(hotel.totalNumberOfGuests() + " guests");
                    System.out.println("Press any key return to the main menu");
                    String input4 = scanner.nextLine();
                    choice = input4.toLowerCase();
                    break;
                case "4":
                    System.out.println("£" + hotel.getMoneyMade());
                    System.out.println("Press any key to return to the main menu");
                    String input5 = scanner.nextLine();
                    choice = input5.toLowerCase();
                    break;
                case "5":
                    System.out.println("Number of rooms: ");
                    Integer numberOfRooms = Integer.parseInt(scanner.nextLine());
                    for (int j = 0; j < numberOfRooms; j++) {
                    System.out.println("Number of nights: ");
                    Integer numberOfNights = Integer.parseInt(scanner.nextLine());
                    System.out.println("Choose a room: ");
                    System.out.println(hotel.unoccupiedRooms());
                    Integer roomNumber = Integer.parseInt(scanner.nextLine());
                    Group guestList = new Group();
                    System.out.println("Number of Customers: ");
                    Integer numberOfGuests = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numberOfGuests; i++) {
                        System.out.println("Guest name: ");
                        String guestName = scanner.nextLine();
                        guestList.addGuest(new Guest(guestName, 1000.0));
                    }
                    System.out.println("Confirm details. Type 'Yes' to continue:");
                    System.out.println("Room " + hotel.findRoom(roomNumber).getRoomNumber());
                    String answer = scanner.nextLine();
                    if (answer.equals("Yes")) {
                        hotel.checkIn(hotel.findRoom(roomNumber), guestList, numberOfNights);
                        System.out.println("Check In successful");
                    }
            }
                    break;
                case "6":
                    System.out.println(hotel.allRoomInfo());
                    System.out.println("Type the room number for the room you wish to check out");
                    Integer roomNumberCheckOut = Integer.parseInt(scanner.nextLine());
                    hotel.checkOut(hotel.findRoom(roomNumberCheckOut));
                    System.out.println("Guests checked out from Room " + hotel.findRoom(roomNumberCheckOut).getRoomNumber());
                    break;

                case "7":
                    System.out.println(hotel.viewConferenceRoomActivity());
                    System.out.println("Press any key return to the main menu");
                    String input6 = scanner.nextLine();
                    choice = input6.toLowerCase();
                    break;

                case "8":
                    Group conferenceList = new Group();
                    System.out.println("Conference Room Use ");
                    String conferenceRoomUse = scanner.nextLine();
                    System.out.println("Number of Customers: ");
                    Integer numberOfConferenceGuests = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numberOfConferenceGuests; i++) {
                        System.out.println("Guest name: ");
                        String guestName = scanner.nextLine();
                        conferenceList.addGuest(new Guest(guestName, 1000.0));
                    }
                    System.out.println("Confirm details. Type 'Yes' to continue:");
                    System.out.println(conferenceList.getGuestsList().size() + " guests want to use the conference room for a "
                    + conferenceRoomUse);
                    String answer1 = scanner.nextLine();
                    if(answer1.equals("Yes")){
                        hotel.bookConferenceRoom(hotel.getConferenceRooms().get(0), conferenceList, conferenceRoomUse);
                        System.out.println("Booking successful");
                    }
                    break;

                case "9":
                    System.out.println("Reset conference Room? Yes/No");
                    String answer2 = scanner.nextLine();
                    if(answer2.equals("Yes")){
                        hotel.leaveConferenceRoom(hotel.getConferenceRooms().get(0));
                    }
                    break;

                case "10":
                    System.out.println(hotel.viewRestaurant());
                    System.out.println("Press any key return to the main menu");
                    String input8 = scanner.nextLine();
                    choice = input8.toLowerCase();
                    break;

                case "11":
                    Group restaurantGroup = new Group();
                    System.out.println("Choose a room to book a table for:");
                    Integer roomNumberToBook = Integer.parseInt(scanner.nextLine());
                    Bedroom bedroomToBook = hotel.findRoom(roomNumberToBook);
                        for (Guest guest : bedroomToBook.getGuestsInRoom()) {
                            restaurantGroup.addGuest(guest);
                        }
                    System.out.println("Number of tables required: ");
                    Integer tablesRequired = Integer.parseInt(scanner.nextLine());
                    System.out.println("Room " + roomNumberToBook + " to book " + tablesRequired + " table(s). Type 'Yes' to confirm");
                    String answer3 = scanner.nextLine();
                    if(answer3.equals("Yes")){
                        hotel.bookRestaurant(restaurantGroup, hotel.getRestaurants().get(0), tablesRequired);
                        System.out.println("Booking Successful ");
                    }
                    break;

                case "q":
                    System.exit(0);
                    break;

                    default:
                        System.out.println("Unrecognised Command");
                        System.out.println("Press any key return to the main menu");
                        String input9 = scanner.nextLine();
                        choice = input9.toLowerCase();
                        break;
            }
        } while (!choice.equals("q"));
    }


}
