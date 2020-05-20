package UI.BenjaminController;

import Simulation.Objects.GameObject;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Models.Model;
import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjectTransformer {
    private RotationCircleUI circleUI;
    private ScaleSquareUI squareUI;

    public ObjectTransformer(GameObject gameObject) {
        float radius = calculateRadius(gameObject);
        circleUI = new RotationCircleUI(radius + 20, 30, 0,0,0, gameObject.getX(), gameObject.getY());
        squareUI = new ScaleSquareUI(radius*2+10, 0,0,0,gameObject.getX(), gameObject.getY());

    }

    //Radius des Kreises bestimmen, der um das GameObject gezogen wird
    public float calculateRadius(GameObject gameObject) {
        //ausgegebener Radius kann auch die Länge eines Rechtecks werden
        float distance;

        ArrayList<Vector2f> vertices = new ArrayList<Vector2f>();
        Vector2f gameObjectPosition = new Vector2f(gameObject.getX(), gameObject.getY());

        float pointCenterDistance;
        float temporaryLongestDistance = 0;

        //Testen, ob GameObject Sphere oder Polygon ist
        if (gameObject.getCollisionContext().getBoundingPolygons().length == 0) {
            distance = gameObject.getCollisionContext().getBoundingCircle(0).getRadius();
            return distance;
        }

        else if (gameObject.getCollisionContext().getBoundingCircles().length == 0) {
            for (int vertexAddCounter = 0; vertexAddCounter < gameObject.getCollisionContext().getBoundingPolygons().length; vertexAddCounter++) {
                vertices.addAll(Arrays.asList(gameObject.getCollisionContext().getBoundingPolygon(vertexAddCounter).getPoints()));
            }

            for (int i = 0; i < vertices.size(); i++) {
                //Abstandsvektor zum Mittelpunkt berechnen
                if (i == 0) {
                    temporaryLongestDistance = calculateDistance(vertices.get(0), gameObjectPosition);
                }

                pointCenterDistance = calculateDistance(vertices.get(i), gameObjectPosition);

                if (pointCenterDistance > temporaryLongestDistance) {
                    temporaryLongestDistance = pointCenterDistance;
                }
            }

            distance = temporaryLongestDistance;
            return distance;
        }

        return 0;
    }

    private float calculateDistance(Vector2f vecOne, Vector2f vecTwo) {
        Vector2f newVector = new Vector2f(0,0);
        newVector.x = vecTwo.x - vecOne.x;
        newVector.y = vecTwo.y - vecOne.y;

        float length;
        length = (float) Math.sqrt((newVector.x * newVector.x) + (newVector.y * newVector.y));

        return length;
    }

    public RotationCircleUI getCircleUI() {
        return circleUI;
    }

    public void setCircleUI(RotationCircleUI circleUI) {
        this.circleUI = circleUI;
    }

    public ScaleSquareUI getSquareUI() {
        return squareUI;
    }

    public void setSquareUI(ScaleSquareUI squareUI) {
        this.squareUI = squareUI;
    }

    public void setX(float x) {
        circleUI.getCircleModel().setX(x);
        squareUI.getRectangleLine().setX(x);

        squareUI.calculateVertices();
    }

    public void setY(float y) {
        circleUI.getCircleModel().setY(y);
        squareUI.getRectangleLine().setY(y);
    }
}
