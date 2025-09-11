package RenderComponents;

import Main.IWindowMoved;
import Main.IWindowRescaled;
import Main.Window;

public class MeasurementHandler {
	private static int WindowWidth = 0;
	private static int WindowHeight = 0;
	private static int WindowX = 0;
	private static int WindowY = 0;
	
	public static Window window;
	
	public static void setWindow(Window window) {
		MeasurementHandler.window = window;
		
		WindowX = window.getXOnScreen();
		WindowY = window.getYOnScreen();
		WindowWidth = window.getWidth();
		WindowHeight = window.getHeight();
		
		window.setWindowMovedListener(new IWindowMoved() {
			
			@Override
			public void windowMoved(int x, int y) {
				// TODO Auto-generated method stub
				WindowX = window.getXOnScreen();
				WindowY = window.getYOnScreen();
			}
		});
		
		window.setWindowRescaledListener(new IWindowRescaled() {
			
			@Override
			public void rescaled(int width, int height) {
				// TODO Auto-generated method stub
				WindowWidth = window.getWidth();
				WindowHeight = window.getHeight();
			}
		});
	}
}
