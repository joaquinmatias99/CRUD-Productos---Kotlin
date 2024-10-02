package com.producto.crud.controller

import com.producto.crud.model.Proveedor
import com.proveedor.crud.service.IProveedorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/proveedor")
class ProveedorController(private val proveedorService: IProveedorService) {

    @GetMapping
    fun getProveedores(): ResponseEntity<List<Proveedor>> {
        return ResponseEntity.ok(proveedorService.getProveedores())
    }

    @GetMapping("/{id}")
    fun getProveedorById(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(proveedorService.getProveedorsById(id))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }
    }

    @PostMapping
    fun addProveedor(@RequestBody proveedor: Proveedor): ResponseEntity<Proveedor> {
        val proveedorNuevo = proveedorService.addProveedor(proveedor)
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorNuevo)
    }

    @PutMapping("/{id}")
    fun editProveedor(@PathVariable id: Long, @RequestBody proveedor: Proveedor): ResponseEntity<Any> {
        try {
            val proveedorEditado = proveedorService.editProveedor(id, proveedor)
            return ResponseEntity.ok(proveedorEditado)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")
        }

    }

    @DeleteMapping("/{id}")
    fun deleteProveedorById(@PathVariable id: Long): ResponseEntity<String> {
        try {
            proveedorService.deleteProveedorById(id)
            return ResponseEntity.noContent().build()
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ${e.message}")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ${e.message}")

        }
    }

}