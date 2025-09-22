package Main;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.ImageCapabilities;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import javax.swing.JFrame;

import Modifiers.Quality;
import RenderComponents.Entity;

public class Window extends Entity implements Runnable {
	
	private static JFrame frame;
	private boolean running = false;
	private int refreshRate = 0;
	private int FPS = 0;
	private int bufferFPS = 0;
	private IfpsListener fpsChanged;
	private IGraphicWindow graphicWindow;
	private IWindowRescaled windowRescaledListener;
	private IWindowMoved windowMovedListener;
	
	private HashMap<Key, Object> renderHints = new HashMap<>();
	
	private double FRAME_PER_NANOS;
	
	public void build(String title, int width, int height) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		setQuality(Quality.LOW);
		
		setBufferFPS(0);
		
		ComponentListener componentListener = new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
				if(windowRescaledListener != null) {
					JFrame window = (JFrame)e.getSource();
					windowRescaledListener.rescaled(
							window.getWidth(), 
							window.getHeight()
					);
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				if(windowMovedListener != null) {
					JFrame window = (JFrame)e.getSource();
					
					Point pointOnScreen = window.getLocationOnScreen();
					
					windowMovedListener.windowMoved(
						pointOnScreen.x, 
						pointOnScreen.y
					);
				}
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
		};
		
		frame.addComponentListener(componentListener);
	}
	
	public void setTitle(String title) {
		frame.setTitle(title);
	}
	
	public void setMinSize(int width, int height) {
		frame.setMinimumSize(new Dimension(width, height));
	}
	
	public void setMaxSize(int width, int height) {
		frame.setMaximumSize(new Dimension(width, height));
	}
	
	public void startEngine() {
		running = true;
		if(this.graphicWindow != null) {
			this.graphicWindow.setup(this);
		}
		new Thread(this).run();
	}
	
	public int getRefreshRate() {
		return refreshRate;
	}
	
	public void setFPSListener(IfpsListener method) {
		fpsChanged = method;
	}
	
	public void setBufferFPS(int bufferfps) {
		this.bufferFPS = bufferfps;
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        
        refreshRate = device.getDisplayMode().getRefreshRate();
        
        final double NANOS_PER_SECOND = 1_000_000_000.0;
        FRAME_PER_NANOS = (refreshRate + bufferFPS) / NANOS_PER_SECOND;
	}
	
	public void setGraphicWindow(IGraphicWindow graphicWin) {
		this.graphicWindow = graphicWin;
	}
	
	public void setWindowRescaledListener(IWindowRescaled windowRescale) {
		this.windowRescaledListener = windowRescale;
	}
	
	public void setWindowMovedListener(IWindowMoved windowMoved) {
		this.windowMovedListener = windowMoved;
	}
	
	public int getFPS() {
		return FPS;
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
	
	public int getXOnScreen() {
		return frame.getLocation().x;
	}
	
	public int getYOnScreen() {
		return frame.getLocation().y;
	}
	
	public void setQuality(Quality quality) {
		renderHints.clear();
		switch(quality) {
		case LOW:
			lowQuality();
			break;
		case MEDIUM:
			mediumQuality();
			break;
		case HIGH:
			highQuality();
			break;
		default:
			lowQuality();
			break;
		}
	}
	
	private void lowQuality() {
		renderHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		renderHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		renderHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		renderHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		renderHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
		renderHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		renderHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	}
	
	private void mediumQuality() {
		renderHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
		renderHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_DEFAULT);
		renderHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		renderHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		renderHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	}
	
	private void highQuality() {
		renderHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		renderHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		renderHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		renderHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		renderHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		renderHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		renderHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	}

	public void run() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        
        refreshRate = device.getDisplayMode().getRefreshRate();
        
        float delta = 0f;
        
        long now = System.nanoTime(),
        	last = System.nanoTime();

        final double NANOS_PER_SECOND = 1_000_000_000.0;
        FRAME_PER_NANOS = (refreshRate + bufferFPS) / NANOS_PER_SECOND;
        
        long timer = System.currentTimeMillis();
        int fps = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - last) * FRAME_PER_NANOS;
			last = now;
			
			if(delta > 1) {
				render();
				tick();
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				if(fpsChanged != null) {
					fpsChanged.listen(FPS, fps);
				}
				FPS = fps;
				fps = 0;
			}
		}
	}
	
	public void tick() {
		for(Entity entity : getDescendents()) {
			entity.tick();
		}
		graphicWindow.tick(this);
	}
	
	private void render() {
		BufferStrategy bs = frame.getBufferStrategy();
		if(bs == null) {
			ImageCapabilities imageCapability = new ImageCapabilities(false);
			
			BufferCapabilities bc = new BufferCapabilities(
					imageCapability,
					imageCapability, 
					FlipContents.PRIOR
			);
			try {
				frame.createBufferStrategy(3, bc);
			} catch (AWTException e) {
				frame.createBufferStrategy(3);
			}
			return;
		}
		
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2.setRenderingHints(renderHints);
		
		this.render(g2);
		graphicWindow.render(this, g2);
		
		g2.dispose();
		bs.show();
	}

	@Override
	public void render(Graphics2D g2) {
		for(Entity entity : getDescendents()) {
			entity.render(g2);
		}
	}
}
