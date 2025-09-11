package RenderComponents;

import java.awt.Graphics2D;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public abstract class Entity {
	
	public static long index = 0;
	public long objectIndex = 0;
	private String name;
	private Entity parent;
	private ArrayList<Entity> children = new ArrayList<>();
	
	public Entity() {
		objectIndex = ++index;
	}
	
	public long getIndex() {
		return this.objectIndex;
	}
	
	public void setIndex(long index) {
		objectIndex = index;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Entity findFirstChild(String name) {
		for(Entity entity: getChildren()) {
			if(entity.getName().equals(name)) {
				return entity;
			}
		}
		return null;
	}
	
	public boolean hasParent() {
		return parent != null;
	}
	
	public void setParent(Entity entity) {
		entity.addChild(this);
	}
	
	public void removeParent(Entity entity) {
		entity.removeChild(this);
	}
	
	public Entity getParent() {
		return parent;
	}

	public Entity[] getChildren() {
		return children.toArray(new Entity[children.size()]);
	}
	
	public Entity[] getDescendents() {
	    ArrayList<Entity> desc = new ArrayList<>();
	    for (Entity entity : getChildren()) {
	        desc.add(entity); 
	        for (Entity childDesc : entity.getDescendents()) {
	            desc.add(childDesc);
	        }
	    }
	    return desc.toArray(new Entity[desc.size()]);
	}
	
	public void addChild(Entity entity) {
		children.add(entity);
		entity.parent = this;
	}
	
	public void removeChild(Entity entity) {
		children.remove(entity);
		entity.parent = null;
	}
	
	public abstract void render(Graphics2D g2);
	public abstract void tick();
}