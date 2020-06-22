module jfx {
	exports Simulation.RenderEngine.Core.Camera;
	exports Simulation.RenderEngine.Core.CatchErrors;
	exports Simulation.RenderEngine.Core.Lights;
	exports Simulation.RenderEngine.Core.Renderer;
	exports Simulation.RenderEngine.Core.Math;
	exports Simulation.RenderEngine.Core.Models;
	exports Simulation.RenderEngine.Primitives;
	exports Simulation.RenderEngine.Core.Shaders.Core;
	exports Simulation.RenderEngine.Core;
	exports Simulation.BoundingCreator;
	exports MAIN;
 
	requires  gluegen.rt;
	requires  java.desktop;
	requires  javafx.base;
	requires  javafx.controls;
	requires transitive javafx.graphics;
	requires  javafx.swing;
	requires  jogl.all;
	requires javafx.media;
}