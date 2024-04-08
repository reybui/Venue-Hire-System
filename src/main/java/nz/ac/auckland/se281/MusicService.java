package nz.ac.auckland.se281;

public class MusicService extends Service {

  public MusicService() {
    super(500);
  }

  @Override
  public Type getServiceType() {
    return Type.MUSIC;
  }
}
