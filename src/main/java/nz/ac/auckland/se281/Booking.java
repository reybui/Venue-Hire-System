package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Service.Type;

public class Booking {
  private String bookingReference;
  private String bookingDate;
  private String clientEmail;
  private String numOfAttendees;
  private String venueName;
  private String systemDate;
  private ArrayList<Service> services = new ArrayList<Service>();

  // Booking booking = new Booking(bookingReference, bookingDate, clientEmail,
  // Integer.parseInt(numOfAttendees), bookingCode);
  public Booking(
      String bookingReference,
      String bookingDate,
      String clientEmail,
      String numOfAttendees,
      String venueName,
      String systemDate) {
    this.bookingReference = bookingReference;
    this.bookingDate = bookingDate;
    this.clientEmail = clientEmail;
    this.numOfAttendees = numOfAttendees;
    this.venueName = venueName;
    this.systemDate = systemDate;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venueName, this.bookingDate, this.numOfAttendees);
  }

  public String getClientEmail() {
    return this.clientEmail;
  }

  public String getBookingDate() {
    return this.bookingDate;
  }

  public int getBookingDay() {
    String[] dateParts = this.bookingDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    return day;
  }

  public String getSystemDate() {
    return this.systemDate;
  }

  public String getVenueName() {
    return this.venueName;
  }

  public String getBookingReference() {
    return this.bookingReference;
  }

  public void addService(Service service) {
    services.add(service);
  }

  public Type getServiceType() {
    for (Service service : services) {
      if (service.getServiceType() == Service.Type.CATERING) {
        return Service.Type.CATERING;
      } else if (service.getServiceType() == Service.Type.FLORAL) {
        return Service.Type.FLORAL;
      } else if (service.getServiceType() == Service.Type.MUSIC) {
        return Service.Type.MUSIC;
      }
    }
    // Default return statement
    return null;
  }

  public void printFloralService() {
    for (Service service : services) {
      if (service.getServiceType() == Service.Type.FLORAL) {
        FloralService floralService = (FloralService) service;
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            floralService.getFloralName(), Integer.toString(floralService.getCost()));
      }
    }
  }

  public void printCateringService() {
    for (Service service : services) {
      if (service.getServiceType() == Service.Type.CATERING) {
        CateringService cateringService = (CateringService) service;
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            cateringService.getCateringType(), Integer.toString(cateringService.getCost()));
      }
    }
  }

  public void printMusicService() {
    for (Service service : services) {
      if (service.getServiceType() == Service.Type.MUSIC) {
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage("500");
      }
    }
  }

  public int getNumberOfAttendees() {
    return Integer.parseInt(this.numOfAttendees);
  }

  public int calculateTotalCost(int venueFee) {
    int totalCost = venueFee;
    for (Service service : services) {
      totalCost += service.getCost();
    }
    return totalCost;
  }
}
