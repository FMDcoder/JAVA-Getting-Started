package RenderComponents;

import java.awt.Point;

import Modifiers.Position;

public abstract class DefaultAttributes extends Entity {

	private boolean active = true;
	private IActivityListener activityListener;

	public void setActivity(boolean active) {
		if(active == this.active) return;
		
		this.active = active;
		if(activityListener != null) {
			activityListener.activityToggled(this.active);
		}
	}
	
	public void toggleActivity() {
		this.active = !active;
		if(activityListener != null) {
			activityListener.activityToggled(this.active);
		}
	}
	
	public boolean getActivity() {
		return this.active;
	}
	
	public void setActivityListener(IActivityListener listener) {
		this.activityListener = listener;
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
		return this.positionDimension.getAbsolute(getParent(), false);
	}
	
	protected Point getAbsoluteSizePixels() {
		return this.sizeDimension.getAbsolute(getParent(), true);
	}
}
