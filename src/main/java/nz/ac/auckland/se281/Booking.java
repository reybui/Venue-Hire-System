package nz.ac.auckland.se281;

public class Booking {
  private String bookingReference;
  private String bookingDate;
  private String clientEmail;
  private String numOfAttendees;
  private String venueName;

  // Booking booking = new Booking(bookingReference, bookingDate, clientEmail,
  // Integer.parseInt(numOfAttendees), bookingCode);
  public Booking(
      String bookingReference,
      String bookingDate,
      String clientEmail,
      String numOfAttendees,
      String venueName) {
    this.bookingReference = bookingReference;
    this.bookingDate = bookingDate;
    this.clientEmail = clientEmail;
    this.numOfAttendees = numOfAttendees;
    this.venueName = venueName;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venueName, this.bookingDate, this.numOfAttendees);
  }

  public String getBookingDate() {
    return this.bookingDate;
  }

  public String getvenueName() {
    return this.venueName;
  }
}
