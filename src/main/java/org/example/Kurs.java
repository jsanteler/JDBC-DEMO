package org.example;

import java.sql.*;

public class Kurs {
    public static void main(String[] args) {


        selectAllDemo();
        System.out.println();
        insertKursDemo("POS",5);
        System.out.println();
        selectAllDemo();
        System.out.println();
        updateKursDemo(2,"Turnen",2);
        System.out.println();
        selectAllDemo();
        System.out.println();
        deleteKursDemo(6);
        System.out.println();
        findAllByNameLike("POS");

    }

    private static void findAllByNameLike(String pattern) {

        System.out.println("Find all by Name Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/datenpersistenz";
        String user = "root";
        String pwd = "1234";

        try(Connection conn = DriverManager.getConnection(connectionUrl,user,pwd))
        {



            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `kurs` WHERE `kurs`.`name` LIKE ?");
            preparedStatement.setString(1,"%" +pattern +"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stunden = rs.getInt("stunden");
                System.out.println("Kurs aus der DB: [ID] " + id + " [NAME] " + name + " [STUNDEN] " + stunden);
            }
        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }


    public static void deleteKursDemo(int kursId){

        System.out.println("DELETE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/datenpersistenz";
        String user = "root";
        String pwd = "1234";


        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `kurs` WHERE `kurs` . `id` = ?"
            );
            try {
                preparedStatement.setInt(1,kursId);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: " + rowAffected);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-UPDATE Statemnet: " + ex.getMessage());
            }
        }catch (SQLException e) {
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }


    }

    public static void updateKursDemo(int id, String neuerName, int neueStunden) {

        System.out.println("UPDATE Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/datenpersistenz";
        String user = "root";
        String pwd = "1234";


        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `kurs` SET `name` = ?, `stunden`= ? WHERE `kurs`.`id`= ?"
            );
            try {
                preparedStatement.setString(1,neuerName);
                preparedStatement.setInt(2, neueStunden);
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
    public static void insertKursDemo(String name, int stunden) {

        System.out.println("INSERT Demo mit JDBC");
        String connectionUrl = "jdbc:mysql://localhost:3306/datenpersistenz";
        String user = "root";
        String pwd = "1234";

        //connection in try block damit man connection nicht schließen muss
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, pwd)) {
            System.out.println("Verbindung zur DB hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `kurs` (`id`, `name`, `stunden`) VALUES (NULL, ?, ?)"
            );
            try {

                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,stunden);
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
        String connectionUrl = "jdbc:mysql://localhost:3306/datenpersistenz";
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

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `kurs`");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stunden = rs.getInt("stunden");
                System.out.println("Kurs aus der DB: [ID] " + id + " [NAME] " + name + " [STUNDEN] " + stunden);
            }
        } catch (SQLException e){
            System.out.println("Fehler beim Aufbau der Verbindung zur DB: " + e.getMessage());
        }
    }
}



