package controller;

import java.sql.SQLException;

public interface DominationTableLoadEvent {
    void loadDataToTable() throws SQLException, ClassNotFoundException;
}
