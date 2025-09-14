package RenderComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import Modifiers.Overflow;

public class GraphicsAttributes extends DefaultAttributes {

	private Measurement borderSize0, borderSize1, borderSize2, borderSize3 ;
	private Color borderColor = null;
	private float borderTransparency = 0;
	private Measurement borderRadius0, borderRadius1, borderRadius2, borderRadius3; 
	
	private Color backgroundColor = null;
	private float backgroundTransparency = 0;
	private Overflow overflow;
	private IEvent eventListener = null;
	
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
	
	public void setBorderSize(Measurement allsides) {
		allsides.setValue(Math.max(allsides.getValue(), 0));
		
		borderSize0 = allsides;
		borderSize1 = allsides;
		borderSize2 = allsides;
		borderSize3 = allsides;
	}
	
	public void setBorderSize(
			Measurement topLeft, 
			Measurement bottomRight
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		
		borderSize0 = topLeft;
		borderSize2 = bottomRight;
	}
	
	public void setBorderSize(
			Measurement topLeft, 
			Measurement topRight, 
			Measurement bottomRight,
			Measurement bottomLeft
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		topRight.setValue(Math.max(topRight.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		bottomLeft.setValue(Math.max(bottomLeft.getValue(), 0));
		
		borderSize0 = topLeft;
		borderSize1 = topRight;
		borderSize2 = bottomRight;
		borderSize3 = bottomLeft;
	}
	
	public void setBorderSize(
			Measurement topLeft, 
			Measurement topRightAndBottomLeft, 
			Measurement bottomRight
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		topRightAndBottomLeft.setValue(Math.max(topRightAndBottomLeft.getValue(), 0));
		
		borderSize0 = topLeft;
		borderSize1 = topRightAndBottomLeft;
		borderSize2 = bottomRight;
		borderSize3 = topRightAndBottomLeft;
	}
	
	public Measurement[] getBorderSize() {
		return new Measurement[] {
				borderSize0,
				borderSize1,
				borderSize2,
				borderSize3
		};
	}
	
	public void setBorderRadius(Measurement allsides) {
		allsides.setValue(Math.max(allsides.getValue(), 0));
		
		borderRadius0 = allsides;
		borderRadius1 = allsides;
		borderRadius2 = allsides;
		borderRadius3 = allsides;
	}
	
	public void setBorderRadius(
			Measurement topLeft, 
			Measurement bottomRight
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		
		borderRadius0 = topLeft;
		borderRadius2 = bottomRight;
	}
	
	public void setBorderRadius(
			Measurement topLeft, 
			Measurement topRight, 
			Measurement bottomRight,
			Measurement bottomLeft
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		topRight.setValue(Math.max(topRight.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		bottomLeft.setValue(Math.max(bottomLeft.getValue(), 0));
		
		borderRadius0 = topLeft;
		borderRadius1 = topRight;
		borderRadius2 = bottomRight;
		borderRadius3 = bottomLeft;
	}
	
	public void setBorderRadius(
			Measurement topLeft, 
			Measurement topRightAndBottomLeft, 
			Measurement bottomRight
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		topRightAndBottomLeft.setValue(Math.max(topRightAndBottomLeft.getValue(), 0));
		
		borderRadius0 = topLeft;
		borderRadius1 = topRightAndBottomLeft;
		borderRadius2 = bottomRight;
		borderRadius3 = topRightAndBottomLeft;
	}
	
	public Measurement[] getBorderRadius() {
		return new Measurement[]{
				borderRadius0, 
				borderRadius1, 
				borderRadius2, 
				borderRadius3
		};
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
	
	public void setOverflow(Overflow overflow) {
		this.overflow = overflow;
	}
	
	public Overflow getOverflow() {
		return this.overflow;
	}
	
	public void setEventListener(IEvent eventListener) {
		this.eventListener = eventListener;
	}
	
	
	@Override
	public void render(Graphics2D g2) {}
	@Override
	public void tick() {}

}