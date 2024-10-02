package com.producto.crud.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Proveedor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var nombre: String,
    var apellido: String,
    var telefono: String,


)