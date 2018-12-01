import com.fasterxml.jackson.databind.JsonNode;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main( String[] args )
  {

    LOGGER.info("#INFO");
    LOGGER.debug("#DEBUG");
//    customerToJson();

    compare();
  }

  private static void customerToJson() {
    Address address = new Address();
    address.setId(11);
    address.setName("Pine st");
    address.setZip("89576");
    Customer customer = new Customer();
    customer.setId(1);
    customer.setName("Tom");
    customer.setAge(18);
    customer.setAddress(address);

    JsonNode customerJson = JsonHelper.toJson(customer);
    System.out.println(customerJson);
  }

  private static void compare() {
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
/*
# Compare json: does not work because no ignore
Diff:
1. ValueChange{globalId:'com.fasterxml.jackson.databind.node.ObjectNode/#_children/id', property:'_value', oldVal:'1', newVal:'2'}

true

# Compare pojo: @DiffIgnore works!
Diff:

false
 */
