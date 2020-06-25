package Simulation.Objects.StaticObjects.StaticExternalObjects;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.CircleLine;
import Simulation.Util;
import com.jogamp.nativewindow.util.Point;

public class Magnet extends StaticExternalObject{

    private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 4f);
    private static Vector2f negativeSrc, positiveSrc;
    private float offset = 65;
    private double charge = 2.7 * Math.pow(10,-6); //in coulomb, equals 25000 gauss -> gauss:coulomb - 8.9*10^11:1

    //DEBUG
    private LineModel line1, line2;
    private CircleLine circle1 = new CircleLine (5, 10, 0);

    public Magnet(float x, float y) {
        super("stabmagnet_tri","PlaneTriangles","magnetTexture.png", material, x, y);
        //reference points for magnetic sources (both ends)
        negativeSrc = new Vector2f (x, y);
        positiveSrc = new Vector2f (x, y);

        setScale(1f);

        line1 = new LineModel(circle1, 0,0,0,negativeSrc.getX(), negativeSrc.getY());
        line2 = new LineModel(circle1, 0,0,0,positiveSrc.getX(), positiveSrc.getY());
    }

    @Override
    public void onCollision () {
    }

    @Override
    public void setScale (float scale) {
        super.setScale(scale);
        //reset position, set new offset
        negativeSrc.setX(this.getX()-(offset*scale));
        positiveSrc.setX(this.getX()+(offset*scale));
        negativeSrc.setY(this.getY());
        positiveSrc.setY(this.getY());
        //rotate correctly
        negativeSrc = Util.rotateArroundPoint(negativeSrc.getX(), negativeSrc.getY(), this.getRotation(), this.getX(), this.getY());
        positiveSrc = Util.rotateArroundPoint(positiveSrc.getX(), positiveSrc.getY(), this.getRotation(), this.getX(), this.getY());
    }

    @Override
    public void setRotation (float rotation) {
        float alpha = rotation-this.getRotation();

        negativeSrc = Util.rotateArroundPoint(negativeSrc.getX(), negativeSrc.getY(), alpha, this.getX(), this.getY());
        positiveSrc = Util.rotateArroundPoint(positiveSrc.getX(), positiveSrc.getY(), alpha, this.getX(), this.getY());

        super.setRotation(rotation);
    }

    @Override
    public void setX (float x) {
        float deltaX = x-this.getX();
        negativeSrc.setX(negativeSrc.getX()+deltaX);
        positiveSrc.setX(positiveSrc.getX()+deltaX);

        super.setX(x);
    }

    @Override
    public void setY (float y) {
        float deltaY = y-this.getY();
        negativeSrc.setY(negativeSrc.getY()+deltaY);
        positiveSrc.setY(positiveSrc.getY()+deltaY);

        super.setY(y);
    }

    @Override
    public void update() {
        double threshold = 2000; //set infinite for full realism, maybe not needed

        for (GameObject object: allObjects) {
            if (object instanceof MoveableObject) {
                Vector2f repel = new Vector2f(negativeSrc.getX()-object.getX(), negativeSrc.getY()-object.getY());
                Vector2f attract = new Vector2f(object.getX()-positiveSrc.getX(), object.getY()-positiveSrc.getY());

                double attractDistance = Util.calcVectorSize(attract);
                double repelDistance = Util.calcVectorSize(repel);

                if (attractDistance < repelDistance) {
                    Vector2f attractNorm = Util.normVector(attract);
                    float accX = attractNorm.getX() * forceFunction(attractDistance, charge);
                    float accY = attractNorm.getY() * forceFunction(attractDistance, charge);
                    ((MoveableObject) object).applyForce(accX, accY);
                }

                else {
                    Vector2f repelNorm = Util.normVector(repel);
                    float accX = repelNorm.getX() * forceFunction(repelDistance, charge);
                    float accY = repelNorm.getY() * forceFunction(repelDistance, charge);
                    ((MoveableObject) object).applyForce(accX, accY);
                }
                }
            }
        }

    private float forceFunction (double r, double Q) {
        final double e0 = 8.854187 * Math.pow(10,-12); //constant
        double eR = 1; //depending on material, 1 full magnetism -> 0 no magnetism
        double eQ = 1.602/*Math.pow(10,-2)*/; //charge of object
        float f =  (float)((1/(4*Math.PI*eR*e0)*(Q*eQ))/(Math.pow(r,2)));

        return 20;
        //System.out.println(f);
        //return f*100;
    }


}