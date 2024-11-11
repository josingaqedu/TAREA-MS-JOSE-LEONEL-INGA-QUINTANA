package com.ms_rest_template.ms_rest_template.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "company")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "estado")
    private String estado;

    @Column(name = "condicion")
    private String condicion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ubigeo")
    private String ubigeo;

    @Column(name = "via_tipo")
    private String viaTipo;

    @Column(name = "via_nombre")
    private String viaNombre;

    @Column(name = "zona_codigo")
    private String zonaCodigo;

    @Column(name = "zona_tipo")
    private String zonaTipo;

    @Column(name = "numero")
    private String numero;

    @Column(name = "interior")
    private String interior;

    @Column(name = "lote")
    private String lote;

    @Column(name = "dpto")
    private String dpto;

    @Column(name = "manzana")
    private String manzana;

    @Column(name = "kilometro")
    private String kilometro;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "es_agente_retencion")
    private Boolean esAgenteRetencion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "actividad_economica")
    private String actividadEconomica;

    @Column(name = "numero_trabajadores")
    private String numeroTrabajadores;

    @Column(name = "tipo_facturacion")
    private String tipoFacturacion;

    @Column(name = "tipo_contabilidad")
    private String tipoContabilidad;

    @Column(name = "comercio_exterior")
    private String comercioExterior;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "creado_por")
    private String creadoPor;

    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;

    @Column(name = "actualizado_por")
    private String actualizadoPor;
}
