package Simulation.Objects.StaticObjects.StaticExternalObjects;

import Simulation.Objects.GameObject;
import Simulation.Objects.MovableObjects.MoveableObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Math.Vector3f;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.Util;

public class StaticMagnet extends StaticExternalObject{

    private static Material material = new Material(new Vector3f(0.2f), new Vector3f(0.5f), new Vector3f(1f), 4f);

    public StaticMagnet(float x, float y) {
        super("MagnetObject","PlaneTriangles","magnetTexture.jpg", material, x, y);
        setScale(0.25f);
        setOriginalscale(0.25f);
    }

    @Override
    public void update() {
        float currentRotation = (float)(this.getRotation()*Math.PI/180);
        Vector2f directional = Util.rotate(-1, 0, currentRotation);
        //angle value between 0 and 180
        double thresholdAngle = 90;
        int thresholdDistance = 200;


        for (GameObject object : allObjects) {
            if (object instanceof MoveableObject) {
                Vector2f connecting = new Vector2f (object.getX() - this.getX(), object.getY() - this.getY());
                double distance = Util.calcVectorSize(connecting);
                double scalar = Util.calcScalar(directional, connecting);

                if (Math.acos(scalar) > thresholdAngle && distance <= thresholdDistance) {
                    float force = 50f; //TO-DO force function
                    Vector2f initForceDirection = Util.rotate(-1, 0, currentRotation);
                    ((MoveableObject) object).applyForce(initForceDirection.getX()*force, initForceDirection.getY()*force);
                }
            }
        }
    }

    private float forceFunction () {
        return 50f;
    }
}