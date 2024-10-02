package com.producto.crud.repository

import com.producto.crud.model.Proveedor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IProveedorRepository : JpaRepository<Proveedor,Long>
{
}