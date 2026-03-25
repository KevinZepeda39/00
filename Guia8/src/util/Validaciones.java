package util;

public class Validaciones {
    public static boolean esNumero(String valor) {
        return valor.matches("[0-9]+");
    }

    public static boolean esTexto(String texto) {
        return texto.matches("[a-zA-Z ]+");
    }

    public static boolean noVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
}
