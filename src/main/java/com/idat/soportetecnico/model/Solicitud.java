package com.idat.soportetecnico.model;

public class Solicitud {

    private Long id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private EstadoSolicitud estado;
    private Cliente cliente;
    private Tecnico tecnico;

    // Constructor vacío
    public Solicitud() {
    }

    // Constructor con todos los atributos
    public Solicitud(Long id,
                     String titulo,
                     String descripcion,
                     Prioridad prioridad,
                     EstadoSolicitud estado,
                     Cliente cliente,
                     Tecnico tecnico) {

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.cliente = cliente;
        this.tecnico = tecnico;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}