package modelo;

import java.util.List;
import java.util.stream.Collectors;

public class GestiondeInventario {
    public static void main(String[] args) {
       
        InventarioService servicio = InventarioService.getInstancia();
        List<Producto> inventario = servicio.getListaInventario();

        System.out.println("=== REPORTE GENERAL DE APEX DEPÓSITOS S.A. ===");

        
        List<Producto> stockAdecuado = inventario.stream()
                .filter(p-> p.getStock() > 50)
                .collect(Collectors.toList());
        System.out.println("Productos con stock mayor a 50 unidades: " + stockAdecuado.size());

       
        List<String> descripcionesMayus = inventario.stream()
                .map(p-> p.getDescripcion().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Descripcion de los Materiales dentro del Inventario: " + descripcionesMayus);

        
        int stockTotal=inventario.stream()
                .map(Producto::getStock)
                .reduce(0, (subtotal, stockActual) -> subtotal + stockActual);
        
        System.out.println(">>> Stock Total del Almacen es de: " + stockTotal + " unidades <<<\n");
    }
}