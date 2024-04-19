package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;

import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

public class JuegoManagerImpl implements JuegoManager{

    private static JuegoManager instance;
    protected List<Objeto> listaObjetos;
    protected HashMap<String, Usuario> listaUsuarios;
    final static Logger logger = Logger.getLogger(JuegoManagerImpl.class);

    public JuegoManagerImpl(){
        this.listaObjetos = new ArrayList<>();
        this.listaUsuarios = new HashMap<>();
    }
    public HashMap<String, Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    public List<Objeto> getListaObjetos() {
        return listaObjetos;
    }
    public static JuegoManager getInstance() {
        if (instance==null) instance = new JuegoManagerImpl();
        return instance;
    }
    public int size() {
        int ret = this.listaUsuarios.size();
        logger.info("size " + ret);

        return ret;
    }

    public void registrarUsuario(String nombre, String apellidos, String fechadenacimiento, String correo, String password){
        logger.info("Se ha intentado registrar al usuario con nombre: " + nombre + " y correo: " + correo);
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios.values()) {
            if (usuario.getCorreo().equals(correo)) {

                encontrado = true;
                break;
            }
        }
        if(encontrado) logger.info("El usuario con correo: " + correo + " ya existe");
        else
        {
            logger.info("El usuario con nombre: " + nombre + " y correo: " + correo + " se ha registrado correctamente");
            Usuario u = new Usuario(nombre, apellidos, fechadenacimiento, correo, password);
            this.listaUsuarios.put(u.getNombre(), u);
        }


    }
    public void  registrarUsuario(Usuario u){
        logger.info("Se ha intentado registrar al usuario con nombre: " + u.getNombre() + " y correo: " + u.getCorreo());

        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios.values()) {
            if (usuario.getCorreo().equals(u.getCorreo())) {

                encontrado = true;
                break;
            }
        }
        if(encontrado) logger.info("El usuario con correo: " + u.getCorreo() + " ya existe");
        else
        {
            logger.info("El usuario con nombre se ha registrado correctamente");

            this.listaUsuarios.put(u.getNombre(), u);
        }

    }
    public List<Usuario> listarUsuariosporAlf(){
        // Convert HashMap to List
        List<Usuario> usuarios = new ArrayList<>(listaUsuarios.values());

        // Sort list by apellido and then nombre
        Collections.sort(usuarios, new Comparator<Usuario>() {
            //@Override
            public int compare(Usuario u1, Usuario u2) {
                int apellidoCompare = u1.getApellidos().compareTo(u2.getApellidos());
                if (apellidoCompare != 0) {
                    return apellidoCompare;
                } else {
                    return u1.getNombre().compareTo(u2.getNombre());
                }
            }
        });

        return usuarios;
    }
    public void Login(String correo, String password)
    {
        logger.info("Se ha intentado logear el usuario con correo: " + correo);
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios.values()) {
            if (usuario.getCorreo().equals(correo)) {
                if (usuario.getPassword().equals(password)) {
                    logger.info("El usuario con correo: " + correo + " se ha logeado correctamente");
                    encontrado = true;
                }
            }
        }
        if(!encontrado) logger.info("El usuario con correo: " + correo + " no se ha logeado correctamente");

    }
    public void añadirObjeto(String nombre, String descripcion, double coins){
        logger.info("Añadiendo objeto: " + nombre);
        Objeto o = new Objeto(nombre, descripcion, coins);
        this.listaObjetos.add(o);
    }
    public void añadirObjeto(Objeto objeto){
        logger.info("Añadiendo objeto: " + objeto.getNombre());
        this.listaObjetos.add(objeto);
    }

    public List<Objeto> listarObjetosporPrecio()
    {
        Comparator<Objeto> pricecomparator = Comparator.comparingDouble(Objeto::getCoins);
        listaObjetos.sort(pricecomparator.reversed());
        return listaObjetos;

    }
    public void comprarObjeto(String nombreUsuario, String nombreObjeto)
    {
        if(listaUsuarios.get(nombreUsuario)==null) logger.info("El usuario no existe");
        else{
            Usuario u = listaUsuarios.get(nombreUsuario);
            Objeto o = null;
            for(Objeto objeto: listaObjetos)
            {
                if(objeto.getNombre().equals(nombreObjeto))
                {
                    o = objeto;
                }
            }
            if(o.getCoins() <= u.getDsaCoins())
            {
                u.setDsaCoins(u.getDsaCoins() - o.getCoins());
                logger.info("Al usuario le quedan:" + u.getDsaCoins() + " coins");
                u.getListaObjetosUsuario().add(o);
                logger.info("Se ha comprado correctamente el objeto");
            }
            else
            {
                logger.info("No se ha podido comprar el objeto");
            }
        }

    }
    public List<Objeto> listarObjetosporUsuario(String nombreUsuario){
        Usuario u = listaUsuarios.get(nombreUsuario);
        return u.getListaObjetosUsuario();
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>(this.listaUsuarios.values());
        return usuarios;
    }
    public Usuario getUsuario(String id) {
        logger.info("getUsuario("+id+")");

        for (Usuario t: this.listaUsuarios.values()) {
            if (t.getIdUser().equals(id)) {
                logger.info("getTrack("+id+"): "+t);

                return t;
            }
        }

        logger.warn("not found " + id);
        return null;
    }
}
