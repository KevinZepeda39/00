package vista;

import dao.AlumnoDAO;
import dao.AlumnoMateriaDAO;
import dao.MateriaDAO;
import dao.ReporteDAO;
import util.Validaciones;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private static final MateriaDAO materiaDAO = new MateriaDAO();
    private static final AlumnoMateriaDAO alumnoMateriaDAO = new AlumnoMateriaDAO();
    private static final ReporteDAO reporteDAO = new ReporteDAO();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    insertarAlumno(sc);
                    break;
                case "2":
                    if (!alumnoDAO.listar()) {
                        mostrarError("No se pudo listar alumnos.");
                    }
                    break;
                case "3":
                    actualizarAlumno(sc);
                    break;
                case "4":
                    eliminarAlumno(sc);
                    break;
                case "5":
                    insertarMateria(sc);
                    break;
                case "6":
                    asignarMateria(sc);
                    break;
                case "7":
                    reporteMaterias(sc);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Menu ===");
        System.out.println("1. Insertar alumno");
        System.out.println("2. Listar alumnos");
        System.out.println("3. Actualizar alumno");
        System.out.println("4. Eliminar alumno");
        System.out.println("5. Insertar materia");
        System.out.println("6. Asignar materia a alumno");
        System.out.println("7. Reporte materias de alumno");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    private static void insertarAlumno(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Edad: ");
        String edadStr = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();

        if (!Validaciones.noVacio(nombre) || !Validaciones.noVacio(apellido) ||
                !Validaciones.noVacio(edadStr) || !Validaciones.noVacio(direccion)) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }
        if (!Validaciones.esTexto(nombre) || !Validaciones.esTexto(apellido)) {
            System.out.println("Nombre y apellido solo deben contener letras.");
            return;
        }
        if (!Validaciones.esNumero(edadStr)) {
            System.out.println("Edad debe ser un numero.");
            return;
        }

        int edad = Integer.parseInt(edadStr);
        if (alumnoDAO.insertar(nombre, apellido, edad, direccion)) {
            mostrarExito("Alumno guardado.");
        } else {
            mostrarError("No se pudo guardar el alumno.");
        }
    }

    private static void actualizarAlumno(Scanner sc) {
        System.out.print("ID alumno: ");
        String idStr = sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        if (!Validaciones.esNumero(idStr) || !Validaciones.noVacio(nombre)) {
            System.out.println("Datos invalidos.");
            return;
        }
        if (!Validaciones.esTexto(nombre)) {
            System.out.println("Nombre solo debe contener letras.");
            return;
        }

        if (alumnoDAO.actualizar(Integer.parseInt(idStr), nombre)) {
            mostrarExito("Alumno actualizado.");
        } else {
            mostrarError("No se pudo actualizar el alumno.");
        }
    }

    private static void eliminarAlumno(Scanner sc) {
        System.out.print("ID alumno: ");
        String idStr = sc.nextLine();

        if (!Validaciones.esNumero(idStr)) {
            System.out.println("ID invalido.");
            return;
        }

        if (alumnoDAO.eliminar(Integer.parseInt(idStr))) {
            mostrarExito("Alumno eliminado.");
        } else {
            mostrarError("No se pudo eliminar el alumno.");
        }
    }

    private static void insertarMateria(Scanner sc) {
        System.out.print("Nombre materia: ");
        String nombre = sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        if (!Validaciones.noVacio(nombre) || !Validaciones.noVacio(descripcion)) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }
        if (!Validaciones.esTexto(nombre)) {
            System.out.println("Nombre solo debe contener letras.");
            return;
        }

        if (materiaDAO.insertar(nombre, descripcion)) {
            mostrarExito("Materia guardada.");
        } else {
            mostrarError("No se pudo guardar la materia.");
        }
    }

    private static void asignarMateria(Scanner sc) {
        System.out.print("ID alumno: ");
        String idAlumno = sc.nextLine();
        System.out.print("ID materia: ");
        String idMateria = sc.nextLine();

        if (!Validaciones.esNumero(idAlumno) || !Validaciones.esNumero(idMateria)) {
            System.out.println("IDs invalidos.");
            return;
        }

        if (alumnoMateriaDAO.asignar(Integer.parseInt(idAlumno), Integer.parseInt(idMateria))) {
            mostrarExito("Asignacion realizada.");
        } else {
            mostrarError("No se pudo asignar la materia.");
        }
    }

    private static void reporteMaterias(Scanner sc) {
        System.out.print("ID alumno: ");
        String idAlumno = sc.nextLine();

        if (!Validaciones.esNumero(idAlumno)) {
            System.out.println("ID invalido.");
            return;
        }

        if (!reporteDAO.materiasDeAlumno(Integer.parseInt(idAlumno))) {
            mostrarError("No se pudo generar el reporte.");
        }
    }

    private static void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operacion no realizada", JOptionPane.ERROR_MESSAGE);
    }
}
