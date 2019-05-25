package com.wildcodeschool.myProjectWithDB.repositories;

import com.wildcodeschool.myProjectWithDB.entities.School;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchoolRepository {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "Musashi66.";

    public static List<School> selectByName(String name) {
        try(
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM school WHERE name LIKE ?"
                );
        ) {
            statement.setString(1, name);

            try(
                    ResultSet resulSet = statement.executeQuery();
            ) {
                List<School> schools = new ArrayList<School>();

                while(resulSet.next()){
                    int id = resulSet.getInt("id");
                    name = resulSet.getString("name");
                    int capacity = resulSet.getInt("capacity");
                    String country = resulSet.getString("country");
                    String graduate = resulSet.getString("graduate");


                    schools.add(new School(id, name, capacity, country, graduate));
                }

                return schools;
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }

    public static School selectById(int id) {
        try(
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM school WHERE id=?"
                );
        ) {
            statement.setInt(1, id);

            try(
                    ResultSet resulSet = statement.executeQuery();
            ) {
                if(resulSet.next()){
                    id = resulSet.getInt("id");
                    String name = resulSet.getString("name");
                    int capacity = resulSet.getInt("capacity");
                    String country = resulSet.getString("country");
                    String graduate = resulSet.getString("graduate");

                    return new School(id, name, capacity, country, graduate);
                }
                else {
                    throw new ResponseStatusException(
                            HttpStatus.INTERNAL_SERVER_ERROR, "unknown id in table school"
                    );
                }
            }
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }

    public static int update(
            int id,
            String name,
            int capcity,
            String country,
            String graduate
    ) {
        try(
                Connection connection = DriverManager.getConnection(
                        DB_URL, DB_USER, DB_PASSWORD
                );
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE school " +
                                "SET name=?, capcity=?, country=?, graduate=? " +
                                "WHERE id=?"
                );
        ) {
            statement.setString(1, name);
            statement.setInt(2, capcity);
            statement.setString(3, country);
            statement.setString(4, graduate);


            return statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "", e
            );
        }
    }
}
