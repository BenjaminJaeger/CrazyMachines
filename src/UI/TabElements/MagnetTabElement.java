package UI.TabElements;

import Simulation.Objects.GameObject;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticMagnet;
import javafx.scene.layout.Pane;

public class MagnetTabElement extends TabElement{

    public MagnetTabElement(Pane glass) {
        super(glass,"Magnet", "BasketBall.png");
    }

    public MagnetTabElement(Pane glass,int ammount) {
        super(glass,"Magnet", "BasketBall.png",ammount);
    }

    @Override
    public GameObject createObject(float x, float y) {
        return new StaticMagnet(x,y);
    }

}