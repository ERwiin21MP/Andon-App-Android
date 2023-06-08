package com.erwiin21mp.andon;

public class AndonModel {
    String id, nombre_de_la_maquina, estado, responsable, accion, descripcion;
    int numero_de_maquina, dia, mes, year, hora, minuto, segundo, tiempo_de_resolucion_horas, tiempo_de_resolucion_minutos;

    public AndonModel(String id, String nombre_de_la_maquina, String estado, String responsable, String accion, String descripcion, int numero_de_maquina, int dia, int mes, int year, int hora, int minuto, int segundo, int tiempo_de_resolucion_horas, int tiempo_de_resolucion_minutos) {
        this.id = id;
        this.nombre_de_la_maquina = nombre_de_la_maquina;
        this.estado = estado;
        this.responsable = responsable;
        this.accion = accion;
        this.descripcion = descripcion;
        this.numero_de_maquina = numero_de_maquina;
        this.dia = dia;
        this.mes = mes;
        this.year = year;
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
        this.tiempo_de_resolucion_horas = tiempo_de_resolucion_horas;
        this.tiempo_de_resolucion_minutos = tiempo_de_resolucion_minutos;
    }

    public String getId() {
        return id;
    }

    public String getNombre_de_la_maquina() {
        return nombre_de_la_maquina;
    }

    public String getEstado() {
        return estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getAccion() {
        return accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNumero_de_maquina() {
        return numero_de_maquina;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getYear() {
        return year;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public int getTiempo_de_resolucion_horas() {
        return tiempo_de_resolucion_horas;
    }

    public int getTiempo_de_resolucion_minutos() {
        return tiempo_de_resolucion_minutos;
    }
}
