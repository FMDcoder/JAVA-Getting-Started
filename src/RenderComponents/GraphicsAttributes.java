package RenderComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import Modifiers.Overflow;
import Modifiers.Side;

public class GraphicsAttributes extends DefaultAttributes {

	private Measurement borderSize0, borderSize1, borderSize2, borderSize3 ;
	private Color borderColor = null;
	private float borderTransparency = 0;
	private Measurement 
		borderRadius0 = Measurement.Pixel(), 
		borderRadius1 = Measurement.Pixel(), 
		borderRadius2 = Measurement.Pixel(), 
		borderRadius3 = Measurement.Pixel(); 
	
	private Color backgroundColor = null;
	private float backgroundTransparency = 0;
	private Overflow overflow;
	private IEvent eventListener = null;
	
	private Polygon shapePolygon = null;
	
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
		
		invalidateShape();
	}
	
	public void setBorderRadius(
			Measurement topLeft, 
			Measurement bottomRight
		) {
		topLeft.setValue(Math.max(topLeft.getValue(), 0));
		bottomRight.setValue(Math.max(bottomRight.getValue(), 0));
		
		borderRadius0 = topLeft;
		borderRadius2 = bottomRight;
		
		invalidateShape();
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
		
		invalidateShape();
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
		
		invalidateShape();
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
	
	private Polygon getSideCalc(
			double radius, 
			double offsetRad
	) {
		Polygon poly = new Polygon();
		
		Point pos = getAbsolutePositionPixels();
		Point size = getAbsoluteSizePixels();
		
		int steps0 = (int)Math.ceil(radius * Math.PI / 2);
		double angStep = Math.PI / (2 * steps0);
		
		int xo = (int)(1 - Math.sin(offsetRad));
		int yo = (int)(1 - Math.cos(offsetRad));
		
		for(int i = 0; i < steps0; i++) {
			int x = (int)(
					Math.cos(i * angStep) * radius 
					+ pos.x + (size.x >> 1) * xo);
			
			int y = (int)(
					Math.sin(i * angStep) * radius 
					+ pos.y + (size.y >> 1)) * yo;
			
			poly.addPoint(x, y);
		}
		
		return poly;
	}
	
	private Polygon combinePolygons(Polygon... p) {
		Polygon union = new Polygon();
		
		for(Polygon poly: p) {
			for(int i = 0; i < poly.npoints; i++) {
				union.addPoint(
						poly.xpoints[i], 
						poly.ypoints[i]
				);
			}
		}
		
		return union;
	}
	
	public Polygon getSide(Side side) {
		Measurement[] cornerRadius = getBorderRadius();
		Polygon poly = new Polygon();
		
		double stepAng = Math.PI / 2;
		
		switch(side) {
		case LEFT_TOP_CORNER:
			if(cornerRadius[0] == null) {
				cornerRadius[0] = Measurement.Pixel();
			}
			poly = getSideCalc(cornerRadius[0].getResult(), 0);
			break;
		case LEFT_BOTTOM_CORNER:
			if(cornerRadius[1] == null) {
				cornerRadius[1] = Measurement.Pixel();
			}
			poly = getSideCalc(cornerRadius[1].getResult(), stepAng);
			break;
		case RIGHT_BOTTOM_CORNER:
			if(cornerRadius[2] == null) {
				cornerRadius[2] = Measurement.Pixel();
			}
			poly = getSideCalc(cornerRadius[2].getResult(), stepAng * 2);
			break;
		case RIGHT_TOP_CORNER:
			if(cornerRadius[3] == null) {
				cornerRadius[3] = Measurement.Pixel();
			}
			poly = getSideCalc(cornerRadius[3].getResult(), stepAng * 3);
			break;
		case LEFT_SIDE:
			poly = combinePolygons(
				getSide(Side.LEFT_TOP_CORNER),
				getSide(Side.LEFT_BOTTOM_CORNER)
			);
			break;
		case BOTTOM_SIDE:
			poly = combinePolygons(
				getSide(Side.LEFT_BOTTOM_CORNER),
				getSide(Side.RIGHT_BOTTOM_CORNER)
			);
			break;
		case RIGHT_SIDE:
			poly = combinePolygons(
					getSide(Side.RIGHT_BOTTOM_CORNER),
					getSide(Side.RIGHT_TOP_CORNER)
			);
			break;
		case TOP_SIDE: 
			poly = combinePolygons(
					getSide(Side.RIGHT_TOP_CORNER),
					getSide(Side.LEFT_TOP_CORNER)
			);
			break;
		case ALL:
			poly = combinePolygons(
					getSide(Side.LEFT_TOP_CORNER),
					getSide(Side.LEFT_BOTTOM_CORNER),
					getSide(Side.RIGHT_BOTTOM_CORNER),
					getSide(Side.RIGHT_TOP_CORNER)		
			);
			break;
		}
		
		return poly;
	}
	
	public Polygon getShape() {
		if(shapePolygon == null) {
			Polygon poly = new Polygon();
			
			poly = getSide(Side.ALL);
			
			shapePolygon = poly;
			return poly;
		}
		return shapePolygon;
	}
	
	private void invalidateShape() {
		shapePolygon = null;
	}
	
	
	@Override
	public void render(Graphics2D g2) {}
	@Override
	public void tick() {}

}