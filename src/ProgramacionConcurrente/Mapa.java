package ProgramacionConcurrente;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

//import javax.swing.JFrame;

public class Mapa {
	public static Rectangle deposito  = new Rectangle(120, 70,10, 10);
	public static Rectangle fabrica  = new Rectangle(120, 310,10, 10);
	public static Rectangle mercado  = new Rectangle(240, 190,10, 10);
	public static Rectangle guarida  = new Rectangle(360, 310,10, 10);

	private Game game;

	public Mapa(Game game) {
		this.game = game;
		
	}

	public void paint(Graphics2D g) {

		// plano de la ciudad 3x3 frame.setSize(380, 380);
		g.setColor(Color.ORANGE); // deposito
		g.fillRect(20, 20, 100, 100);

		g.setColor(Color.YELLOW); // Fabrica
		g.fillRect(20, 260, 100, 100);
		g.setColor(Color.GREEN);// Mercado
		g.fillRect(140, 140, 100, 100);
		g.setColor(Color.DARK_GRAY); // guarida
		g.fillRect(260, 260, 100, 100);
		g.setColor(Color.BLACK);// manzanas
		g.fillRect(20, 140, 100, 100);
		g.fillRect(140, 20, 100, 100);
		g.fillRect(260, 20, 100, 100);
		g.fillRect(260, 140, 100, 100);
		g.fillRect(140, 260, 100, 100);

		g.setFont(new Font("Verdana", Font.BOLD, 15));
		g.drawString("DEPOSITO", 28, 65);
		g.drawRect(120, 70,10,10);
		g.drawString("FABRICA", 30, 315);
		g.drawRect(120, 310,10,10);
		g.drawString("MERCADO", 150, 195);
		g.drawRect(240, 190,10,10);
		g.drawString("GUARIDA", 275, 315);
		g.drawRect(360, 310,10,10);
		g.setFont(new Font("Verdana", Font.BOLD, 10));
		g.drawString("BUFFER: ", 30, 85);
		g.drawString(game.getBufferDeposito(),90,85);
		g.drawString("CARGAS: ", 22, 345);
		g.drawString(game.getBufferFabrica(),90,345);
		g.drawString("DESCARGAS:", 145, 210);
		g.drawString(game.getBufferMercado(),225,210);
		g.drawString("ROBOS: ", 280, 345);
		g.drawString(game.getRobos(),330,345);

		
	}

}
