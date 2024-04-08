package nz.ac.auckland.se281;

public abstract class Service {
  protected String cateringType;
  protected int cost;

  public enum Type {
    CATERING,
    FLORAL,
    MUSIC
  }

  public Service(String name, int cost) {
    this.cateringType = name;
    this.cost = cost;
  }

  public String getCateringType() {
    return cateringType;
  }

  public abstract Type getServiceType();
}
