package org.example;

import java.sql.*;

public class Jdbcdemo {

    public static void main(String[] args) {
        System.out.println("JDBC DEMO!");
        // INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, 'Julian Santeler', 'julian.santeler@myimst.at'), (NULL, 'Riccardo Burger', 'riburger@myimst.at');

        selectAllDemo();
        insertStudentDemo("Name des Studenten", "Email@prova.at");
        selectAllDemo();
        updateStudentDemo(4, "Neuer Name", "neueemail@gmail.com");
        selectAllDemo();
        deleteStudentDemo(5);
        selectAllDemo();
        findAllByNameLike("er");

    }

    private static void findAllByNameLike(String pattern) {

        System.out.println("Find all by Name Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";

        try(Connection conn = DriverManager.getConnection(connectionUrl,user,pwd))
        {
            System.out.println("Verbindung zur DB hergestellt!");


            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student` WHERE `student`.`name` LIKE ?");
            preparedStatement.setString(1,"%" +pattern +"%");
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


    public static void deleteStudentDemo(int studentId){

        System.out.println("DELETE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";


        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `student` WHERE `student` . `id` = ?"
            );
            try {
                    preparedStatement.setInt(1,studentId);
                    int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: " + rowAffected);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-UPDATE Statemnet: " + ex.getMessage());
            }
        }catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }


    }

    public static void updateStudentDemo(int id, String neuerName, String neueEmail) {

        System.out.println("UPDATE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pwd = "1234";


        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email`= ?WHERE `student`.`id`= ?"
            );
            try {
                preparedStatement.setString(1,neuerName);
                preparedStatement.setString(2, neueEmail);
                preparedStatement.setInt(3, id);
                int affectedRows = preparedStatement.executeUpdate();
                System.out.println("Anzahl der aktualisierten Datensätze: " + affectedRows);
            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-UPDATE Statemnet: " + ex.getMessage());
            }
        }catch (SQLException e) {
                System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
            }


    }
    public static void insertStudentDemo(String name, String email) {

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

                preparedStatement.setString(1,name);
                preparedStatement.setString(2,email);
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
