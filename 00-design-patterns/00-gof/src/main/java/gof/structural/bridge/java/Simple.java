package gof.structural.bridge.java;

interface ITV {
  void on ();

  void off ();

  void switchProgram (int channel);
}

class SamsungTV implements ITV {
  @Override
  public void on () {
    System.out.println("Samsung is turned on.");
  }

  @Override
  public void off () {
    System.out.println("Samsung is turned off.");
  }

  @Override
  public void switchProgram (int channel) {
    System.out.println("Samsung: channel - " + channel);
  }
}

class SonyTV implements ITV {

  @Override
  public void on () {
    System.out.println("Sony is turned on.");
  }

  @Override
  public void off () {
    System.out.println("Sony is turned off.");
  }

  @Override
  public void switchProgram (int channel) {
    System.out.println("Sony: channel - " + channel);
  }
}
abstract class BridgeRemoteControl {

  // Introduced has a relationship
  private ITV tv;

  public BridgeRemoteControl (ITV tv) {
    this.tv = tv;
  }

  public void turnOn () {
    tv.on();
  }

  public void turnOff () {
    tv.off();
  }

  public void setChannel (int channel) {
    tv.switchProgram(channel);
  }

  // additional features
  public void recordProgram(){
    System.out.println("IndependentRemoteControl use keyword to set channel.");
  }
}

class IndependentRemoteControl extends BridgeRemoteControl {
  public IndependentRemoteControl (ITV tv) {
    super(tv);
  }

  public void setChannelKeyboard (int channel) {
    setChannel(channel);
    System.out.println("IndependentRemoteControl use keyword to set channel.");
  }
}

// coupled implementation of remote controller
class DependentRemoteControl implements ITV {
  @Override
  public void on () {
    System.out.println(" Forced to change, if TV interface changes ");
  }

  @Override
  public void off () {
    System.out.println(" Forced to change, if TV interface changes ");
  }

  @Override
  public void switchProgram (int channel) {
    System.out.println(" Forced to change, if TV interface changes ");
  }

}
public class Simple {
  public static void main(String[] args) {
    System.out.println("Bridge design pattern example ");
    ITV tv = new SonyTV();

    // Bridge between Remote and TV interface helps both of them to
    // evolve independently
    IndependentRemoteControl remote = new IndependentRemoteControl(tv);
    remote.turnOn();
    remote.setChannel(7);
    remote.turnOff();
    // additional methods
    remote.recordProgram();
  }
}
/*
Bridge design pattern example
Sony is turned on.
Sony: channel - 7
Sony is turned off.
IndependentRemoteControl use keyword to set channel.
 */
