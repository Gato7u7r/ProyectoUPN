package modelo;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Producto p1 = new Producto("P0001", "Cemento Sol 42.5kg", 150);
		
		System.out.println("=== REGISTRO DE APEX DEPÓSITOS S.A. ===");
		System.out.println("Material Registrado: " + p1.getDescripcion());
		System.out.println("Código: " + p1.getCodigo());
		System.out.println("Stock Inicial: " + p1.getStock() + " unidades");
	}

}