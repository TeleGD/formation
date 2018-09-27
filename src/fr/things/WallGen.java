package fr.things;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;

public class WallGen {
	
	private ArrayList<Wall> walls;
	private Player player;
	
	
	public WallGen(ArrayList<Wall> walls,Player player) {
		this.walls=walls;
		this.player=player;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// Crée ~3 nouveaux murs par secondes
		if(Math.random()>0.95) {
			createWall();
		}
	}
	
	
	private void createWall() {
		// Instancie un nouveau mur et l'ajoute à la liste des murs existants
		walls.add(new Wall(Math.random()*500,Math.random()*400+100,player));
	}
}
