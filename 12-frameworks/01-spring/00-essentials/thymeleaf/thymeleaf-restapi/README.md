# thymeleaf restapi

User.java

```
@Data
@AllArgsConstructor
public class User {
  private int id;
  private String userName;
  private String phone;
}
```

UserController.java

```
@RestController
public class UserController {

  private Map<Integer, User> users = new LinkedHashMap<>();

  public UserController() {
    createSomeData();
  }

  @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public void create(@RequestBody User user) {
    int id = user.getId();
    users.put(id, user);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody User user) {
    int id = user.getId();
    User currentUser = users.get(id);

    currentUser.setUserName(user.getUserName());
    currentUser.setPhone(user.getPhone());
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<User> findOne(@PathVariable("id") int id) {
    User user = users.get(id);
    HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<User>(user, status);
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Iterable<User>> findAll() {
    Iterable<User> currentUsers = this.users.values();
    HttpStatus status = currentUsers != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Iterable<User>>(currentUsers, status);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") int id) {
    users.remove(id);
  }

  private void createSomeData() {
    User tom = new User(1, "Tom", "123");
    users.put(tom.getId(), tom);

    User harry = new User(2, "Harry", "789");
    users.put(harry.getId(), harry);
  }

}
```

## Test

http://localhost:8080/findAll
