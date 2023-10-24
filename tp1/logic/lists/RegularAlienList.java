package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.RegularAlien;

public class RegularAlienList {
	
	private RegularAlien[] objects;
	private int num;
	
	public RegularAlienList(int size) {
		this.objects = new RegularAlien[size];
		this.num = 0;
	}
	
	public void add(RegularAlien alien) {
		this.objects[num] = alien;
		this.num++;
	}
	
	private void remove(RegularAlien alien) {
		int i = 0;
		while (i < num) {
			if (this.objects[i] == alien) {
				this.objects[i] = null;
				while (i < num - 1) {
					this.objects[i] = this.objects[i + 1];
					i++;
				}
			}
			i++;
		}
	}
	
	public int size() {
		return (this.num);
	}
	
	public RegularAlien getObjectInPosition(Position pos) {
		for (int i = 0; i < this.num; i++) {
			if (pos.equals(this.objects[i].getPosition())) {
				return (this.objects[i]);
			}
		}
		return null;
	}
	
	public String toString() {
		return "hola";
		
	}
	
	public void computerActions() {
		
	}
	
	public void automaticMoves() {
		
	}
	
	public void removeDead() {
		
	}
	
	public void checkAttacks() {
		
	}

}
