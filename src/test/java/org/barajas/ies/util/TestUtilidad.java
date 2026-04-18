package org.barajas.ies.util;

import static org.junit.Assert.*;

import org.barajas.ies.entities.Registro;
import org.barajas.ies.entities.Usuario;
import org.barajas.ies.services.Gestion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUtilidad {

	// Propiedad estática de la clase
	private static Registro registro;

	// Propiedad de la clase
	private Usuario usuario;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Inicializamos la propiedad "Registro"
		// para tenerla disponible en los diferentes
		// métodos de prueba
		// Antes de la ejecución de cualquier método
		registro = Registro.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Limpiamos el contenido de la propiedad "Registro"
		// tras la ejecución de todos los métodos a probar
		// en esta clase JUnit Test Case
		registro = null;
	}

	@Before
	public void setUp() throws Exception {
		// Inicializamos la propiedad "Usuario"
		// para tenerla disponible en los diferentes
		// métodos de prueba
		usuario = new Usuario("Usuario", "usuario@mail.es", "Usu123");
	}

	@After
	public void tearDown() throws Exception {
		// Aseguramos que después de la ejecución de
		// cualquier método de prueba, el registro
		// no tenga usuarios
		for (int index = 0; index < registro.getUsuarios().length; index++) {
			registro.setUsuario(null, index);
		}
	}

	@Test
	public void testRegistroVacio() {
		// fail("Not yet implemented");

		// Comprobamos que inicialmente el registro no tiene usuarios
		assertTrue(Utilidad.registroVacio(registro));

		// Añadimos un usuario al registro, para comprobar cuando no está vacío
		registro.setUsuario(usuario, 0);

		// Comprobamos que ahora el registro tiene algún usuario
		assertFalse(Utilidad.registroVacio(registro));
	}

	@Test
	public void testRegistroLleno() {
		fail("Not yet implemented");
	}

	@Test
	public void testPosicionDisponible() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmailUnico() {
		fail("Not yet implemented");
	}

	@Test
	public void testUsuarioRepetido() {
		fail("Not yet implemented");
	}

	@Test
	public void testPosicionUsuario() {
		// fail("Not yet implemented");

		// Si se inserto el usuario...
		if (Gestion.addUser(registro, usuario)) {
			// comprobaremos que la posición de nuestro "Usuario"
			// es la primera
			assertEquals(Utilidad.posicionUsuario(registro, usuario), 0);
		} else {
			// Sino se insertó dará fallo.
			fail("No se insertó el usuario");
		}
	}

	@Test
	public void testPasswordValido() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmailValido() {
		fail("Not yet implemented");
	}

}