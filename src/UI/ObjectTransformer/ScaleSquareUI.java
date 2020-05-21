package UI.ObjectTransformer;


import java.util.ArrayList;

import Simulation.RenderEngine.Core.Config;
import Simulation.RenderEngine.Core.Math.Vector2f;
import Simulation.RenderEngine.Core.Models.LineModel;
import Simulation.RenderEngine.Core.Shaders.Core.BasicShader;
import Simulation.RenderEngine.Primitives.RectangleLine;

public class ScaleSquareUI {
	
    private LineModel rectangleLine;
    public static BasicShader shader = new BasicShader("Line");

    private ArrayList<Vector2f> verticesSmaller = new ArrayList<Vector2f>();
    private ArrayList<Vector2f> verticesBigger = new ArrayList<Vector2f>();

    float radius;

    public ScaleSquareUI (float size, float r, float g, float b, float x, float y) {
        this.radius = size;
        rectangleLine = new LineModel(new RectangleLine(size, 0), r, g, b, x, y);

        verticesSmaller.add(0, new Vector2f((rectangleLine.getMesh().getVertices()[0] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[1]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.add(1, new Vector2f((rectangleLine.getMesh().getVertices()[3] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[4]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.add(2, new Vector2f((rectangleLine.getMesh().getVertices()[6] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[7]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.add(3, new Vector2f((rectangleLine.getMesh().getVertices()[9] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[10]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));

        verticesBigger.add(0, new Vector2f((rectangleLine.getMesh().getVertices()[0] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[1]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.add(1, new Vector2f((rectangleLine.getMesh().getVertices()[3] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[4]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.add(2, new Vector2f((rectangleLine.getMesh().getVertices()[6] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[7]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.add(3, new Vector2f((rectangleLine.getMesh().getVertices()[9] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[10]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
    }

    public void calculateVertices() {
        verticesSmaller.set(0, new Vector2f((rectangleLine.getMesh().getVertices()[0] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[1]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.set(1, new Vector2f((rectangleLine.getMesh().getVertices()[3] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[4]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.set(2, new Vector2f((rectangleLine.getMesh().getVertices()[6] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[7]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));
        verticesSmaller.set(3, new Vector2f((rectangleLine.getMesh().getVertices()[9] * Config.CANVAS_WIDTH)*0.8f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[10]*Config.CANVAS_HEIGHT)*0.8f+rectangleLine.getY()));

        verticesBigger.set(0, new Vector2f((rectangleLine.getMesh().getVertices()[0] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[1]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.set(1, new Vector2f((rectangleLine.getMesh().getVertices()[3] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[4]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.set(2, new Vector2f((rectangleLine.getMesh().getVertices()[6] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[7]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
        verticesBigger.set(3, new Vector2f((rectangleLine.getMesh().getVertices()[9] * Config.CANVAS_WIDTH)*1.2f+rectangleLine.getX(),(rectangleLine.getMesh().getVertices()[10]*Config.CANVAS_HEIGHT)*1.2f+rectangleLine.getY()));
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

    public ArrayList<Vector2f> getVerticesSmaller() {
        return verticesSmaller;

    }

    public ArrayList<Vector2f> getVerticesBigger() {
        return verticesBigger;
    }

    public void setScale(float scale) {
        rectangleLine.setScale(scale);
        calculateVertices();
    }
    
    public void setX(float x) {
    	rectangleLine.setX(x);
    	calculateVertices();
    }
    
    public void setY(float y) {
    	rectangleLine.setY(y);
    	calculateVertices();
    }
    
}
