package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues = new ArrayList<Venue>();
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private String systemDate;

  public VenueHireSystem() {}

  public void printVenues() {
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    } else if (venues.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    } else if (venues.size() == 2) {
      MessageCli.NUMBER_VENUES.printMessage("are", "two", "s");
    } else if (venues.size() == 3) {
      MessageCli.NUMBER_VENUES.printMessage("are", "three", "s");
    } else if (venues.size() == 4) {
      MessageCli.NUMBER_VENUES.printMessage("are", "four", "s");
    } else if (venues.size() == 5) {
      MessageCli.NUMBER_VENUES.printMessage("are", "five", "s");
    } else if (venues.size() == 6) {
      MessageCli.NUMBER_VENUES.printMessage("are", "six", "s");
    } else if (venues.size() == 7) {
      MessageCli.NUMBER_VENUES.printMessage("are", "seven", "s");
    } else if (venues.size() == 8) {
      MessageCli.NUMBER_VENUES.printMessage("are", "eight", "s");
    } else if (venues.size() == 9) {
      MessageCli.NUMBER_VENUES.printMessage("are", "nine", "s");
    } else if (venues.size() > 9) {
      int size = venues.size();
      String sizeAsString = Integer.toString(size);
      MessageCli.NUMBER_VENUES.printMessage("are", sizeAsString, "s");
    }

    for (int i = 0; i < venues.size(); i++) {
      Venue venue = venues.get(i);
      String name = venue.getVenueName();
      String code = venue.getVenueCode();
      int capacity = venue.getCapacity();
      int hireFee = venue.getHireFee();
      String availableDate = venue.getAvailableDate();
      MessageCli.VENUE_ENTRY.printMessage(
          name, code, Integer.toString(capacity), Integer.toString(hireFee), availableDate);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }
    if (!isNumeric(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }
    if (!isNumeric(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }
    if (Integer.parseInt(capacityInput) <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }
    if (Integer.parseInt(hireFeeInput) <= 0) { // needs to be whole number as well
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    for (Venue venue : venues) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
    }

    Venue newVenue =
        new Venue(
            venueName, venueCode, Integer.parseInt(capacityInput), Integer.parseInt(hireFeeInput));
    venues.add(newVenue);
  }

  private boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    if (!venues.isEmpty()) {
      for (Venue venue : venues) {
        venue.setAvailableDate(dateInput);
      }
    }
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    // check if system date is set
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // determine if systemDate is set
    if (this.systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }
    // determine if there are venues in the sytem
    if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }
    // assign each option
    String bookingCode = options[0];
    String bookingDate = options[1];
    String clientEmail = options[2];
    String numOfAttendees = options[3];

    String[] dateParts = bookingDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    String[] systemDateParts = systemDate.split("/");
    int sysDay = Integer.parseInt(systemDateParts[0]);
    int sysMonth = Integer.parseInt(systemDateParts[1]);
    int sysYear = Integer.parseInt(systemDateParts[2]);

    // check if booking code exists in the system
    Venue venue = null;
    for (Venue ven : venues) {
      if (ven.getVenueCode().equals(bookingCode)) {
        venue = ven;
        break;
      }
    }

    if (venue == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookingCode);
      return;
    }

    // check if booking date is in the past ADD EXTRA TESTS !TURN INTO FUNCTION!
    if (!systemDate.equals(bookingDate) && year <= sysYear) {
      if (year == sysYear) {
        if (month < sysMonth) {
          MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
          return;
        } else if (month == sysMonth) {
          if (day < sysDay) {
            MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
            return;
          }
        }
      } else {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
        return;
      }
    }

    // check if venue is already booked on the date
    for (Booking b : bookings) {
      if (b.getBookingDate().equals(bookingDate) && b.getVenueName().equals(venue.getVenueName())) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            venue.getVenueName(), bookingDate);
        return;
      }
    }

    // adjust number of attendees
    if (Integer.parseInt(numOfAttendees) > venue.getCapacity()) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numOfAttendees,
          Integer.toString(venue.getCapacity()),
          Integer.toString(venue.getCapacity()));
      numOfAttendees = Integer.toString(venue.getCapacity());
    } else if (Integer.parseInt(numOfAttendees) < (venue.getCapacity() / 4)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          numOfAttendees,
          Integer.toString(venue.getCapacity() / 4),
          Integer.toString(venue.getCapacity()));
      numOfAttendees = Integer.toString(venue.getCapacity() / 4);
    }

    // create booking reference
    String bookingReference = BookingReferenceGenerator.generateBookingReference();

    // create booking
    Booking booking =
        new Booking(
            bookingReference,
            bookingDate,
            clientEmail,
            numOfAttendees,
            venue.getVenueName(),
            systemDate);
    bookings.add(booking);

    // update next available date !!NOT FINISHED!!
    setNextAvailableDate(venue, bookingDate);
  }

  public void setNextAvailableDate(Venue venue, String date) {
    // split date into parts
    String[] dateParts = date.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    day = day + 1;

    String formattedDay = String.format("%02d", day);
    String formattedMonth = String.format("%02d", month);

    venue.setAvailableDate(formattedDay + "/" + formattedMonth + "/" + dateParts[2]);
  }

  public void printBookings(String venueCode) {

    if (venues.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }
    Venue venue = null;
    for (Venue ven : venues) {
      if (ven.getVenueCode().equals(venueCode)) {
        venue = ven;
        break;
      }
    }

    if (venue == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venue.getVenueName());
    boolean hasBookings = false;
    for (Booking b : bookings) {
      if (b.getVenueName().equals(venue.getVenueName())) {
        hasBookings = true;
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(b.getBookingReference(), b.getBookingDate());
      }
    }

    if (!hasBookings) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venue.getVenueName());
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // check if booking reference exists
    Booking booking = null;
    for (Booking b : bookings) {
      if (b.getBookingReference().equals(bookingReference)) {
        booking = b;
        break;
      }
    }

    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }

    String cateringName = cateringType.getName();
    int cateringCost = cateringType.getCostPerPerson();
    int numberOfAttendees = booking.getNumberOfAttendees();

    // create catering service
    CateringService cateringService =
        new CateringService(cateringName, cateringCost, numberOfAttendees);
    booking.addService(cateringService);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringName + ")", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    // check if booking reference exists
    Booking booking = null;
    for (Booking b : bookings) {
      if (b.getBookingReference().equals(bookingReference)) {
        booking = b;
        break;
      }
    }

    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }

    MusicService musicService = new MusicService();
    booking.addService(musicService);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // check if booking reference exists
    Booking booking = null;
    for (Booking b : bookings) {
      if (b.getBookingReference().equals(bookingReference)) {
        booking = b;
        break;
      }
    }

    if (booking == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }

    // get floral type details
    String floralName = floralType.getName();
    int floralCost = floralType.getCost();

    // create floral service
    FloralService floralService = new FloralService(floralName, floralCost);
    booking.addService(floralService);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + floralName + ")", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // check if booking reference exists
    Booking booking = null;
    for (Booking b : bookings) {
      if (b.getBookingReference().equals(bookingReference)) {
        booking = b;
        break;
      }
    }

    if (booking == null) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    int venueFee = 0;
    for (Venue v : venues) {
      if (v.getVenueName().equals(booking.getVenueName())) {
        venueFee = v.getHireFee();
      }
    }

    // print invoice
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference,
        booking.getClientEmail(),
        booking.getSystemDate(),
        booking.getBookingDate(),
        Integer.toString(booking.getNumberOfAttendees()),
        booking.getVenueName());

    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(Integer.toString(venueFee));
    // determine what services are in the booking and print them
    if (booking.getServiceType() == Service.Type.CATERING) {
      booking.printCateringService();
    }
    if (booking.getServiceType() == Service.Type.FLORAL) {
      booking.printFloralService();
    }
    if (booking.getServiceType() == Service.Type.MUSIC) {
      booking.printMusicService();
    }

    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(
        Integer.toString(booking.calculateTotalCost(venueFee)));

    MessageCli.END.printMessage();
  }
}
