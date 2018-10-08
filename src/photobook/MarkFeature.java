package photobook;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class MarkFeature implements IMarkFeature {
    private Region region;

    private boolean marked;

    public MarkFeature(Region region) {
        super();
        this.region = region;
        this.marked = false;
    }

    protected Region getRegion() {
        return this.region;
    }

    protected boolean isMarked() {
        return this.marked;
    }

    protected void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void markRegion(){
        Effect effect = new DropShadow(10, Color.DARKBLUE);
        getRegion().setEffect(effect);
        setMarked(true);
    }

    public void demarkRegionIfNecessary() {
        if (isMarked()){
            demarkRegion();
        }
    }

    private void demarkRegion(){
        Effect effect = new DropShadow(10, Color.RED);
        getRegion().setEffect(null);
        setMarked(false);
    }

    public void demarkOtherRegionsOfScene(){
        for (Node node : getRegion().getScene().getRoot().getChildrenUnmodifiable()){
            Event markEvent = new MarkEvent(this, node);
            node.fireEvent(markEvent);
        }
    }
}
