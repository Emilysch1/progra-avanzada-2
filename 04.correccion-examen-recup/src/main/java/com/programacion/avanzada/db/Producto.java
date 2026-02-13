package com.programacion.avanzada.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "producto_gen")
    @TableGenerator(
            name = "producto_gen",
            table = "tablas_generadoras",
            pkColumnName = "tabla",
            valueColumnName = "valor",
            allocationSize = 1,
            pkColumnValue = "productos"
    )
    @Column(name = "producto_id")
    private Integer id;

    @Column(name = "producto_nombre")
    private String nombre;

    @Column(name = "producto_precio")
    private BigDecimal precio;

    @OneToMany(mappedBy = "producto")
    private List<OrdenCompra> ordenCompras;

    public Producto() {
        //requerido por JPA
    }

    public Producto(String nombre, BigDecimal precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}

