
/**
 * Class meant to be used for creating friends along your journey during the game.
 * Each friend will have health, intelligence, regeneration, mental health, typespeed,
 * and procastination stats
 *
 * @author Anthony Rubio
 * @version 1
 */
public class Friendly extends Stats
{
    /**
     * Default constructor for Friendly Class
     */
    public Friendly()
    {
        super();
    }
    /**
     * Constructor for objects of class Friendly, only when passing name parameter
     * 
     * @param name  name of the players friend
     */
    public Friendly(double health, double attack, String name)
    {
        super(health,0,0,0, attack,0, name);
    }
    
    /**
     * Constructor for Friendly for perhaps customization for you friends
     * 
     * @param hp    health stat
     * @param intelligent intelligence stat
     * @param mhp   mental health stat
     * @param regen regeneration stat
     * @param tSpeed    type speed stat
     * @param pro   procastination stat
     * @param name  name of the players friend
     * 
     */
    public Friendly (double hp, int intelligent, double mhp, double regen, double attack,
        double pro, String nam)
    {
        super(hp,intelligent,mhp,regen,attack,pro, nam);
    }
    
    /**
     * Helper method used for attacking the enemy with the amount of damage that
     * you would possibly perform. The method would then return the damage afflicted by 
     * your friend. It would help changed the health through the changeHealth method in 
     * the Chance class
     * 
     * @param fiend enemy that you want to attack
     * @param damage    dmamage that you want to inflict on your enemy
     * @return damAfflict   damage that has been afflicted on the enemy
     */
    public double attack(Enemy fiend, int dam)
    {
        double damAfflict = (Math.random() * dam);  //random damage done between 0.0 and dam
        Chance temp = fiend.getChance();    //returns the chance variable
        
        temp.changeHealth(dam, fiend);  //changes the health done to fiend
        return damAfflict;  //returns damaged afflicted
    }
    
    /**
     * Returns the name of the friend, and quickly identifies himeself as a friend
     * @return string of the name of the friend
     */
    public String toString()
    {
        return "My name is " + super.toString() + " and I am a friend";
    }
}
