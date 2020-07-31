    
    /**
    * Parent class for all the other stat subclasses
    *
    * @author (Anthony Rubio)
    * @version (28 May 2019)
    */
    public class Stats
    {
    protected int[] statistic = {0,1,2,3,4,5,6}; //this lists out each statistic for reroll;
    protected Chance chance = new Chance();
    protected boolean alive = true;
    protected double health; //max for health will be 20
                         //lowest for health will be 1
    protected int intel;  //intellegent max bound will be 20
                      //lowest will be 0
    protected double mhp; //mental health marker
                      //lowest will be 0 and highest will be 20
    protected double regen; //regeneration rate
                        //lowest will be 0.5
                        //highest will be 10
    protected double attack; //typing speed
                       //lowest will be 1
                       //highest will be 10
    protected double procras; //procrastination levels
                          //lowest will be 0
                          //highest will be Double.MAX_VALUE
    protected int luck = (int)(Math.random()* 10 + 0.5);
    protected String name;
    
    /**
     * Default constructor of Stats class
     */
    public Stats()
    {
        health = 20;
        intel = (int)(Math.random() * 20) + 1;
        mhp = (Math.random() * 20) + 1;
        regen = (Math.random() * 10) + 0.5;
        attack = (Math.random() * 10) + 1;
        procras = (Math.random() * Double.MAX_VALUE);
        name = "Jeff";
    }
    
     /**
     * Default constructor of Stats class
     */
    public Stats(String myName)
    {
        health = 20;
        intel = (int)(Math.random() * 20) + 1;
        mhp = (Math.random() * 20) + 1;
        regen = (Math.random() * 10) + 0.5;
        attack = (Math.random() * 10) + 1;
        procras = (Math.random() * Double.MAX_VALUE);
        name = myName;
    }
    
    /**
     * Constructor for stats for perhaps customization
     * 
     * @param hp    health stat
     * @param intelligent intelligence stat
     * @param mhp   mental health stat
     * @param regen regeneration stat
     * @param speed speed stat
     * @param pro   procastination stat
     */
    public Stats(double hp, int intelligent, double mhp, double regen, double atk,
        double pro, String nam)
    {
        health = hp;
        intel = intelligent;
        mhp = mhp;
        regen = regen;
        attack = atk;
        procras = pro;
        name = nam;
    }
    
    /**
     * This is a work in progress for the random rollinator
     */
    public void reroll()
    {
        int chance = (int)(Math.random() * 6 + 0.5);
        reroll(chance);
        
    }
    
    /**
     * Rerolls any part of stat in Stats class depending on what being passed
     * @param statt     stat to be randomized again
     */
    public void reroll(int statt)
    {
        if(statt == 0)
        {
            health = (Math.random() * 20);  //health stat
        }
        else
            if(statt == 1)
            {
                intel = (int)(Math.random() * 20);  //intelligence stat
            }
            else
                if(statt == 2)
                {
                    mhp = (Math.random() * 20); //mental health stat
                }
                else
                    if(statt == 3)
                    {
                        regen = (Math.random() * 20);   //regeneration stat
                    }
                    else
                        if(statt == 4)
                        {
                            attack = (Math.random() * 10);  //attack speed stat
                        }
                        else
                            if(statt == 5)
                            {
                                procras = (Math.random() * Double.MAX_VALUE);   //procrastination stat
                            }
                            else
                                if(statt == 6)
                                    luck = (int)(Math.random()* 10 + 0.5);  //luck stat
        
    }
    
    /**
     * Helper method that sets the new health
     * @param hp
     */
    public void setHealth(double hp)
    {
        health = hp;
    }
    
    /**
     * Helper method that sets the new intelligence
     * @param inte
     */
    public void setInt(int inte)
    {
        intel = inte;
    }
    
    /**
     * Helper method that sets the new mental health
     * @param mhp
     */
    public void setmhp(double mph)
    {
        mhp = mph;
    }
    
    /**
     * Helper method that sets the new regeneration
     * @param rege
     */
    public void setregen(double rege)
    {
        regen = rege;
    }
    
    /**
     * Helper method that sets the new Attack
     * @param atk
     */
    public void setAttack(double atk)
    {
        attack = atk;
    }
    
    /**
     * Helper method that sets the new procrastination
     * @param procra
     */
    public void setProcras(double procra)
    {
        procras = procras;
    }
    
    /**
     * Helper method that sets the new life from between living or dead
     * @param live
     */
    public void setLife(boolean live)
    {
        alive = live;
    }
    
    /**
     * Accessor method that returns the health
     * @return health
     */
    public double getHealth()
    {
        return health;
    }
    
    /**
     * Accessor method that returns the intelligence
     * @return intel
     */
    public int getInt()
    {
        return intel;
    }
    
    /**
     * Accessor method that returns the mhp
     * @return mhp
     */
    public double getMhp()
    {
        return mhp;
    }
    
    /**
     * Accessor method that returns the regen
     * @return regen
     */
    public double getRegen()
    {
        return regen;
    }
    
    /**
     * Accessor method that returns the attack
     * @return attack
     */
    public double getAttack()
    {
        return attack;
    }
    
    /**
     * Accessor method that returns the procrastination
     * @return procras
     */
    public double getProcras()
    {
        return procras;
    }
    
    /**
     * Accessor method that returns the luck
     * @return luck
     */
    public int getLuck()
    {
        return luck;
    }
    
    /**
     * Accessor method that returns the life status
     * @return life
     */
    public boolean getLife()
    {
        return alive;
    }
    /**
     * Accessor method that returns the Chance object
     * @return health
     */
    public Chance getChance()
    {
        return chance;
    }
    /**
     * Accessor method that returns the name
     * @return name
     */
    public String toString()
    {
        return name;
    }
}
