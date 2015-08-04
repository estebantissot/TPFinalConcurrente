package ProgramacionConcurrente;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;

public class Camion implements Runnable {
	private static final int DIAMETER = 10;
	Thread thread;
	int contador;
	Direccion dir = new Direccion();
	private Game game;
	private int x, y, xa,ya;
	private boolean cargado, colision;
	private String name;

	public Camion(Game game, int x, int y, int contador, String name) {
		//super(x,y,1,1);
		thread = new Thread(this);
		thread.start();	
		this.contador = contador;
		this.game = game;
		this.x = x;
		this.y =y;
		xa = 0;
		ya = 0;
		cargado = false;
		colision = false;
		this.name = name;
		
	}
	
	public String getName() {return name;}

	public int getDiameter() {return DIAMETER;}					
	
	public Rectangle getBounds() {return new Rectangle(x, y, DIAMETER, DIAMETER);}	
	
	public boolean collision() { return game.player.getBounds().intersects(getBounds());}

	//private boolean collision(Rectangle cuadra) { return cuadra.intersects(getBounds());}
	
	public int getX() {return x;}

	public int getY() {return y;}

	public void setY(int i) {y = i;}

	public void setX(int i) {x=i;}
	
	public int getXA() {return xa;}

	public void setFlag(boolean f) {colision = f;}
	
	public boolean getFlag() {return colision;}
	
	public void cargar() {
		cargado = true;
		System.out.println("Cargue"+getName());
	}
	
	public void descargar() {
		cargado = false;
		System.out.println("Descargue"+getName());
	}
	
	public boolean isCargado() {return cargado;}
	
	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//si esta cargado lo dibujo lleno, si no un circulo vacio
		if (cargado) {g.fillOval(x, y, 10, 10);}
		else {g.drawOval(x, y, 10, 10);}
		
	}
	
	public void run() {
		
		while(true){
			
			if (contador % 12 == 0) {
				do {			
			
					dir.recalcular();		
					
					if (dir.getDir().equals("arriba")) {
						ya = -10;
						xa = 0;
					}
					if (dir.getDir().equals("abajo")) {
						ya = 10; //setYA(10);
						xa = 0; //setXA(0);
					}
					if (dir.getDir().equals("derecha")) {
						xa = 10; //setXA(10);
						ya = 0; //SetYA(0);
					}
					if (dir.getDir().equals("izquierda")) {
						xa =-10; //setXA(-10);
						ya = 0; //setYA(0);
					}

				} 
				while (((x + xa) < 0) || (((x + xa) > 360) || ((y + ya) < 10) || ((y + ya) >= 380))); 								
				//(((getPosX() + getXA()) < 10) || ((getPosX() + getXA()) >= 380) || ((getPosY() + getYA()) < 10) || ((getPosY() + getYA()) >= 380))
				dir.set();
		}
		
		
		contador++;
						
		x = x + xa; //setPosX(getPosX() + getXA());
		y = y + ya; //setPosY(getPosY() + getYA());
		
		if (name.equals("blanco")){
			if ((Mapa.deposito.intersects(getBounds())) && (isCargado())) {descargar();}
			if ((Mapa.fabrica.intersects(getBounds())) && (!isCargado())) {cargar();}
		}
		
		if (name.equals("azul")){
			if ((Mapa.mercado.intersects(getBounds())) && (isCargado())) {descargar();}
			if ((Mapa.deposito.intersects(getBounds())) && (!isCargado())) {cargar();}
		}
	
		
		try {Thread.sleep(110);} 
		catch (Exception e) {}	
		}
	}

}

