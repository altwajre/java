package aCommand;

import java.util.ArrayList;
import java.util.List;

/*
https://www.safaribooksonline.com/videos/design-patterns-clean/9780135485965/9780135485965-DPCC_E25

https://github.com/jrogalsk/patterns/tree/master/src/main/java/com/jrsoft/learning/patterns/behavioural/command/actor_model
 */
class IO {
  public static void out(int ioAddress, int i) {
    System.out.println("# IO.out(): ioAddress=" + ioAddress);
  }

  public static int in(int buttonAddr) {
    return 0;
  }
}

interface Command {
  void execute();
}

class ButtonCommand implements Command {
  private int buttonAddr;
  private Command onPress;

  public ButtonCommand(int buttonAddr, Command onPress) {
    this.buttonAddr = buttonAddr;
    this.onPress = onPress;
  }

  @Override
  public void execute() {
    System.out.println("# ButtonCommand.execute(): Thread=" + Thread.currentThread().getName());
    ActorModel.commands.add(buttonHasBeenPressed() ? onPress : this);
  }

  private boolean buttonHasBeenPressed() {
    return IO.in(buttonAddr) == 0;
  }

  public static class LightCommand implements Command {
    private int ioAddress;

    public LightCommand(int ioAddress) {
      this.ioAddress = ioAddress;
    }

    @Override
    public void execute() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      IO.out(ioAddress, 1);
    }
  }
}

class ActorModel {
  public static List<Command> commands = new ArrayList<>();

  public ActorModel() {
    commands.add(new ButtonCommand(1, new ButtonCommand.LightCommand(11)));
    commands.add(new ButtonCommand(2, new ButtonCommand.LightCommand(12)));
    commands.add(new ButtonCommand(3, new ButtonCommand.LightCommand(13)));
    commands.add(new ButtonCommand(4, new ButtonCommand.LightCommand(14)));
    commands.add(new ButtonCommand(5, new ButtonCommand.LightCommand(15)));
  }

  public void run() {
    while (commands.size() != 0) {
      Command cmd = commands.get(0);
      commands.remove(0);
      cmd.execute();
    }
  }
}

public class ActorModelApp {
  public static void main(String[] args) {
    ActorModel actorModel = new ActorModel();
    actorModel.run();
  }

}
/*
# ButtonCommand.execute(): Thread=main
# ButtonCommand.execute(): Thread=main
# ButtonCommand.execute(): Thread=main
# ButtonCommand.execute(): Thread=main
# ButtonCommand.execute(): Thread=main
# IO.out(): ioAddress=11
# IO.out(): ioAddress=12
# IO.out(): ioAddress=13
# IO.out(): ioAddress=14
# IO.out(): ioAddress=15
 */
