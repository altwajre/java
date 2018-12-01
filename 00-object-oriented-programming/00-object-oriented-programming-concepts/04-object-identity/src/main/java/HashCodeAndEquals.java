/*
Implementing hashCode() and equals()

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_3
 */

import java.util.HashMap;
import java.util.Map;

class Client {
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long id;

  public Client(long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Client client = (Client) o;
    return id == client.id;
  }

  @Override
  public int hashCode() {
    return (int) this.id % 7; // up to 7 buckets possible
  }
}

class Policy {
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long id;

  public Policy(long id) {
    this.id = id;
  }
}

public class HashCodeAndEquals {
  public static void main(String[] args) {
    Map<Client, Policy> myClientPolicyMap = new HashMap<>();

    Client client13 = new Client(13);
    Policy policy13 = new Policy(13);

    myClientPolicyMap.put(client13, policy13);

    Client client13_2 = new Client(13);

    System.out.println("Are equal? " + client13_2.equals(client13) + " so client should be found in myClientPolicyMap");

    if (myClientPolicyMap.get(client13_2) != null) {
      System.out.println("Found!");
    } else {
      System.out.println("Not found");
    }

    System.out.println("Override client13.hashCode(): " + client13.hashCode());
    System.out.println("Override client13_2.hashCode(): " + client13_2.hashCode());

    System.out.println("Original System.identityHashCode(client13): " + System.identityHashCode(client13));
    System.out.println("Original System.identityHashCode(client13_2): " + System.identityHashCode(client13_2));
  }
}
/*
Are equal? true so client should be found in myClientPolicyMap
Found!
Override client13.hashCode(): 6
Override client13_2.hashCode(): 6
Original System.identityHashCode(client13): 1625635731
Original System.identityHashCode(client13_2): 1580066828
 */
