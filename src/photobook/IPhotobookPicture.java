package photobook;

import javafx.scene.paint.Color;

public interface IPhotobookPicture extends IResizablePhotobookComponent {
    void setBorder(Color borderColor, int borderWidth);
    void setImage(String imageName);
}
