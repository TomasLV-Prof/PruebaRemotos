package org.barajas.ies.services;

import org.barajas.ies.entities.Registro;
import org.barajas.ies.entities.Usuario;
import org.barajas.ies.util.Utilidad;

public class Gestion {

	public static boolean addUser(Registro registro, Usuario usuario) {
		boolean added = false;

		if (!Utilidad.registroLleno(registro) && !Utilidad.usuarioRepetido(registro, usuario)) {
			int posicion = Utilidad.posicionDisponible(registro);
			if (posicion != -1) {
				registro.setUsuario(usuario, posicion);
				added = true;
			}
			
		}

		return added;
	}
	
	public static boolean removeUser(Registro registro, Usuario usuario) {
		boolean removed = false;
		
		int posicion = Utilidad.posicionUsuario(registro, usuario);
		
		if (posicion != -1) {
			registro.setUsuario(null, posicion);
			removed = true;
		}
		
		return removed;
	}

}