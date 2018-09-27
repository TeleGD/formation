package fr.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private double x,y,speedX,speedY,newX,newY;
	public double getNewX() {
		return newX;
	}


	public void setNewX(double newX) {
		this.newX = newX;
	}


	public double getNewY() {
		return newY;
	}


	public void setNewY(double newY) {
		this.newY = newY;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getSpeedX() {
		return speedX;
	}


	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}


	public double getSpeedY() {
		return speedY;
	}


	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}

	private int width=25;
	private int height=25;
	private boolean rightPress,downPress,leftPress,upPress,droitegauche,hautbas;
	
	
	private int col;
	
	public Player() {
		this.x=50;
		this.y=50;
		this.col=0;
	}
	
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// Affichage du carré bleu
		g.setColor(Color.blue);
		g.fillRect((float)x, (float)y, width, height);
		
		// Affichage du nombre de collisions
		g.setColor(Color.red);
		g.drawString(""+col, 399, 10);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		horizontalMove();
		verticalMove();
		x+=speedX*delta;
		y+=speedY*delta;
		newX=x+speedX;
		newY=y+speedY;
	}
	
	// déplacement selon les flèches gauche et droite appuyées (détection des flèches multiples)
	private void horizontalMove() {
		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > 0) {
				speedX = -1;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 1280 - width) {

				speedX = 1;
			}
		}
	}
	
	// déplacement selon les flèches haut et bas appuyées (détection des flèches multiples)
	private void verticalMove() {
		speedY = 0;
		if ((upPress && !downPress) || (upPress && downPress && !hautbas)) {
			if (y > 0) {
				speedY = -1;
			}

		}
		if ((!upPress && downPress) || (upPress && downPress && hautbas)) {
			if (y < 720 - height) {

				speedY = 1;
			}
		}
	}
	
	
	// Détection des entrées de clavier (relache une touche)
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_DOWN:
			downPress = false;
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		}
	}
	
	// Détection des entrées de clavier (appuie sur une touche)
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			hautbas=false;
			break;

		case Input.KEY_DOWN:
			downPress = true;
			hautbas=true;
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_RIGHT:
			rightPress = true;
			droitegauche = true;
			break;
		}
	}
	
	// Incrémentation du compteur de collisions (appelé par les murs lors d'une collision)
	public void collision() {
		this.col+=1;
	}
}
