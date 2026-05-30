package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService {

    // CORRECCIÓN 1: Inyección por constructor (Code Smell resuelto)
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // CORRECCIÓN 3: Método principal orquestador para reducir Complejidad Ciclomática
    public Producto procesarProducto(String nombre, Double precio, Integer stock, String cat, boolean activo, String proveedor) {
        validarDatos(nombre, precio, stock);
        Producto producto = new Producto();
        producto.setNombre(nombre.strip());
        producto.setPrecio(precio);
        producto.setStock(stock);
        // TODO: implementar lógica de categoría y proveedor
        return productoRepository.save(producto);
    }

    // CORRECCIÓN 3: Validación extraída a método privado
    private void validarDatos(String nombre, Double precio, Integer stock) {
        // CORRECCIÓN 2: Uso correcto de isBlank() en lugar de equals("")
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (precio > 999999) {
            throw new IllegalArgumentException("El precio excede el máximo permitido");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // CORRECCIÓN BUG CRÍTICO: Se lanza excepción en lugar de retornar null
    public Producto buscar(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado: " + id));
    }
}