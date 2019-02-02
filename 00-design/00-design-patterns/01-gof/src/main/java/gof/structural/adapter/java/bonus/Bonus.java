package gof.structural.adapter.java.bonus;

/*
https://learning.oreilly.com/videos/learn-design-patterns/9781788838795/9781788838795-video4_1

 */
class AmazonCloudProvider {
  public void startVM(String id){
    System.out.println(this.getClass().getSimpleName() + " start  ");
  }

  public void stopVM(String id){
    System.out.println(this.getClass().getSimpleName() + " stop  ");
  }

  public void rebootVM(String id){
    System.out.println(this.getClass().getSimpleName() + " reboot  ");
  }
}

class GoogleCloudProvider {
  public void startVM(String id){
    System.out.println(this.getClass().getSimpleName() + " start  ");
  }

  public void shutdownVM(String id){
    System.out.println(this.getClass().getSimpleName() + " stop  ");
  }
}

// ICloudAdapter
interface ICloudAdapter {
  void start(String id);
  void stop(String id);
  void restart(String id);
}

class AmazonCloudAdapter implements ICloudAdapter {
  AmazonCloudProvider adapteeProvider = new AmazonCloudProvider();

  @Override
  public void start(String id) {
    adapteeProvider.startVM(id);
  }

  @Override
  public void stop(String id) {
    adapteeProvider.stopVM(id);
  }

  @Override
  public void restart(String id) {
    adapteeProvider.rebootVM(id);
  }
}

class GoogleCloudAdapter implements ICloudAdapter {
  GoogleCloudProvider adapteeProvider = new GoogleCloudProvider();

  @Override
  public void start(String id) {
    adapteeProvider.startVM(id);
  }

  @Override
  public void stop(String id) {
    adapteeProvider.shutdownVM(id);
  }

  @Override
  public void restart(String id) {
    adapteeProvider.shutdownVM(id);
    adapteeProvider.startVM(id);
  }
}

public class Bonus {
  public static void main(String[] args) {
    ICloudAdapter amazonCloudAdapter = new AmazonCloudAdapter();
    amazonCloudAdapter.start("");
    amazonCloudAdapter.stop("");
    amazonCloudAdapter.restart("");
  }
}
