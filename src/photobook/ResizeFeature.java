package photobook;

import javafx.scene.Cursor;
import javafx.scene.layout.Region;

public class ResizeFeature implements IResizeFeature {
    private IResizablePhotobookComponent resizablePhotobookComponent;

    private boolean resizeActive;

    private boolean resizeXDirectionPossible;
    private boolean resizeYDirectionPossible;

    private double lastScreenX;
    private double lastScreenY;

    public ResizeFeature(IResizablePhotobookComponent resizablePhotobookComponent) {
        this.resizablePhotobookComponent = resizablePhotobookComponent;
        this.resizeActive = false;
        this.resizeXDirectionPossible = false;
        this.resizeYDirectionPossible = false;
        this.lastScreenX = 0;
        this.lastScreenY = 0;
    }

    private IResizablePhotobookComponent getResizablePhotobookComponent() {
        return resizablePhotobookComponent;
    }

    private boolean isResizeActive() {
        return resizeActive;
    }

    private void setResizeActive(boolean resizeActive) {
        this.resizeActive = resizeActive;
    }

    private boolean isResizeXDirectionPossible() {
        return resizeXDirectionPossible;
    }

    private void setResizeXDirectionPossible(boolean resizeXDirectionPossible) {
        this.resizeXDirectionPossible = resizeXDirectionPossible;
    }

    private boolean isResizeYDirectionPossible() {
        return resizeYDirectionPossible;
    }

    private void setResizeYDirectionPossible(boolean resizeYDirectionPossible) {
        this.resizeYDirectionPossible = resizeYDirectionPossible;
    }

    private double getLastScreenX() {
        return lastScreenX;
    }

    private void setLastScreenX(double lastScreenX) {
        this.lastScreenX = lastScreenX;
    }

    private double getLastScreenY() {
        return lastScreenY;
    }

    private void setLastScreenY(double lastScreenY) {
        this.lastScreenY = lastScreenY;
    }

    @Override
    public void startResize(double mouseX, double mouseY, double screenX, double screenY) {
        setResizeActive(isMousePointerInResizeArea(mouseX, mouseY));
        setResizeXDirectionPossible(isMousePointerInHorizontalResizeArea(mouseX, mouseY) || isMousePointerInDiagonalResizeArea(mouseX, mouseY));
        setResizeYDirectionPossible(isMousePointerInVerticalResizeArea(mouseX, mouseY) || isMousePointerInDiagonalResizeArea(mouseX, mouseY));
        setLastScreenX(screenX);
        setLastScreenY(screenY);
    }

    @Override
    public boolean resize(double screenX, double screenY) {
        if (!isResizeActive()) {
            return false;
        }

        double distanceX = isResizeXDirectionPossible() ? screenX - getLastScreenX() : 0;
        double distanceY = isResizeYDirectionPossible() ? screenY - getLastScreenY() : 0;

        getResizablePhotobookComponent().resizeComponent(getResizablePhotobookComponent().getWidth() + distanceX,
                getResizablePhotobookComponent().getHeight() + distanceY);

        setLastScreenX(screenX);
        setLastScreenY(screenY);

        return true;
    }

    private boolean isMousePointerInResizeArea(double mouseX, double mouseY) {
        return isMousePointerInHorizontalResizeArea(mouseX, mouseY)
            || isMousePointerInVerticalResizeArea(mouseX, mouseY)
            || isMousePointerInDiagonalResizeArea(mouseX, mouseY);
    }

    private boolean isMousePointerInHorizontalResizeArea(double mouseX, double mouseY) {
        return isMousePointerInResizeAreaLeft(mouseX, mouseY) || isMousePointerInResizeAreaRight(mouseX, mouseY);
    }

    private boolean isMousePointerInVerticalResizeArea(double mouseX, double mouseY) {
        return isMousePointerInResizeAreaBottom(mouseX, mouseY) || isMousePointerInResizeAreaTop(mouseX, mouseY);
    }

    private boolean isMousePointerInDiagonalResizeArea(double mouseX, double mouseY) {
        return isMousePointerInResizeAreaTopLeft(mouseX, mouseY) || isMousePointerInResizeAreaTopRight(mouseX, mouseY)
            || isMousePointerInResizeAreaBottomLeft(mouseX, mouseY) || isMousePointerInResizeAreaBottomRight(mouseX, mouseY);
    }

    private boolean isMousePointerInResizeAreaTopRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isMousePointerInResizeAreaRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1);
    }

    private boolean isMousePointerInResizeAreaBottomRight(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isMousePointerInResizeAreaBottom(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + 1,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - 1,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isMousePointerInResizeAreaTopLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isMousePointerInResizeAreaTop(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getResizablePhotobookComponent().getWidth() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH);
    }

    private boolean isMousePointerInResizeAreaLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH + 1,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH - 1);
    }

    private boolean isMousePointerInResizeAreaBottomLeft(double mouseX, double mouseY) {
        return isInRectangle(mouseX, mouseY,
                INDENT_FROM_BORDER_TO_RESIZE_AREA,
                INDENT_FROM_BORDER_TO_RESIZE_AREA + RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA - RESIZE_AREA_WIDTH,
                getResizablePhotobookComponent().getHeight() - INDENT_FROM_BORDER_TO_RESIZE_AREA);
    }

    private boolean isInRectangle(double x, double y, double left, double right, double top, double bottom){
        return (x>=left && x<=right && y>=top && y<=bottom);
    }

    public void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY) {
        if (isMousePointerInResizeAreaTopRight(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.NE_RESIZE);
        } else if (isMousePointerInResizeAreaBottomRight(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.SE_RESIZE);
        } else if (isMousePointerInResizeAreaTopLeft(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.NW_RESIZE);
        } else if (isMousePointerInResizeAreaBottomLeft(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.SW_RESIZE);
        } else if (isMousePointerInResizeAreaRight(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.E_RESIZE);
        } else if (isMousePointerInResizeAreaBottom(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.S_RESIZE);
        } else if (isMousePointerInResizeAreaLeft(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.W_RESIZE);
        } else if (isMousePointerInResizeAreaTop(mouseX, mouseY)) {
            getResizablePhotobookComponent().setCursor(Cursor.N_RESIZE);
        }
    }
}
