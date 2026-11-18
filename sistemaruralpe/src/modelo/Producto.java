package modelo;

public class Producto {//Como estaran conformados cada producto
    private String codigo;
    private String descripcion;
    private int stock;  
    public Producto(String codigo, String descripcion, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
    }   
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}