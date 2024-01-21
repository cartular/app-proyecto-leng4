package com.varasccatalina.myapplication;

// Clase de datos para representar una facultad
public class Facultad {
    private String nombre;
    private String horarios;
    private String telefono;
    private String descripcion;
    public Facultad(String nombre, String horarios, String telefono) {
        this.nombre = nombre;
        this.horarios = horarios;
        this.telefono = telefono;

    }

    // Getters y setters para acceder a la información de la facultad
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getDescripcion() {
        return "Horarios: " + horarios + "\nTeléfono: " + telefono;
    }
}
