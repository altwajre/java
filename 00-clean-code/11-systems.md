# Systems

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter11.html

## SEPARATE CONSTRUCTING A SYSTEM FROM USING IT

Software systems should separate the startup process.
The global setup strategy should not be scattered across the application.
We should modularize the startup process separately from the normal runtime logic and we should make sure that we have a global, consistent strategy for resolving major dependencies.

## Separation of Main

Move all aspects of construction to main(), or modules called by main(), and to design the rest of the system assuming that all objects have been constructed and wired up appropriately.
The main() builds the startup objects necessary for the system, then passes them to the application.

## Dependency Injection

In the context of dependency management, an object should not take responsibility for instantiating dependencies itself.
The class takes no direct steps to resolve its dependencies; it is completely passive.
It provides setter methods or constructor arguments (or both) that are used to inject the dependencies.
The DI container instantiates the required dependency objects via constructor arguments or setter methods.

## SCALING UP

Software architectures can grow incrementally if we maintain the proper separation of concerns.

### Cross-Cutting Concerns

Spring AOP

The desired transactional, security, and some of the persistence behaviors are declared in the deployment descriptors, independently of the source code.

## JAVA PROXIES

There is a lot of code here and it is relatively complicated, even for this simple case.
This code "volume" and complexity are two of the drawbacks of proxies.
They make it hard to create clean code!

> JDK Proxy Example

```
// Bank.java (suppressing package names…)
import java.utils.*;
// The abstraction of a bank.
public interface Bank {
  Collection<Account> getAccounts();
  void setAccounts(Collection<Account> accounts);
}
// BankImpl.java
import java.utils.*;
// The “Plain Old Java Object” (POJO) implementing the abstraction.
public class BankImpl implements Bank {
  private List<Account> accounts;
  @Override
  public Collection<Account> getAccounts() {
    return accounts;
  }
  @Override
  public void setAccounts(Collection<Account> accounts) {
    this.accounts = new ArrayList<Account>();
    for (Account account: accounts) {
      this.accounts.add(account);
    }
  }
}
// BankProxyHandler.java
import java.lang.reflect.*;
import java.util.*;
// “InvocationHandler” required by the proxy API.
public class BankProxyHandler implements InvocationHandler {
  private Bank bank;
  public BankHandler (Bank bank) {
    this.bank = bank;
  }
  // Method defined in InvocationHandler
  @Override
  public Object invoke(Object proxy, Method method, Object[] args)
      throws Throwable {
  String methodName = method.getName();
  if (methodName.equals(”getAccounts”)) {
    bank.setAccounts(getAccountsFromDatabase());
    return bank.getAccounts();
  } else if (methodName.equals(”setAccounts”)) {
    bank.setAccounts((Collection<Account>) args[0]);
    setAccountsToDatabase(bank.getAccounts());
    return null;
  } else {
    …
  }
}
// Lots of details here:
protected Collection<Account> getAccountsFromDatabase() { … }
protected void setAccountsToDatabase(Collection<Account> accounts) { … }
}
// Somewhere else…
Bank bank = (Bank) Proxy.newProxyInstance(
  Bank.class.getClassLoader(),
  new Class[] { Bank.class },
  new BankProxyHandler(new BankImpl()));
```

## PURE JAVA AOP (aspect oriented programming) FRAMEWORKS

Proxies are used internally in several Java frameworks, for example, Spring AOP and JBoss AOP, to implement aspects in pure Java.
In Spring, you write your business logic as Plain-Old Java Objects.
POJOs are purely focused on their domain. They have no dependencies on enterprise frameworks (or any other domains).
Hence, they are conceptually simpler and easier to test drive.
You incorporate the required application infrastructure, including cross-cutting concerns like persistence, transactions, security, caching, failover, and so on, using declarative configuration files or APIs.
These declarations drive the dependency injection (DI) container, which instantiates the major objects and wires them together on demand.

## ASPECTJ ASPECTS

The most full-featured tool for separating concerns through aspects is the AspectJ language.

## TEST DRIVE THE SYSTEM ARCHITECTURE

It is not necessary to do a Big Design Up Front18 (BDUF)
An optimal system architecture consists of modularized domains of concern, each of which is implemented with Plain Old Java Objects. The different domains are integrated together with minimally invasive Aspects or Aspect-like tools.
this architecture can be test-driven, like like the code.

## SYSTEMS NEED DOMAIN-SPECIFIC LANGUAGES

Domain-Specific Languages (DSLs), which are separate, small scripting languages in standard languages that permit code to be written so that it reads like a structured form that a domain expert might write.
Domain-Specific Languages allow all levels of abstraction and all domains in the application to be expressed as POJOs, from high-level policy to low-level details.
