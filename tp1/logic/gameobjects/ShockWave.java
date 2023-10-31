package tp1.logic.gameobjects;

import tp1.logic.Game;

public class ShockWave {
	private boolean enabled;
	
	public ShockWave() {
		this.enabled = false;
	}
	
	public void ufoKilled() {
		this.enabled = true;
	}
	
	@Override
	public String toString() {
		if (this.enabled) { return ("ON"); }
		return ("OFF");
	}
	
	public boolean isAvailable() {
		return (this.enabled);
	}
	
	public void use() {
		if (this.enabled) {
			this.enabled = false;
		}
	}
}
