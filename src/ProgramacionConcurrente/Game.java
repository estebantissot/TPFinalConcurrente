/*
 * 
 *  Monitores para las esquinas
 * 
 * Socket, el juego se debe poder jugar en red
 * 
 * Si hay una colision en recta mover uno de los auros a un costado, dormirlo por milisegundos 
 * hasta que pase el otro y volver a despertarlo (semaforo?) 
 * 
 * Crear los buffers
 * Fabrica buffer ilimitado
 * Deposito buffer limitado
 * Mercado buffer ilimitado
 * 
 * 
 * */


package ProgramacionConcurrente;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	Camion camion1 = new Camion(this, 120, 250, 0,"blanco");
	Camion camion2 = new Camion(this, 240, 250, 0,"azul");
	Camion camion3 = new Camion(this, 00, 10, 0, "blanco");
	Camion camion4 = new Camion(this, 240, 10, 0, "azul");
	Mapa mapa = new Mapa(this);
	Player player = new Player(this);
	int score = 0;
	ArrayList<Camion> camiones = new ArrayList<Camion>();

	public Game() {
		
		camiones.add(camion1);
		camiones.add(camion2);
		camiones.add(camion3);
		camiones.add(camion4);

		/*
		 * Se crea una funcion anonima Listener es un objeto que
		 * "escucha una interrupcion de teclado". El Listener se registra en el
		 * JPanel (o KeyboardExample) usando el método
		 * addKeyListener(KeyListener listener). Para que un objeto JPanel
		 * reciba las notificaciones del teclado es necesario incluir la
		 * instrucción setFocusable(true) que permite que la clase Game reciba
		 * el foco Una vez registrado, cuando Game (nuestro JPanel) tenga el
		 * foco y alguien oprima una tecla KeyboardExample informará al objeto
		 * listener registrado. El objeto Listener de nuestro ejemplo implementa
		 * la interfaz KeyListener que tiene los métodos keyTyped(),
		 * keyPressed() y keyReleased(). El método keyPressed será llamado cada
		 * vez que una tecla sea oprimida (y varias veces si se mantiene
		 * oprimida). El método keyReleased será llamado cuando solemos una
		 * tecla. Los métodos antes mencionados reciben como parámetro un objeto
		 * KeyEvent que contiene información sobre que tecla se ha oprimido o
		 * soltado. Usando e.getKeyCode() podemos obtener el código de la tecla
		 * y si le pasamos un código de tecla a la función estatica
		 * KeyEvent.getKeyText(...) podemos obtener el texto asociado a la
		 * tecla.
		 */
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}


			public void keyReleased(KeyEvent e) {
				player.keyReleased(e);
			}


			public void keyPressed(KeyEvent e) {
				player.keyPressed(e);
			}
		});
		setFocusable(true);
		
	}

	public int getScore() {
		return score;
	}

	private void move() {
		
		player.move();
		
		for(Camion c:camiones){
			if ((c.collision()) && (c.isCargado())) {
				c.descargar();
				player.cargar();
			}
		}
		
		/*prueba de colision entre camiones, problemas en las esquinas, probar despues de implementar monitores */
		
/*		for(Camion c:camiones){
			if (c.getFlag()){
				if (c.getXA() != 0) {c.setY(c.getY() + 10);}
				else {c.setX(c.getX() - 10);}
				c.setFlag(false);
			}
		}
		
		for(int i=0; i<(camiones.size()-1); i++){
			Camion c1 = camiones.get(i);
			for(int j=i+1; j<camiones.size(); j++){
				Camion c2 = camiones.get(j);
				if (c1.getBounds().intersects(c2.getBounds())) {
					//modificar x de c1
					if (c1.getXA() != 0) {c1.setY(c1.getY() - 10);}
					else {c1.setX(c1.getX() + 10);}
					c1.setFlag(true);
				}
			}			
		}*/
		
		
	}

	public void paint(Graphics g) {
		super.paint(g); // Llamamos a la clase padre de paint para borrar el
						// dibujo
		Graphics2D g2d = (Graphics2D) g;

		mapa.paint(g2d); // Creamos un mapa

		
		player.paint(g2d); // Auto del jugador
	
		
		// Pintamos los camiones, para ello primero seleccionamos el color
		for(Camion c:camiones){
			if (c.getName().equals("blanco")) {g2d.setColor(Color.WHITE);}
			if (c.getName().equals("azul")) {g2d.setColor(Color.BLUE);}
			c.paint(g2d);
		}
		
		
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Ciudad"); // Creamos un Marco o ventana
		Game game = new Game(); // Creamos un objeto game
		frame.add(game); // añadimos el objeto game al marco
		frame.setSize(380, 380); // Dimensionamos el marco
		frame.setVisible(true); // Hacemos visible el marco
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Esta sentencia 
																// hace que
																// finalice el
																// programa al
																// cerrar el
																// marco.

		while (true) { // Game Loop
			game.move(); // Update
			game.repaint(); // Render
			Thread.sleep(40); // dormimos al hilo actual por 10ms para que en
								// la proxima iteracion el otro hilo pueda usar
								// los recursos.
		}
	}
}
