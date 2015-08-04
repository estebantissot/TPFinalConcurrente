package ProgramacionConcurrente;

import java.util.Random;

public class Direccion {
	// ARRIBA, ABAJO, DERECHA, IZQUIERDA;
	String x = "";
	Random rand = new Random();
	private int aleatorio;
	private int anterior;

	public Direccion() {
	}

	public String getDir() {
		
		if (aleatorio == 0)
			x = "arriba";
		else if (aleatorio == 1)
			x = "abajo";
		else if (aleatorio == 2)
			x = "derecha";
		else if (aleatorio == 3)
			x = "izquierda";

		return x;
	}
	
	
	/*public void recalcular() {
			aleatorio = rand.nextInt(4);
		}
*/
	public void recalcular() {
		do {
			aleatorio = rand.nextInt(4);
		}
		while (((anterior+aleatorio)==1) || ((anterior+aleatorio) == 5));
	}

	public void set() {
		anterior=aleatorio;
	}
	
	
}
