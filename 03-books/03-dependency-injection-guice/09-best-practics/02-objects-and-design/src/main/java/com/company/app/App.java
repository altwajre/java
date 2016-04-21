package com.company.app;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import javax.swing.text.html.HTML;

/*
One thing that’s clear to use from these three operations is that UserDao and UserManager are services. The common
thread that runs through them is that they perform operations on instances of the User class, which is data. Clearly,
UserDao and UserManager are appropriate to be constructed and tested with DI. Class User, on the other hand, is
constructed often at the presentation layer and has no dependencies.
 */
class User{
    private long userId;
    private String name;
    private String password;
    private boolean active = true;
    public User(long userId, String name){
        this.userId = userId;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isActive(){
        return active;
    }
    public void deactivate(){
        this.active = false;
    }
}
class UserDao{
    public void save(User user){}
    public User read(long userId){return null;}
}
/*
UserManager is a singleton service that's shared by all instances of the NewUserPage and is called at various data
points for purposes like creating users, deactivating their accounts, or changing their details.
 */
class UserManager{
    final UserDao userDao;
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }
    public void createNewUser(User user){
        validate(user);
        userDao.save(user);
    }
    public void updatePassword(long userId, String password){
        User user = userDao.read(userId);
        user.setPassword(password);
        validate(user);
        userDao.save(user);
    }
    public void deactivate(long userId){
        User user = userDao.read(userId);
        user.deactivate();
        userDao.save(user);
    }
    void validate(User user){}
}
// http://google.github.io/guice/api-docs/latest/javadoc/index.html?com/google/inject/servlet/RequestScoped.html
// Apply this to implementation classes when you want one instance per request.
@RequestScoped
class NewUserPage{
    final UserManager userManager;
    @Inject
    public NewUserPage(UserManager userManager) {
        this.userManager = userManager;
    }
    public HTML registerNewUser(String name){
        userManager.createNewUser(new User(nextUserId(), name));
        return new HTML();
    }
    long nextUserId(){return 0;}
}
public class App {
}
