/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Controllers.BSTFrontController;
import com.jfoenix.controls.JFXButton;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ZAIN,ASAD,FAIQ,OMAR
 */
public class BSTFront extends Application {

    Pane root = new Pane();
    public static JFXButton Add = new JFXButton("ADD");
    public static JFXButton Delete = new JFXButton("DELETE");
    public static JFXButton Find = new JFXButton("FIND");
    public static JFXButton FindS = new JFXButton("FIND SUCCESSOR");
    public static JFXButton Search = new JFXButton("SEARCH");
    BSTFrontController Controller = new BSTFrontController(root);

  
    
    @Override
    public void start(Stage primaryStage) {
         BSTFrontMenu(primaryStage);  
    }

    private void BSTFrontMenu(Stage primaryStage)
    {
      
        root.getChildren().add(Add);
        root.getChildren().add(Delete);
        root.getChildren().add(Find);
        root.getChildren().add(FindS);
        root.getChildren().add(Search);
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("/CSS/BSTFront.css").toExternalForm());

        Add.getStyleClass().add("jfxbutton");
        Delete.getStyleClass().add("jfxbutton");
        Find.getStyleClass().add("jfxbutton");
        FindS.getStyleClass().add("jfxbutton");
        Search.getStyleClass().add("jfxbutton");

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        int height = Toolkit.getDefaultToolkit().getScreenSize().height - 70;
        double gap = 30;
        double screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width - 6 * gap;

        double buttonwidth = screenwidth / 5;
        Add.setPrefWidth(buttonwidth);
        Delete.setPrefWidth(buttonwidth);
        Find.setPrefWidth(buttonwidth);
        FindS.setPrefWidth(buttonwidth);
        Search.setPrefWidth(buttonwidth);

        Add.setLayoutX(gap);
        Add.setLayoutY(height);
        Delete.setLayoutX((2 * gap) + buttonwidth);
        Delete.setLayoutY(height);
        Find.setLayoutX((3 * gap) + (2 * buttonwidth));
        Find.setLayoutY(height);
        FindS.setLayoutX((4 * gap) + (3 * buttonwidth));
        FindS.setLayoutY(height);
        Search.setLayoutX((5 * gap) + (4 * buttonwidth));
        Search.setLayoutY(height);

        Add.setOnAction(e -> {

            Controller.Addnode();

        });
        Find.setOnAction(e -> {

            Controller.Findnode(Find);

        });
        Search.setOnAction(e -> {

            Controller.Searchnode();

        });
        FindS.setOnAction(e -> {

            Controller.FindSuccessor();

        });
        
        Delete.setOnAction(e -> {

            Controller.DelNode();

        });
        
        primaryStage.setTitle("BST Applet");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
        });

    }

  
    

}
