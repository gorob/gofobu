package photobook;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class PhotobookApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group rootNode = new Group();

        Scene scene = new Scene(rootNode, 1024, 500);
        primaryStage.setScene(scene);

        Label statusLabel = new Label();
        statusLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        statusLabel.setLayoutX(0);
        statusLabel.setLayoutY(635);
        statusLabel.setPrefWidth(1360);
        statusLabel.setPrefHeight(20);
        rootNode.getChildren().add(statusLabel);

        PhotobookPicture image1 = new PhotobookPicture("testpic.jpeg", statusLabel);
        rootNode.getChildren().add(image1);

        PhotobookPicture image2 = new PhotobookPicture("testpic.jpeg", statusLabel);
        rootNode.getChildren().add(image2);


        primaryStage.setTitle("GoFobu");
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setWidth(1365);
        primaryStage.setHeight(690);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
