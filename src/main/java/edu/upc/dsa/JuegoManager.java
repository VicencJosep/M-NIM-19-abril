package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;

import java.util.HashMap;
import java.util.List;

public interface JuegoManager {
    public void registrarUsuario(String nombre, String apellidos, String fechadenacimiento, String correo, String password);
    public void  registrarUsuario(Usuario u);
    public List<Usuario> listarUsuariosporAlf();
    public void Login(String correo, String password);
    public void añadirObjeto(String nombre, String descripcion, double coins);
    public void añadirObjeto(Objeto objeto);
    public List<Objeto> listarObjetosporPrecio();
    public void comprarObjeto(String nombreUsuario, String nombreObjeto);
    public List<Objeto> listarObjetosporUsuario(String nombreUsuario);

    public HashMap<String, Usuario> getListaUsuarios();
    public List<Objeto> getListaObjetos();
    public int size();
    public List<Usuario> findAll();
    public Usuario getUsuario(String id);
}
