package photobook;

public interface IRotationFeature {
    double INDENT_FROM_BORDER_TO_ROTATION_AREA = IResizeFeature.INDENT_FROM_BORDER_TO_RESIZE_AREA + 5;
    double ROTATION_AREA_WIDTH = 30;

    void startRotation(double mouseX, double mouseY, double screenY);
    boolean rotate(double newScreenY);
    boolean isMousePointerInRotationArea(double mouseX, double mouseY);
    void changeMouseCursor(boolean mousePressed, double mouseX, double mouseY);
}
