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
	exports TESTING.Simulation;
	exports TESTING.UI;
	exports MAIN;

	requires transitive gluegen.rt;
	requires transitive java.desktop;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.swing;
	requires transitive jogl.all;
}