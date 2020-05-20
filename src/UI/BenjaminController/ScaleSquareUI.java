package UI.BenjaminController;

import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Primitives.RectangleLine;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScaleSquareUI {
    private LineModel rectangleLine;
    public static BasicShader shader = new BasicShader("Line");

    private ArrayList<Vector2f> vertices = new ArrayList<Vector2f>();

    float radius;

    public ScaleSquareUI (float size, float r, float g, float b, float x, float y) {
        this.radius = size;
        rectangleLine = new LineModel(new RectangleLine(size, 0), r, g, b, x, y);

        vertices.add(new Vector2f(rectangleLine.getX()-this.getRadius()/2,rectangleLine.getY()-this.getRadius()/2));
        vertices.add(new Vector2f(rectangleLine.getX()+this.getRadius()/2,rectangleLine.getY()-this.getRadius()/2));
        vertices.add(new Vector2f(rectangleLine.getX()+this.getRadius()/2,rectangleLine.getY()+this.getRadius()/2));
        vertices.add(new Vector2f(rectangleLine.getX()-this.getRadius()/2,rectangleLine.getY()+this.getRadius()/2));
    }

    public void calculateVertices() {
        vertices.set(0, new Vector2f(rectangleLine.getX()-this.getRadius()/2,rectangleLine.getY()-this.getRadius()/2));
        vertices.set(1, new Vector2f(rectangleLine.getX()+this.getRadius()/2,rectangleLine.getY()-this.getRadius()/2));
        vertices.set(2, new Vector2f(rectangleLine.getX()+this.getRadius()/2,rectangleLine.getY()+this.getRadius()/2));
        vertices.set(3, new Vector2f(rectangleLine.getX()-this.getRadius()/2,rectangleLine.getY()+this.getRadius()/2));

        System.out.println(vertices);
    }

    public LineModel getRectangleLine() {
        return rectangleLine;
    }

    public void setRectangleLine(LineModel rectangleLine) {
        this.rectangleLine = rectangleLine;
    }

    public static BasicShader getShader() {
        return shader;
    }

    public static void setShader(BasicShader shader) {
        ScaleSquareUI.shader = shader;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public ArrayList<Vector2f> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vector2f> vertices) {
        this.vertices = vertices;
    }
}
