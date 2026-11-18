/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite de pruebas unitarias para la clase Producto.
 */
public class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        // Arrange global: Se instancia un producto base antes de cada prueba
        // Código: FER-001, Nombre: Taladro Percutor, Precio: $10.000, Stock: 20 unidades
        producto = new Producto("FER-001", "Taladro Percutor", 10000.0, 20);
    }

    @ParameterizedTest(name = "Descuento del {0}% sobre $10.000 debe resultar en ${1}")
    @CsvSource({
        "0, 10000.0",
        "10, 9000.0",
        "25, 7500.0",
        "50, 5000.0"
    })
    @DisplayName("Calcular descuento con porcentajes válidos (0% a 50%)")
    void testCalcularDescuentoValido(double porcentaje, double precioEsperado) {
        // Act
        double resultado = producto.calcularDescuento(porcentaje);

        // Assert
        assertEquals(precioEsperado, resultado, 0.01, "El precio con descuento no coincide con el esperado.");
    }

    @Test
    @DisplayName("Lanzar excepción si el descuento es negativo")
    void testCalcularDescuentoNegativoLanzaExcepcion() {
        // Arrange
        double porcentajeInvalido = -5.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            producto.calcularDescuento(porcentajeInvalido);
        }, "Debería lanzar IllegalArgumentException para descuentos menores a 0.");
    }

    @Test
    @DisplayName("Lanzar excepción si el descuento supera el 50%")
    void testCalcularDescuentoMayorA50LanzaExcepcion() {
        // Arrange
        double porcentajeExcesivo = 60.0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            producto.calcularDescuento(porcentajeExcesivo);
        }, "Debería lanzar IllegalArgumentException para descuentos mayores a 50.");
    }

    @Test
    @DisplayName("Vender una cantidad válida descuenta correctamente el stock")
    void testVenderCantidadValida() {
        // Arrange
        int cantidadVenta = 5;

        // Act
        producto.vender(cantidadVenta);

        // Assert
        assertEquals(15, producto.getStock(), "El stock restante debería ser 15.");
    }

    @Test
    @DisplayName("Lanzar excepción al intentar vender más unidades que el stock disponible")
    void testVenderSinStockSuficienteLanzaExcepcion() {
        // Arrange
        int cantidadExcesiva = 25; // El stock inicial es 20

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            producto.vender(cantidadExcesiva);
        }, "Debería lanzar excepción cuando la venta supera el stock disponible.");
    }

    @Test
    @DisplayName("Reponer unidades válidas incrementa correctamente el stock")
    void testReponerCantidadValida() {
        // Arrange
        int cantidadReposicion = 10;

        // Act
        producto.reponer(cantidadReposicion);

        // Assert
        assertEquals(30, producto.getStock(), "El stock actualizado debería ser 30.");
    }

    @Test
    @DisplayName("Lanzar excepción al intentar reponer cantidad cero o negativa")
    void testReponerCantidadCeroONegativaLanzaExcepcion() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            producto.reponer(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            producto.reponer(-10);
        });
    }

    @Test
    @DisplayName("Verificar que estaAgotado() devuelve false cuando hay stock")
    void testEstaAgotadoConStock() {
        // Act
        boolean agotado = producto.estaAgotado();

        // Assert
        assertFalse(agotado, "El producto tiene 20 unidades, no debería estar agotado.");
    }

    @Test
    @DisplayName("Verificar que estaAgotado() devuelve true cuando el stock llega a cero")
    void testEstaAgotadoCuandoStockEsCero() {
        // Arrange
        producto.vender(20); // Se vende todo el stock disponible

        // Act
        boolean agotado = producto.estaAgotado();

        // Assert
        assertTrue(agotado, "El producto debería marcarse como agotado tras vender todo su stock.");
    }
}
