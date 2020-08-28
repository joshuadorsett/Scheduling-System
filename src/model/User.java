package model;

/**
 * creates an user object used to contain the data selected from the DB
 * @author joshuadorsett
 */
public class User {
    private int userId;
    private String userName;
    private String password;

    public User(int userId, String userName, String userPassword){
        this.userId = userId;
        this.userName = userName;
        this.password = userPassword;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }
}
