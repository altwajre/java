package gof.behavioral.command.java;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@FunctionalInterface
interface Command {
  void execute();
}
final class CommandFactory {
  private final Map<String, Command> commands;
  private CommandFactory() {
    commands = new HashMap<>();
  }
  public void addCommand(final String name, final Command command) {
    commands.put(name, command);
  }
  public void executeCommand(String name) {
    if (commands.containsKey(name)) {
      commands.get(name).execute();
    }else{
      System.out.println(name+ " Command not found");
    }
  }
  public void listCommands() {
    System.out.println("Available commands: " + commands.keySet().stream().collect(Collectors.joining(", ")));
  }
  /* Factory pattern */
  public static CommandFactory init() {
    final CommandFactory cf = new CommandFactory();
    // commands are added here using lambdas.
    // It is also possible to dynamically add commands without editing the code.
    cf.addCommand("LightOn", () -> System.out.println("Light turned on"));
    cf.addCommand("LightOff", () -> System.out.println("Light turned off"));
    return cf;
  }
}
public class Base {
  public static void main (String[] args) {
    System.out.println("Light switch Project using COMMAND DESIGN PATTERN \n ");

    // All available Commands will be created and init by command factory
    CommandFactory cf = CommandFactory.init();

    // Will list all commands which can be executed
    cf.listCommands();

    // Commands can be added and listed dynamically with ZERO code changes in client side
    System.out.println("Master - what command you want to execute ");
    String command = "LightOn";

    // Actual execution of command will happen
    cf.executeCommand(command);
  }
}
/*
Light switch Project using COMMAND DESIGN PATTERN

Available commands: LightOff, LightOn
Master - what command you want to execute
Light turned on
 */
