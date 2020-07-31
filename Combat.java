import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.*;
import java.util.*;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.*;
import javafx.scene.Parent;
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.beans.binding.NumberExpressionBase;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ListView;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
/**
 * Combat class deals with setting up the layout of combat in the game, while
 * displaying certain values such as addMoney and combat options. Also features a delay in
 * timer. Returns money that is gonna be added after combat.
 *
 * @author Jason Giese and Thomas Joel
 * @version 1.0
 */
public class Combat
{
    //private instance variables
    private static double addMoney = 0;
    private static int timer = 5;

    private static double temp;

    private static final int vSceneSize = 400;
    private static final int hSceneSize = 650;
    
    /**
     * Static helper method that deals with handling user to enemy combat, while also
     * taking care of inventory and health
     * 
     * @param money how much money you have
     * @param list  List of items in inventory
     * @param hp    health points
     * @param atk   how much attack damage you do
     * @return addMoney money that would be added to your balance
     */
    public static double start(double money, Inventory list, int hp, int atk)
    {        
        ListView inv = new ListView(list.getItemList());
        //Timer for user attacks
        timer = 5;
        //Holder variable to keep track of how much money the user earns in combat
        addMoney = money;
        
        //level and user classes
        Levels lvl = new Levels();
        Friendly user = new Friendly(hp, atk,"User");
        
        //labels and buttons
        Label curBal = new Label("Money: " + (int)money);
        Button exit = new Button("EXIT");
        Button attack = new Button("ATTACK");
        
        //adding buttons to toolbar, creating text box with scrolling
        ToolBar toolBar = new ToolBar(attack, exit);
        Text eventText = new Text("LET THE BATTLE BEGIN!");
        ScrollPane scroller = new ScrollPane();

        //creating a new stage
        Stage combStart = new Stage();
        
        //displaying a sprite in the grahpics window
        ImageView displayer = new ImageView("/Sprites EXTRA/Actual Enemies/Hard1.png");
        
        //creating new GridPanes and Groups for window visuals
        GridPane masterPane = new GridPane();
        GridPane columnOne = new GridPane();
        Group graphics = new Group();      
        
        //creating a scene for the masterPane
        Scene scene = new Scene(masterPane, hSceneSize, vSceneSize);  
        
        //adding the imageview to the graphics
        graphics.getChildren().add(displayer);
        
        //setting the scale of the image
        displayer.setFitHeight(200);
        displayer.setFitWidth(180);

        //setting the width of the text box and giving it to the scroller
        eventText.setWrappingWidth(hSceneSize * 0.72);
        scroller.setContent(eventText);
        
        //combat window startup and title
        combStart.initModality(Modality.APPLICATION_MODAL);
        combStart.setTitle("Combat");

        //for testing purposes
        //masterPane.setGridLinesVisible(true);
        //columnOne.setGridLinesVisible(true);
        
        //Deals with user inputs for combat, timer of 5 seconds per attack        
        Timeline attackTimer = new Timeline(new KeyFrame(Duration.millis(1000), 
            e -> {
                //If the timer hasn't gone to 0, continues to decrement
                if(timer > 1)
                {
                    timer -= 1;
                    attack.setText("ATTACK:  " + timer + "s");
                }
                //Otherwise notifies the user their attack is available
                else
                {   
                    timer = 0;
                    attack.setText("ATTACK: RDY");
                }
            }));
        //Has the timer run continually while the user is on combat window
        attackTimer.setCycleCount(Animation.INDEFINITE);
        attackTimer.play();
        
        //deals with enemy attack timers
        Timeline enemyTimer = new Timeline(new KeyFrame(Duration.millis(4000), 
            e -> {
                temp = (lvl.getEnemy().getAttack());
                user.setHealth(user.getHealth() - temp);
                if(user.getHealth() <= 0)
                {
                    addMoney /= -2;
                    attackTimer.stop();
                    combStart.close();
                }
                else
                {
                    eventText.setText(eventText.getText() + "\n\n" + lvl.getEnemy().toString() + " attacked for "
                        + (int)temp + " HP! " + " Your hp is now: " + (int)user.getHealth() + "\n");
                    scroller.setVvalue(1.0);
                }
            }));
            //Starts enemyTimer
        enemyTimer.setCycleCount(Animation.INDEFINITE);
        enemyTimer.play();
        
        attack.setText("ATTACK:  " + timer + "s");
        attack.setOnAction(e -> {
                if(timer == 0) 
                {
                    //Gets the attack value from the user
                    temp = user.getAttack();
                    //If the enemy is dead, displays to user and goes to next enemy
                    if((lvl.getEnemy().getHealth() - temp) == 0)
                    {
                        lvl.getEnemy().setHealth(lvl.getEnemy().getHealth() - temp);
                        //Stops the decrementing of timer, and an issue where the timers stacked
                        eventText.setText(eventText.getText() + "\nEnemy died!");
                        scroller.setVvalue(1.0);
                        addMoney *= 1.25;
                        try
                        {
                            lvl.getEnemies();
                            eventText.setText(eventText.getText() + "\nNew enemy approaching!");
                            scroller.setVvalue(1.0);
                        }
                        catch(ArrayIndexOutOfBoundsException f)
                        {
                            eventText.setText(eventText.getText() + "\nYou finished the game!");
                            combStart.close();
                        }
                    }
                    //If enemy is still alive, displays text to user and resets timer
                    else
                    {
                        attack.setText("ATTACK:  " + timer + "s");
                        lvl.getEnemy().setHealth(lvl.getEnemy().getHealth() - temp);                    
                        eventText.setText(eventText.getText() + "\nYou attacked for " + (int)temp + " HP! " 
                            + "Enemy HP is now: " + (int)lvl.getEnemies().getHealth());
                        scroller.setVvalue(1.0);
                    }
                    timer = 5;
                }
            });
            
            //setting the action of the exit button to exit the window
        exit.setOnAction(e -> { 
            //Properly exits the window
            combStart.close();
        });
   
        //adding toolbar, buttons, and graphis to the gridPanes
        columnOne.getChildren().addAll(toolBar, curBal, graphics, scroller);
        masterPane.getChildren().addAll(columnOne, inv);
        
        //allocating slots of the GridPane for certain nodes
        columnOne.setConstraints(toolBar, 0, 0);
        columnOne.setConstraints(curBal, 0, 1);
        columnOne.setConstraints(graphics, 0, 2);
        columnOne.setConstraints(scroller, 0, 3);
        masterPane.setConstraints(columnOne, 0, 0);
        masterPane.setConstraints(inv, 1, 0);
        
        //setting up height and width constraints for the rows/columns of the Gridpane
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getRowConstraints().add(new RowConstraints(225));
        columnOne.getRowConstraints().add(new RowConstraints(123));
        columnOne.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.25));
        masterPane.getRowConstraints().add(new RowConstraints(vSceneSize));

        //window interaction
        combStart.setScene(scene);
        combStart.showAndWait();

        //returning the updated money
        return addMoney;
    }
}
