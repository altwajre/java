# Method Chaining

https://en.wikipedia.org/wiki/Method_chaining

Try NOT to Use it due to following

## Train Wreck

`Train wreck` is violation of `Tell Don't Ask` and `Law of Demeter`

```
o.getX()
  .getY()
    .getZ()
      .doSomething();
```

> Tell Don't Ask

Tell the object what to do, but not to ask the object for its state.

> Law of Demeter

Talk to friends, not to strangers
