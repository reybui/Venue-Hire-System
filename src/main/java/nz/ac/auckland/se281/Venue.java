package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String GetVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public int capacity() {
    return capacity;
  }

  public int hireFee() {
    return hireFee;
  }
}
