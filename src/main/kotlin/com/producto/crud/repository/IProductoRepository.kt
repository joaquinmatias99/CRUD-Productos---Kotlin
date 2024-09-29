package com.producto.crud.repository

import com.producto.crud.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProductoRepository : JpaRepository<Producto, Long> {
}