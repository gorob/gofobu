package photobook;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PhotobookPicture extends StackPane implements IPhotobookPicture {
    private ImageView imageView;
    private HBox imageViewContainer;

    private IMarkFeature markFeature;
    private IRotationFeature rotationFeature;
    private IMoveFeature moveFeature;
    private IResizeFeature resizeFeature;

    public PhotobookPicture(String imageName) {
        this();
        setImage(imageName);
    }

    public PhotobookPicture() {
        super();

        initEmptyImageView();
        setBackground(new Background(new BackgroundFill(Color.BROWN, null, null)));

        this.markFeature = new MarkFeature(getImageViewContainer());
        this.rotationFeature = new RotationFeature(this);
        this.moveFeature = new MoveFeature(this);
        this.resizeFeature = new ResizeFeature(this);

        initEvents();
    }

    protected ImageView getImageView() {
        return imageView;
    }

    protected HBox getImageViewContainer() {
        return imageViewContainer;
    }

    protected IMarkFeature getMarkFeature() {
        return markFeature;
    }

    protected IRotationFeature getRotationFeature() {
        return rotationFeature;
    }

    protected IMoveFeature getMoveFeature() {
        return moveFeature;
    }

    protected IResizeFeature getResizeFeature() {
        return resizeFeature;
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
            getMarkFeature().demarkRegionIfNecessary();
        });
    }

    private void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY) {
        if (mousePressed) {
            return;
        }

        getScene().setCursor(Cursor.DEFAULT);

        getRotationFeature().changeMouseCursor(mousePressed, mouseX, mouseY);
        getResizeFeature().changeMouseCursor(mousePressed, mouseX, mouseY);
    }

    private void doOnMousePressed(double mouseX, double mouseY, double screenY, double sceneX, double sceneY) {
        getRotationFeature().startRotation(mouseX, mouseY, screenY);

        getMoveFeature().startMove(sceneX, sceneY);

        getMarkFeature().demarkOtherRegionsOfScene();
        getMarkFeature().markRegion();
    }

    private void doOnMouseDragged(double screenY, double sceneX, double sceneY) {
        boolean rotated = getRotationFeature().rotate(screenY);

        if (!rotated) {
            getMoveFeature().move(sceneX, sceneY);
        }
    }

    private void initEmptyImageView() {
        this.imageViewContainer = new HBox();
        getChildren().add(getImageViewContainer());

        this.imageView = new ImageView();
        getImageView().scaleXProperty().setValue(1.0);
        getImageViewContainer().getChildren().add(getImageView());

        setBorder(Color.GRAY, 0);
    }

    public void setBorder(Color borderColor, int borderWidth) {
        Border border = new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, null, new BorderWidths(borderWidth)));
        getImageViewContainer().setBorder(border);
    }

    public void setImage(String imageName) {
        Image image = new Image(PhotobookApp.class.getResourceAsStream(imageName));
        getImageView().setImage(image);
    }
}
