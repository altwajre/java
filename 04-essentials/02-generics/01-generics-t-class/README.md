# get a class instance of generics type t

```
public class AbstractDao<T, ID extends Serializable> implements Dao<T, ID> {
  private Class<T> persistentClass;
  public AbstractDao() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }
```

run InfiniteFinancesSchema.sql in mysql before running App
