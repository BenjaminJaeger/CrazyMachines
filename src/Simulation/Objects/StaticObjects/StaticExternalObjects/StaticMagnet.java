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
        double thresholdAngle = 7.5;
        int thresholdDistance = 200;
        Vector2f directional = Util.calculateDirection(-1, 0, this.getRotation());

        for (GameObject object : allObjects) {
            if (object instanceof MoveableObject) {
                Vector2f connecting = new Vector2f (object.getX() - this.getX(), object.getY() - this.getY());
                double distance = Util.calcVectorSize(connecting);
                double scalar = Util.calcScalar(directional, connecting);

                if (scalar > thresholdAngle && distance <= thresholdDistance) {
                    ((MoveableObject) object).applyForce(35, 0);
                }
            }
        }
    }
}