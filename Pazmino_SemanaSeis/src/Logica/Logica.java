package Logica;

import Comunicacion.Comunicacion;
import processing.core.PApplet;

public class Logica {

	private PApplet app;
	private Comunicacion com;
	
	public Logica(PApplet app) {
		this.app = app;
		com = new Comunicacion();
		com.start();
	}
	
	public void ejecutable(){
		
	}

}
