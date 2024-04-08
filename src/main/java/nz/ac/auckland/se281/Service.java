package nz.ac.auckland.se281;

public abstract class Service {
  protected int cost;

  public enum Type {
    CATERING,
    FLORAL,
    MUSIC
  }

  public Service(int cost) {
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

  public abstract Type getServiceType();
}
