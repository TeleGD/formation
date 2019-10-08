package formation.characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {

	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double newX;
	private double newY;

	private int width;
	private int height;
	private boolean rightPress;
	private boolean downPress;
	private boolean leftPress;
	private boolean upPress;
	private boolean droitegauche;
	private boolean hautbas;

	private int col;

	public Player() {
		this.x = 50;
		this.y = 50;
		this.width = 25;
		this.height = 25;
		this.col = 0;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.horizontalMove();
		this.verticalMove();
		this.x += this.speedX * delta;
		this.y += this.speedY * delta;
		this.newX = this.x + this.speedX;
		this.newY = this.y + this.speedY;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// Affichage du carré bleu
		context.setColor(Color.blue);
		context.fillRect((float) this.x, (float) this.y, this.width, this.height);

		// Affichage du nombre de collisions
		context.setColor(Color.red);
		context.drawString("" + this.col, 399, 10);
	}

	public void keyPressed(int key, char c) {
		// Détection des entrées de clavier (appuie sur une touche)
		switch (key) {
			case Input.KEY_UP: {
				this.upPress = true;
				this.hautbas = false;
				break;
			}
			case Input.KEY_DOWN: {
				this.downPress = true;
				this.hautbas = true;
				break;
			}
			case Input.KEY_LEFT: {
				this.leftPress = true;
				this.droitegauche = false;
				break;
			}
			case Input.KEY_RIGHT: {
				this.rightPress = true;
				this.droitegauche = true;
				break;
			}
		}
	}

	// Détection des entrées de clavier (relache une touche)
	public void keyReleased(int key, char c) {
		switch (key) {
			case Input.KEY_UP: {
				this.upPress = false;
				break;
			}
			case Input.KEY_DOWN: {
				this.downPress = false;
				break;
			}
			case Input.KEY_LEFT: {
				this.leftPress = false;
				break;
			}
			case Input.KEY_RIGHT: {
				this.rightPress = false;
				break;
			}
		}
	}

	private void horizontalMove() {
		// Déplacement selon les flèches gauche et droite appuyées (détection des flèches multiples)
		this.speedX = 0;
		if ((this.leftPress && !this.rightPress) || (this.leftPress && this.rightPress && !this.droitegauche)) {
			if (this.x > 0) {
				this.speedX = -1;
			}
		}
		if ((!this.leftPress && this.rightPress) || (this.leftPress && this.rightPress && this.droitegauche)) {
			if (this.x < 1280 - this.width) {
				this.speedX = 1;
			}
		}
	}

	private void verticalMove() {
		// Déplacement selon les flèches haut et bas appuyées (détection des flèches multiples)
		this.speedY = 0;
		if ((this.upPress && !this.downPress) || (this.upPress && this.downPress && !this.hautbas)) {
			if (this.y > 0) {
				this.speedY = -1;
			}
		}
		if ((!this.upPress && this.downPress) || (this.upPress && this.downPress && this.hautbas)) {
			if (this.y < 720 - this.height) {
				this.speedY = 1;
			}
		}
	}

	public void collision() {
		// Incrémentation du compteur de collisions (appelé par les murs lors d'une collision)
		this.col += 1;
	}

	public void setNewX(double newX) {
		this.newX = newX;
	}

	public double getNewX() {
		return this.newX;
	}

	public void setNewY(double newY) {
		this.newY = newY;
	}

	public double getNewY() {
		return this.newY;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return this.x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return this.y;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedX() {
		return this.speedX;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getSpeedY() {
		return this.speedY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return this.width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return this.height;
	}

}
