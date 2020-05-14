package Simulation.Objects.MetaObjects.Moveable;

import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.MovableObjects.Ball.TestBall;

public class TestBallMeta extends MetaObject {
	

    public TestBallMeta(String objName, String objImgURL) {
        super(objName, objImgURL);
    }

    @Override
    public void createObject (float x, float y) {
        new TestBall( x, y);
    }

}
