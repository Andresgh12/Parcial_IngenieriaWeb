package com.parcial.service;

import com.parcial.model.Producto;
import com.parcial.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto nuevoProducto) {
        return repository.findById(id).map(producto -> {
            producto.setNombre(nuevoProducto.getNombre());
            producto.setDescripcion(nuevoProducto.getDescripcion());
            producto.setPrecio(nuevoProducto.getPrecio());
            producto.setStock(nuevoProducto.getStock());
            return repository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
