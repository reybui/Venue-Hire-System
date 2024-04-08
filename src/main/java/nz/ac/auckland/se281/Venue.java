package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private String availableDate = "";
  private String systemDate;

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getHireFee() {
    return hireFee;
  }

  public void setAvailableDate(String date) {
    if(date.equals(this.availableDate))
    this.availableDate = date;
  }

  public String getAvailableDate() {
    return availableDate;
  }

  public int getAvailableDay() {
    String[] dateParts = availableDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    return day;
  }

  // public int getAvailableMonth(String date) {
  //   String[] dateParts = date.split("/");
  //   int month = Integer.parseInt(dateParts[1]);
  //   return month;
  // }

  // public int getAvailableYear(String date) {
  //   String[] dateParts = date.split("/");
  //   int year = Integer.parseInt(dateParts[2]);
  //   return year;
  // }

  public void setSystemDate(String date) {
    this.systemDate = date;
  }
}
