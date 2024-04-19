package edu.upc.dsa.services;


import edu.upc.dsa.JuegoManager;
import edu.upc.dsa.JuegoManagerImpl;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Objeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/juego", description = "Endpoint to Juego Service")
@Path("/juego")
public class JuegoService {
    private JuegoManager jm;
    public JuegoService() {
        this.jm = JuegoManagerImpl.getInstance();
        if (jm.size() == 0) {

            this.jm.registrarUsuario("Nathan", "Grillo", "12/12/1999", "asasa@", "1234");
            this.jm.registrarUsuario("David", "Arroyo", "12/12/1990", "babab@", "4321");
            this.jm.registrarUsuario("Vicenç","Peñalver","14/10/2004", "vicenç@gmail.com", "6789");
            this.jm.registrarUsuario("Juan", "Baut", "12/12/1990", "juan@", "4321");

            this.jm.añadirObjeto("Espada", "Espada de acero", 10);
            this.jm.añadirObjeto("Escudo", "Escudo de madera", 5);
            this.jm.añadirObjeto("Pocion", "Pocion de vida", 15);

            this.jm.comprarObjeto("Nathan", "Espada");
        }
    }

    @GET
    @ApiOperation(value = "get all Usuarios", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {

        List<Usuario> usuarios = this.jm.findAll();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @GET
    @ApiOperation(value = "get a Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") String id) {
        Usuario t = this.jm.getUsuario(id);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

    @POST
    @ApiOperation(value = "create a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getNombre()==null || usuario.getApellidos()==null || usuario.getCorreo()==null || usuario.getPassword()==null)  return Response.status(500).entity(usuario).build();
        this.jm.registrarUsuario(usuario);
        return Response.status(201).entity(usuario).build();
    }

    @GET
    @ApiOperation(value = "order usuarios by alf", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/listarUsuariosporAlf")

    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosporAlf() {

        List<Usuario> usuarios = this.jm.listarUsuariosporAlf();

        /*System.out.println("size: "+usuarios.size());
        for (Usuario p: usuarios) {
            System.out.println(p.getNombre());
        }*/

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {};
        return Response.status(201).entity(entity).build()  ;

    }

    /*@POST
    @ApiOperation(value = "create a new Objeto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Objeto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(Objeto objeto) {

        if (objeto.getNombre()==null  || objeto.getDescripcion()==null ) return Response.status(500).entity(objeto).build();
        this.jm.añadirObjeto(objeto);
        return Response.status(201).entity(objeto).build();
    }
*/
    @GET
    @ApiOperation(value = "order objetos by precio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/listarObjetospoPrecio")

    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetosporPrecio() {

        List<Objeto> objetos = this.jm.listarObjetosporPrecio();



        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "listar objetos de usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
    })
    @Path("/listarObjetosdeUsuario/{id}")

    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetosUsuario(@PathParam("id") String id) {

        List<Objeto> objetos = this.jm.listarObjetosporUsuario(id);



        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetos) {};
        return Response.status(201).entity(entity).build()  ;

    }


}
