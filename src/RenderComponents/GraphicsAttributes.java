package RenderComponents;

import java.awt.Graphics2D;

public class GraphicsAttributes extends DefaultAttributes {

	private int borderSize = 0;
	private int borderColor = 0;
	private float borderTransparency = 0;
	private float borderRadius = 0;
	
	private int backgroundColor = 0;
	private float backgroundTransparency = 0;
	
	
	private float setTransparency(float value) {
		return Math.max(0, Math.min(1, value));
	}
	
	public void setBackgroundTransparency(float value) {
		backgroundTransparency = setTransparency(value);
	}
	
	public void setBorderTransparency(float value) {
		borderTransparency = setTransparency(value);
	}
	
	@Override
	public void render(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
