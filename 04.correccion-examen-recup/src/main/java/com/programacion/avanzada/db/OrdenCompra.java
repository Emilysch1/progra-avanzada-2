package com.programacion.avanzada.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "ordenes_compra")
@Getter
@Setter
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ordenes_compra_gen")
    @TableGenerator(
            name = "ordenes_compra_gen",
            table = "tablas_generadoras",
            pkColumnName = "tabla",
            valueColumnName = "valor",
            allocationSize = 1,
            pkColumnValue = "ordenes_compra"
    )
    @Column(name = "orden_compra_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "orden_compra_precio")
    private BigDecimal precio;

    public OrdenCompra() {

    }

    public OrdenCompra(Cliente cliente, Producto producto, BigDecimal precio) {
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
    }
}
