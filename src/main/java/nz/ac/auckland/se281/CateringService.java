package nz.ac.auckland.se281;

public class CateringService extends Service {
  public CateringService(String cateringType, int cost) {
    super(cateringType, cost);
  }

  @Override
  public Service.Type getServiceType() {
    return Service.Type.CATERING;
  }
}
