import java.util.*;
import javafx.collections.*;

/**
 * Class deals with inventory of items in the game, such as while loops, for loops
 * and recursive methods
 *
 * @author Thomas Joel
 * @version 1.0
 */
public class Inventory
{
    //private instance variable
    private ObservableList<Item> inventory;
    
    /**
     * Default constructor for Inventory class
     */
    public Inventory()
    {
        inventory = FXCollections.observableArrayList();

        inventory.add(new Item("While Loop", 40, 0, 0, 0.05, 1.05));
        inventory.add(new Item("For Loop", 100, 0, 1, 0.075, 1.075));
        inventory.add(new Item("Recursive Method", 1000, 0, 2, 0.15, 1.1));


    }
    
    /**
     * Returns the item given the index
     * @param index location of where the item is located in the list
     * @return the value of item based on location
     */
    public Item getItem(int index)
    {
        return inventory.get(index);
    }
    
    /**
     * Replaces an item in inventory with the pased item
     * 
     * @param Item item to replace the item at the index
     * @param Index location of item to be replaced
     */
    public void setItem(Item item, int index)
    {
        inventory.set(index, item);
    }
    
    /**
     * Returns the Inventory list
     * @return inventory    List that contains all the items
     */
    public ObservableList<Item> getItemList()
    {
        return inventory;
    }
}
