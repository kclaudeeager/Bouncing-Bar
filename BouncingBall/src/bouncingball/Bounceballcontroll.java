/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Kwizera
 */
public class Bounceballcontroll extends Application {
    @Override // Override the start method in the Application class
public void start(Stage primaryStage) {
 BallPane ballPane = new BallPane(); // Create a ball pane

 // Pause and resume animation
 ballPane.setOnMousePressed(e->ballPane.pause());
 ballPane.setOnMouseReleased(e->ballPane.play());

 // Increase and decrease animation
 ballPane.setOnKeyPressed(e->{
 if (e.getCode() == KeyCode.UP) {
 ballPane.increaseSpeed();
 }
 else if (e.getCode() == KeyCode.DOWN) {
 ballPane.decreaseSpeed();
 }
 });

 // Create a scene and place it in the stage
 Scene scene = new Scene(ballPane, 250, 150);
 primaryStage.setTitle("Bounce BallControl Journe to Java"); // Set the stage title
 primaryStage.setScene(scene); // Place the scene in the stage
primaryStage.show(); // Display the stage

 // Must request focus after the primary stage is displayed
 ballPane.requestFocus();
 }
 
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
class BallPane extends Pane {
 public final double radius = 30;
 private double x = radius, y = radius;
private double dx = 1, dy = 1;
 private Circle circle = new Circle(x, y, radius);
private Timeline animation;

 public BallPane() {
 circle.setFill(Color.GREEN); // Set ball color
 getChildren().add(circle); // Place a ball into this pane

// Create an animation for moving the ball
 animation = new Timeline(new KeyFrame(Duration.millis(50),e->moveBall()));
 animation.setCycleCount(Timeline.INDEFINITE);
 animation.play(); // Start animation
}
 
       
 public void play() {
animation.play();
}

public void pause() {
 animation.pause();
 }

 public void increaseSpeed() {
 animation.setRate(animation.getRate() + 0.1);
 }

 public void decreaseSpeed() {
animation.setRate(animation.getRate()>0?animation.getRate()-0.1:animation.getRate());
}

 public DoubleProperty rateProperty() {
 return animation.rateProperty();
 }

 protected void moveBall() {
// Check boundaries
if(x<radius || x>getWidth()-radius)
{
    dx*=-1;
}
if(y<radius || y>getHeight()-radius)
{
    dy*=-1;
}


 // Adjust ball position
 x += dx;
 y += dy;
 circle.setCenterX(x);
 circle.setCenterY(y);
 
 }
}