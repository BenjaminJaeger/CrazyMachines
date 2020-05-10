package Simulation.Objects.MetaObjects.Moveable;

import Simulation.Objects.MetaObjects.MetaObject;

public class MetallBallMeta extends MetaObject {
    private float r, g ,b, radius;
    private int resolution;

    public MetallBallMeta(String objName, String objImgURL, float radius, int resolution, float r, float g, float b) {
        super(objName, objImgURL);
        this.r = r;
        this.g = g;
        this.b = b;
        this.radius = radius;
        this.resolution = resolution;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}
