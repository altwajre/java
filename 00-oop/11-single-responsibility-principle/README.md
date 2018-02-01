# Single Responsibility Principle

- Each class and module should focus on a single task at a time
- Everything in the class should be related to that single purpose
- There can be many members in the class as long as they related to the single responsibility
- With SRP, classes become smaller and cleaner
- Code is less fragile

> Intent

A class should have only one reason to change.

https://en.wikipedia.org/wiki/Single_responsibility_principle

> youtube

https://www.youtube.com/watch?v=hGf2upfDpdo

> example

http://www.oodesign.com/single-responsibility-principle.html

```
interface IContent {
  String getAsString(); // used for serialization
}
class HtmlContent implements IContent {
  @Override
  public String getAsString() {
    return "HtmlContent";
  }
}
interface IEmail {
  void setSender(String sender);
  void setReceiver(String receiver);
  void setContent(IContent content);
}
class Email implements IEmail {
  @Override
  public void setSender(String sender) {
    System.out.println("Email setSender: sender=" + sender);
  }
  @Override
  public void setReceiver(String receiver) {
    System.out.println("Email setReceiver: receiver=" + receiver);
  }
  @Override
  public void setContent(IContent content) {
    System.out.println("Email setContent: content=" + content.getAsString());
  }
}
public class App {
  public static void main(String[] args) {
    IEmail email = new Email();
    email.setSender("Tom");
    email.setReceiver("Dick");
    email.setContent(new HtmlContent());
  }
}
```
