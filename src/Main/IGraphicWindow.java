package Main;
import java.awt.Graphics2D;

import RenderComponents.Entity;

public interface IGraphicWindow {
	public void setup(Entity body);
	public void tick(Entity body);
	public void render(Entity body, Graphics2D g2);
}
