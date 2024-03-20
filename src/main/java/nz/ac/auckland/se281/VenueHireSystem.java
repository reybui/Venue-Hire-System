package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private ArrayList<Venue> venues;

  public VenueHireSystem() {
    this.venues = new ArrayList<>();
  }

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
      String name = venue.GetVenueName();
      String code = venue.getVenueCode();
      int capacity = venue.capacity();
      int hireFee = venue.hireFee();
      MessageCli.VENUE_ENTRY.printMessage(
          name, code, Integer.toString(capacity), Integer.toString(hireFee), "'to do'");
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
    Venue newVenue =
        new Venue(
            venueName, venueCode, Integer.parseInt(capacityInput), Integer.parseInt(hireFeeInput));
    venues.add(newVenue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
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
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
