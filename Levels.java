import java.util.*;
import chn.util.*;
import chn.util.*;
import java.util.*;
import chn.util.*;
import java.util.List;
/**
 * Class that adds the enemys from difficulty, from easy to hard
 *
 * @author David Ryan
 * @version 1.0
 */
public class Levels
{
    //private instance variables
    private Friendly user;
    private List<Enemy> listEnemies = new ArrayList<Enemy>();
    private ConsoleIO keyboard = new ConsoleIO();
    private Enemy temp;
    
    /**
     * Default constructor for Levels class
     */
    public Levels()
    {
        user = new Friendly();
        addEnemies();
        temp = listEnemies.get(0);
    }
    
    /**
     * Constructor for Levels class for passing the Friendly parameter
     * 
     * @param player    player that you want to initialize with
     */
    public Levels(Friendly player)
    {
        user = player;
        addEnemies();
        temp = listEnemies.get(0);
    }
    
    /**
     * Helper method that adds all the enemies
     */
    private void addEnemies()
    {
        addEasyEnemies();
        addMediumEnemies();
        addHardEnemies();
    }
    
    /**
     * Levels constructor in which the user passes the integer in which
     * it chooses which difficulty for the enemies
     * @param val   val passed by the user
     */
    public Levels(int val)
    {
        if(val == 1)
            addEasyEnemies();
        else
            if(val == 2)
                addMediumEnemies();
            else
                addHardEnemies();
        temp = listEnemies.get(0);
        user = new Friendly();
    }
    
    /**
     * adds the easy enemies to listEnemies list
     */
    private void addEasyEnemies()
    {
        listEnemies.add(new Enemy(5,6,"W.A.4.1 Simple I-O"));
        listEnemies.add(new Enemy(7,4,"W.A.5.1 Precedence"));
        listEnemies.add(new Enemy(4,7,"W.A.5.2 Math operators"));
        listEnemies.add(new Enemy(8,6,"W.A.6.1 Pizza Parlor"));
        listEnemies.add(new Enemy(9,3,"W.A.7.1 Methods"));
        listEnemies.add(new Enemy(12,9,"Lab 8.1 IRS"));
        listEnemies.add(new Enemy(10,10,"W.A.12.1 String Class"));
        listEnemies.add(new Enemy(15,12,"Lab 12.3 PigLatinator"));
        listEnemies.add(new Enemy(23,19,"Strings test"));
        listEnemies.add(new Enemy(8,7,"W.A.13.1 Inheritance"));
        listEnemies.add(new Enemy(38,40,"APCS1 Final Exam"));
    }
    
    /**
     * adds the medium enemies to listEnemies list
     */
    private void addMediumEnemies()
    {
        listEnemies.add(new Enemy(18,10,"Lab 18.2 Knights Tour"));
        listEnemies.add(new Enemy(12,8,"W.A.19.1 ArrayList"));
        listEnemies.add(new Enemy(21,6,"Elevens Lab"));
        listEnemies.add(new Enemy(30,17,"Elevens test"));
        listEnemies.add(new Enemy(15,9,"W.A.23.1 Order"));
        listEnemies.add(new Enemy(14,9,"W.A.24.1 MergeSort"));
        listEnemies.add(new Enemy(25,16,"Lab 22.1-24.1 Quadsorts"));
        listEnemies.add(new Enemy(12,12,"W.A.27.1 SeqSearch"));
        listEnemies.add(new Enemy(15,13,"W.A.28.1 Exceptions"));
        listEnemies.add(new Enemy(50,48,"APCS2 Final Exam"));
    }
    
    /**
     * adds the hard enemies to listEnemies list
     */
    private void addHardEnemies()
    {
        listEnemies.add(new Enemy(22,14,"Lab 30.1-31.1 Ordered List"));
        listEnemies.add(new Enemy(17,21,"W.A.33.1 Binary Trees"));
        listEnemies.add(new Enemy(19,19,"W.A.34.1 Tree Recursion"));
        listEnemies.add(new Enemy(65,148,"Linked-Lists Test"));
        listEnemies.add(new Enemy(32,21,"Lab 38.1 Hashing"));
        listEnemies.add(new Enemy(75,132,"Mock Exam 1"));
        listEnemies.add(new Enemy(40,125,"Lab 40.1 Heap Sort"));
        listEnemies.add(new Enemy(125,167,"Mock Exam 2"));
        listEnemies.add(new Enemy(50,42,"Final Project"));
        listEnemies.add(new Enemy(500,500,"COLLEGE BOARD PRESENTS: AP Computer Science A Exam"));
    }
    
    /**
     * gets the first enemy from the arraylist
     * @return the first enemy
     */
    public Enemy getEnemy()
    {
        return listEnemies.get(0);
    }
    
    /**
     * Returns the enemy that has been defeated
     * @return killed enemy
     */
    public Enemy getEnemies()
    {
        //String event = null;
        if(listEnemies != null)
        {
            if(!(listEnemies.get(0).getHealth() > 0))  
            {
                //If the enemy is dead, moves to the next in the list
                listEnemies.remove(0);
                temp = listEnemies.get(0);
            }
        }
        return temp;   
    }
    
    /**
     * Accessor method that returns the user
     * @return user
     */
    public Friendly getUser()
    {
        return user;
    }
}
