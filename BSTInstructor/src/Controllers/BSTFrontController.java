/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


 // <editor-fold defaultstate="collapsed" desc="Imports">
import static MainPackage.BSTFront.Add;
import static MainPackage.BSTFront.Delete;
import static MainPackage.BSTFront.Find;
import static MainPackage.BSTFront.FindS;
import static MainPackage.BSTFront.Search;
import Prompt.Alert;
import bst.Node;
import bst.Tree;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.awt.Toolkit;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Duration;
// </editor-fold> 

/**
 *
 * @author Asad jivani
 */
public class BSTFrontController {
    

 // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final TextField Node = new TextField();
    private final Stage PopUpStage = new Stage();
    private final JFXButton btn = new JFXButton();
    private final Tree Algo = new Tree();
    private Pane Layout = new Pane();
    private Text text = new Text();
    private Text Stext = new Text();

    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = 50, constantHeight = 50;

    private final double constantLeftStart = 165, constantLeftEnd = 335, constantRightStart = 15, constantRightEnd = 205;
    private double LocationX, LocationY, SLocationX, SLocationY;
    private double radius, angleInRad1, angleInRad2, angle1, angle2;
    private double x1LA, x2LA, y1LA, y2LA, x1RA, y1RA, x2RA, y2RA;
    private double animationX, animationY;

    private boolean caseOne, caseTwoLeft, caseTwoRight, caseThree, successorLeft, successorHaveNode;
    // </editor-fold> 

    
 // <editor-fold defaultstate="collapsed" desc="Functions">
    
    
 // <editor-fold defaultstate="collapsed" desc="Constructor">
     
    public BSTFrontController(Pane Layout) {

        this.Layout = Layout;

    }

    // </editor-fold> 
    
    
 // <editor-fold defaultstate="collapsed" desc="Add a node in Binary Tree"> 
    
