package formation.things;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import formation.characters.Player;

public class WallGen {

	private List<Wall> walls;
	private Player player;

	public WallGen(List<Wall> walls, Player player) {
		this.walls = walls;
		this.player = player;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// Crée ~3 nouveaux murs par secondes
		if (Math.random() > .95) {
			this.createWall();
		}
	}

	private void createWall() {
		// Instancie un nouveau mur et l'ajoute à la liste des murs existants
		this.walls.add(new Wall(Math.random() * 500, Math.random() * 400 + 100, this.player));
	}

}
