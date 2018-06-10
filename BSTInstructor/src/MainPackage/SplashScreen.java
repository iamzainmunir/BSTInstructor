/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.Toolkit;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author asadj
 */
public class SplashScreen extends Application {

       Group root = new Group();
    @Override
    public void start(Stage primaryStage) throws Exception {

      
        root.setStyle("-fx-background-color: transparent;");
        Image image = new Image(getClass().getResourceAsStream("/Images/Splash.png"));
        ImageView imv = new ImageView(image);
        centerImage(imv);
        root.getChildren().add(imv);
        Scene scene = new Scene(root);
        TextAnimation("Developed By:", 520,0,10);
        TextAnimation("1) Zain Munir (B15101155)", 540,1.5,10);
        TextAnimation("2) Faiq Ehsan (B15101068)", 560,3,10);
        TextAnimation("3) Omar Faheem (B15101108)", 580,4.5,10);
        TextAnimation("4) Muhammad Asad Jivani (B15101064)", 600,6,10);
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(11), ev2 -> {

            FadeTransition ft = new FadeTransition();
            ft.setNode(imv);
            ft.setDuration(new Duration(3000));
            ft.setFromValue(5.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);

            ft.play();

        }));
        timeline.play();

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(15), ev2 -> {
        Platform.setImplicitExit(false);
               new BSTFront().start(new Stage());
        }));
        timeline2.play();
        

    }

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() + 100 / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = (img.getWidth() - 100) * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX(((imageView.getFitWidth() - w) / 2) + 80);
            imageView.setY(((imageView.getFitHeight() - h) / 2) - 80);

        }
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);

    }
    
    
       private void TextAnimation(String content, double height, double delay,double fade) {

        Text Instruction = new Text((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 150, height, "");
        Instruction.setFont(Font.font("Verdana",FontWeight.BOLD ,15));
        Instruction.setFill((javafx.scene.paint.Color) PaintToColor("#ffffff"));
                root.getChildren().add(Instruction);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay + 0.01), ev2 -> {

            Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(1500));
                }

                @Override
                protected void interpolate(double frac) {
                    int length = content.length();
                    int n = Math.round(length * (float) frac);
                    Instruction.setText(content.substring(0, n));

                }

            };
            animation.play();

        }));
        timeline.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(fade), ev2 -> {

           FadeTransition ft = new FadeTransition();
            ft.setNode(Instruction);
            ft.setDuration(new Duration(5000));
            ft.setFromValue(10.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);

            ft.play();

        }));
        timeline2.play();

    }
       
        private Paint PaintToColor(String code) {

        java.awt.Color awtColor = java.awt.Color.decode(code);
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        return fxColor;

    }
}
