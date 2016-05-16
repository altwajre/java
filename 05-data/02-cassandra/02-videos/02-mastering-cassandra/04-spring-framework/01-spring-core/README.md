# The Spring Core

## non dependency-injection version

- `non-spring` project

```
public interface Client {
	void saySomething();
}
public class Client1 implements Client {
	@Override
	public void saySomething(){
		System.out.println("This is Client1");
	}
}
public class Server {
	private Client client = new Client1();
	public void callClient(){
		client.saySomething();
	}
}
	public static void main(String[] args) {
		SpringApplication.run(NonSpringApplication.class, args);
		Server server = new Server();
		server.callClient();
	}
```

## dependency-injection version

- `spring-based` project

```
public interface Client {
	void saySomething();
}
@Component // concrete component of Client to be injected by spring, it tells spring to create an instance in the application context
public class Client1 implements Client {
	@Override
	public void saySomething(){
		System.out.println("This is Client1");
	}
}
@Component
public class Server {
	@Resource // dependency injection - inject Client1 by spring
	private Client client;
	public void callClient(){
		client.saySomething();
	}
}
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBasedApplication.class, args);
		Server server = ctx.getBean("server", Server.class);
		server.callClient();
		ctx.close();
	}
```

## spring mvc

http://docs.spring.io/autorepo/docs/spring/3.2.x/spring-framework-reference/html/mvc.html

