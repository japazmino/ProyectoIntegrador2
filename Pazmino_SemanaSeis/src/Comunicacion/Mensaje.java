package Comunicacion;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private boolean existe;
	private String tipoIngreso;
	private String tipoExiste;
	private String nombre;
	private String contra;
	static final long serialVersionUID = 42L;

	public Mensaje(String tipo, String nombre, String contra) {
		this.tipoIngreso = tipo;
		this.contra = contra;
		this.nombre = nombre;
	}
	
	public Mensaje(String tipo, boolean existe){
		this.tipoExiste = tipo;
		this.existe = existe;
	}

	public String getTipoIngreso() {
		return tipoIngreso;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContra() {
		return contra;
	}

	public boolean isExiste() {
		return existe;
	}

	public String getTipoExiste() {
		return tipoExiste;
	}

}
