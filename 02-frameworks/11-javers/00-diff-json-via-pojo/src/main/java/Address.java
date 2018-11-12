import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;

@Data
public class Address {
  @DiffIgnore
  private int id;
  private String name;
  private String zip;
}
