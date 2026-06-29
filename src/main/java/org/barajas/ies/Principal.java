package org.barajas.ies;

import java.util.Scanner;

import org.barajas.ies.entities.Registro;
import org.barajas.ies.entities.Usuario;
import org.barajas.ies.services.Gestion;
import org.barajas.ies.util.Utilidad;

public class Principal {
	
	private static Scanner entrada = new Scanner(System.in);
	private static Registro registro = Registro.getInstance();
	
	private static int menu() {
		int opcion = 0;
		
		System.out.println("*** ########## GESTIÓN DE USUARIOS ########## ***");
		System.out.println("*** -------------------------------------------------------------- ***");
		System.out.println("1. Ver registro de usuarios ");
		System.out.println("2. Dar de alta nuevo usuario");
		System.out.println("3. Eliminar usuario ");
		System.out.println("0. Terminar programa");
		
		System.out.println();
		
		opcion = entrada.nextInt();
		// A veces el Scanner deja en la caché un salto de línea
		// Hay que obligar a vaciar el buffer.
		if (entrada.hasNextLine())
			entrada.nextLine();
		
		return opcion;
	}
	
	private static void finOpcion() {
		System.out.println("*** Pulse ENTER para volver al menú ... ***");
		
		// La utilizamos para recoger la opción que haya introducido poniendo el número
		// correspondiente el usuario por teclado
		entrada.nextLine();
		
		System.out.println("\n\n\n");

	}
	
	private static void verRegistro() {
		
		if (Utilidad.registroVacio(registro)) {
			System.out.println("\t\tNo hay usuarios registrados");
		} else {
			for (Usuario usuario: registro.getUsuarios()) {
				if (usuario != null) {
					System.out.println("\t\t"+usuario.toString());
				}
			}
		}
		
		finOpcion();
	}
	
	private static void altaUsuario() {
		Usuario nuevo = new Usuario();
		boolean fallo = false;
		
		System.out.println("\t*** Introduzca el EMAIL del nuevo Usuario y después pulse ENTER ***");
		String campo = entrada.nextLine();
		if (!Utilidad.emailValido(campo)) {
			System.out.println("\n\t\tEmail NO válido");
			fallo=true;
		} else if(!Utilidad.emailUnico(registro, campo)){
			System.out.println("\n\t\tYa existe un usuario registrado con ese email");
			fallo=true;
		} else {
			nuevo.setEmail(campo);
		}
		
		if (!fallo) {
			System.out.println("\t*** Introduzca el NOMBRE del nuevo Usuario y después pulse ENTER ***");
			campo = entrada.nextLine();
			nuevo.setNombre(campo);
			if (Utilidad.usuarioRepetido(registro, nuevo)) {
				System.out.println("\n\t\tYa existe un usuario registrado con ese nombre");
				fallo=true;
			} else {
				System.out.println("\t*** Introduzca la CONTRASEÑA del nuevo Usuario y después pulse ENTER ***");
				campo = entrada.nextLine();
				while (!Utilidad.passwordValido(campo)) {
					System.out.println("\n\t\tCorrija los errores en la CONTRASEÑA \n");
					System.out.println("\t*** Introduzca la CONTRASEÑA del nuevo Usuario y después pulse ENTER ***");
					campo = entrada.nextLine();
				}
				nuevo.setPassword(campo);
			}
		}
		
		if (!fallo) {
			Gestion.addUser(registro, nuevo);
		}
		
		finOpcion();
		
	}
	
	private static void bajaUsuario() {
		Usuario antiguo = new Usuario();
		boolean fallo = false;
		
		System.out.println("\t*** Introduzca el EMAIL del Usuario a borrar y después pulse ENTER ***");
		String campo = entrada.nextLine();
		if (!Utilidad.emailValido(campo)) {
			System.out.println("\n\t\tEmail NO válido");
			fallo=true;
		} else {
			antiguo.setEmail(campo);
		}
		
		if (!fallo) {
			System.out.println("\t*** Introduzca el NOMBRE del Usuario a Borrar y después pulse ENTER ***");
			campo = entrada.nextLine();
			antiguo.setNombre(campo);
			if (Utilidad.usuarioRepetido(registro, antiguo)) {
				if (Gestion.removeUser(registro, antiguo)) {
					System.out.println("\n\t\tUsuario eliminado correctamente");
				} else {
					System.out.println("\n\t\tFallo al eliminar el usuario al no estar registrado");
					fallo=true;
				}
			}
		}
		
		finOpcion();
		
	}
	
	public static void main(String[] args) {
		
		int opcion = -1;
		
		do {
			
			opcion = menu();
			switch (opcion) {
				case 0:
					System.out.println("*** ----------------------- FIN DEL PROGRAMA --------------------- ***");
					break;
				case 1:
					verRegistro();
					break;
				case 2:
					altaUsuario();
					break;
				case 3:
					bajaUsuario();
					break;
				default:
					finOpcion();
			}
			
		} while (opcion != 0);
		
	}
}