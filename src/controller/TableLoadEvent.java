package controller;

import java.sql.SQLException;

public interface TableLoadEvent {
    void loadData() throws SQLException, ClassNotFoundException;
}
