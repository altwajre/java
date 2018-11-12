import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;

@Data
public class Customer {
  @DiffIgnore
  private int id;
  private String name;
  private int age;
  private Address address;
}
