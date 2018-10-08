package photobook;

import javafx.scene.layout.Region;

public class MoveFeature implements IMoveFeature {
    private Region region;

    private double lastSceneX;
    private double lastSceneY;
    private double lastTranslateX;
    private double lastTranslateY;

    public MoveFeature(Region region) {
        this.region = region;
        this.lastSceneX = 0;
        this.lastSceneY = 0;
        this.lastTranslateX = 0;
        this.lastTranslateY = 0;
    }

    protected Region getRegion() {
        return region;
    }

    protected double getLastSceneX() {
        return lastSceneX;
    }

    protected void setLastSceneX(double lastSceneX) {
        this.lastSceneX = lastSceneX;
    }

    protected double getLastSceneY() {
        return lastSceneY;
    }

    protected void setLastSceneY(double lastSceneY) {
        this.lastSceneY = lastSceneY;
    }

    protected double getLastTranslateX() {
        return lastTranslateX;
    }

    protected void setLastTranslateX(double lastTranslateX) {
        this.lastTranslateX = lastTranslateX;
    }

    protected double getLastTranslateY() {
        return lastTranslateY;
    }

    protected void setLastTranslateY(double lastTranslateY) {
        this.lastTranslateY = lastTranslateY;
    }

    public void startMove(double newSceneX, double newSceneY) {
        setLastTranslateX(getRegion().getTranslateX());
        setLastTranslateY(getRegion().getTranslateY());
        setLastSceneX(newSceneX);
        setLastSceneY(newSceneY);
    }

    public void move(double newSceneX, double newSceneY) {
        getRegion().setTranslateX(getLastTranslateX() + newSceneX - getLastSceneX());
        getRegion().setTranslateY(getLastTranslateY() + newSceneY - getLastSceneY());
    }
}
