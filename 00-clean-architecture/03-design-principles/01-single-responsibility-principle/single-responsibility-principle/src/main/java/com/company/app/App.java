package com.company.app;

/*
http://www.oodesign.com/single-responsibility-principle.html

Intent:
- A class should only one reason to change
- Change Email class should not need to change Content classes

Benefit:
- adding a new protocol causes changes only in the Email
- adding a new type of content supported causes changes only in Content class

 */
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
/*
output:
Email setSender: sender=Tom
Email setReceiver: receiver=Dick
Email setContent: content=HtmlContent
 */
