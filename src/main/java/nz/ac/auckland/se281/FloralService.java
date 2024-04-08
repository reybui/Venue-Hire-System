package nz.ac.auckland.se281;

public class FloralService extends Service {
  private String floralName;

  public FloralService(String floralName, int cost) {
    super(cost);
    this.floralName = floralName;
  }

  public String getFloralName() {
    return floralName;
  }

  @Override
  public Type getServiceType() {
    return Type.FLORAL;
  }
}
