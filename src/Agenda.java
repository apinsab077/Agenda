import java.io.*;
import java.util.*;

public class Agenda implements Serializable{
    private List<Contacto> contactos;

    private class Contacto implements Serializable {
        String nombre;
        String telefono;

        public Contacto(String nombre, String telefono) {
            this.nombre = nombre;
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + ", Teléfono: " + telefono;
        }
    }

    public Agenda() {
        contactos = new ArrayList<>();
        cargarContactos();
    }

    public void agregarContacto(String nombre, String telefono) {
        contactos.add(new Contacto(nombre, telefono));
        guardarContactos();
    }

    public void eliminarContacto(String nombre) {
        contactos.removeIf(contacto -> contacto.nombre.equals(nombre));
        guardarContactos();
    }

    public void mostrarContactos() {
        contactos.forEach(System.out::println);
    }

    private void cargarContactos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("agenda.dat"))) {
            contactos = (List<Contacto>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardarContactos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("agenda.dat"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Agenda agenda = new Agenda();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nombre, telefono;
        int opcion;

        do {
            System.out.println("\n1. Agregar contacto\n2. Eliminar contacto\n3. Mostrar contactos\n4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(reader.readLine());

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del contacto: ");
                    nombre = reader.readLine();
                    System.out.print("Ingrese el teléfono del contacto: ");
                    telefono = reader.readLine();
                    agenda.agregarContacto(nombre, telefono);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del contacto a eliminar: ");
                    nombre = reader.readLine();
                    agenda.eliminarContacto(nombre);
                    break;
                case 3:
                    agenda.mostrarContactos();
                    break;
            }
        } while (opcion != 4);
    }
}