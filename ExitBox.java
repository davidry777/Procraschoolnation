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

/**
 * Allows the user to safely exit the program
 *
 * @Jason G
 * @5/28/19
 */
public class ExitBox
{
    //Keeps track of what the user responds with
    private static boolean answer;
    
    /**
     * Class that displays the exit window and allows the user 
     * to safely exit the program.
     */
    public static boolean display(String title, String message)
    {
        //Setup for window display
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        Label label = new Label();
        label.setText(message);
        
        //Creates a yes and no button for the user
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        
        //Sets the actions of each button
        yesButton.setOnAction(e -> { 
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> { 
            answer = false;
            window.close();
        });
        
        //Display of the exit window
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        //Captures the user response
        return answer;
    }
}