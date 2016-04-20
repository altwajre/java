# Type Checked Annotation

## @Named() annotation

```
    // annotating the constructor with @Inject isn't enough. Guice would see three int(s) it doesn't know how to inject.
    // We must distinguish them somehow. @Named annotation is one way to do this:
    @Inject
    public Box(@Named("width") final int width, @Named("height") int height, @Named("depth") int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
```

