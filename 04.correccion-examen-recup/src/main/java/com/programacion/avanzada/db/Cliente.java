package com.programacion.avanzada.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cliente_gen")
    @TableGenerator(
            name = "cliente_gen",
            table = "tablas_generadoras",
            pkColumnName = "tabla",
            valueColumnName = "valor",
            allocationSize = 1,
            pkColumnValue = "clientes"
    )
    @Column(name = "cliente_id")
    private Integer id;

    @Column(name = "cliente_nombre", nullable = false)
    private String nombre;

    @Column(name = "cliente_apellido", nullable = false)
    private String apellido;

    @Column(name = "cliente_direccion", nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<OrdenCompra> ordenCompras;

    public Cliente() {
        //requerido por JPA
    }

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public String getNombreCompleto() {
        return this.apellido + " " + this.nombre;
    }
}
