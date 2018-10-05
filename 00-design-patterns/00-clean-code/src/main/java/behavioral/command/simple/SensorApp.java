package behavioral.command.simple;

interface Command {
  void execute();
}

class MotorOffCommand implements Command {

  @Override
  public void execute() {
    System.out.println("# MotorOffCommand.execute()");
  }
}

class MotorOnCommand implements Command {

  @Override
  public void execute() {
    System.out.println("# MotorOnCommand.execute()");
  }
}

class Sensor {
  String name;
  Command command;

  public Sensor(String name, Command command) {
    this.name = name;
    this.command = command;
  }

  public void run() {
    command.execute();
  }
}

public class SensorApp {

  public static void main(String... args) {
    Sensor on = new Sensor("on_command", new MotorOnCommand());
    on.run();
    Sensor off = new Sensor("off_command", new MotorOffCommand());
    off.run();
  }

}
/*
# MotorOnCommand.execute()
# MotorOffCommand.execute()
 */
