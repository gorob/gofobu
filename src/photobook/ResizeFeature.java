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

    private boolean isResizeAreaTopRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isResizeAreaRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1);
    }

    private boolean isResizeAreaBottomRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isResizeAreaBottom(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + 1,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - 1,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isResizeAreaTopLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isResizeAreaTop(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getRegion().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isResizeAreaLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1);
    }

    private boolean isResizeAreaBottomLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getRegion().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isInRectangle(double x, double y, double left, double right, double top, double bottom){
        return (x>=left && x<=right && y>=top && y<=bottom);
    }

    public void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY) {
        if (isResizeAreaTopRight(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.NE_RESIZE);
        } else if (isResizeAreaBottomRight(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.SE_RESIZE);
        } else if (isResizeAreaTopLeft(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.NW_RESIZE);
        } else if (isResizeAreaBottomLeft(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.SW_RESIZE);
        } else if (isResizeAreaRight(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.E_RESIZE);
        } else if (isResizeAreaBottom(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.S_RESIZE);
        } else if (isResizeAreaLeft(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.W_RESIZE);
        } else if (isResizeAreaTop(mouseX, mouseY)) {
            getRegion().getScene().setCursor(Cursor.N_RESIZE);
        }
    }
}
