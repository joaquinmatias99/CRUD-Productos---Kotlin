package com.producto.crud.service

import com.producto.crud.model.Proveedor
import com.producto.crud.repository.IProductoRepository
import com.producto.crud.repository.IProveedorRepository
import com.proveedor.crud.service.IProveedorService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class ProveedorService(
        private val proveedorRepository: IProveedorRepository,
        private val productoRepository: IProductoRepository
) : IProveedorService {
    override fun getProveedores(): List<Proveedor> {
        return proveedorRepository.findAll()
    }

    override fun getProveedorsById(id: Long): Proveedor? {
        return proveedorRepository.findById(id).orElseThrow {
           Exception("No existe un proveedor con la id: $id")
        }
    }

    override fun addProveedor(proveedor: Proveedor): Proveedor {
        return proveedorRepository.save(proveedor)
    }

    override fun editProveedor(id: Long, proveedor: Proveedor): Proveedor {
        val proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow { Exception("No existe un proveedor con la id: $id") }

        val proveedorActualizado = proveedorExistente.copy(
                nombre = proveedor.nombre,
                apellido = proveedor.apellido,
                telefono = proveedor.telefono
        )
        return proveedorRepository.save(proveedorActualizado)
    }

    override fun deleteProveedorById(id: Long) {
        if (!proveedorRepository.existsById(id)) {
            throw Exception("No existe un proveedor con la id: $id")
        }
        if (productoRepository.existsByProveedorId(id)) {
            throw IllegalArgumentException("No se puede eliminar el proveedor con ID: $id porque tiene productos asociados.")
        }
        proveedorRepository.deleteById(id)
    }

}