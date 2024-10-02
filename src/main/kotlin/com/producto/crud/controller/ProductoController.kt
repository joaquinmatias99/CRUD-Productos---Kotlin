package com.producto.crud.controller

import com.producto.crud.model.Producto
import com.producto.crud.service.IProductoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/productos")
class ProductoController(private val productoService: IProductoService)
{
    @GetMapping
    fun getProductos(): ResponseEntity<List<Producto>>{
        return ResponseEntity.ok(productoService.getProductos())
    }

    @GetMapping("/{id}")
    fun getProductosById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val producto = productoService.getProductosById(id)
            ResponseEntity.ok(producto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }
    }
    @GetMapping("/proveedor/{id}")
    fun getProductosByIdProveedor(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val producto = productoService.getProductosByIdProveedor(id)
            ResponseEntity.ok(producto)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }
    }

    @PostMapping
    fun addProducto(@RequestBody producto: Producto) : ResponseEntity<Producto>
    {
        val productoNuevo = productoService.addProducto(producto)
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo)
    }
    @PutMapping("/{id}")
    fun editProducto(@PathVariable id: Long, @RequestBody producto: Producto): ResponseEntity<Any> {
        return try {
            val productoEditado = productoService.editProducto(id, producto)
            ResponseEntity.ok(productoEditado)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProducto(@PathVariable id: Long): ResponseEntity<String>
    {
        return try {
            productoService.deleteProductoById(id)
            ResponseEntity.noContent().build();
        }catch (e:Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }
    }

}