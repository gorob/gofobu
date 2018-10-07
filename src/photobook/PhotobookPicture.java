package sample;

import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PhotobookPicture extends StackPane {
    private ImageView imageView;
    private HBox imageViewContainer;

    private double aktY;

    private double aktSceneX;
    private double aktSceneY;

    private double aktTranslateX;
    private double aktTranslateY;

    private double aktRotation;


    private boolean rotationActive;
    private boolean rotateToTheRight;

    private boolean marked;

    private static final double DREH_BEREICH_BREITE = 50;

    public PhotobookPicture(String imageName) {
        this();
        createAndShowImage(imageName);
    }

    public PhotobookPicture() {
        super();

        this.imageView = null;
        this.imageViewContainer = null;
        this.aktRotation = 0;
        this.aktY = 0;
        this.aktSceneX = 0;
        this.aktSceneY = 0;
        this.aktTranslateX = 0;
        this.aktTranslateY = 0;
        this.rotationActive = false;
        this.rotateToTheRight = false;
        this.marked = false;

        setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));
        createEmptyImageView();

        initEvents();
    }

    private void initEvents(){
        getImageView().setOnMouseEntered(e -> {
            changeMouseCursor(e.isPrimaryButtonDown(), e.getX(), e.getY());
        });

        getImageView().setOnMouseMoved(e -> {
            changeMouseCursor(e.isPrimaryButtonDown(), e.getX(), e.getY());
        });

        getImageView().setOnMouseExited(e -> {
            changeMouseCursor(e.isPrimaryButtonDown(), e.getX(), e.getY());
        });

        getImageView().setOnMouseReleased(e -> {
            changeMouseCursor(e.isPrimaryButtonDown(), e.getX(), e.getY());
        });

        getImageView().setOnMousePressed(e -> {
            doOnMousePressed(e.getX(), e.getY(), e.getScreenY(), e.getSceneX(), e.getSceneY());
        });

        getImageView().setOnMouseDragged(e -> {
            doOnMouseDragged(e.getScreenY(), e.getSceneX(), e.getSceneY());
        });

        addEventHandler(MarkEvent.MARK_EVENT_TYPE, e -> {
            demarkIfNecessary();
        });
    }

    private void changeMouseCursor(boolean mousePressed, double x, double y) {
        if (mousePressed) {
            return;
        }

        if (isVergroesserungsbereichRechteEckeOben(x, y)) {
            getScene().setCursor(Cursor.NE_RESIZE);
        } else if (isVergroesserungsbereichRechteEckeUnten(x, y)) {
            getScene().setCursor(Cursor.SE_RESIZE);
        } else if (isVergroesserungsbereichLinkeEckeOben(x, y)) {
            getScene().setCursor(Cursor.NW_RESIZE);
        } else if (isVergroesserungsbereichLinkeEckeUnten(x, y)) {
            getScene().setCursor(Cursor.SW_RESIZE);
        } else if (isMousePointerinDrehbereich(x, y)) {
            getScene().setCursor(Cursor.CLOSED_HAND);
        } else {
            getScene().setCursor(Cursor.DEFAULT);
        }
    }

    private void demarkIfNecessary() {
        if (isMarked()){
            demarkImage();
        }
    }

    private void doOnMousePressed(double x, double y, double screenY, double sceneX, double sceneY){
        this.rotationActive = isMousePointerinDrehbereich(x, y);
        this.rotateToTheRight = isMousePointerinDrehbereichRechts(x, y);

        this.aktTranslateX = this.getTranslateX();
        this.aktTranslateY = this.getTranslateY();
        this.aktY = screenY;
        this.aktSceneX = sceneX;
        this.aktSceneY = sceneY;

        this.demarkiereAndereNodes();
        this.markImage();
    }

    private void doOnMouseDragged(double screenY, double sceneX, double sceneY){
        if (this.rotationActive) {
            this.rotate(screenY);
        } else {
            this.move(sceneX, sceneY);
        }
    }

    protected ImageView getImageView() {
        return imageView;
    }

    protected HBox getImageViewContainer() {
        return imageViewContainer;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    private void createEmptyImageView(){
        this.imageViewContainer = new HBox();
        this.imageView = new ImageView();
        getImageView().scaleXProperty().setValue(1.0);
        setBorder(Color.GRAY, 0);
        getImageViewContainer().getChildren().add(getImageView());
        getChildren().add(getImageViewContainer());
    }

    public void setBorder(Color borderColor, int borderWidth) {
        getImageViewContainer().setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, null, new BorderWidths(borderWidth))));
    }

    public void markImage(){
        Effect effect = new DropShadow(10, Color.DARKBLUE);
        getImageViewContainer().setEffect(effect);
        setMarked(true);
    }

    public void demarkiereAndereNodes(){
        for (Node node : this.getParent().getChildrenUnmodifiable()){
            Event markEvent = new MarkEvent(this, node);
            node.fireEvent(markEvent);
        }
    }

    public void demarkImage(){
        Effect effect = new DropShadow(10, Color.RED);
        getImageViewContainer().setEffect(null);
        setMarked(false);
    }

    public void createAndShowImage(String imageName){
        Image image = new Image(PhotobookApp.class.getResourceAsStream(imageName));
        getImageView().setImage(image);
    }

    public void createBorder(Color borderColor, int borderWidth){
        setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, null, new BorderWidths(borderWidth))));
    }

    private boolean isVergroesserungsbereichRechteEckeOben(double x, double y) {
        return isInRectangle(x, y,getImageView().getImage().getWidth()-9, getImageView().getImage().getWidth()-1, 0, 9);
    }

    private boolean isVergroesserungsbereichRechteEckeUnten(double x, double y) {
        return isInRectangle(x, y,getImageView().getImage().getWidth()-9, getImageView().getImage().getWidth()-1,
                getImageView().getImage().getHeight()-9, getImageView().getImage().getHeight()-1);
    }

    private boolean isVergroesserungsbereichLinkeEckeOben(double x, double y) {
        return isInRectangle(x, y, 0, 9, 0, 9);
    }

    private boolean isVergroesserungsbereichLinkeEckeUnten(double x, double y) {
        return isInRectangle(x, y, 0, 9, getImageView().getImage().getHeight()-9, getImageView().getImage().getHeight()-1);
    }

    private boolean isDrehbereichRechteEckeOben(double x, double y) {
        return isInRectangle(x, y,getImageView().getImage().getWidth()-DREH_BEREICH_BREITE, getImageView().getImage().getWidth()-10, 10, DREH_BEREICH_BREITE);
    }

    private boolean isDrehbereichRechteEckeUnten(double x, double y) {
        return isInRectangle(x, y,getImageView().getImage().getWidth()-DREH_BEREICH_BREITE, getImageView().getImage().getWidth()-10,
                getImageView().getImage().getHeight()-DREH_BEREICH_BREITE, getImageView().getImage().getHeight()-10);
    }

    private boolean isDrehbereichLinkeEckeOben(double x, double y) {
        return isInRectangle(x, y, 10, DREH_BEREICH_BREITE, 10, DREH_BEREICH_BREITE);
    }

    private boolean isDrehbereichLinkeEckeUnten(double x, double y) {
        return isInRectangle(x, y, 10, DREH_BEREICH_BREITE,
          getImageView().getImage().getHeight()-DREH_BEREICH_BREITE, getImageView().getImage().getHeight()-10);
    }


    private boolean isInRectangle(double x, double y, double left, double right, double top, double bottom){
        return (x>=left && x<=right && y>=top && y<=bottom);
    }

    private boolean isMousePointerinDrehbereich(double x, double y) {
        return isMousePointerinDrehbereichLinks(x, y) || isMousePointerinDrehbereichRechts(x, y);
    }

    private boolean isMousePointerinDrehbereichLinks(double x, double y) {
        return isDrehbereichLinkeEckeOben(x, y) || isDrehbereichLinkeEckeUnten(x, y);
    }

    private boolean isMousePointerinDrehbereichRechts(double x, double y) {
        return isDrehbereichRechteEckeOben(x, y) || isDrehbereichRechteEckeUnten(x, y);
    }

    private void rotate(double screenY){
        double diff = screenY - this.aktY;

        if (!this.rotateToTheRight) {
            diff *= -1;
        }

        this.aktRotation +=diff;

        if (diff>0) {
            if (this.aktRotation >360) {
                this.aktRotation -=360;
            }
        } else {
            if (this.aktRotation <0) {
                this.aktRotation +=360;
            }
        }

        this.setRotate(this.aktRotation);

        this.aktY = screenY;
    }

    private void move(double sceneX, double sceneY) {
        double offsetX = sceneX - this.aktSceneX;
        double offsetY = sceneY - this.aktSceneY;
        double newTranslateX = this.aktTranslateX + offsetX;
        double newTranslateY = this.aktTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);
    }

}
