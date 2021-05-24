package me.calebclifton.database;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MySQLLogin {
    public static String host, port, database, username, password;

    public static void init() {
        List<String> config;
        try {
            config = Files.readAllLines(Paths.get("mysql_login.txt"));
            for (String line : config) {
                String[] setting = line.split(": ");
                switch (setting[0]) {
                    case "host":
                        host = setting[1];
                        break;
                    case "port":
                        port = setting[1];
                        break;
                    case "database":
                        database = setting[1];
                        break;
                    case "username":
                        username = setting[1];
                        break;
                    case "password":
                        password = setting[1];
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
