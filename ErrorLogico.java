// Código con error introducido a propósito:
public double calcularDescuento(double porcentaje) {
    if (porcentaje < 0 || porcentaje > 50) {
        throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 50.");
    }
    // ERROR INTENCIONAL: División por 10 en lugar de 100.0
    return this.precio - (this.precio * (porcentaje / 10)); 
}
