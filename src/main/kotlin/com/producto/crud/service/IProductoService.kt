package com.producto.crud.service

import com.producto.crud.model.Producto
import java.util.*

interface IProductoService {
    fun getProductos():List<Producto>
    fun getProductosById(id: Long): Producto?
    fun addProducto(producto: Producto) : Producto
    fun editProducto(id: Long, producto: Producto): Producto?
    fun deleteProductoById(id: Long)
}