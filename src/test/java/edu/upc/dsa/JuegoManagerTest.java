package edu.upc.dsa;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class JuegoManagerTest {
JuegoManager jm;
    @Before
    public void setUp() throws Exception {
        this.jm = new JuegoManagerImpl();
        this.jm.registrarUsuario("Nathan", "Grillo", "12/12/1999", "asasa@", "1234");
        this.jm.registrarUsuario("David", "Arroyo", "12/12/1990", "babab@", "4321");
        this.jm.registrarUsuario("Vicenç","Peñalver","14/10/2004", "vicenç@gmail.com", "6789");

        this.jm.añadirObjeto("Espada", "Espada de acero", 10);
        this.jm.añadirObjeto("Escudo", "Escudo de madera", 5);
        this.jm.añadirObjeto("Pocion", "Pocion de vida", 15);

        this.jm.comprarObjeto("Nathan", "Espada");
    }

    @After
    public void tearDown() {
        this.jm = null;
    }

    @Test
    public void testAñadirUsuario() {

        //List<Usuario> lista = new ArrayList<>(jm.getListaUsuarios().values());
        assertEquals(3, this.jm.getListaUsuarios().size());
        //assertEquals(50, this.jm.getListaUsuarios().get("1").getDsaCoins());

    }
    @Test
    public void testListarUsuariosporAlf() {
        List<Usuario> lista = this.jm.listarUsuariosporAlf();
        assertEquals("David", lista.get(0).getNombre());
        assertEquals("Nathan", lista.get(1).getNombre());
        assertEquals("Vicenç", lista.get(2).getNombre());
    }
    @Test
    public void testLogin() {
        this.jm.Login("asasa@", "1234");
        this.jm.Login("babab@", "4321");
        this.jm.Login("vicenç@gmail.com", "6789");
    }
    @Test
    public void testAñadirObjeto() {
        assertEquals(3, this.jm.getListaObjetos().size());
    }
    @Test
    public void testListarObjetosporPrecio() {
        List<Objeto> lista = this.jm.listarObjetosporPrecio();
        assertEquals("Escudo", lista.get(2).getNombre());
        assertEquals("Espada", lista.get(1).getNombre());
        assertEquals("Pocion", lista.get(0).getNombre());
    }
    @Test
    public void testComprarObjeto() {
        this.jm.comprarObjeto("Vicenç", "Espada");

        //assertEquals(40.0, this.jm.getListaUsuarios().get("Vicenç").getDsaCoins());
    }
    @Test
    public void testListarObjetosporUsuario() {
        List<Objeto> lista = this.jm.listarObjetosporUsuario("Nathan");
        assertEquals("Espada", lista.get(0).getNombre());
    }

}
