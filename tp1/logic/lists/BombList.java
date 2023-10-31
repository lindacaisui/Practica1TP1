package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.UCMLaser;

public class BombList {
	private Bomb[] objects;
	private int num;
	
	public BombList(int size) {
		this.objects = new Bomb[size];
		this.num = 0;
	}
	
	public void add(Bomb bomb) {
		this.objects[this.num] = bomb;
		this.num++;
	}
	
	private void remove(Bomb bomb) {
		int i = 0;
		while (i < this.num) {
			if (this.objects[i] == bomb) {
				this.objects[i] = null;
				while (i < this.num - 1) {
					this.objects[i] = this.objects[i + 1];
					i++;
				}
				this.objects[i] = null;
				this.num--;
			}
			i++;
		}
	}
	
	public int size() {
		return (this.num);
	}
	
	public Bomb getObjectInPosition(Position pos) {
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
	
	public void automaticMoves() {
		for (int i = 0; i < this.num; i++) {
			this.objects[i].automaticMove();
		}
	}
	
	public void removeDead() {
		for (int i = 0; i < this.num; i++) {
			if (!objects[i].isAlive()) {
				this.remove(objects[i]);
			}
		}
	}
	
	public void checkAttacks(UCMLaser laser) {
		if (laser == null) { return; }
		Position laser_pos = laser.getPosition();
		for (int i = 0; i < this.num; i++) {
			if (objects[i].getPosition().equals(laser_pos)) {
				laser.receiveAttack(objects[i]);
				objects[i].receiveDamage(1);
				break;
			}
		}
	}
}
