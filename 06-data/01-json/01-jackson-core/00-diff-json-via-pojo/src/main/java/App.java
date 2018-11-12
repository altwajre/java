import com.fasterxml.jackson.databind.JsonNode;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

public class App {
  public static void main( String[] args )
  {
    Javers javers = JaversBuilder.javers().build();

    System.out.println("# Compare json: does not work because no ignore");
    JsonNode json1 = ResourceHelper.get("data/customer.json");
    JsonNode json2 = ResourceHelper.get("data/customer2.json");

    Diff diffJson = javers.compare(json1, json2);
    System.out.println(diffJson);
    System.out.println(diffJson.hasChanges());

    System.out.println("\n# Compare pojo: @DiffIgnore works!");
    Customer customer1 = JsonHelper.toPojo(json1, Customer.class);
    Customer customer2 = JsonHelper.toPojo(json2, Customer.class);

    Diff diffPojo = javers.compare(customer1, customer2);
    System.out.println(diffPojo);
    System.out.println(diffPojo.hasChanges());
  }
}