    public void Addnode() {

        PopUp("Add Node");

        Node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> {

            String Input;
            try {
                Input = Node.getText();
                Node.clear();

                PopUpStage.close();
                String[] Nodes = Input.split(",");

                for (int i = 0; i < Nodes.length; i++) {
                    Algo.insert(Integer.parseInt(Nodes[i]));
                }
                Layout.getChildren().clear();
                Layout.getChildren().addAll(Add, Delete, Find, FindS, Search);
                NodeCreater(Layout, 0, width, height, 90, Algo.getRoot());

            } catch (NumberFormatException error) {
                Alert.display("Error", "Invalid Input");
            }

        });

    }

    // </editor-fold> 
    

 // <editor-fold defaultstate="collapsed" desc="Point out a node graphically in Binary Tree">   
    public void Findnode(JFXButton find) {

        PopUp("Find Node");

        Node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> {

            try {
                int data = Integer.parseInt(Node.getText());
                Node.clear();
                PopUpStage.close();

                Node temp = Algo.find(data);

                if (temp != null) {
                    TextDetector(data);
                    ColorBlinker("#2600FC", 4, CoordinatesToCircle(LocationX, LocationY), 0);
                } else {
                    Alert.display("Error", "Element Not Found");
                }

            } catch (NumberFormatException error) {
                Alert.display("Error", "Invalid Input");
            }

        });
    }
    
     // </editor-fold> 

    
 // <editor-fold defaultstate="collapsed" desc="Trace out a node path in Binary Tree">      
    
    public void Searchnode() {

        PopUp("Search Node");

        Node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> {

            try {
                int data = Integer.parseInt(Node.getText());
                Node.clear();
                PopUpStage.close();

                Node chk = Algo.find(data);

                if (chk != null) {
                    Text instruction = new Text((width / 2) - 70, 580, "INSTRUCTIONS");
                    instruction.setFont(Font.font("Verdana", 20));
                    instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                    Layout.getChildren().add(instruction);

                    Node temp = Algo.getRoot();
                    boolean noChild1 = true;
                    double indelay = 5;
                    double delay = 6;
                    int i = 0, j = 0;

                    if (temp.data != data) {
                        TextAnimation(i + 1 + ") Traverse from the root i.e " + temp.data, 620, j * indelay,"#000000");
                    }

                    while (temp.data != data) {

                        TextDetector(temp.data);

                        ColorBlinker("#2600FC", 12, CoordinatesToCircle(LocationX, LocationY), i * delay);
                        j++;
                        i++;

                        if (temp.data > data) {
                            TextAnimation(i + 1 + ") Since " + temp.data + ">" + data + " therefore go to the left child", 620, j * indelay,"#000000");
                            temp = temp.lchild;
                            noChild1 = false;

                        } else if (temp.data < data) {
                            TextAnimation(i + 1 + ") Since " + temp.data + "<" + data + " therefore go to the right child", 620, j * indelay,"#000000");
                            temp = temp.rchild;
                            noChild1 = false;
                        }

                    }

                    if (temp.data == data) {
                        if (!noChild1) {
                            j++;
                        }

                        TextAnimation("                   NODE FOUND ", 620, j * indelay,"#000000");
                    }

                    TextDetector(temp.data);
                    ColorBlinker("#F7FF50", 12, CoordinatesToCircle(LocationX, LocationY), i * delay);
                    i++;

                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds((i * delay) + 2), ev2 -> {
                        Layout.getChildren().clear();
                        Layout.getChildren().addAll(Add, Delete, Find, FindS, Search);
                        NodeCreater(Layout, 0, width, height, 90, Algo.getRoot());

                    }));

                    timeline.play();

                } else {
                    Alert.display("Error", "Elemnt not found");
                }

            } catch (NumberFormatException error) {
                Alert.display("Error", "Invalid Input");
            }
        });

    }
     // </editor-fold> 

 
 // <editor-fold defaultstate="collapsed" desc="Find a Minimum greatest number in Binary Tree of a particular Node">      
 public void FindSuccessor() {

        PopUp("Find Successor");

        Node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> {

            try {
                  int data = Integer.parseInt(Node.getText());
            Node.clear();
            PopUpStage.close();
            Node temp = Algo.getRoot();
           
            
            while (temp.data != data) {
           
                if (temp.data > data) {
                    temp = temp.lchild;

                } else if (temp.data < data) {
                    temp = temp.rchild;

                }

            }
            Node node=temp;
            Node Successor = node.rchild;
            Node Current = node.rchild;
            

            if (node.rchild.lchild == null) {
                
            } else {
                while (Current != null) {
                    
                    Successor = Current;
                    Current = Current.lchild;
                }
                
            }
            TextDetector(Successor.data);
            ColorBlinker("#FE8D1C",4 , CoordinatesToCircle(LocationX, LocationY), 0);

            } catch (NumberFormatException error) {
                Alert.display("Error", "Invalid Input");
            }
        });

    }
  // </editor-fold> 

 
 // <editor-fold defaultstate="collapsed" desc="Delete a particular node in Binary Tree">      
    public void DelNode() {
        PopUp("Delete Node");

        Node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btn.fire();
            }
        });

        btn.setOnAction(e -> {

            try {
                int data = Integer.parseInt(Node.getText());
                Node.clear();
                PopUpStage.close();

                Node temp = Algo.find(data);

                if (temp != null) {

                    caseOne = false;
                    caseTwoLeft = false;
                    caseTwoRight = false;
                    caseThree = false;

                    boolean noChild = false;

                    boolean nochild = SuccessorAnimation(data);

                    if (caseOne) {

                        Text instruction = new Text((width / 2) - 70, 560, "INSTRUCTIONS");
                        instruction.setFont(Font.font("Verdana", 20));
                        instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                        Layout.getChildren().add(instruction);

                        Text Case = new Text((width / 2) - 140, 590, "CASE 1 : Delete node have no child");
                        Case.setFont(Font.font("Verdana", 16));
                        Case.setFill((javafx.scene.paint.Color) PaintToColor("#290602"));
                        Layout.getChildren().add(Case);

                        TextAnimation("Since, the node to be deleted(" + data + "), doesn't have any child so just delete node", 620, 0,"#000000");
                    } else if (caseTwoLeft) {

                        Text instruction = new Text((width / 2) - 70, 560, "INSTRUCTIONS");
                        instruction.setFont(Font.font("Verdana", 20));
                        instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                        Layout.getChildren().add(instruction);

                        Text Case = new Text((width / 2) - 140, 590, "CASE 2 : Delete node have one child");
                        Case.setFont(Font.font("Verdana", 16));
                        Case.setFill((javafx.scene.paint.Color) PaintToColor("#290602"));
                        Layout.getChildren().add(Case);

                        TextAnimation("*) Since, the node to be deleted have only one left child/subtree (" + Stext.getText() + "),", 620, 0,"#000000");
                        TextAnimation("So, just replace the node by it's left child/subtree", 620, 6,"#000000");
                    } else if (caseTwoRight) {

                        Text instruction = new Text((width / 2) - 70, 560, "INSTRUCTIONS");
                        instruction.setFont(Font.font("Verdana", 20));
                        instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                        Layout.getChildren().add(instruction);

                        Text Case = new Text((width / 2) - 140, 590, "CASE 2 : Delete node have one child");
                        Case.setFont(Font.font("Verdana", 16));
                        Case.setFill((javafx.scene.paint.Color) PaintToColor("#290602"));
                        Layout.getChildren().add(Case);

                        TextAnimation("*) Since, the node to be deleted have only one right child/subtree (" + Stext.getText() + "),", 620, 0,"#000000");
                        TextAnimation("So, just replace the node by it's right child/subtree", 620, 6,"#000000");
                    } else if (caseThree) {
                        if (successorLeft && successorHaveNode) {

                            Text instruction = new Text((width / 2) - 70, 560, "INSTRUCTIONS");
                            instruction.setFont(Font.font("Verdana", 20));
                            instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                            Layout.getChildren().add(instruction);

                            Text Case = new Text((width / 2) - 140, 590, "CASE 3 : Delete node have two child");
                            Case.setFont(Font.font("Verdana", 16));
                            Case.setFill((javafx.scene.paint.Color) PaintToColor("#290602"));
                            Layout.getChildren().add(Case);

                            TextAnimation("1 ) The Successor of the node to be deleted is (" + Stext.getText() + ")", 620, 0,"#36B300");
                            TextAnimation("2 ) Remove the Node(" + data + ")", 620, 6,"#F60A0A");
                            TextAnimation("3 ) Replace the Node with its successor", 620, 12,"#000000");
                            TextAnimation("4 ) Join Successor's parent to successor's right child/subtree", 620, 18,"#000000");
                        } else {

                            Text instruction = new Text((width / 2) - 70, 560, "INSTRUCTIONS");
                            instruction.setFont(Font.font("Verdana", 20));
                            instruction.setFill((javafx.scene.paint.Color) PaintToColor("#6100FF"));
                            Layout.getChildren().add(instruction);

                            Text Case = new Text((width / 2) - 140, 590, "CASE 3 : Delete node have two child");
                            Case.setFont(Font.font("Verdana", 16));
                            Case.setFill((javafx.scene.paint.Color) PaintToColor("#290602"));
                            Layout.getChildren().add(Case);

                            TextAnimation("1 ) The Successor of the node to be deleted is (" + Stext.getText() + ")", 620, 0,"#36B300");
                            TextAnimation("2 ) Remove the Node(" + data + ")", 620, 6,"#F60A0A");
                            TextAnimation("3 ) Replace the Node with its successor", 620, 12,"#000000");
                        }

                    }

                    if (nochild) {
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(10), ev2 -> {

                            DelAnimation(data, noChild);

                        }));
                        timeline1.play();

                        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(16), ev2 -> {

                            JoinSuccessorAnimation(data);

                        }));
                        timeline2.play();
                    }
                } else {
                    Alert.display("Error", "Elemnt not found");
                }

            } catch (NumberFormatException error) {
                Alert.display("Error", "Invalid Input");
                error.printStackTrace();
            }

        });
    }
      // </editor-fold> 

    
 // <editor-fold defaultstate="collapsed" desc="Animation for deletion process">      
    
    private boolean SuccessorAnimation(int data) {

        animationX = (width / 2) / 2;
        animationY = 90;
        int i = 2, j = 1, k = 0;

        boolean goToAnimation = true;
        boolean noChildCheck = true;

        double animation;

        Node temp = Algo.getRoot();

        while (temp.data != data) {

            if (temp.data > data) {
                temp = temp.lchild;
                animationX = animationX / i;

            } else if (temp.data < data) {
                temp = temp.rchild;
                animationX = animationX / i;
            }

        }

        Node node = temp;
        Node Successor = node.rchild;
        Node Current = node.rchild;

        if (node.rchild == null && node.lchild != null) {
            Successor = node.lchild;
            caseTwoLeft = true;

        } else if (node.rchild != null && node.lchild == null) {
            Successor = node.rchild;
            caseTwoRight = true;

        } else if (node.lchild == null && node.rchild == null) {
            noChildCheck = false;
            goToAnimation = false;
            DelAnimation(data, true);
            caseOne = true;

        } else {
            caseThree = true;

            if (node.rchild.lchild == null) {
                Successor = node.rchild;
                successorLeft = false;
            } else {

                successorLeft = true;
                while (Current != null) {

                    Successor = Current;
                    Current = Current.lchild;
                    animationX = animationX / j;
                    animationY = animationY + k;
                    j = 2;
                    k = 90;
                }

            }
        }

        if (goToAnimation) {

            if (Successor.rchild != null) {
                successorHaveNode = true;
            } else {
                successorHaveNode = false;
            }

            TextDetector2(Successor.data, data);
            ColorBlinker("#0BAE1C", 6, CoordinatesToCircle(LocationX, LocationY), 0);

            Stext = text;
            SLocationX = LocationX;
            SLocationY = LocationY;

            if (Successor.data < Algo.getRoot().data) {
                animation = 100;
            } else if (Successor.data > Algo.getRoot().data) {
                animation = -100;
            } else {
                animation = -100;
            }

            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(4), ev2 -> {

                TranslateTransition animationCircle, animationText;
                animationCircle = new TranslateTransition();
                animationCircle.setDuration(Duration.seconds(3));
                animationCircle.setToX(animation);
                animationCircle.setNode(CoordinatesToCircle(LocationX, LocationY));
                animationCircle.play();

                animationText = new TranslateTransition();
                animationText.setDuration(Duration.seconds(3));
                animationText.setToX(animation);
                animationText.setNode(text);
                animationText.play();

            }));
            timeline1.play();
        }

        return noChildCheck;

    }
        
    private void DelAnimation(int data, boolean noChild) {
        TextDetector(data);
        ColorBlinker("#F80515", 6, CoordinatesToCircle(LocationX, LocationY), 0);
        int animation = width + 50;

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(4), ev2 -> {

            TranslateTransition animationCircle, animationText;
            animationCircle = new TranslateTransition();
            animationCircle.setDuration(Duration.seconds(3));
            animationCircle.setToX(animation);
            animationCircle.setToY(90);
            animationCircle.setNode(CoordinatesToCircle(LocationX, LocationY));
            animationCircle.play();

            animationText = new TranslateTransition();
            animationText.setDuration(Duration.seconds(3));
            animationText.setToX(animation);
            animationText.setToY(90);
            animationText.setNode(text);
            animationText.play();

        }));
        timeline1.play();

        if (noChild) {
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(8), ev2 -> {

                Algo.delete(data);
                Layout.getChildren().clear();
                Layout.getChildren().addAll(Add, Delete, Find, FindS, Search);
                NodeCreater(Layout, 0, width, height, 90, Algo.getRoot());

            }));
            timeline2.play();
        }

    }

    private void JoinSuccessorAnimation(int data) {
        if (Integer.parseInt(Stext.getText()) >= data) {
            animationX = (animationX * -1);
        }

        TranslateTransition animationCircle, animationText;
        animationCircle = new TranslateTransition();
        animationCircle.setDuration(Duration.seconds(3));
        animationCircle.setToX(animationX);
        animationCircle.setToY(-animationY);
        animationCircle.setNode(CoordinatesToCircle(SLocationX, SLocationY));
        animationCircle.play();

        animationText = new TranslateTransition();
        animationText.setDuration(Duration.seconds(3));
        animationText.setToX(animationX);
        animationText.setToY(-animationY);
        animationText.setNode(Stext);
        animationText.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(7), ev2 -> {

            Algo.delete(data);
            Layout.getChildren().clear();
            Layout.getChildren().addAll(Add, Delete, Find, FindS, Search);
            NodeCreater(Layout, 0, width, height, 90, Algo.getRoot());

        }));
        timeline2.play();
    }
 // </editor-fold> 
    
    
 // <editor-fold defaultstate="collapsed" desc="Creates a Node graphically in Binary Search">       
    
    private void NodeCreater(Pane layout, int StartWidth, int EndWidth, int StartHeight, int Level, Node root) {

        int x1L = (StartWidth + EndWidth) / 2;
        int x2L = (x1L + StartWidth) / 2;

        int x1R = (StartWidth + EndWidth) / 2;
        int x2R = ((x1R + EndWidth) / 2);

        Line l1 = null, l2 = null;

        if (root != null) {
            Text textNode = null;
            Circle circle = null;
            String value = String.valueOf(root.data);
          if(Integer.valueOf(value)>=10 && Integer.valueOf(value)<100){  
            textNode = createText(value, (StartWidth + EndWidth) / 2, StartHeight + 18);
            circle = encircle(textNode, ((StartWidth + EndWidth) / 2) + 25, StartHeight); 
          }
          else if(Integer.valueOf(value)>=100){
           textNode = createText(value, (StartWidth + EndWidth) / 2, StartHeight + 16);
           circle = encircle(textNode, ((StartWidth + EndWidth) / 2) + 35, StartHeight);
          
          }else if(Integer.valueOf(value)>=1 && Integer.valueOf(value)<10){
           
           textNode = createText(value, (StartWidth + EndWidth) / 2, StartHeight + 18);
           circle = encircle(textNode, ((StartWidth + EndWidth) / 2) + 15, StartHeight);
          
          }
            layout.getChildren().addAll(
                    circle,
                    textNode
            );

            layout.setPadding(new Insets(20));

            if (root.lchild == null && root.rchild == null) {
                return;
            } else if (root.lchild == null && root.rchild != null) {
                AngleAligmentRight(StartHeight, Level);
                radius = circle.getRadius();
                angleInRad1 = (angle1 * Math.PI) / 180;

                x1RA = Math.cos(angleInRad1) * radius;
                y1RA = Math.sin(angleInRad1) * radius;

                angleInRad2 = (angle2 * Math.PI) / 180;

                x2RA = Math.cos(angleInRad2) * radius;
                y2RA = Math.sin(angleInRad2) * radius;

                l2 = new Line(x1RA + (x1R + 25), y1RA + StartHeight, x2RA + (x2R + 25), y2RA + (StartHeight + Level));
                l1 = new Line(0, 0, 0, 0);
            } else if ((root.lchild != null && root.rchild == null))// || (root.lchild != null && root.rchild != null))
            {
                AngleAligmentLeft(StartHeight, Level);
                radius = circle.getRadius();
                angleInRad1 = (angle1 * Math.PI) / 180;

                x1LA = Math.cos(angleInRad1) * radius;
                y1LA = Math.sin(angleInRad1) * radius;

                angleInRad2 = (angle2 * Math.PI) / 180;

                x2LA = Math.cos(angleInRad2) * radius;
                y2LA = Math.sin(angleInRad2) * radius;

                l2 = new Line(0, 0, 0, 0);
                l1 = new Line(x1LA + (x1L + 25), y1LA + (StartHeight), x2LA + ((x2L + 25)), y2LA + (StartHeight + Level));

            } else if (root.lchild != null && root.rchild != null) {
                AngleAligmentLeft(StartHeight, Level);
                radius = circle.getRadius();
                angleInRad1 = (angle1 * Math.PI) / 180;

                x1LA = Math.cos(angleInRad1) * radius;
                y1LA = Math.sin(angleInRad1) * radius;

                angleInRad2 = (angle2 * Math.PI) / 180;

                x2LA = Math.cos(angleInRad2) * radius;
                y2LA = Math.sin(angleInRad2) * radius;

                AngleAligmentRight(StartHeight, Level);
                radius = circle.getRadius();
                angleInRad1 = (angle1 * Math.PI) / 180;

                x1RA = Math.cos(angleInRad1) * radius;
                y1RA = Math.sin(angleInRad1) * radius;

                angleInRad2 = (angle2 * Math.PI) / 180;

                x2RA = Math.cos(angleInRad2) * radius;
                y2RA = Math.sin(angleInRad2) * radius;

                l1 = new Line(x1LA + (x1L + 25), y1LA + (StartHeight), x2LA + ((x2L + 25)), y2LA + (StartHeight + Level));
                l2 = new Line(x1RA + (x1R + 25), y1RA + StartHeight, x2RA + (x2R + 25), y2RA + (StartHeight + Level));

            }

            NodeCreater(layout, StartWidth, ((StartWidth + EndWidth) / 2), StartHeight + Level, Level, root.lchild);

            NodeCreater(layout, ((StartWidth + EndWidth) / 2), EndWidth, StartHeight + Level, Level, root.rchild);

            layout.getChildren().add(l1);//, l2);
            layout.getChildren().add(l2);
        }
    }
    // </editor-fold> 
    
    
 // <editor-fold defaultstate="collapsed" desc="Set an angle between Node in Binary Tree">        

    private void AngleAligmentLeft(int StartHeight, int Level) {
        int levelAngle = -1;

        if (StartHeight == this.constantHeight) {
            levelAngle = 1;
        } else if (StartHeight == (this.constantHeight + (Level))) {
            levelAngle = 2;
        } else if (StartHeight == (this.constantHeight + (2 * Level))) {
            levelAngle = 3;
        } else if (StartHeight == (this.constantHeight + (3 * Level))) {
            levelAngle = 4;
        } else if (StartHeight == (this.constantHeight + (4 * Level))) {
            levelAngle = 5;
        } else if (StartHeight == (this.constantHeight + (5 * Level))) {
            levelAngle = 6;
        } else if (StartHeight == (this.constantHeight + (6 * Level))) {
            levelAngle = 7;
        }

        switch (levelAngle) {
            case 1: {
                angle1 = (this.constantLeftStart - 8);
                angle2 = (this.constantLeftEnd);
                break;
            }
            case 2: {
                angle1 = (this.constantLeftStart - 16);
                angle2 = (this.constantLeftEnd - 20);
                break;
            }
            case 3: {
                angle1 = (this.constantLeftStart - 24);
                angle2 = (this.constantLeftEnd - 40);
                break;
            }
            case 4: {
                angle1 = (this.constantLeftStart - 32);
                angle2 = (this.constantLeftEnd - 60);
                break;
            }
            case 5: {
                angle1 = (this.constantLeftStart - 40);
                angle2 = (this.constantLeftEnd - 80);
                break;
            }
            case 6: {
                angle1 = (this.constantLeftStart - 48);
                angle2 = (this.constantLeftEnd - 100);
                break;
            }
            case 7: {
                angle1 = (this.constantLeftStart - 56);
                angle2 = (this.constantLeftEnd - 120);
                break;
            }
            default: {
                System.out.println("Out Of Range");
            }
        }
    }

    private void AngleAligmentRight(int StartHeight, int Level) {

        int levelAngle = -1;

        if (StartHeight == this.constantHeight) {
            levelAngle = 1;
        } else if (StartHeight == (this.constantHeight + (Level))) {
            levelAngle = 2;
        } else if (StartHeight == (this.constantHeight + (2 * Level))) {
            levelAngle = 3;
        } else if (StartHeight == (this.constantHeight + (3 * Level))) {
            levelAngle = 4;
        } else if (StartHeight == (this.constantHeight + (4 * Level))) {
            levelAngle = 5;
        } else if (StartHeight == (this.constantHeight + (5 * Level))) {
            levelAngle = 6;
        } else if (StartHeight == (this.constantHeight + (6 * Level))) {
            levelAngle = 7;
        }

        switch (levelAngle) {
            case 1: {
                angle1 = (this.constantRightStart + 8);
                angle2 = (this.constantRightEnd);
                break;
            }
            case 2: {
                angle1 = (this.constantRightStart + 16);
                angle2 = (this.constantRightEnd + 20);
                break;
            }
            case 3: {
                angle1 = (this.constantRightStart + 24);
                angle2 = (this.constantRightEnd + 40);
                break;
            }
            case 4: {
                angle1 = (this.constantRightStart + 32);
                angle2 = (this.constantRightEnd + 60);
                break;
            }
            case 5: {
                angle1 = (this.constantRightStart + 40);
                angle2 = (this.constantRightEnd + 80);
                break;
            }
            case 6: {
                angle1 = (this.constantRightStart + 48);
                angle2 = (this.constantRightEnd + 100);
                break;
            }
            case 7: {
                angle1 = (this.constantRightStart + 56);
                angle2 = (this.constantRightEnd + 120);
                break;
            }
            default: {
                System.out.println("Out Of Range");
            }
        }

    }
    
     // </editor-fold> 

    
 // <editor-fold defaultstate="collapsed" desc="Pops Up a dialog Box on Screen">    
    
    private void PopUp(String buttonpurpose) {

        Pane layout = new Pane();
        layout.setPadding(new Insets(20, 20, 20, 20));

        Node.setPromptText(buttonpurpose);
        Node.setLayoutX(40);
        Node.setLayoutY(30);

        btn.setLayoutX(40);
        btn.setLayoutY(70);
        btn.setText(buttonpurpose);
        btn.setPrefWidth(145);
        btn.getStyleClass().add("jfxbutton-add");

        layout.getChildren().addAll(Node, btn);
        Scene scene = new Scene(layout, 220, 100);
        scene.getStylesheets().add(getClass().getResource("/CSS/BSTFront.css").toExternalForm());

        PopUpStage.setScene(scene);
        PopUpStage.setTitle(buttonpurpose);
        PopUpStage.setResizable(false);
        PopUpStage.show();

    }
    
      // </editor-fold> 

    
 // <editor-fold defaultstate="collapsed" desc="Creates a Node graphically in Binary Search Tree">    
    
    private Text createText(String string, int Xc, int Yc) {
        Text text = new Text(Xc, Yc, string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";"
                + "-fx-font-style: italic;"
                + "-fx-font-size: 48px;"
        );

        return text;
    }

    private Circle encircle(Text text, int Xc, int Yc) {
        Circle circle = new Circle(Xc, Yc, 8);
        circle.setFill(PaintToColor("#13E7A6"));
        final double PADDING = 10;
        circle.setRadius(getWidth(text) / 2 + PADDING);
        return circle;
    }

    private double getWidth(Text text) {
        new Scene(new Group(text));
        text.applyCss();

        return text.getLayoutBounds().getWidth();
    }
    
     // </editor-fold> 

 
 // <editor-fold defaultstate="collapsed" desc="Prerequisite Functionalities for other Core Functions">    
    
    private Paint PaintToColor(String code) {

        Color awtColor = Color.decode(code);
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        return fxColor;

    }

    private void TextDetector(int NodeValue) {

        for (int i = 0; i < Layout.getChildren().size(); i++) {

            if (Layout.getChildren().get(i).getClass() == text.getClass()) {

                String value = String.valueOf(NodeValue);
                text = (Text) Layout.getChildren().get(i);
                if (value.equals(text.getText())) {
                    LocationX = text.getX();
                    LocationY = text.getY();
                    break;
                }

            }

        }

    }

    private void TextDetector2(int NodeValue, int inputVal) {

        int count = 0;

        for (int i = 0; i < Layout.getChildren().size(); i++) {

            if (Layout.getChildren().get(i).getClass() == text.getClass()) {

                String value = String.valueOf(NodeValue);
                String value2 = String.valueOf(inputVal);
                if (count == 1) {
                    value2 = null;
                }
                text = (Text) Layout.getChildren().get(i);
                if (value.equals(text.getText())) {
                    if (!value.equals(value2)) {
                        LocationX = text.getX();
                        LocationY = text.getY();
                        break;
                    } else {
                        count = 1;
                    }

                }

            }

        }

    }

    private Circle CoordinatesToCircle(double x, double y) {

        Circle circle = new Circle();

        for (int i = 0; i < Layout.getChildren().size(); i++) {

            if (Layout.getChildren().get(i).getClass() == circle.getClass()) {

                circle = (Circle) Layout.getChildren().get(i);
                if (circle.getCenterX() == x + 25 && circle.getCenterY() == y - 18) {
                    return circle;
                }

            }

        }

        return circle;

    }

    private void ColorBlinker(String ColorToblink, int noofcycle, Circle circle, double delay) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay + 0.2), ev2 -> {
            javafx.scene.paint.Color Color1 = (javafx.scene.paint.Color) PaintToColor("#13E7A6");
            javafx.scene.paint.Color Color2 = (javafx.scene.paint.Color) PaintToColor(ColorToblink);
            FillTransition ft = new FillTransition(Duration.millis(500), circle, Color1, Color2);
            ft.setCycleCount(noofcycle);
            ft.setAutoReverse(true);
            ft.play();

        }));
        timeline.play();

    }

    private void TextAnimation(String content, double height, double delay,String Color) {

        Text Instruction = new Text((width / 2) - 150, height, "");
        Instruction.setFont(Font.font("Verdana", 15));
        Instruction.setFill((javafx.scene.paint.Color) PaintToColor(Color));
        Layout.getChildren().add(Instruction);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay + 0.01), ev2 -> {

            Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(2500));
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

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(delay + 4), ev2 -> {

            Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(1000));
                }

                @Override
                protected void interpolate(double frac) {
                    int length = content.length();
                    int n = Math.round(length * (float) frac);
                    n = length - n;
                    Instruction.setText(content.substring(0, n));

                }

            };
            animation.play();

        }));
        timeline2.play();

    }
    // </editor-fold>
    
    // </editor-fold> 
    
    
}
