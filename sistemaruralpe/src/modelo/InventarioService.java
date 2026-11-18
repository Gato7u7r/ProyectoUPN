package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventarioService {

	private static InventarioService instancia;
	private List<Producto> listaInventario;

	private InventarioService() {
		listaInventario = new ArrayList<>();

		listaInventario.add(crearProducto("P0001", "Cemento Sol 42.5kg", 150));
		listaInventario.add(crearProducto("P0002", "Fierro Corrugado 3/8", 30));
		listaInventario.add(crearProducto("P0003", "Ladrillo King Kong 18H", 500));
	}

	
	public static InventarioService getInstancia() {
		if (instancia == null) {
			instancia = new InventarioService();
		}
		return instancia;
	}

	
	public static Producto crearProducto(String codigo, String descripcion, int stock) {
		return new Producto(codigo.toUpperCase().trim(), descripcion.trim(), stock);
	}

	public void agregarProducto(Producto p) {
		listaInventario.add(p);
	}

	
	public List<Producto> getListaInventario() {
		return Collections.unmodifiableList(listaInventario);
	}
}