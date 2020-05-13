package Simulation.Objects.MetaObjects.Static;

import Simulation.Objects.MetaObjects.MetaObject;
import Simulation.Objects.StaticObjects.StaticBox;

public class PlankMeta extends MetaObject {

    private float height, width, r, g, b;

    public PlankMeta (String objName, String objImageURL, float height, float width, float r, float g, float b) {
        super(objName, objImageURL);
        this.height = height;
        this.width = width;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void createObject (float x, float y) {
       new StaticBox(height, width,  r, g, b, x, y);
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}