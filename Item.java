
import apcslib.*;


/**
 * Class for all the items in the shop
 *
 * @author Thomas Joel
 * @version 6/4/19
 */
public class Item
{
    //private instance variables
    private String myName;
    private double myCost;
    private int myNumOwned;
    private int indexInList;

    private final double UPGS;
    private final double PRICEINCREMENT;
    
    /**
     * Constructor for the Item class
     * @param name  name of the item
     * @param cost  cost of the item
     * @param   numOwned    quantity of items owned
     * @param   listIndex   the index of the item
     * @param   upgs    the upgrades
     * @param priceInc  the percent of the price increased
     */
    public Item(String name, double cost, int numOwned, int listIndex, double upgs, double priceInc)
    {
        myName = name;
        myCost = cost;
        myNumOwned = numOwned;
        indexInList = listIndex;

        UPGS = upgs;
        PRICEINCREMENT = priceInc;

    }
    
    /**
     * Helper method that updates the cost of the item
     * @param addCost
     */
    public void updateCost(double addCost)
    {
        myCost += addCost;
    }
    
    /**
     * Helper method that increments number of items owned
     */
    public void incNumOwned()
    {
        myNumOwned++;
    }
    
    /**
     * Accessor method that returns the value of cost
     * @return myCost
     */
    public double getCost()
    {
        return myCost;
    }
    
    /**
     * Accessor method that returns number of items owned
     * @return myNumOwned
     */
    public int getNumOwned()
    {
        return myNumOwned;
    }
    
    /**
     * Accessor method that returns the name of the item
     * @return myName
     */
    public String getName()
    {
        return myName;
    }
    
    /**
     * Accessor method that returns the index of the item in the list
     * @return indexInList
     */
    public int getIndexInList()
    {
        return indexInList;
    }
    
    /**
     * Accessor method that returns the upgrades
     * @return UPGS
     */
    public double getUPGS()
    {
        return UPGS;
    }
    
    /**
     * Accessor method that returns the price incremented
     * @return PRICEINCREMENT
     */
    public double getPriceInc()
    {
        return PRICEINCREMENT;

    }
    
    /**
     * Accessor method that returns the string of how much you own the item
     * @return string 
     */
    public String toString()
    {
        return Format.right(myNumOwned, 2) + "  |  " + myName;
    }
    
    /**
     * Lexicographically compares the two items to see if they are the same, and returns 0
     * if it is. More or less than 0 if it us not
     * @return 0 if equal, less or more than 0 if not
     */
    public int compareTo(Object other)
    {
        return this.getName().compareTo(((Item)other).getName());
    }
    
    /**
     * Checks if items are equal
     * @return true if equal. false otherwise
     */
    public boolean equals(Object other)
    {
        return compareTo(other) == 0;
    }
}
