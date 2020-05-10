package UI.Controls;

import Simulation.Objects.MetaObjects.MetaObject;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TabElement extends VBox {

    private MetaObject metaObject;

    public TabElement(MetaObject metaObject) {
        super(10);
        this.metaObject = metaObject;
        this.setAlignment(Pos.BOTTOM_CENTER);

        Label name = new Label(metaObject.getObjectName());
        ImageView image = new ImageView ();
        image.setImage(new Image(metaObject.getObjectImageURL()));
        image.setFitWidth(100);
        image.setFitHeight(100);

        this.getChildren().addAll(image, name);
    }

    public MetaObject getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }
}
