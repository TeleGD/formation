package fr.main;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;
import fr.things.Wall;
import fr.things.WallGen;

public class World extends BasicGameState {

	public static int ID = 0;
	private Player Nico;
	private ArrayList<Wall> walls;
	private WallGen wallGen;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// Création du joueur
		Nico=new Player();
		
		// Création de la liste contenant les murs
		walls=new ArrayList<Wall>();
		
		// Initialisation de cette liste, le premier mur de toutes les parties sera le même. Les suivants sont aléatoires
		walls.add(new Wall(20,150,Nico));
		
		// Création du générateur de mur
		wallGen = new WallGen(walls,Nico);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// Affichage du joueur
		Nico.render(arg0, arg1, arg2);
		
		// Affichage de chaque mur (1 par 1)
		for(Wall w:walls) {
			w.render(arg0, arg1, arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// Mise à jour du joueur
		Nico.update(arg0, arg1, arg2);
		
		// Mise à jour de tous les murs (1 par 1)
		for(Wall w:walls) { // for each
			w.update(arg0, arg1, arg2);
		}
		
		// Destruction des murs s'il y a eu collision (on les retire de la liste des murs existants et java les supprime car ils ne sont plus reliés à rien)
		for(int i=0;i<walls.size();i++) {
			if(walls.get(i).isDestructed()) {
				walls.remove(i);
			}
		}
		
		// Mise à jour du générateur de murs (ajout d'un nouveau mur ?)
		wallGen.update(arg0, arg1, arg2);
	}

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
	}


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
		
		// ESCAPE pour quitter le jeu
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public Player getPlayer() {
		return Nico;
	}

	public void setPlayer(Player playerP) {
		Nico = playerP;
	}

	

}
