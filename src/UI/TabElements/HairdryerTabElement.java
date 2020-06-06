package UI.TabElements;

import Simulation.Objects.GameObject;
import Simulation.Objects.StaticObjects.StaticExternalObjects.StaticHairdryer;
import javafx.scene.layout.Pane;

public class HairdryerTabElement extends TabElement {
    public HairdryerTabElement(Pane glass) {
        super(glass,"Foehn", "Hairdryer.png");
    }

    public HairdryerTabElement(Pane glass,int ammount) {
        super(glass,"Foehn", "Hairdryer.png",ammount);
    }

    @Override
    public GameObject createObject(float x, float y) {
        return new StaticHairdryer(x,y);
    }
}
