import Logica.Logica;
import processing.core.PApplet;

public class Ejecutable extends PApplet {

	Logica logica;
	
	public void setup() {
		logica = new Logica(this);
		size(500, 500);
	}
	
	public void draw(){
		background(255);
		logica.ejecutable();
	}

}
