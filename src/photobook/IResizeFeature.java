package photobook;

public interface IResizeFeature {
    double INDENT_FROM_BORDER_TO_RESIZE_AREA = 1;
    double RESIZE_AREA_WIDTH = 8;

    void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY);
}