package org.barajas.ies.entities;

public class Usuario {

	// Propiedades de la clase "Usuario"
	// cuyo conjunto concreto de valores 
	// puede identificar a un "Usuario" en concreto
	private String nombre;
	private String email;
	private String password;
	
	// Constructores para crear instancias
	// que representarían nuevos "Usuario"s
	public Usuario() {
		super();
	}
	
	public Usuario(String nombre, String email, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	// Métodos accesores a las propiedades de
	// una instancia de un "Usuario" en concreto
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// Sobreecritura del método "toString"
	// utilizado para mostrar los valores de un
	// "Usuario" en concreto
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
	}
	
}