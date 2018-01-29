# Functions Structure

https://www.safaribooksonline.com/library/view/clean-code/9780134661742/CODE_01_04_00.html

Function should be small. It should only does one thing.

## Arguments

- no more than three arguments
- do not pass boolean argument because it means the function does two things
- no output arguments because it causes double take
- for internal api, do not pass null as argument, and no defensive programming. unit tests should catch the problem.

## The Stepdown Rule

public methods at the top, private methods at the bottom

```
public
private
```

## Switches and Cases

System should be independently deployable and independently developable.

> Problems

- Switch and long-chain if statements are a Fan-Out problem, because it is tightly coupled with dependencies.
- Switch and dependencies can not be compiled independently.

> Solutions

- Inverse dependencies for polymorphism
- Replace `switch` and `long-chain if` statements with polymorphic dispatch

### Main Partition

- Draw a line between `App` core functionalities and `Main` low level stuff like factories and configuration data
- Main partition should depend on App partition
- App partition should have not dependencies on Main partition, main is plugin to the App

### Dependency Injection

- Carefully design the `App` and `Main` partitions
- Only injects a few from `Main` to `App`, and then let `Main` to do the rest of work with factories and strategies
- `switch` statements in `Main` and `plugins` are safe. `Main` is also a `plugin`

## Functional Programming

- Write program without any assignment statement
- No `side effects`

### Temporal Coupling

functions need to be called in certain order (open and close)

- passing a block to eliminate temporal coupling

```
public void open(File f, Command c) {
  f.open();
  c.process(f);
  f.close();
}
```

### Command Query Separation

- Command changes the state of the system and returns nothing but it can throw exception that has `side effects`
- Query returns the computation value or the state of the system
- Function changes state should not return value but it can throw exception (setter) has `side effects`
- Function returns value should not change state (getter) does not have `side effects`

### Tell Don't Ask

Tell the object what to do, but not to ask the object for its state.

#### Train Wreck

`Train wreck` is violation of `Tell Don't Ask` and `Law of Demeter`

```
o.getX()
  .getY()
    .getZ()
      .doSomething();
```

#### Law of Demeter

Talk to friends, not to strangers

- Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.
- Each unit should only talk to its friends; don't talk to strangers.
- Only talk to your immediate friends.

## Structured Programming

Single entry on the top, and single exit on the bottom

- Sequence - blocks, the exit of the fist block leads to the entry of the second block
- Selection - if statement
- Iteration - while loop statement

### Early Returns

Returns and break in the middle of loop violates the structure

## Error Handling

- Use unchecked exception
- Exception without message because the best comment is the comment you don't write

### Special Case

- Zero size
- Null is not an Error
