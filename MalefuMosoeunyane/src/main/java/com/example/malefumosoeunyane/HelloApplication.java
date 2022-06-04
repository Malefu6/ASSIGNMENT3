package com.example.malefumosoeunyane;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static javafx.scene.paint.Color.*;

public class HelloApplication extends Application {
    int points;

    TranslateTransition moveleru1 = new TranslateTransition(Duration.millis(11000));
    TranslateTransition moveleru2 = new TranslateTransition(Duration.millis(11000));
    TranslateTransition moveleru3 = new TranslateTransition(Duration.millis(11000));
    TranslateTransition moveleru4 = new TranslateTransition(Duration.millis(8000));
    TranslateTransition moveleru5 = new TranslateTransition(Duration.millis(9000));
    TranslateTransition movecoin1 =new TranslateTransition(Duration.millis(11500));
    TranslateTransition movecoin2= new TranslateTransition(Duration.millis(12000));
    TranslateTransition movecoin3= new TranslateTransition(Duration.millis(12000));
    //TranslateTransition clouds7trans= new TranslateTransition(Duration.millis(12000));

    @Override
    public void start(Stage stage) throws IOException {


        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 500);

        //score
        Label scorelbl= new Label();
        scorelbl.setLayoutX(185);
        scorelbl.setLayoutY(10);
        scorelbl.setTextFill(RED);
        scorelbl.setFont(Font.font("Arial", FontWeight.BOLD,50));
        Label score=new Label("Score:");
        score.setTextFill(RED);
        score.setFont(Font.font("Arial",FontWeight.BOLD,50));
        score.setLayoutY(10);
        score.setLayoutX(30);





        //lose
        String lose = "YOU CRASHED!START AGAIN!";
        Label loselbl = new Label();
        loselbl.setTextFill(DARKRED);
        loselbl.setLayoutX(400);
        loselbl.setLayoutY(300);
        loselbl.setFont(Font.font("Ariel",FontWeight.BOLD,50));
        ImageView plane = createPlane(scene);
        ImageView leru1 = createLeru1(scene);
        ImageView leru2 = createLeru2(scene);
        ImageView leru3 = createLeru3(scene);
        ImageView leru4 = createLeru4(scene);
        ImageView leru5 = createLeru5(scene);
        ImageView coin1 = createCoin1(scene);
        ImageView coin2 = createCoin2(scene);
        ImageView coin3 = createCoin3(scene);


