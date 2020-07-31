
/**
 * Class that allows the Friend or Enemy to change it procrastination and 
 * health stats through possible luck
 *
 * @author Anthony Rubio
 * @version 1.0
 */
public class Chance
{
    /**
     * Helper method in which it would help decide how much procrastination should go down
     * depending on the reroll method from Stats class
     * 
     * @param person    person from Stats class that is going lose procrast. points
     */
    public void changeProcras(Stats person)
    {
        if(person.getHealth() == 20) //20 is max health
        {
            if((int)(Math.random() * 10 + 0.5) < 5)
                person.reroll(5);   //recieves random number from Stats class depending on above condition
        }
    }
    
    /**
     * Helper method in which it would help decide how much health should go down
     * depending on the reroll method from Stats class. Returns the amount of damage
     * inflicetd by the Friendly or Enemy
     * 
     * @param person    person from Stats class that is going lose procrast. points
     */
    public double changeHealth(int posDam, Stats person) //possible damage
    {
        int realDamAfflict;
        if(person.getLuck() > 6)    //if person's luck is greater than 6
        {
            /*then it sets the health to be decreased by any possible damage - 2*/
            person.setHealth(person.getHealth() - (posDam * 2));
            realDamAfflict = posDam * 2;
        }
        else
        {
            /*else, it sets the health to be decreased by 2*/
            person.setHealth(person.getHealth() - posDam);
            realDamAfflict = posDam;
        }
        person.reroll(6);
        if (person.getHealth() < 0)
                person.setLife(false);  //if person is dead, set his life to flase
        return realDamAfflict;  //returns dmg afflicted
    }
}
