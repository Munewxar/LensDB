package dataManagment;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LensListInitializer {
    private final ObservableList<Lens> lensList = FXCollections.observableArrayList();

    private void receiveData(Connection connection) {
        String dataReceiveQuery = "select * from lens_table";

        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(dataReceiveQuery);

            while (resultSet.next()) {
                lensList.add(new Lens(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getString(6)));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void initialize(Connection connection) {
        receiveData(connection);
    }

    public ObservableList<Lens> getLensList() {
        return lensList;
    }
}
