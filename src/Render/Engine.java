package Render;

import java.awt.Graphics2D;

import Main.IGraphicWindow;
import Modifiers.Position;
import RenderComponents.Dimension;
import RenderComponents.Entity;
import RenderObjects.Frame;

public class Engine implements IGraphicWindow {

	private Frame frame, frame2;
	
	@Override
	public void setup(Entity body) {
		// TODO Auto-generated method stub
		frame = new Frame();
		frame.setPosition(
				new Dimension(0.5, -100, 0.5, -100)
		);
		
		frame.setSize(
				new Dimension(0, 200, 0, 200)
		);
		
		frame.setPositionType(Position.RELATIVE);
		
		
		frame2 = new Frame();
		frame2.setPosition(
				new Dimension(0.5, -50, 0.5, -50)
		);
		
		frame2.setSize(
				new Dimension(0, 100, 0, 100)
		);
		
		frame2.setPositionType(Position.RELATIVE);
		
		frame.addChild(frame2);
		
		
		body.addChild(frame);
	}

	@Override
	public void tick(Entity body) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Entity body, Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}
}
