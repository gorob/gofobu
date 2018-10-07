package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class PhotobookApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group rootNode = new Group();

        Scene scene = new Scene(rootNode, 1024, 500);
        primaryStage.setScene(scene);

        PhotobookPicture image1 = new PhotobookPicture("testpic.jpeg");
        rootNode.getChildren().add(image1);

        PhotobookPicture image2 = new PhotobookPicture("testpic.jpeg");
        rootNode.getChildren().add(image2);

        primaryStage.setTitle("Photobook");
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
