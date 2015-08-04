package ProgramacionConcurrente;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Player {
	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	int x = 120, y = 120;
	int xa = 0, ya = 0;
	private Game game;
	private boolean cargado;

	public Player(Game game) {
		this.game = game;
		cargado = false;
	}

	public void move() {
		if ((x + xa > -10)
				&& (x + xa < game.getWidth())
				&& (((y + ya > -10) && (y + ya < 20))
						|| ((y + ya >= 120) && (y + ya <= 130))
						|| ((y + ya >= 240) && (y + ya <= 250)) || (y + ya >= 360)
						&& (y + ya < game.getHeight())))
			x = x + xa;
		if ((y + ya > -10)
				&& (y + ya < game.getHeight())
				&& (((x + xa > -10) && (x + xa < 20))
						|| ((x + xa >= 120) && (x + xa <= 130))
						|| ((x + xa >= 240) && (x + xa <= 250)) || (x + xa >= 360)
						&& (x + xa < game.getWidth())))
			y = y + ya;
		
		if ((Mapa.guarida.intersects(getBounds())) && (cargado)) {descargar();}
		
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (cargado) {g.fillOval(x, y, WIDTH, HEIGHT);}
		else {g.drawOval(x, y, WIDTH, HEIGHT);}
		
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -10;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 10;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = 10;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			ya = -10;
	}

	public Rectangle getBounds() { // retorna un objeto de tipo rectángulo
									// indicando la posición
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void cargar() {cargado = true;}
	
	public void descargar() {
		cargado = false;
		game.incrementRobos();
	}

	public boolean isCargado() {return cargado;}
	
}