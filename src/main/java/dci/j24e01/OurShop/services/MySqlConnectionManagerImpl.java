package dci.j24e01.OurShop.services;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class MySqlConnectionManagerImpl implements DBConnectionManager {

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/OurShop",
                    "root",
                    "1q2w3e4r"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
