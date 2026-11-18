/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

/**
 *
 * @author Bryan
 */
public class Producto
{
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    
    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    /**
     * Calcula el precio final aplicando un porcentaje de descuento entre 0% y 50%.
     */
    public double calcularDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 50)
        { throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 50."); }
        
        //Recien entonces calcula el precio con descuento
        return this.precio - (this.precio * (porcentaje / 100.0));
    }
    
    /**
     * Descuenta unidades del stock tras una venta.
     */
    public void vender(int cantidad) {
        if (cantidad <= 0)
        { throw new IllegalArgumentException("La cantidad a vender debe ser mayor a cero."); }
        
        if (cantidad > this.stock)
        { throw new IllegalArgumentException("Stock insuficiente para realizar la venta."); }
        
        //Finalmente actualiza la cantidad restante
        this.stock -= cantidad;
    }
    
    /**
     * Suma unidades al stock disponible.
     */
    public void reponer(int cantidad) {
        if (cantidad <= 0)
        { throw new IllegalArgumentException("La cantidad a reponer debe ser mayor a cero."); }
        
        this.stock += cantidad;
    }
    
    /**
     * Indica si el producto se encuentra sin unidades disponibles.
     */
    public boolean estaAgotado() {
        return this.stock == 0;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}
