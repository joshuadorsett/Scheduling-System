package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import utilities.MakeConnection;
import utilities.MakePreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import static utilities.MakePreparedStatement.getPreparedStatement;
import static utilities.MakePreparedStatement.makePreparedStatement;

/**
 *
 * @author joshuadorsett
 */
public class UserDaoImpl {
    /**
     * create User object and convert it into SQL code and add it to database
     */
    public void addUser(){

    }

    /**
     * get all User objects from database
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        Connection connection = MakeConnection.getConnection();
        String selectStatement = "Select * FROM U07nke.user";
        makePreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            int userId = resultSet.getInt("userId");
            String userName = resultSet.getString("userName");
            String password = resultSet.getString("password");

            User user = new User(userId, userName, password);
            allUsers.add(user);
            System.out.println(userId + " | " + userName + " | " + password + " ");
        }
        return allUsers;
    }

    /**
     * modify User object and convert it into SQl code and update database
     */
    public void modifyUser() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        // make the sql code with ? for each value that is variable. this index starts at 1 and skips if a value is entered.
        String updateStatement = "UPDATE country SET country = ?, createdBy = ? WHERE country = ?";

        //create prepared statement
        MakePreparedStatement.makePreparedStatement(connection, updateStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();

        // make variables for ps
        String countryName, newCountry, createdBy;

        // keyboard input
        Scanner keyboard = new Scanner(System.in);
        //promt one enter country for filter
        System.out.print("enter a country to update! ");
        countryName = keyboard.nextLine();
        // prompt for new country
        System.out.print("enter new country! ");
        newCountry = keyboard.nextLine();
        //prompt for who created it
        System.out.print("enter user name! ");
        createdBy = keyboard.nextLine();

        // key-value mapping
        ps.setString(1, newCountry);
        ps.setString(2, createdBy);
        ps.setString(3, countryName);

        //execute statement
        ps.execute();

        if (ps.getUpdateCount() > 0 ){
            System.out.println(ps.getUpdateCount() + " rows updated.");
        } else {
            System.out.println("no rows updated");
        }

    }
    /**
     * delete User object from database
     */
    public void deleteUser() throws SQLException {
        Connection connection = MakeConnection.getConnection(); //get reference to connection object

        // make the sql code with ? for each value that is variable. this index starts at 1 and skips if a value is entered.
        String deleteStatement = "DELETE FROM country WHERE country = ?";

        //create prepared statement
        MakePreparedStatement.makePreparedStatement(connection, deleteStatement); //create statement object
        PreparedStatement ps = MakePreparedStatement.getPreparedStatement();

        // make variables for ps
        String countryName;

        // keyboard input
        Scanner keyboard = new Scanner(System.in);

        System.out.print("enter a country to delete! ");
        countryName = keyboard.nextLine();

        // key-value mapping
        ps.setString(1, countryName);


        //execute statement
        ps.execute();

        if (ps.getUpdateCount() > 0 ){
            System.out.println(ps.getUpdateCount() + " rows deleted.");
        } else {
            System.out.println("no rows deleted");
        }
    }
}
