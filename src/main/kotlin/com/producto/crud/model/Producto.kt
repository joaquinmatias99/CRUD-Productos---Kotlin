package com.producto.crud.model

import jakarta.persistence.*

@Entity
data class Producto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        var nombre: String,
        var precio: Double,

        @ManyToOne
        @JoinColumn(name = "proveedor_id")
        var proveedor: Proveedor
)