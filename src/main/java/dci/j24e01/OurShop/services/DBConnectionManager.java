package dci.j24e01.OurShop.services;

import java.sql.Connection;

public interface DBConnectionManager {
    Connection getConnection();
}
