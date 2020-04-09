module jfx {
	exports Engine.Core.Camera;
	exports Engine.Core.CatchErrors;
	exports Engine.Core.Lights;
	exports Engine.Core.Renderer;
	exports ComputerGraphics;
	exports Engine.Core.Math;
	exports Engine.Core.Models;
	exports Engine.Primitives;
	exports Engine.Core.Shaders.Core;
	exports Engine.Core;
	exports Main;

	requires transitive gluegen.rt;
	requires transitive java.desktop;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.swing;
	requires transitive jogl.all;
}