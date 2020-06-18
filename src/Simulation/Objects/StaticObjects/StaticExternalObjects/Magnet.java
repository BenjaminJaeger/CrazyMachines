package Simulation.Objects.StaticObjects.StaticExternalObjects;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.Util;
import com.jogamp.nativewindow.util.Point;

public class Magnet extends StaticExternalObject{

    private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 4f);
    private static Vector2f negativeSrc, positiveSrc;
    private float offset = 10;
    private double charge = 2.7 * Math.pow(10,-6); //in coulomb, equals 25000 gauss -> gauss:coulomb - 8.9*10^11:1

    public Magnet(float x, float y) {
        super("stabmagnet_tri","PlaneTriangles","magnetTexture.png", material, x, y);

        setScale(1f);
        setOriginalscale(1f);
        negativeSrc = new Vector2f (x, y-(offset*this.getScale()));
        positiveSrc = new Vector2f (x, y-(offset*this.getScale()));
    }

    @Override
    public void update() {
        double threshold = 2000; //set infinite for full realism
        Vector2f newNegative = Util.rotateArroundPoint(negativeSrc.getX(), negativeSrc.getY(), this.getRotation(), this.getX(), this.getY());
        Vector2f newPositive = Util.rotateArroundPoint(positiveSrc.getX(), positiveSrc.getY(), this.getRotation(), this.getX(), this.getY());

        for (GameObject object: allObjects) {
            if (object instanceof MoveableObject) {
                Vector2f repel = new Vector2f(newNegative.getX()-object.getX(), newNegative.getY()-object.getY());
                Vector2f attract = new Vector2f(object.getX()-newPositive.getX(), object.getY()-newPositive.getY());

                double attractDistance = Util.calcVectorSize(attract);
                double repelDistance = Util.calcVectorSize(repel);

                float mass = object.getMass();

                if (attractDistance < repelDistance) {
                    Vector2f attractNorm = Util.normVector(attract);
                    float accX = attractNorm.getX() * forceFunction(attractDistance, charge, mass);
                    float accY = attractNorm.getY() * forceFunction(attractDistance, charge, mass);
                    ((MoveableObject) object).applyForce(accX, accY);
                }

                else {
                    Vector2f repelNorm = Util.normVector(repel);
                    float accX = repelNorm.getX() * forceFunction(repelDistance, charge, mass);
                    float accY = repelNorm.getY() * forceFunction(repelDistance, charge, mass);
                    ((MoveableObject) object).applyForce(accX, accY);
                }
                }
            }
        }


    private float forceFunction (double r, double Q, float m) {
        final double e0 = 8.854187 * Math.pow(10,-12);
        double eR = 1;
        double eQ = 1.602*Math.pow(10,-2);
        double f =  (1/(4*Math.PI*eR*e0)*(Q*eQ))/(Math.pow(r,2));

        return 50;
        //System.out.println((float)(f/m));
        //return (float)(f/m);
    }
}