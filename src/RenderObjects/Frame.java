package RenderObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import RenderComponents.DefaultAttributes;

public class Frame extends DefaultAttributes {

	@Override
	public void render(Graphics2D g2) {
		Point pos = getAbsolutePositionPixels();
		Point size = getAbsoluteSizePixels();
		
		g2.setColor(Color.BLACK);
		g2.drawRect(
				pos.x,
				pos.y, 
				size.x,
				size.y
		);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
