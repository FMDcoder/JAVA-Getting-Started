package RenderComponents;

import java.awt.Color;
import java.awt.Graphics2D;

public class GraphicsAttributes extends DefaultAttributes {

	private Measurement borderSize;
	private Color borderColor = null;
	private float borderTransparency = 0;
	private Measurement borderRadius;
	
	private Color backgroundColor = null;
	private float backgroundTransparency = 0;
	
	private float setTransparency(float value) {
		return Math.max(0, Math.min(1, value));
	}
	
	public void setBackgroundTransparency(float value) {
		backgroundTransparency = setTransparency(value);
	}
	
	public float getBackgroundTransparency() {
		return backgroundTransparency;
	}
	
	public void setBorderTransparency(float value) {
		borderTransparency = setTransparency(value);
	}
	
	public float getBorderTransparency() {
		return borderTransparency;
	}
	
	public void setBorderSize(Measurement measurement) {
		if(measurement.getResult() < 0) {
			borderSize = new Measurement(measurement.getMeasurement(), 0);
			return;
		}
		borderSize = measurement;
	}
	
	public Measurement getBorderSize() {
		return borderSize;
	}
	
	public void setBorderRadius(Measurement measurement) {
		if(measurement.getResult() < 0) {
			borderRadius = new Measurement(measurement.getMeasurement(), 0);
			return;
		}
		borderRadius = measurement;
	}
	
	public Measurement getBorderRadius() {
		return borderRadius;
	}
	
	public void setBorderColor(Color color) {
		borderColor = color;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public void setBackgroundColor(Color color) {
		backgroundColor = color;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
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
