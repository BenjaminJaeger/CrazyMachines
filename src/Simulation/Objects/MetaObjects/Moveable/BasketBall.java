package Simulation.Objects.MetaObjects.Moveable;

import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.MovableObjects.Ball.BastetBall;

public class BasketBall extends MetaObject {
	

    public BasketBall(String objName, String objImgURL) {
        super(objName, objImgURL);
    }

    @Override
    public void createObject (float x, float y) {
        new BastetBall( x, y);
    }

}
