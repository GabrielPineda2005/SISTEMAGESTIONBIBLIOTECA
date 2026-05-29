/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoprogra3;

import java.util.*;

/**
 *
 * @author angel
 */
public class ProyectoProgra3 {

    // ========================= CLASE LIBRO =========================
    static class Libro {
        String codigo;
        String titulo;
        String autor;
        boolean disponible;

        Queue<String> colaEspera = new LinkedList<>();

        public Libro(String codigo, String titulo, String autor) {
            this.codigo = codigo;
            this.titulo = titulo;
            this.autor = autor;
            this.disponible = true;
        }

        @Override
        public String toString() {
            return "Código: " + codigo
                    + " | Título: " + titulo
                    + " | Autor: " + autor
                    + " | Disponible: " + (disponible ? "Sí" : "No");
        }
    }

    // ========================= CLASE USUARIO =========================
    static class Usuario {
        String id;
        String nombre;

        public Usuario(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Nombre: " + nombre;
        }
    }

    // ========================= CLASE PRESTAMO =========================
    static class Prestamo {
        String usuario;
        String libro;
        String fecha;

        public Prestamo(String usuario, String libro, String fecha) {
            this.usuario = usuario;
            this.libro = libro;
            this.fecha = fecha;
        }

        @Override
        public String toString() {
            return "Usuario: " + usuario
                    + " | Libro: " + libro
                    + " | Fecha: " + fecha;
        }
    }

    // ========================= ESTRUCTURAS =========================
    static ArrayList<Libro> libros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static LinkedList<Prestamo> prestamos = new LinkedList<>();
    static Stack<String> historial = new Stack<>();
    static HashMap<String, Usuario> mapaUsuarios = new HashMap<>();

    // ========================= MAIN =========================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n========= BIBLIOTECA UNIVERSITARIA =========");
            System.out.println("1. Registrar libro");
            System.out.println("2. Mostrar libros");
            System.out.println("3. Buscar libro");
            System.out.println("4. Eliminar libro");
            System.out.println("5. Registrar usuario");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Buscar usuario");
            System.out.println("8. Registrar préstamo");
            System.out.println("9. Registrar devolución");
            System.out.println("10. Mostrar préstamos");
            System.out.println("11. Mostrar historial");
            System.out.println("12. Mostrar cola de espera");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    registrarLibro(sc);
                    break;

                case 2:
                    mostrarLibros();
                    break;

                case 3:
                    buscarLibro(sc);
                    break;

                case 4:
                    eliminarLibro(sc);
                    break;

                case 5:
                    registrarUsuario(sc);
                    break;

                case 6:
                    mostrarUsuarios();
                    break;

                case 7:
                    buscarUsuario(sc);
                    break;

                case 8:
                    registrarPrestamo(sc);
                    break;

                case 9:
                    registrarDevolucion(sc);
                    break;

                case 10:
                    mostrarPrestamos();
                    break;

                case 11:
                    mostrarHistorial();
                    break;

