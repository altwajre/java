# async patterns

## github

> simondean vertx-async

https://github.com/simondean/vertx-async

## Patterns

> Series

```
  public void seriesExample(AsyncResultHandler<List<String>> handler) {
    Async.<String>series()
      .task(taskHandler -> {
        String result = getSomeResult();
        taskHandler.handle(DefaultAsyncResult.succeed(result));
      })
      .task(taskHandler -> {
        someAsyncMethodThatTakesAHandler(taskHandler);
      })
      .run(result -> {
        if (result.failed()) {
          handler.handle(DefaultAsyncResult.fail(result.cause()));
          return;
        }

        List<String> resultList = result.result();
        doSomethingWithTheResults(resultList);

        handler.handle(DefaultAsyncResult.succeed(resultList));
      });
  }
```

> Waterfall

```
  public void waterfallExample(AsyncResultHandler<Integer> handler) {
    Async.waterfall()
      .<String>task(taskHandler -> {
        String result = getSomeResult();
        taskHandler.handle(DefaultAsyncResult.succeed(result));
      })
      .<Integer>task((result, taskHandler) -> {
        someAsyncMethodThatTakesAResultAndHandler(result, taskHandler);
      })
      .run(result -> {
        if (result.failed()) {
          handler.handle(DefaultAsyncResult.fail(result.cause()));
          return;
        }

        Integer resultValue = result.result();
        doSomethingWithTheResults(resultValue);

        handler.handle(DefaultAsyncResult.succeed(resultValue));
      });
  }
```

> Each

```
  public void eachExample(AsyncResultHandler<Void> handler) {
    List<String> list = Arrays.asList("one", "two", "three");

    Async.iterable(list)
      .each((item, eachHandler) -> {
        doSomethingWithItem(item, eachHandler);
      })
      .run(vertx, handler);
  }
```

> Retry

```
  public void retryExample(AsyncResultHandler<String> handler) {
    Async.retry()
      .<String>task(taskHandler -> {
        someAsyncMethodThatTakesAHandler(taskHandler);
      })
      .times(5)
      .run(result -> {
        if (result.failed()) {
          handler.handle(DefaultAsyncResult.fail(result));
          return;
        }

        String resultValue = result.result();

        doSomethingWithTheResults(resultValue);

        handler.handle(DefaultAsyncResult.succeed(resultValue));
      });
  }
```

> Forever

```
  public void foreverExample(AsyncResultHandler<String> handler) {
    Async.forever()
      .task(taskHandler -> {
        someAsyncMethodThatTakesAHandler(taskHandler);
      })
      .run(vertx, result -> {
        handler.handle(DefaultAsyncResult.fail(result));
      });
  }
```
