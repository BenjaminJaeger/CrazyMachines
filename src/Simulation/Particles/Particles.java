package Simulation.Particles;

import Simulation.RenderEngine.Core.Models.InstancedModel;
import Simulation.RenderEngine.Core.Shaders.Core.Material;
import Simulation.RenderEngine.Primitives.Primitive;

public class Particles extends InstancedModel {
    private float [] vx;
    private float [] vy;
    private float life;

    public Particles(Primitive primitive, int instances, Material material, float[] colors, float life) {
        super(primitive, instances, material, colors);
        vx = new float[instances];
        vy = new float[instances];
        this.life = life;
    }

    public float[] getVx() {
        return vx;
    }

    public void setVx(float[] vx) {
        this.vx = vx;
    }

    public float[] getVy() {
        return vy;
    }

    public void setVy(float[] vy) {
        this.vy = vy;
    }

    public float getVxElement(int index) {
        return vx[index];
    }

    public float getVyElement(int index) {
        return vy[index];
    }


}
