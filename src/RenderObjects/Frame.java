package RenderObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import RenderComponents.DefaultAttributes;
import RenderComponents.GraphicsAttributes;

public class Frame extends GraphicsAttributes {

	@Override
	public void render(Graphics2D g2) {
		AffineTransform orgAt = g2.getTransform();
		
		AffineTransform modified = g2.getTransform();
		
		// bode
		Polygon s = getShape();
		
		g2.setColor(Color.BLACK);
		
		/*for(int i = 0; i < s.npoints; i++) {
			g2.fillOval(s.xpoints[i], s.ypoints[i], 20, 20);
		}*/
		g2.drawPolygon(s);
		
		g2.setTransform(orgAt);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