       root.setBackground(new Background(new BackgroundFill(BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
        root.getChildren().addAll(score,scorelbl,loselbl,plane,leru1,leru2,leru3,leru4,
                leru5,coin1,coin2,coin3);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double x = plane.getX();
            double y = plane.getY();
            switch (event.getCode()) {
                case UP -> plane.setY(y - 10);
                case DOWN -> plane.setY(y + 10);
                case LEFT -> plane.setX(x-10);
                case RIGHT -> plane.setX(x+10);
            }
        });
        AnimationTimer checkCollision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                CollisionDetected(leru1,leru2,leru3,leru4,leru5);
            }
            private void CollisionDetected( ImageView leru1, ImageView leru2, ImageView leru3,
                                            ImageView leru4,ImageView leru5) {
                if (plane.getBoundsInParent().intersects(leru1.getBoundsInParent()) ||
                        plane.getBoundsInParent().intersects(leru2.getBoundsInParent()) ||
                        plane.getBoundsInParent().intersects(leru3.getBoundsInParent()) ||
                        plane.getBoundsInParent().intersects(leru4.getBoundsInParent()) ||
                        plane.getBoundsInParent().intersects(leru5.getBoundsInParent())) {
                    loselbl.setText(lose);
                    moveleru1.stop();
                    moveleru2.stop();
                    moveleru3.stop();
                    moveleru4.stop();
                    moveleru5.stop();
                    movecoin1.stop();
                    movecoin2.stop();
                    movecoin3.stop();
                    plane.setImage(new Image("44502.png"));
                }
                leru1.translateXProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (plane.getBoundsInParent().intersects(coin1.getBoundsInParent())||
                            plane.getBoundsInParent().intersects(coin2.getBoundsInParent())||
                            plane.getBoundsInParent().intersects(coin3.getBoundsInParent())) {
                            points=points+2; scorelbl.setText(points+"");
                        }
                    }

                });
            }


        };
        checkCollision.start();
        stage.setTitle("ASSIGNMENT2");
        stage.setScene(scene);
        stage.show();
    }
    private ImageView createPlane(Scene scene){
       ImageView plane=new ImageView(new Image("helicopter.png"));
        plane.setFitWidth(200);
        plane.setFitHeight(200);
        plane.setX(100);
        plane.setY(scene.getHeight() - plane.getFitHeight());

        return plane;



    }
    private  ImageView createLeru1(Scene scene){
        ImageView leru1=new ImageView(new Image("leru1.png"));
        leru1.setFitHeight(200);
        leru1.setFitWidth(100);
        leru1.setY(50);
        leru1.setX(1000);
        moveleru1.setNode(leru1);
        moveleru1.setByX(-1500);
        moveleru1.setCycleCount(Integer.MAX_VALUE);
        moveleru1.play();
        return leru1;
    }
    private  ImageView createLeru2(Scene scene){
        ImageView leru2=new ImageView(new Image("leru2.png"));
        leru2.setFitHeight(80);
        leru2.setFitWidth(80);
        leru2.setY(150);
        leru2.setX(1200);
        moveleru2.setNode(leru2);
        moveleru2.setByX(-1800);
        moveleru2.setCycleCount(Integer.MAX_VALUE);
        moveleru2.play();
        return leru2;
    }
    private  ImageView createLeru3(Scene scene){
        ImageView leru3=new ImageView(new Image("leru3.png"));
        leru3.setFitHeight(80);
        leru3.setFitWidth(80);
        leru3.setY(350);
        leru3.setX(2000);
        moveleru3.setNode(leru3);
        moveleru3.setByX(-1800);
        moveleru3.setCycleCount(Integer.MAX_VALUE);
        moveleru3.play();
        return leru3;
    }
    private  ImageView createLeru4(Scene scene){
        ImageView leru4=new ImageView(new Image("leru1.png"));
        leru4.setFitHeight(80);
        leru4.setFitWidth(80);
        leru4.setY(500);
        leru4.setX(1500);
        moveleru4.setNode(leru4);
        moveleru4.setByX(-1800);
        moveleru4.setCycleCount(Integer.MAX_VALUE);
        moveleru4.play();
        return leru4;
    }
    private  ImageView createLeru5(Scene scene){
        ImageView leru5=new ImageView(new Image("leru2.png"));
        leru5.setFitHeight(80);
        leru5.setFitWidth(80);
        leru5.setX(1700);
        leru5.setY(100);
        moveleru5.setNode(leru5);
        moveleru5.setByX(-1800);
        moveleru5.setCycleCount(Integer.MAX_VALUE);
        moveleru5.play();
        return leru5;
    }
    private  ImageView createCoin1(Scene scene){
        ImageView coin1=new ImageView(new Image("coin1.png"));
        coin1.setFitHeight(80);
        coin1.setFitWidth(80);
        coin1.setX(1800);
        coin1.setY(100);
        movecoin1.setNode(coin1);
        movecoin1.setByX(-1800);
        movecoin1.setCycleCount(Integer.MAX_VALUE);
        movecoin1.play();
        return coin1;
    }
    private  ImageView createCoin2(Scene scene){
        ImageView coin2=new ImageView(new Image("coin2.png"));
        coin2.setFitHeight(80);
        coin2.setFitWidth(80);
        coin2.setX(1200);
        coin2.setY(500);
        movecoin2.setNode(coin2);
        movecoin2.setByX(-1800);
        movecoin2.setCycleCount(Integer.MAX_VALUE);
        movecoin2.play();
        return coin2;
    }
    private  ImageView createCoin3(Scene scene){
        ImageView coin3=new ImageView(new Image("coin3.png"));
        coin3.setFitHeight(80);
        coin3.setFitWidth(80);
        coin3.setX(2500);
        coin3.setY(400);
        movecoin3.setNode(coin3);
        movecoin3.setByX(-1800);
        movecoin3.setCycleCount(Integer.MAX_VALUE);
        movecoin3.play();
        return coin3;
    }



    public static void main(String[] args) {
        launch();
    }
}