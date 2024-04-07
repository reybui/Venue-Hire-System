package nz.ac.auckland.se281;

public class Booking {
  private String bookingReference;
  private String bookingDate;
  private String clientEmail;
  private String numOfAttendees;
  private Venue venue;

  // Booking booking = new Booking(bookingReference, bookingDate, clientEmail,
  // Integer.parseInt(numOfAttendees), bookingCode);
  public Booking(
      String bookingReference,
      String bookingDate,
      String clientEmail,
      String numOfAttendees,
      Venue venue) {
    this.bookingReference = bookingReference;
    this.bookingDate = bookingDate;
    this.clientEmail = clientEmail;
    this.numOfAttendees = numOfAttendees;
    this.venue = venue;
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venue.getVenueName(), this.bookingDate, this.numOfAttendees);
  }
}
