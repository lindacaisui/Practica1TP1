package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.UCMLaser;

public class DestroyerAlienList {
	private DestroyerAlien[] objects;
	private int num;
	
	public DestroyerAlienList(int size) {
		this.objects = new DestroyerAlien[size];
		this.num = 0;
	}
	
	public void add(DestroyerAlien alien) {
		this.objects[this.num] = alien;
		this.num++;
	}
	
	private void remove(DestroyerAlien alien) {
		int i = 0;
		while (i < this.num) {
			if (this.objects[i] == alien) {
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
	
	public DestroyerAlien getObjectInPosition(Position pos) {
		for (int i = 0; i < this.num; i++) {
			if (pos.equals(this.objects[i].getPosition())) {
				return (this.objects[i]);
			}
		}
		return null;
	}
	
	public void computerActions(UCMLaser laser) {
		this.checkAttacks(laser);
		this.removeDead();
		
		for (int i = 0; i < this.num; i++) {
			this.objects[i].readyToDescend();
			this.objects[i].computerActions();
		}
	}
	
	public void automaticMoves() {
		for (int i = 0; i < this.num; i++) {
			this.objects[i].automaticMove();
		}
	}
	
	public void removeDead() {
		for (int i = 0; i < this.num; i++) {
			if (!objects[i].isAlive()) {
				objects[i].die();
				this.remove(objects[i]);
			}
		}
	}
	
	public void shockWave() {
		for (int i = 0; i < this.num; i++) {
			this.objects[i].receiveDamage(1);
		}
		this.removeDead();
	}
	
	public void checkAttacks(UCMLaser laser) {
		if (laser == null) { return; }
		Position laser_pos = laser.getPosition();
		for (int i = 0; i < this.num; i++) {
			if (objects[i].getPosition().equals(laser_pos)) {
				laser.performAttack(objects[i]);
				laser.receiveDamage(1);
				break;
			}
		}
	}
}
