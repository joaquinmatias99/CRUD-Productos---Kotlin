package com.producto.crud.service

import com.producto.crud.model.Producto
import com.producto.crud.repository.IProductoRepository
import org.springframework.stereotype.Service

@Service
class ProductoService(private val productoRepository: IProductoRepository) : IProductoService {
    override fun getProductos(): List<Producto> {
        return productoRepository.findAll()
    }

    override fun getProductosById(id: Long): Producto {
        return productoRepository.findById(id).orElseThrow {
            Exception("No existe un producto con la id: $id")
        }
    }

    override fun getProductosByIdProveedor(id: Long): List<Producto> {
        val productos = productoRepository.findByProveedorId(id)
        if (productos.isEmpty()) {
            throw Exception("No existen productos asociados al proveedor con id: $id")
        }
        return productos
    }

    override fun addProducto(producto: Producto): Producto {
        return productoRepository.save(producto)
    }

    override fun editProducto(id: Long, producto: Producto): Producto {
        val productoExistente = productoRepository.findById(id)
        return if (productoExistente.isPresent) {
            val productoActualizado = productoExistente.get().copy(nombre = producto.nombre, precio = producto.precio, proveedor = producto.proveedor)
            productoRepository.save(productoActualizado)
        } else {
            throw Exception("No existe un producto con la ID: $id")
        }
    }


    override fun deleteProductoById(id: Long) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id)
        } else {
            throw Exception("No existe un producto con la id:$id")
        }
    }
}