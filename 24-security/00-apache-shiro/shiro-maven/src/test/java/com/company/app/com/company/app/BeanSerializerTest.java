package com.company.app.com.company.app;

import com.company.app.not.needed.Gatekeeper;
import com.company.app.secure.CustomBeanSerializerModifier;
import com.company.app.secure.Customer;
import com.company.app.secure.Views;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

public class BeanSerializerTest {

  static ObjectMapper mapper;

  @BeforeClass
  public static void initMapper() {
    mapper = new ObjectMapper();
    /* Setting the default view in jackson */
    mapper.setConfig(mapper.getSerializationConfig().withView(Views.Default.class));
    mapper.registerModule(new SimpleModule() {
      @Override
      public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new CustomBeanSerializerModifier());
      }
    });
    startShiro();
  }

  public static void simulate(String user, String password, Customer customer) {
    final Subject currentUser = SecurityUtils.getSubject();
    if (!currentUser.isAuthenticated()) {
      final UsernamePasswordToken token = new UsernamePasswordToken(user, password);
      try {
        currentUser.login(token);
        System.out.println("Authentication success");

        System.out.println("Object serialized using the default view: ");
        System.out.println("------------------------------------------");
        System.out.println(mapper.writeValueAsString(customer));/**/

        System.out.println();

        System.out.println("Object serialized using the secure view: ");
        System.out.println("------------------------------------------");
        System.out.println(mapper.writerWithView(Views.Secure.class).writeValueAsString(customer));
        currentUser.logout();
      } catch (Exception ex) {
        System.out.println("Get out of here " + user + "");
      }
    }
  }


  private Customer customer() {
    final Customer customer = new Customer();
    customer.setCustomerId(UUID.randomUUID().toString());
    customer.setResourceId(UUID.randomUUID().toString());
    customer.setVersion(1);
    customer.setFirstName("Ebenezer");
    customer.setLastName("Scrooge");
    customer.setPin("666");
    customer.setAlive(Boolean.FALSE);
    customer.setSsn("023-12-7194");
    customer.setEmail("escrooge@stingy.com");
    return customer;
  }

  @Test
  public void dummyTest() {
//    simulate("guest", "guest", customer());
//    simulate("c3", "c3", customer());
    simulate("intruder", "c3", customer());
  }

  public static void startShiro() {
    SecurityUtils.setSecurityManager(Gatekeeper.INSTANCE.securityManager());
  }
}
