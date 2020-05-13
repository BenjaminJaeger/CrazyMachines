package MAIN.Controller;

import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Primitives.RectangleLine;

public class ScaleSquareUI {
    private LineModel rectangleLine;
    private float scale;
    public static BasicShader shader = new BasicShader("Line");

    float radius;

    public ScaleSquareUI (float size, float r, float g, float b, float x, float y) {
        this.radius = size;
        rectangleLine = new LineModel(new RectangleLine(size, 0), r, g, b, x, y);
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
}
