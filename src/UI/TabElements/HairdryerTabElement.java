package UI.TabElements;

import Simulation.Objects.GameObject;
import Simulation.Objects.StaticObjects.StaticExternalObjects.Hairdryer;
import javafx.scene.layout.Pane;

public class HairdryerTabElement extends TabElement {
    public HairdryerTabElement(Pane glass) {
        super(glass,"F�hn", "Hairdryer.png");
    }

    public HairdryerTabElement(Pane glass,int ammount) {
        super(glass,"F�hn", "Hairdryer.png",ammount);
    }

    @Override
    public GameObject createObject(float x, float y) {
        return new Hairdryer(x,y);
    }
}
