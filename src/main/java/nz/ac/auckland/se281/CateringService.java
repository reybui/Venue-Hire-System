package nz.ac.auckland.se281;

public class CateringService extends Service {
  private int numberOfAttendees;
  private String cateringType;

  public CateringService(String cateringType, int cost, int numberOfAttendees) {
    super(cost);
    this.numberOfAttendees = numberOfAttendees;
    this.cateringType = cateringType;
  }

  public String getCateringType() {
    return cateringType;
  }

  @Override
  public Service.Type getServiceType() {
    return Service.Type.CATERING;
  }

  @Override
  public int getCost() {
    return cost * numberOfAttendees;
  }
}
