# Throw exceptions appropariate to the abstraction

## Exception Translation

> higher layers should catch lower-level exceptions, throw exceptions can be explained in higher-level abstraction.

```
// Exception Translation
try {
  // Use lower-level abstraction to do our bidding
  ...
} catch(LowerLevelException e) {
  throw new HigherLevelException(...);
}
```


