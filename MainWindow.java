import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.*;
import javafx.util.Duration;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ListView;
import javafx.collections.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
/**
 * Driver for user interaction with game windows
 * 
 * @Jason G
 * @5/28/19
 */
public class MainWindow extends Application
{
    //Instance variables for everything
    Button incMoney, combat, exit, shop;
    
    //Variables for user interaction
    double money = 0, upgs = 0, cursUpgs = 0;
    int procrastination = 0;
    int hp = 30;
    int attack = 5;
    double[] retSet = new double[5];
    boolean result;
    Stage window;
    
    //Window sizes
    private final int vSceneSize = 500;
    private final int hSceneSize = 625;
    
    /**
     * Launches the program
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    /**
     * Start of program
     * @param primaryStage  Stage class being used in program
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Temp change for merging the two thingies
        //Initialization of objects
        Label curBal = new Label("Money: " + money);
        Label userHP = new Label("HP: " + hp);
        Label userATK = new Label("ATK: " + attack);
        Label procr = new Label("Procrastination 'score': " + procrastination);
        VBox userInfo = new VBox(20);
        
        //Sets up the user info Vbox
        userInfo.getChildren().addAll(curBal, userHP, userATK, procr);
        
        //Setup for window
        GridPane masterPane = new GridPane();
        GridPane columnOne = new GridPane();
        primaryStage.setTitle("Procraschoolnation");
        
        //Button setups
        incMoney = new Button("MONEY++");
        combat = new Button("BATTLE");
        exit = new Button("EXIT GAME");
        shop = new Button("SHOP");
        
        //Additional window layouts
        ToolBar toolBar = new ToolBar(incMoney, combat, shop, exit);
        Inventory inv = new Inventory();
        ListView listView = new ListView(inv.getItemList());
        
        //creating a new Scene with the masterPane
        Scene scene = new Scene(masterPane, hSceneSize, vSceneSize);
        
        //setting window to primaryStage
        window = primaryStage;
        
        //Setting the continual generation of money
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), 
            ae -> curBal.setText("Money: " + (int)(money += upgs))));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        //Setting up a "points" counter
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(1000), 
            ae -> procr.setText("Procrastination 'score': " + procrastination++)));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
        
        //for testing purposes
        //masterPane.setGridLinesVisible(true);
        //columnOne.setGridLinesVisible(true);
        
        //Setting up the text and action of each button
        incMoney.setOnAction(e -> {
            money += 1 + cursUpgs;
            curBal.setText("Money: " + (int)money);
        });
        
        //Starts combat window
        combat.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Increases the balance of money 
             * @param ActionEvent e
             */
            @Override
            public void handle(ActionEvent e)
            {
                money += Combat.start(money, inv, hp, attack);
            }
        });
        
        //Closes game
        exit.setOnAction(e -> closeGame());
        
        //Goes to the shop, returning values necessary to update user data
        shop.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Used for opening the shop window
             * @param ActionEvent e
             */
            @Override
            public void handle(ActionEvent e)
            {
                //The settings that need to be updated
                retSet = Shop.shopWindow(money, inv);
                //Money related updates
                money = retSet[0];
                curBal.setText("Money: " + money);
                upgs = retSet[1];
                cursUpgs = retSet[2];
                //User stat related updates
                hp = (int)retSet[3];
                attack = (int)retSet[4];
                //HP AND ATTACK
                userHP.setText("HP: " + hp);
                userATK.setText("ATK: " + attack);
            }
        });
        
        //Setup for window display
        columnOne.getChildren().addAll(toolBar, userInfo);
        masterPane.getChildren().addAll(columnOne, listView);
        
        //allocating slots of columnOne and masterPane for certain nodes
        columnOne.setConstraints(toolBar, 0, 0);
        columnOne.setConstraints(userInfo, 0, 1);
        masterPane.setConstraints(columnOne, 0, 0);
        masterPane.setConstraints(listView, 1, 0);
        
        //setting the size of the rows and columns of columnOne and masterPane
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.25));
        masterPane.getRowConstraints().add(new RowConstraints(vSceneSize));
        
        //Startup for window viewing
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Exits the game and closes the program
     */
    private void closeGame()
    {
        Boolean response = true;
        
        //checking if the user wants to exit the game
        response = ExitBox.display("Exit", "Are you sure?");
        if(response)
            //closes the window
            window.close();
    }
}
