package RenderComponents;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import Main.IWindowMoved;
import Main.IWindowRescaled;
import Main.Window;
import Modifiers.Position;

public class Dimension {
	private double ScaleY, ScaleX;
	private int PixelX, PixelY;
	
	private static int WindowWidth = 0;
	private static int WindowHeight = 0;
	private static int WindowX = 0;
	private static int WindowY = 0;
	
	public static Window window;
	
	private Position position = Position.RELATIVE;
	
	public Dimension(double scaleY, int pixelY, double scaleX, int pixelX) {
		ScaleY = scaleY;
		ScaleX = scaleX;
		
		PixelY = pixelY;
		PixelX = pixelX;
	}
	
	public static void setWindow(Window window) {
		Dimension.window = window;
		
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
	
	public static int getWindowWidth() {
		return WindowWidth;
	}
	
	public static int getWindowHeight() {
		return WindowHeight;
	}
	
	public void setPositionType(Position position) {
		this.position = position;
	}
	
	public Position getPositionType() {
		return this.position;
	}
	
	public double getScaleX() {
		return this.ScaleX;
	}
	
	public void setScaleX(double scaleX) {
		this.ScaleX = scaleX;
	}
	
	public double getScaleY() {
		return this.ScaleY;
	}
	
	public void setScaleY(double scaleY) {
		this.ScaleY = scaleY;
	}
	
	public int getPixelX() {
		return this.PixelX;
	}
	
	public void setPixelX(int pixelX) {
		this.PixelX = pixelX;
	}
	 
	public int getPixelY() {
		return this.PixelY;
	}
	
	public void setPixelY(int pixelY) {
		this.PixelY = pixelY;
	}
	
	private int getPixel(double scale, int size, int pixel) {
		return (int)(scale * size + pixel);
	}
	
	private Point getAbsoluteRelative(Entity parent) {
		
		if(parent instanceof DefaultAttributes) {
			DefaultAttributes defAttr = (DefaultAttributes) parent;
			
			Point offset = defAttr.getPosition()
					.getAbsolute(defAttr.getParent());
			
			Point size = defAttr.getSize()
					.getAbsolute(defAttr.getParent());
			
			Point res = new Point();
			
			res.x = (int)(offset.x + ScaleX * size.x + PixelX);
			res.y = (int)(offset.y + ScaleY * size.y + PixelY);
		
			return res;
		}
		return getAbsoluteAbsolute();
	}
	
	private Point getAbsoluteAbsolute() {
		Point res = new Point();
		
		res.x = getPixel(ScaleX, WindowWidth, PixelX);
		res.y = getPixel(ScaleY, WindowHeight, PixelY);
		
		return res;
	}
	
	private Point getAbsoluteFixed() {
		Point res = new Point();
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
		
		int screenX = device.getDisplayMode().getWidth();
		int screenY = device.getDisplayMode().getHeight();
		
		res.x = getPixel(ScaleX, screenX, PixelX - WindowX);
		res.y = getPixel(ScaleY, screenY, PixelY - WindowY);
		
		return res;
	}
	
	public Point getAbsolute(Entity parent) {
		Point res = new Point();
		
		switch (position) {
		case RELATIVE: {
			res = getAbsoluteRelative(parent);
			break;
		}
		case ABSOLUTE: {
			res = getAbsoluteAbsolute();
		}
		case FIXED: {
			res = getAbsoluteFixed();
		}
		default:
			break;
		}
		return res;
	}
}
