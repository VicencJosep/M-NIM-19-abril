package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String idUser;
    String nombre;
    String apellidos;
    String fechadenacimiento;
    String correo;
    String password;
    double dsaCoins;
    private List<Objeto> listaObjetosUsuario;
    public Usuario(){}
    public Usuario(String nombre, String apellidos, String fechadenacimiento, String correo, String password){
        this.idUser = RandomUtils.getId();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechadenacimiento = fechadenacimiento;
        this.correo = correo;
        this.password = password;
        this.dsaCoins = 50;
        listaObjetosUsuario = new ArrayList<>();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(double dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

    public List<Objeto> getListaObjetosUsuario() {
        return listaObjetosUsuario;
    }

    public void setListaObjetosUsuario(List<Objeto> listaObjetosUsuario) {
        this.listaObjetosUsuario = listaObjetosUsuario;
    }
}
