package org.example;

import java.sql.*;

public class Jdbcdemo {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");
        // INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Julian Santeler', 'julian.santeler@myimst.at'), (NULL, 'Riccardo Burger', 'riburger@myimst.at');

        selectAllDemo();
        insertStudentDemo();
        selectAllDemo();
        updateStudentDemo();
        selectAllDemo();

    }

    public static void updateStudentDemo() {

        System.out.println("UPDATE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";


        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email`= ?WHERE `student`.`id`=4"
            );
            try {
                preparedStatement.setString(1,"Hans Zimmer");
                preparedStatement.setString(2, "hanszimmer@gmail.com");
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: " + affectedRows);
            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-UPDATE Statemnet: " + ex.getMessage());
            }
        }catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }


    }
    public static void insertStudentDemo() {

        System.out.println("INSERT Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";

        //connection in try block damit man connection nicht schließen muss
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)"
            );
            try {

                preparedStatement.setString(1,"Ali Keskin");
                preparedStatement.setString(2,"ali.keskin@htlimst.at");
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(rowAffected+ "Datensätze eingefügt");
            } catch (SQLException ex){
                System.out.println("Fehler im SQL-INSERT Statemnet: " +ex.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }


    //Verbindung mit DB aufbauen
    //Drivermanager url user und pw mitgeben
    //try catch = weil sql exceptions auftreten können

    public static void selectAllDemo(){

        System.out.println("Select Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";

        try(Connection conn = DriverManager.getConnection(connectionUrl,user,pwd))
        {
            System.out.println("Verbindung zur DB hergestellt!");

            //prepared Statment wird in Variable gespeichert
            //executeQuery = führe diese Datenbankabfrage durch
            // rs wird mit einen Zeiger durchlaufen
            // next = bool rückgabewert
            //Nexter datensatz landet in rs variable
            // mit rs.getint Spaltenaufruf

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student aus der DB: [ID] " + id + " [NAME] " + name + " [EMAIL] " + email);
            }
        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }
}
