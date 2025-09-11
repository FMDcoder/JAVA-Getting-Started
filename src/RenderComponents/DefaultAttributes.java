package RenderComponents;

import java.awt.Point;

import Modifiers.Position;

public abstract class DefaultAttributes extends Entity {

	private boolean active = true;

	public void setActivity(boolean active) {
		this.active = active;
	}
	
	public void toggleActivity() {
		this.active = !active;
	}
	
	public boolean getActivity() {
		return this.active;
	}
	
	private Dimension 
		positionDimension = new Dimension(0, 0, 0, 0),
		sizeDimension = new Dimension(0, 0, 0, 0);
	
	public void setPositionType(Position position) {
		if(positionDimension != null) {
			positionDimension.setPositionType(position);
		}
	}
	
	public Position getPositionType() {
		if(positionDimension != null) {
			return positionDimension.getPositionType();
		}
		return null;
	}
	
	public void setPosition(Dimension dimension) {
		this.positionDimension = dimension;
	}
	
	public Dimension getPosition() {
		return this.positionDimension;
	}
	
	public void setSize(Dimension size) {
		this.sizeDimension = size;
	}
	
	public Dimension getSize() {
		return this.sizeDimension;
	}
	
	protected Point getAbsolutePositionPixels() {
		return this.positionDimension.getAbsolute(getParent());
	}
	
	protected Point getAbsoluteSizePixels() {
		return this.sizeDimension.getAbsolute(getParent());
	}
	
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
}
