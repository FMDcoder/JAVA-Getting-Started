package Render;

import java.awt.Graphics2D;

import Main.IGraphicWindow;
import Modifiers.Measure;
import Modifiers.Position;
import RenderComponents.Dimension;
import RenderComponents.Entity;
import RenderComponents.Measurement;
import RenderObjects.Frame;

public class Engine implements IGraphicWindow {

	private Frame frame, frame2;
	
	@Override
	public void setup(Entity body) {
		// TODO Auto-generated method stub
		frame = new Frame();
		frame.setPosition(
				new Dimension(0.5, 0, 0.5, 0)
		);
		
		frame.setSize(
				new Dimension(0, 200, 0, 200)
		);
		
		frame.setPositionType(Position.RELATIVE);
		
		Measurement measureBorder = new Measurement(Measure.PX, 100);
		frame.setBorderRadius(measureBorder);
		
		
		body.addChild(frame);
	}

	@Override
	public void tick(Entity body, double deltaTime, double totalTimeElapsed) {
		System.out.println(totalTimeElapsed);
		Measurement measureBorder = new Measurement(
				Measure.CM, 
				Math.abs(Math.sin(totalTimeElapsed * 0.1) * 2)
		);
		frame.setBorderRadius(measureBorder);
	}

	@Override
	public void render(Entity body, Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}
}
