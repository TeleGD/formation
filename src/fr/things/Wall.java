package fr.things;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.characters.Player;

public class Wall {
	private double x,y,width,height,speedX;
	private Player player;
	private boolean destructed;
	
	
	// Initialisation des murs lors de leur cr�ation (� chacun).
	// Leur position en ordonn�es et leur hauteur sont al�atoires donc donn�es en argument (cf WallGen.java)
	public Wall(double y,double height,Player player) {
		this.y=y;
		this.height=height;
		this.width=30;
		this.x=1300;
		speedX=-0.5;
		this.player=player;
		this.destructed=false;
	}
	
	// Affichage
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.green);
		g.fillRect((float)x, (float)y,(float) width, (float)height);
	}
	
	
	// Mise � jour des murs (60 fois par seconde)
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// delta = temps qui s'�coule entre 2 frame (varie pour s'approcher au plus de 60 frame par secondes (selon le lag)
		x+=speedX*delta;

		if(colPlayer()) {
			//System.out.println("il y a bien eu une collision");
			this.destructed=true;
			// On ajoute une collision au joueur
			player.collision();
		} else if (this.x+this.width<0) {
			this.destructed=true;
		}

	}

	
	// Renvoie la valeur d�truite (appel�e par le World)
	public boolean isDestructed() {
		return destructed;
	}

	// Met l'�tat du mur � d�truit lorsqu'il touche le joueur, permet au World de le faire disparaitre
	public void setDestructed(boolean destructed) {
		this.destructed = destructed;
	}

	
	// Collisions avec le joueur
	private boolean colPlayer() {
		if(this.x+this.width<player.getX())
			return false;
		if(this.x>player.getX()+player.getWidth())
			return false;
		if(this.y+this.height<player.getY())
			return false;
		if(this.y>player.getY()+player.getHeight())
			return false;
		return true;
	}
}
