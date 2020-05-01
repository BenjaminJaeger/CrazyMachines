module jfx {
	exports RenderEngine.Core.Camera;
	exports RenderEngine.Core.CatchErrors;
	exports RenderEngine.Core.Lights;
	exports RenderEngine.Core.Renderer;
	exports RenderEngine.Core.Math;
	exports RenderEngine.Core.Models;
	exports RenderEngine.Primitives;
	exports RenderEngine.Core.Shaders.Core;
	exports RenderEngine.Core;
	exports Collisions.BoundingCreator;
	exports MAIN.Simulation;
	exports MAIN.UI;

	requires transitive gluegen.rt;
	requires transitive java.desktop;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.swing;
	requires transitive jogl.all;
}