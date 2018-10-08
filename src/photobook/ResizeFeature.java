package photobook;

import javafx.scene.Cursor;
import javafx.scene.layout.Region;

public class ResizeFeature implements IResizeFeature {
    private Region region;

    public ResizeFeature(Region region) {
        this.region = region;
    }

    private Region getRegion() {
        return region;
    }

    private boolean isVergroesserungsbereichRechteEckeOben(double x, double y) {
        return isInRectangle(x, y,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isVergroesserungsbereichRechteEckeUnten(double x, double y) {
        return isInRectangle(x, y,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isVergroesserungsbereichLinkeEckeOben(double x, double y) {
        return isInRectangle(x, y,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isVergroesserungsbereichLinkeEckeUnten(double x, double y) {
        return isInRectangle(x, y,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isInRectangle(double x, double y, double left, double right, double top, double bottom){
        return (x>=left && x<=right && y>=top && y<=bottom);
    }

    public void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY) {
        if (isVergroesserungsbereichRechteEckeOben(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.NE_RESIZE);
        } else if (isVergroesserungsbereichRechteEckeUnten(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.SE_RESIZE);
        } else if (isVergroesserungsbereichLinkeEckeOben(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.NW_RESIZE);
        } else if (isVergroesserungsbereichLinkeEckeUnten(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.SW_RESIZE);
        }
    }
}
