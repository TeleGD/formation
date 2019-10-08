package formation;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import formation.characters.Player;
import formation.things.Wall;
import formation.things.WallGen;

public class World extends BasicGameState {

	private int ID;
	private int state;
	private Player Nico;
	private List<Wall> walls;
	private WallGen wallGen;

	public World (int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play (container, game);
		} else if (this.state == 2) {
			this.resume (container, game);
		}
	}

	@Override
	public void leave (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause (container, game);
		} else if (this.state == 3) {
			this.stop (container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = container.getInput ();
		// ESCAPE pour quitter le jeu
		if (input.isKeyDown (Input.KEY_ESCAPE)) {
			this.setState (1);
			game.enterState (2, new FadeOutTransition (), new FadeInTransition ());
		}

		// Mise à jour du joueur
		this.Nico.update(container, game, delta);

		// Mise à jour de tous les murs (1 par 1)
		for (Wall wall: this.walls) { // for each
			wall.update(container, game, delta);
		}

		// Destruction des murs s'il y a eu collision (on les retire de la liste des murs existants et java les supprime car ils ne sont plus reliés à rien)
		for (int i = 0; i < this.walls.size(); i++) {
			if (this.walls.get(i).isDestructed()) {
				this.walls.remove(i);
			}
		}

		// Mise à jour du générateur de murs (ajout d'un nouveau mur ?)
		this.wallGen.update(container, game, delta);
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		// Affichage du joueur
		this.Nico.render(container, game, context);

		// Affichage de chaque mur (1 par 1)
		for (Wall wall: this.walls) {
			wall.render(container, game, context);
		}
	}

	public void play (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		// Création du joueur
		this.Nico = new Player();

		// Création de la liste contenant les murs
		this.walls = new ArrayList<Wall>();

		// Initialisation de cette liste, le premier mur de toutes les parties sera le même. Les suivants sont aléatoires
		this.walls.add(new Wall(20, 150, this.Nico));

		// Création du générateur de mur
		this.wallGen = new WallGen(this.walls, this.Nico);
	}

	public void pause (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop (GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void keyPressed(int key, char c) {
		this.Nico.keyPressed(key, c);
	}

	public void keyReleased(int key, char c) {
		this.Nico.keyReleased(key, c);
	}

	public void setState (int state) {
		this.state = state;
	}

	public int getState () {
		return this.state;
	}

	public void setPlayer(Player player) {
		this.Nico = player;
	}

	public Player getPlayer() {
		return this.Nico;
	}

}
