# Error Handling

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter07.html

Should separate the business logic and error handling.

Sometimes, it is nearly impossible to see what the code does because of all of the scattered error handling.
Error handling is important, but if it obscures logic, it's wrong
Write code that is both clean and robust - code that handles errors with grace and style.

## USE EXCEPTIONS RATHER THAN RETURN CODES

With the return error codes approach, the caller must check for errors immediately after the call that it's easy to forget.
It is better to throw an exception when you encounter an error. The calling code is cleaner. The algorithm and error handling are now separated.

## WRITE YOUR TRY-CATCH-FINALLY STATEMENT FIRST

It is good practice to start with a try-catch-finally statement when you are writing code that could throw exceptions.
Try to write tests that force exceptions, and then add behavior to your handler to satisfy the tests.

## USE UNCHECKED EXCEPTIONS

Checked exception violates `Open/Closed Principle` because a change at a low level of the software can force signature changes on many higher levels.
Checked exceptions can sometimes be useful if you are writing a critical library: You must catch them.
In general application development the dependency costs outweigh the benefits.

## PROVIDE CONTEXT WITH EXCEPTIONS

Each exception that you throw should provide enough context to determine the source and location of an error.

## DEFINE EXCEPTION CLASSES IN TERMS OF A CALLER'S NEEDS

### Third-party library - covers all exceptions contains duplication.

```
ACMEPort port = new ACMEPort(12);

try {
  port.open();
} catch (DeviceResponseException e) {
  reportPortError(e);
  logger.log(“Device response exception”, e);
} catch (ATM1212UnlockedException e) {
  reportPortError(e);
  logger.log(“Unlock exception”, e);
} catch (GMXError e) {
  reportPortError(e);
  logger.log(“Device response exception”);
} finally {
  …
}
```

### Create wrapper class returns a common exception type

- Wrap a third-party API minimize your dependencies: you can move to a different library in the future
- It is easy to mock out third-party calls when you are testing your own code
- Not tied to a particular vendor's API design choices.
- Often a single exception class is fine for a particular area of code.

```
LocalPort port = new LocalPort(12);
try {
  port.open();
} catch (PortDeviceFailure e) {
  reportError(e);
  logger.log(e.getMessage(), e);
} finally {
  …
}
public class LocalPort {
  private ACMEPort innerPort;

  public LocalPort(int portNumber) {
    innerPort = new ACMEPort(portNumber);
  }

  public void open() {
    try {
      innerPort.open();
    } catch (DeviceResponseException e) {
      throw new PortDeviceFailure(e);
    } catch (ATM1212UnlockedException e) {
      throw new PortDeviceFailure(e);
    } catch (GMXError e) {
      throw new PortDeviceFailure(e);
    }
  }
  …
}
```

## DEFINE THE NORNAL FLOW

`Special Case Pattern` - Create a class or configure an object so that it handles a special case for you.
The client code doesn't have to deal with exceptional behavior because the behavior is encapsulated in the special case object.

## DON'T RETURN NULL

- Consider throwing an exception or returning a Special Case object instead of returning null
- When calling a null-returning method from a third-party API, consider wrapping that method with a method that either throws an exception or returns a special case object.

Every other line was a check for null is bad.

```
public void registerItem(Item item) {
  if (item != null) {
    ItemRegistry registry = peristentStore.getItemRegistry();
    if (registry != null) {
      Item existing = registry.getItem(item.getID());
      if (existing.getBillingPeriod().hasRetailOwner()) {
        existing.register(item);
      }
    }
  }
}
```

When we return null, we are creating work for ourselves and problems upon our callers.
Missing a null check will send an application out of control.

## DON'T PASS NULL

Returning null from methods is bad, but passing null into methods is worse.
