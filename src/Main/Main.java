package Main;

import Render.Engine;
import RenderComponents.Dimension;
import RenderComponents.Measurement;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		window.build("Test", 500, 500);
		window.setMinSize(300, 200);
		window.setBufferFPS(
				(int)(window.getRefreshRate() * 1.5f)
		);
		
		IfpsListener fpsListener = (int oldfps, int newfps) -> {
			window.setTitle("Test | FPS: "+newfps);
		};
		
		window.setFPSListener(fpsListener);
		
		Dimension.setWindow(window);
		
		IGraphicWindow engine = new Engine();
		window.setGraphicWindow(engine);
		
		window.startEngine();
	}
}
