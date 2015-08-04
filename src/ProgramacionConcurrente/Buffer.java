package ProgramacionConcurrente;

public class Buffer {

	private int buffer;
	private boolean bufferVacio;
	private boolean bufferLleno;
	
	
	public Buffer() {
		buffer = 0;
		bufferVacio = true;
		bufferLleno = false;
	}
	
	public void incrementBuffer() {
		if (buffer == 0) {bufferVacio = false;}
		if (buffer == 10) {bufferLleno = true;}
		else {buffer++;}
		}
	
	public void decrementBuffer() {
		if (buffer == 10) {bufferLleno = false;}
		if (buffer == 0) {bufferVacio = true;}
		else {buffer--;}
		}
	
	public String getBuffer() {return ""+buffer;}
	
	
	 public synchronized void get(){
	        if (bufferVacio){
	            try
	            {
	                wait();
	            } 
	            catch (InterruptedException e) 
	            {
	                System.err.println("Contenedor: Error en get -> " + e.getMessage());
	            }
	        }
	        
	        decrementBuffer();
	        notify();
	    }
	 
	  
	 public synchronized void put(){
	        if (bufferLleno){
	            try
	            {
	                wait();
	            } 
	            catch (InterruptedException e) 
	            {
	                System.err.println("Contenedor: Error en put -> " + e.getMessage());
	            }
	        }
	        incrementBuffer();
	        notify();
	    }
	
}


