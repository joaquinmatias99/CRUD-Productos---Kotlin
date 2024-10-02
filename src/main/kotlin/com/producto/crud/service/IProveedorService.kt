package com.proveedor.crud.service

import com.producto.crud.model.Proveedor

interface IProveedorService 
{
    fun getProveedores():List<Proveedor>
    fun getProveedorsById(id: Long): Proveedor?
    fun addProveedor(proveedor: Proveedor) : Proveedor
    fun editProveedor(id: Long, proveedor: Proveedor): Proveedor?
    fun deleteProveedorById(id: Long)
}