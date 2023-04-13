
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import daos.ItemDao;
import entities.Database;
import entities.Item;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        


        List<Item> itemlist;
        try(Connection connection = Database.getDatabaseConnection();
        Statement statement = connection.createStatement();){
            ItemDao itemDao = new ItemDao(connection);

            // Find all Method
            System.out.println("Printing Items...");
            itemlist = itemDao.findAll();
            
            for(Item item : itemlist)
            {
                System.out.println(item);
            }

            // Insert Method

            Item insertItem = new Item();
            insertItem.setId(5);
            insertItem.setName("Irn Bru");
            insertItem.setDesc("Pure sugary taste");

            itemDao.insert(insertItem);
            System.out.println("Item inserted with the id of 5 " + insertItem);



            // Find by ID 
            Item foundItem = itemDao.findById(5);
            System.out.println("Item Found with the id of 5 " + foundItem);
            
            
            
            // update

            Item updateItem = new Item();

            updateItem.setName("Coca Cola");
            updateItem.setId(5);

            itemDao.update(updateItem);
            System.out.println("Item updated with the id of 5 " + updateItem);
            
            
            // Delete 
            
            itemDao.delete(5);
            System.out.println("Item deleted with the id of 5 ");
             
            itemlist = itemDao.findAll();

            for(Item item : itemlist)
            {
                System.out.println(item);
            }
         
           
        }
        catch(Exception e)
        {
            System.err.println("Exception: " + e.getMessage());
        }
    
    }
}
