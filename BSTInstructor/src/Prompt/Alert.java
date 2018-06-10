/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prompt;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zainm
 */
public class Alert 
{
     public static void display (String title, String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
  
        Label label1 = new Label();
        label1.setText(message);
        Button button1 = new Button("Ok");
        button1.setOnAction(e -> window.close());
        
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label1,button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,300,80);
      //  scene.getStylesheets().add("Style.css");
        window.setScene(scene);
        window.showAndWait();
        
    }
    
}
