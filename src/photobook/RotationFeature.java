package photobook;

import javafx.scene.Cursor;
import javafx.scene.layout.Region;

public class RotationFeature implements IRotationFeature {
    private Region region;

    private boolean rotationActive;
    private boolean rotationToTheRight;
    private double lastRotation;
    private double lastScreenY;

    public RotationFeature(Region region) {
        super();

        this.region = region;
        this.rotationActive = false;
        this.rotationToTheRight = false;
        this.lastRotation = 0;
        this.lastScreenY = 0;
    }

    protected Region getRegion() {
        return region;
    }

    protected boolean isRotationToTheRight() {
        return rotationToTheRight;
    }

    protected void setRotationToTheRight(boolean rotationToTheRight) {
        this.rotationToTheRight = rotationToTheRight;
    }

    protected double getLastRotation() {
        return lastRotation;
    }

    protected void setLastRotation(double lastRotation) {
        this.lastRotation = lastRotation;
    }

    protected double getLastScreenY() {
        return lastScreenY;
    }

    protected void setLastScreenY(double lastScreenY) {
        this.lastScreenY = lastScreenY;
    }

    protected boolean isRotationActive() {
        return rotationActive;
    }

    protected void setRotationActive(boolean rotationActive) {
        this.rotationActive = rotationActive;
    }

    public void startRotation(double mouseX, double mouseY, double screenY) {
        setRotationActive(isMousePointerInRotationArea(mouseX, mouseY));
        setRotationToTheRight(isMousePointerInRotationAreaRight(mouseX, mouseY));
        setLastScreenY(screenY);
    }

    public boolean isMousePointerInRotationArea(double mouseX, double mouseY) {
        return isMousePointerInRotationAreaLeft(mouseX, mouseY) || isMousePointerInRotationAreaRight(mouseX, mouseY);
    }

    private boolean isMousePointerInRotationAreaLeft(double mouseX, double mouseY) {
        return isRotationAreaTopLeft(mouseX, mouseY) || isRotationAreaBottomLeft(mouseX, mouseY);
    }

    private boolean isMousePointerInRotationAreaRight(double mouseX, double mouseY) {
        return isRotationAreaTopRight(mouseX, mouseY) || isRotationAreaBottomRight(mouseX, mouseY);
    }

    private boolean isRotationAreaTopRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_ROTATION_AREA - ROTATION_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_ROTATION_AREA,
                INDENT_FROM_BORDER_TO_ROTATION_AREA,
                INDENT_FROM_BORDER_TO_ROTATION_AREA + ROTATION_AREA_WIDTH);
    }

    private boolean isRotationAreaBottomRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_ROTATION_AREA - ROTATION_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_ROTATION_AREA,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_ROTATION_AREA - ROTATION_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_ROTATION_AREA);
    }

    private boolean isRotationAreaTopLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_ROTATION_AREA,
                INDENT_FROM_BORDER_TO_ROTATION_AREA + ROTATION_AREA_WIDTH,
                INDENT_FROM_BORDER_TO_ROTATION_AREA,
                INDENT_FROM_BORDER_TO_ROTATION_AREA + ROTATION_AREA_WIDTH);
    }

    private boolean isRotationAreaBottomLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_ROTATION_AREA,
                INDENT_FROM_BORDER_TO_ROTATION_AREA + ROTATION_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_ROTATION_AREA - ROTATION_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_ROTATION_AREA);
    }

    private boolean isInRectangle(double x, double y, double left, double right, double top, double bottom){
        return (x>=left && x<=right && y>=top && y<=bottom);
    }

    public boolean rotate(double newScreenY) {
        if (!isRotationActive()) {
            return false;
        }

        double distance = newScreenY - getLastScreenY();

        setLastScreenY(newScreenY);

        if (!isRotationToTheRight()) {
            distance *= -1;
        }

        setLastRotation(getLastRotation() + distance);

        if (distance>0 && getLastRotation()>360) {
            setLastRotation(getLastRotation() - 360);
        } else if (distance<0 && getLastRotation()<0) {
            setLastRotation(getLastRotation() + 360);
        }

        getRegion().setRotate(getLastRotation());

        return true;
    }

    public void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY) {
        if (mousePressed) {
            return;
        }

        if (isMousePointerInRotationArea(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.CLOSED_HAND);
        }
    }

}
