package modelo;

public class ValidarInventario {
    public static void main(String[] args) {
       
        String stockTexto = "Cincuenta"; 

        try {
            int stockReal = Integer.parseInt(stockTexto);
            System.out.println("Stock convertido con éxito: " + stockReal);
        } catch (NumberFormatException e) {
            System.out.println("[Alerta de Error de Inventario]: El valor de stock ingresado no es un número válido.");
            System.out.println("El sistema de control continúa funcionando de manera controlada.");
        }
    }
}