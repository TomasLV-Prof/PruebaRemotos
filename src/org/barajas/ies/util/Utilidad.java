package org.barajas.ies.util;

import java.nio.charset.StandardCharsets;

import org.barajas.ies.entities.Registro;
import org.barajas.ies.entities.Usuario;

public class Utilidad {
	
	// Contantes que sirven de indicadores para saber que debe contener un String
	// al pasarlo como 2º parámetro del método "cadenaContiene"
	private static final int LETRAS_MAYUSCULAS = 1;
	private static final int LETRAS_MINUSCULAS = 2;
	private static final int NUMEROS = 3;
	
	// Para ver los códigos Hexadecimales de los correspondientes caracteres en la códificación UTF-8
	// Ver: https://www.utf8-chartable.de/
	
	// Códigos Hexadecimales de las letras mayúsculas en la codificación UTF-8
	private static final int COD_HX_UTF8_INI_MAYUS = 0x41;
	private static final int COD_HX_UTF8_FIN_MAYUS = 0x5a;
	
	// Códigos Hexadecimales de las letras minúsculas en la codificación UTF-8
	private static final int COD_HX_UTF8_INI_MINUS = 0x61;
	private static final int COD_HX_UTF8_FIN_MINUS = 0x7a;
	
	// Códigos Hexadecimales de los dígitos en la codificación UTF-8
	private static final int COD_HX_UTF8_INI_NUMS = 0x30;
	private static final int COD_HX_UTF8_FIN_NUMS = 0x39;
	
	
	// Comprueba si el "Registro" de "Usuario"s que maneja este programa
	// contiene algún objeto Usuario (instancia creada de la clase "Usuario")
	public static boolean registroVacio(Registro registro) {
		boolean vacio = false;

		if ((registro.getUsuarios().length == 0) || (registro.getUsuario(0) == null)) {
			vacio = true;
		}

		return vacio;
	}

	// Comprueba si el "Registro" de "Usuario"s que maneja este programa
	// ya contiene los 10 objetos Usuario (instancia creada de la clase "Usuario")
	// que es capaz de gestionar.
	public static boolean registroLleno(Registro registro) {
		boolean lleno = true;

		if (!registroVacio(registro)) {
			for (Usuario usuario : registro.getUsuarios()) {
				if (usuario == null) {
					lleno = false;
					break;
				}
			}
		} else {
			lleno = false;
		}
		
		return lleno;
	}

	// Comprueba si el "Registro" de "Usuario"s que maneja este programa
	// contiene alguna posición vacía (cuyo valor sea "null")
	public static int posicionDisponible(Registro registro) {
		int posicion = -1;

		if (!registroLleno(registro)) {
			for (int index = 0; index < 10; index++) {
				if (registro.getUsuario(index) == null) {
					posicion = index;
					break;
				}
			}
		}

		return posicion;
	}

	// Comprueba que los "Usuario"s del "Registro" no contengan el 
	// mismo "email" que se indica como parámetro.
	public static boolean emailUnico(Registro registro, String email) {
		boolean unico = true;

		if (!registroVacio(registro)) {
			for (Usuario usuario : registro.getUsuarios()) {
				if (usuario!= null && usuario.getEmail().equalsIgnoreCase(email)) {
					unico = false;
					break;
				}
			}
		}

		return unico;
	}

	// Comprueba que los "Usuario"s del "Registro" no coincidan 
	// con el "Usuario" (sólo su "nombre" e "email") que se indica como parámetro.
	public static boolean usuarioRepetido(Registro registro, Usuario nuevo) {
		boolean repetido = false;

		if (!registroVacio(registro)) {
			for (Usuario usuario : registro.getUsuarios()) {
				if (usuario!= null && usuario.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
					repetido = true;
					break;
				}
			}
		}

		repetido = !emailUnico(registro, nuevo.getEmail());

		return repetido;
	}

	// Busca la posición del "Registro" (índice de su propiedad Array de "Usuario"s)
	// dónde se localiza el "Usuario" que se indica como parámetro.
	public static int posicionUsuario(Registro registro, Usuario usuario) {
		int posicion = -1;

		for (int index = 0; index < registro.getUsuarios().length; index++) {
			Usuario aux = registro.getUsuario(index);
			if (aux != null && aux.getNombre().equalsIgnoreCase(usuario.getNombre())
					&& aux.getEmail().equalsIgnoreCase(usuario.getEmail())) {
				posicion = index;
				break;
			}
		}

		return posicion;
	}

	// Comprueba si la "cadena" que se indica como parámetro contiene algún
	// caracter del tipo indicado con el indicador que es el 2º parámetro
	private static boolean cadenaContiene(String cadena, int tipoCaracteres) {
		boolean contieneTipoCaracterUTF8 = false;
		int codigoTipCaracUTF8Ini, codigoTipCaracUTF8Fin;
		
		if (tipoCaracteres == LETRAS_MAYUSCULAS) {
			codigoTipCaracUTF8Ini = COD_HX_UTF8_INI_MAYUS;
			codigoTipCaracUTF8Fin = COD_HX_UTF8_FIN_MAYUS;
		} else if (tipoCaracteres == LETRAS_MINUSCULAS) {
			codigoTipCaracUTF8Ini = COD_HX_UTF8_INI_MINUS;
			codigoTipCaracUTF8Fin = COD_HX_UTF8_FIN_MINUS;
		} else if (tipoCaracteres == NUMEROS) {
			codigoTipCaracUTF8Ini = COD_HX_UTF8_INI_NUMS;
			codigoTipCaracUTF8Fin = COD_HX_UTF8_FIN_NUMS;
		} else {
			return contieneTipoCaracterUTF8;
		}

		// Obtenemos los códigos hexadecimales de la codificación UTF8
		// correspondientes a cada una de las letras del parámetro "cadena"
		byte[] codigosHx = cadena.getBytes(StandardCharsets.UTF_8);

		// Recorremos dichos códigos, para ver si se corresponde
		// alguno de ellos a los tipos de caracteres buscados.
		for (byte codigoHx : codigosHx) {
			
			if (codigoHx >= codigoTipCaracUTF8Ini && codigoHx <= codigoTipCaracUTF8Fin) {
				contieneTipoCaracterUTF8 = true;
				break;
			}
		}
		
		return contieneTipoCaracterUTF8;

	}

	// Comprueba si el "password" indicado como parámetro 
	// tiene la estructura (formato) válida según los requisitos del programa.
	public static boolean passwordValido(String password) {
		boolean valido = true;

		// La longitud de la password debe ser de entre 5 y 8 caracteres
		if (!(password.length() >= 5 && password.length() <= 8)) {
			System.out.println("La longitud de la password debe ser de entre 5 y 8 caracteres");
			valido = false;
		}
		
		// Debe contener tanto mayúsculas como minúsculas
		if (!cadenaContiene(password, LETRAS_MAYUSCULAS)) {
			System.out.println("La password debe contener mayúsculas");
			valido = false;
		}
		
		if (!cadenaContiene(password, LETRAS_MINUSCULAS)) {
			System.out.println("La password debe contener minúsculas");
			valido = false;
		}
		

		// Debe contener algún número
		if (!cadenaContiene(password, NUMEROS)) {
			System.out.println("La password debe contener algún número");
			valido = false;
		}

		return valido;
	}
	
	
	// Comprueba si el "email" indicado como parámetro 
	// tiene la estructura (formato) válida según los requisitos del programa.
	public static boolean emailValido(String email) {
		boolean valido = false;

		if (email.matches("^\\w+@\\w+\\.(\\D{1,4})$")) {
			valido = true;
		}

		return valido;
	}
}