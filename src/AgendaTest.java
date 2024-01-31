import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class AgendaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAgregarYEliminarContacto() {
        Agenda agenda = new Agenda();

        // Asegurarse de que la agenda está vacía al principio
        agenda.mostrarContactos();
        assertEquals("", outContent.toString().trim());

        // Agregar un contacto y verificar que se agregó correctamente
        agenda.agregarContacto("Prueba", "1234567890");
        outContent.reset();
        agenda.mostrarContactos();
        assertTrue(outContent.toString().trim().contains("Prueba"));
        assertTrue(outContent.toString().trim().contains("1234567890"));

        // Eliminar el contacto y verificar que se eliminó correctamente
        agenda.eliminarContacto("Prueba");
        outContent.reset();
        agenda.mostrarContactos();
        assertEquals("", outContent.toString().trim());
    }
}