                case 12:
                    mostrarColaEspera(sc);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

    }

    // ========================= REGISTRAR LIBRO =========================
    public static void registrarLibro(Scanner sc) {

        System.out.print("Ingrese código del libro: ");
        String codigo = sc.nextLine();

        System.out.print("Ingrese título del libro: ");
        String titulo = sc.nextLine();

        System.out.print("Ingrese autor del libro: ");
        String autor = sc.nextLine();

        Libro libro = new Libro(codigo, titulo, autor);

        libros.add(libro);

        historial.push("Se registró el libro: " + titulo);

        System.out.println("Libro registrado correctamente.");
    }

    // ========================= MOSTRAR LIBROS =========================
    public static void mostrarLibros() {

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        System.out.println("\n===== LISTA DE LIBROS =====");

        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    // ========================= BUSCAR LIBRO =========================
    public static void buscarLibro(Scanner sc) {

        System.out.print("Ingrese código o título del libro: ");
        String dato = sc.nextLine();

        boolean encontrado = false;

        for (Libro libro : libros) {

            if (libro.codigo.equalsIgnoreCase(dato)
                    || libro.titulo.equalsIgnoreCase(dato)) {

                System.out.println("Libro encontrado:");
                System.out.println(libro);

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }
    }

    // ========================= ELIMINAR LIBRO =========================
    public static void eliminarLibro(Scanner sc) {

        System.out.print("Ingrese código del libro a eliminar: ");
        String codigo = sc.nextLine();

        Iterator<Libro> it = libros.iterator();

        while (it.hasNext()) {

            Libro libro = it.next();

            if (libro.codigo.equalsIgnoreCase(codigo)) {

                it.remove();

                historial.push("Se eliminó el libro: " + libro.titulo);

                System.out.println("Libro eliminado correctamente.");
                return;
            }
        }

        System.out.println("Libro no encontrado.");
    }

    // ========================= REGISTRAR USUARIO =========================
    public static void registrarUsuario(Scanner sc) {

        System.out.print("Ingrese ID del usuario: ");
        String id = sc.nextLine();

        System.out.print("Ingrese nombre del usuario: ");
        String nombre = sc.nextLine();

        Usuario usuario = new Usuario(id, nombre);

        usuarios.add(usuario);

        mapaUsuarios.put(id, usuario);

        historial.push("Se registró el usuario: " + nombre);

        System.out.println("Usuario registrado correctamente.");
    }

    // ========================= MOSTRAR USUARIOS =========================
    public static void mostrarUsuarios() {

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("\n===== LISTA DE USUARIOS =====");

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    // ========================= BUSCAR USUARIO =========================
    public static void buscarUsuario(Scanner sc) {

        System.out.print("Ingrese ID del usuario: ");
        String id = sc.nextLine();

        Usuario usuario = mapaUsuarios.get(id);

        if (usuario != null) {

            System.out.println("Usuario encontrado:");
            System.out.println(usuario);

        } else {

            System.out.println("Usuario no encontrado.");
        }
    }

    // ========================= REGISTRAR PRESTAMO =========================
    public static void registrarPrestamo(Scanner sc) {

        System.out.print("Ingrese ID del usuario: ");
        String idUsuario = sc.nextLine();

        Usuario usuario = mapaUsuarios.get(idUsuario);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Ingrese código del libro: ");
        String codigoLibro = sc.nextLine();

        for (Libro libro : libros) {

            if (libro.codigo.equalsIgnoreCase(codigoLibro)) {

                if (libro.disponible) {

                    libro.disponible = false;

                    System.out.print("Ingrese fecha del préstamo: ");
                    String fecha = sc.nextLine();

                    Prestamo prestamo = new Prestamo(usuario.nombre, libro.titulo, fecha);

                    prestamos.add(prestamo);

                    historial.push("Préstamo registrado del libro: " + libro.titulo);

                    System.out.println("Préstamo realizado correctamente.");

                } else {

                    System.out.println("Libro no disponible.");
                    System.out.print("¿Desea entrar a la cola de espera? (si/no): ");

                    String respuesta = sc.nextLine();

                    if (respuesta.equalsIgnoreCase("si")) {

                        libro.colaEspera.add(usuario.nombre);

                        historial.push("Usuario agregado a cola de espera: " + usuario.nombre);

                        System.out.println("Usuario agregado a la cola.");
                    }
                }

                return;
            }
        }

        System.out.println("Libro no encontrado.");
    }

    // ========================= REGISTRAR DEVOLUCION =========================
    public static void registrarDevolucion(Scanner sc) {

        System.out.print("Ingrese código del libro: ");
        String codigo = sc.nextLine();

        for (Libro libro : libros) {

            if (libro.codigo.equalsIgnoreCase(codigo)) {

                libro.disponible = true;

                historial.push("Devolución registrada del libro: " + libro.titulo);

                System.out.println("Libro devuelto correctamente.");

                if (!libro.colaEspera.isEmpty()) {

                    String siguiente = libro.colaEspera.poll();

                    System.out.println("Siguiente usuario en cola: " + siguiente);

                    historial.push("Se atendió cola de espera del libro: " + libro.titulo);
                }

                return;
            }
        }

        System.out.println("Libro no encontrado.");
    }

    // ========================= MOSTRAR PRESTAMOS =========================
    public static void mostrarPrestamos() {

        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        System.out.println("\n===== LISTA DE PRÉSTAMOS =====");

        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }

    // ========================= MOSTRAR HISTORIAL =========================
    public static void mostrarHistorial() {

        if (historial.isEmpty()) {
            System.out.println("No hay historial.");
            return;
        }

        System.out.println("\n===== HISTORIAL =====");

        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println(historial.get(i));
        }
    }

    // ========================= MOSTRAR COLA =========================
    public static void mostrarColaEspera(Scanner sc) {

        System.out.print("Ingrese código del libro: ");
        String codigo = sc.nextLine();

        for (Libro libro : libros) {

            if (libro.codigo.equalsIgnoreCase(codigo)) {

                if (libro.colaEspera.isEmpty()) {

                    System.out.println("No hay usuarios en cola.");

                } else {

                    System.out.println("===== COLA DE ESPERA =====");

                    for (String usuario : libro.colaEspera) {
                        System.out.println(usuario);
                    }
                }

                return;
            }
        }

        System.out.println("Libro no encontrado.");
    }
}