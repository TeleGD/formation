package formation.things;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import formation.characters.Player;

public class Wall {

	private double x;
	private double y;
	private double width;
	private double height;
	private double speedX;
	private Player player;
	private boolean destructed;

	public Wall(double y, double height, Player player) {
		// Initialisation des murs lors de leur création (à chacun).
		// Leur position en ordonnées et leur hauteur sont aléatoires donc données en argument (cf WallGen.java)
		this.y = y;
		this.height = height;
		this.width = 30;
		this.x = 1300;
		this.speedX = -0.5;
		this.player = player;
		this.destructed = false;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// Mise à jour des murs (60 fois par seconde)
		// delta = temps qui s'écoule entre 2 frame (varie pour s'approcher au plus de 60 frame par secondes (selon le lag)
		this.x += this.speedX * delta;
		if (this.colPlayer()) {
			// System.out.println("il y a bien eu une collision");
			this.destructed = true;
			// On ajoute une collision au joueur
			this.player.collision();
		} else if (this.x + this.width < 0) {
			this.destructed = true;
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// Affichage
		context.setColor(Color.green);
		context.fillRect((float) this.x, (float) this.y, (float) this.width, (float) this.height);
	}

	private boolean colPlayer() {
		// Collisions avec le joueur
		if (this.x + this.width < this.player.getX()) {
			return false;
		}
		if (this.x > this.player.getX() + this.player.getWidth()) {
			return false;
		}
		if (this.y + this.height < this.player.getY()) {
			return false;
		}
		if (this.y > this.player.getY() + this.player.getHeight()) {
			return false;
		}
		return true;
	}

	public void setDestructed(boolean destructed) {
		// Met l'état du mur à détruit lorsqu'il touche le joueur, permet au World de le faire disparaitre
		this.destructed = destructed;
	}

	public boolean isDestructed() {
		// Renvoie la valeur détruite (appelée par le World)
		return this.destructed;
	}

}
