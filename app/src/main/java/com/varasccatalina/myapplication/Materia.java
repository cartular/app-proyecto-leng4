package com.varasccatalina.myapplication;

public class Materia {
    private String nombre;
    private String tipo;
    private String horario;
    private String horarioFin;
    private String aula;
    private String profesor;
    private String edificio;

    public Materia(String nombre,String horarioFin, String tipo, String horario, String aula, String profesor,String edificio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.horario = horario;
        this.aula = aula;
        this.profesor = profesor;
        this.edificio = edificio;
        this.horarioFin=horarioFin;
    }
}
