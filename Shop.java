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
import javafx.stage.Stage;
import javafx.animation.*;
import java.util.*;
import javafx.util.Duration;
import javafx.scene.control.ToolBar;
import javafx.collections.*;
import javafx.scene.Group;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;

/**
 * Deal with giving the player the ability to buy items and use them to produce
 * more money in the game
 *
 * @author Jason Giese and Thomas Joel
 * @version 1.0
 */
public class Shop
{
    //slot [0] is for the balance, [1] is for the upgrades on increment, 
    //  [2] is upgrades on cursor [3] is HP upgrades [4] is ATTACK upgrades
    private static double[] ret = new double[5];
    private static final int vSceneSize = 400;
    private static final int hSceneSize = 650;
    private static int hpCost = 50;
    private static int atkCost = 100; 
    private static int cursCost = 100;
    
    public static double[] shopWindow(double money, Inventory list)
    {
        //Base stats for user
        ret[2] = 0;
        ret[3] = 30;
        ret[4] = 5;
        
        //display for window
        ListView inv = new ListView(list.getItemList());
        List regList = list.getItemList();
        List buttonList = new ArrayList();
        List labelList = new ArrayList();
        
        //Button to leave window
        Button exit = new Button("EXIT");
        //Keeps track of user balance
        Label curBal = new Label("Money: " + (int)ret[0]);
        
        //Temporary nodes for inputting and setting up shop buttons
        Item tempItem;
        Button tempButton;
        Label tempLabel;
        Text eventText = new Text("WELCOME TO THE SHOP!");
        ScrollPane scroller = new ScrollPane();
        
        ToolBar toolBar = new ToolBar(exit);
        
        //Window setup 
        GridPane masterPane = new GridPane();
        GridPane columnOne = new GridPane();
        GridPane dualSection = new GridPane();
        VBox shopCatalogue = new VBox(10);
        VBox shopCatalogue2 = new VBox(10);
        //Button setups
        Button hpUpg = new Button("HP++");
        Button atkUpg = new Button("ATK++");
        Button cursorUpg = new Button("Cursor");
        //Label setups
        Label cursReq = new Label("Cost: " + cursCost + " Cursor val: " + (int)ret[2] + 1);
        Label hpReq = new Label("5+ HP Cost: " + hpCost);
        Label atkReq = new Label("1+ ATK Cost: " + atkCost);
        //Puts everything into a vbox
        shopCatalogue2.getChildren().addAll(hpUpg, hpReq, atkUpg, atkReq, cursorUpg, cursReq);
        
        //Setup for window display
        Scene scene = new Scene(masterPane, hSceneSize, vSceneSize);

        Stage window = new Stage();
        
        eventText.setWrappingWidth(hSceneSize * 0.72);
        scroller.setContent(eventText);
        
        //Closing the shop window
        exit.setOnAction(e -> { 
            window.close();
        });
        
        //masterPane.setGridLinesVisible(true);
        //columnOne.setGridLinesVisible(true);
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Shop");
        
         //Takes the info from the mainwindow
        ret[0] = money;
        
        
        //Functionally the same as the resource incrementation in main
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), 
            ae -> curBal.setText("Money: " + (int)(ret[0] += ret[1]))));
        timeline.setCycleCount(Animation.INDEFINITE);        
        timeline.play();
        
        //repeating while there are objects in the inventory list
        for (Object x: regList)
        {
            //getting the Item button name/label
            tempItem = (Item)x;
            tempButton = new Button("Purchase " + tempItem.getName());
            tempLabel = new Label("Cost: " + (int)tempItem.getCost() + "   Amount owned: " + tempItem.getNumOwned());
            buttonList.add(tempButton);
            
            //adding the buttons/labels to the vbox
            shopCatalogue.getChildren().addAll(tempButton, tempLabel);
            labelList.add(tempLabel);
           
            //assigning actions to each button
            tempButton.setOnAction(e -> {
                if(ret[0] >= ((Item)x).getCost())
                {
                    //math for button actions;
                    //setting up returns and static variables for price changes
                    ret[0] -= ((Item)x).getCost();
                    ((Item)x).incNumOwned();
                    curBal.setText("Money: " + (int)ret[0]);    

                    ((Item)x).updateCost((2 + Math.pow(((Item)x).getPriceInc(), ((Item)x).getNumOwned())));
                    
                    ((Label)(labelList.get(((Item)x).getIndexInList()))).setText("Cost: " + (int)((Item)x).getCost() + "   Amount owned: " + ((Item)x).getNumOwned());
                    ret[1] += ((Item)x).getUPGS();
                    list.setItem(((Item)x), ((Item)x).getIndexInList());
                    
                    //displaying what is purchased and scrolling to bottom
                    eventText.setText(eventText.getText() + "\nYou have just purchased 1 " + ((Item)x).getName() + ".");
                    scroller.setVvalue(1.0);
                } 
            });
        }
        //Sets the action of the hp upgrade button for user
        hpUpg.setOnAction(e -> {
            if(ret[0] > hpCost)
            {
                //Updates values
                ret[0] -= hpCost;
                ret[3] += 5;
                hpCost *= 1.5;
                //Updates text
                hpReq.setText("5+ HP Cost: " + hpCost);
                eventText.setText(eventText.getText() + "\nYou purchased health upgrade");
                scroller.setVvalue(1.0);                
            }
        });
        //Sets the action of the attack upgrade button for user
        atkUpg.setOnAction(e -> {
            if(ret[0] > atkCost)
            {
                //Updates values
                ret[0] -= atkCost;
                ret[4] += 1;
                atkCost *= 1.5;
                //Updates text
                atkReq.setText("1+ ATK Cost: " + atkCost);
                eventText.setText(eventText.getText() + "\nYou purchased attack upgrade");
                scroller.setVvalue(1.0);                
            }
        });       
        //Sets the action of the cursor upgrade button
        cursorUpg.setOnAction(e -> {
            if(ret[0] > cursCost)
            {
                ret[0] -= cursCost;
                ret[2] *= 1.5;
                cursCost *= 1.5;
                cursReq.setText("Cost: " + cursCost + " Cursor val: " + (int)ret[2] + 1);
                eventText.setText(eventText.getText() + "\nYou purchased cursor upgrade");
                scroller.setVvalue(1.0);
            }
        });
        
        //adding toolbar, balance, vbox, and textbox to the GridPanes
        columnOne.getChildren().addAll(toolBar, curBal, dualSection, scroller);
        masterPane.getChildren().addAll(columnOne, inv);
        dualSection.getChildren().addAll(shopCatalogue, shopCatalogue2);
        
        //allocating certain spots in the GridPane for certain nodes
        columnOne.setConstraints(toolBar, 0, 0);
        columnOne.setConstraints(curBal, 0, 1);
        columnOne.setConstraints(dualSection, 0, 2);
        columnOne.setConstraints(scroller, 0, 3);
        masterPane.setConstraints(columnOne, 0, 0);
        masterPane.setConstraints(inv, 1, 0);
        dualSection.setConstraints(shopCatalogue, 0, 0);
        dualSection.setConstraints(shopCatalogue2, 1, 0);
        
        //setting up the width and height for each node in the GridPane
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getRowConstraints().add(new RowConstraints());
        columnOne.getRowConstraints().add(new RowConstraints(225));
        columnOne.getRowConstraints().add(new RowConstraints(123));
        columnOne.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75));
        masterPane.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.25));
        masterPane.getRowConstraints().add(new RowConstraints(vSceneSize));
        dualSection.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75 / 2));
        dualSection.getColumnConstraints().add(new ColumnConstraints(hSceneSize * 0.75 / 2));
        
        //showing the window and waiting
        window.setScene(scene);
        window.showAndWait();
        
        //Returns a double array of 2, holding the upgrades and money
        return ret;
    }
    
}
