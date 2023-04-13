package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Item;

public class ItemDao implements Dao<Item, Integer>{
    
    Connection connection;

    // creates a connection to the database to the object

    public ItemDao(Connection connection)
    {
        this.connection = connection;
    }

 // returns the data from the items table
    public List<Item> findAll(){
        List<Item> items = new ArrayList<Item>();
        try(Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery("SELECT * FROM item");
            while(result.next()){
                Item item = new Item();
                item.setId(result.getInt("id"));
                item.setName(result.getString("name"));
                item.setDesc(result.getString("description"));
                items.add(item);
            }
        }
        catch (SQLException e) {
           e.printStackTrace();;
        }

        return  items;
    }
// returns specified data from the items table
    public Item findById(Integer pk)
    {
        Item item = new Item();
        String select = "SELECT * FROM item WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(select)){
            
            ps.setInt(1, pk);
            ResultSet result = ps.executeQuery();
          
            if(result.next()){
               
                item.setId(result.getInt("id"));
                item.setName(result.getString("name"));
                item.setDesc(result.getString("description"));
                
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return  item;
    }
// inserts new data to the items table
    public void insert(Item item)
    {
        try(Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO item VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDesc());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                item.setId(keys.getInt(1));
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    //updates a specific row in the items table
    public Boolean update(Item item)
    {
        Boolean success = true;
        String update = "UPDATE item SET name = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(update))
        {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }

        return success;
    }
// deletes a specified entry from the items table
    public Boolean delete(Integer pk)
    {
        Boolean success = false;
        String delete = "DELETE FROM item WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(delete))
        {
            ps.setInt(1, pk);
            success = true;
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }

        return success;
    }
}
