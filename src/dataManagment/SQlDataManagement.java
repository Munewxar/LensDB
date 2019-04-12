package dataManagment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQlDataManagement {
    private final Connection connection;

    public SQlDataManagement(Connection connection){
        this.connection = connection;
    }

    public void deleteItem(int index){
        String deleteQuery = "delete from lens_table where id = " + "'" + index + "' ";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertItem(String name, int price, int focusDistance, String suitableForCanon,
                           String suitableForNikon){
        String insertQuery = "insert lens_table (lens_name, price, focus_distance, suitable_for_canon, suitable_for_nikon)" +
               " values (" + "'" + name + "'" + ", " + price + ", " + focusDistance + ", " + "'" + suitableForCanon + "'" + ", " +
                "'" + suitableForNikon + "'" + ")";
        System.out.println("ok");
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lens getItem(String name){
        String getItemQuery = "SELECT * FROM lens_table WHERE lens_name = " + "'" + name + "'";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getItemQuery);
            if(resultSet.next()) {
                return new Lens(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
