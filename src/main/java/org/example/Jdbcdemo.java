package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbcdemo {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");
        // INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Julian Santeler', 'julian.santeler@myimst.at'), (NULL, 'Riccardo Burger', 'riburger@myimst.at');

        selectAllDemo();

    }

    //Verbindung mit DB aufbauen
    //Drivermanager url user und pw mitgeben
    public static void selectAllDemo(){

        System.out.println("Select Demo mit JDBC");
        String sqlSelectAllPersons = "SELECT * FROM `student`";
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";

        try(Connection conn = DriverManager.getConnection(connectionUrl,user,pwd))
        {
            System.out.println("Verbindung zur DB hergestellt!");
        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }
}