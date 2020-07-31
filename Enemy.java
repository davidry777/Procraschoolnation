
/**
 * Class meant to be used for creating enemys along your journey during the game.
 * Each enemy will have health, intelligence, regeneration, mental health, aSpeed,
 * and procastination stats
 * 
 * @author David Ryan
 * @version 1.0
 */
public class Enemy extends Stats
{
    
    /**
     * Default constructor for objects of class Enemy, with only passing the name
     * 
     * @param name  name of the enemy
     */
    public Enemy(String name)
    {
        super(name);
    }
    
    /**
     * Constructor for creating an enemy with only health and attack in mind
     * 
     * @param health    health of the enemy
     * @param attack    how much attack speed stat
     * @param name  name of the enemy
     */
    public Enemy(double health, double attack, String name)
    {
        super(health,0,0,0, attack,0, name);
    }
    
    /**
     * Constructor for Enemy
     * 
     * @param hp    health stat
     * @param intelligent intelligence stat
     * @param mhp   mental health stat
     * @param regen regeneration stat
     * @param aSpeed    attack speed stat
     * @param pro   procastination stat
     */
    public Enemy (double hp, int intelligent, double mhp, double regen, double aSpeed,
        double pro, String nam)
    {
        super(hp,intelligent,mhp,regen,aSpeed,pro, nam);
    }
    
    /**
     * Helper method used for attacking the friend with the amount of damage that
     * you would possibly perform. The method would then return the damage afflicted by 
     * your friend. It would help changed the health through the changeHealth method in 
     * the Chance class
     * 
     * @param firend the friendly that you want to attack
     * @param damage    dmamage that you want to inflict on your enemy
     * @return damAfflict   damage that has been afflicted on the enemy
     */
    public double attack(Friendly friend, int dam)
    {
        double damAfflict = (Math.random() * dam);  //random damage done between 0.0 and dam
        friend.getChance().changeHealth(dam, friend); //changes the health of the friend based on chance
        return damAfflict;  //returns damage which has been afflicted
    }
    
    /**
     * Returns the name of the enemy, and quickly identifies himeself as an enemy
     * @return string of the name of the friend
     */
    public String toString()
    {
        return super.toString();
    }
}
