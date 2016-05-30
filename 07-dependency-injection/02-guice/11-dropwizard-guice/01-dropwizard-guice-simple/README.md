# dropwizard guice

## Intellij Run App

> Right click ServerApplication.main(), select run

> go to http://localhost:8080/hello

## @Named() annotation

```
// annotating the constructor with @Inject isn't enough. Guice would see three int(s) it doesn't know how to inject.
// We must distinguish them somehow. @Named annotation is one way to do this:
public class HelloResource {
    private final String message;
    private final String name;

    @Inject
    public HelloResource(
            @Named("message") String message,
            @Named("name") String name) {
        this.message = message;
        this.name = name;
    }
```
