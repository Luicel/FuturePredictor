package me.calebclifton.database;

import me.calebclifton.models.Prediction;

import java.io.IOException;
import java.sql.*;

public class Database {
    private Connection connection;

    public void openConnection(){
        System.out.println("Opening MySQL connection...");

        String host = MySQLLogin.host;
        int port = Integer.parseInt(MySQLLogin.port);
        String database = MySQLLogin.database;
        String username = MySQLLogin.username;
        String password = MySQLLogin.password;

        try {
            synchronized(this) {
                if (getConnection() != null && !getConnection().isClosed()) return;
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&serverTimezone=UTC", username, password));
                System.out.println("MySQL connected!");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean doesNameExistInDatabase(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM predictions WHERE name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String result = resultSet.getString("name");
            statement.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public void insertPredictionIntoDatabase(String name, Prediction prediction) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT into predictions (name, overall_happiness, success_chance, stubbing_toe_chance, eating_raw_ground_beef_chance, reach_dreams_chance, cause_of_death, favorite_condiment, will_find_true_love, will_believe_prediction) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, prediction.getOverallHappiness());
            statement.setInt(3, prediction.getSuccessChance());
            statement.setInt(4, prediction.getStubbingToeChance());
            statement.setInt(5, prediction.getEatingRawGroundBeefChance());
            statement.setInt(6, prediction.getReachDreamsChance());
            statement.setString(7, prediction.getCauseOfDeath());
            statement.setString(8, prediction.getFavoriteCondiment());
            statement.setBoolean(9, prediction.willFindTrueLove());
            statement.setBoolean(10, prediction.willBelievePrediction());
            statement.execute();
            statement.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getIntFromDatabase(String name, String columnName) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM predictions WHERE name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(columnName);
            }
            statement.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getStringFromDatabase(String name, String columnName) {
        String result = "";
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM predictions WHERE name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(columnName);
            }
            statement.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean getBooleanFromDatabase(String name, String columnName) {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM predictions WHERE name=?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getBoolean(columnName);
            }
            statement.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
