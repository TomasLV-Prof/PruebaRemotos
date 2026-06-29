package org.barajas.ies.entities;

public class Registro {
	
	// Propiedades de la clase "Registro"
	private Usuario[] usuarios;
	// Como sólo queremos que se creé una única instancia
	// de la clase "Registro", utilizaremos una propiedad
	// estática, de clase, cuyo valor será la única 
	// instancia permitida
	private static Registro instancia;
	
	// Constructor para crear "la instancia"
	// que representaría el único "Registro"
	// de "Usuario"s que utilizará dicho programa
	private Registro() {
		super();
		// Inicializamos un "array" de usuarios
		// permitiendo gestionar como máximo 10
		// "Usuario"s
		usuarios = new Usuario[10];
	}
	
	// Métodos accesores a las propiedades de
	// la única instancia de "Registro"
	public Usuario[] getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuario[] usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario(int index) {
		return this.usuarios[index];
	}
	
	public void setUsuario(Usuario usuario, int index) {
		this.usuarios[index] = usuario;
	}

	// Método estático de utilidad para obtener la
	// única instancia de "Registro" que se debe 
	// utilizar en el programa
	public static Registro getInstance() {
		// Si no está asignado ningún valor de "Registro"
		// en la propiedad estática...
		if (instancia == null) {
			// ... se le asignará una nueva instancia de "Registro"
			instancia = new Registro();
		}
		// Se devuelve el valor que tiene asignada la propiedad
		// estática "instancia"
		return instancia;
	}

}