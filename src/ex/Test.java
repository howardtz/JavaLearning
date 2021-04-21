package ex;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
