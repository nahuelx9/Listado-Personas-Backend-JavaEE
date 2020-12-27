package com.np.servicio;

import com.np.data.PersonaDao;
import com.np.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Stateless
@Path("/personas")
public class PersonaServiceRS {

    @Inject
    private PersonaDao personaDao;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersona() {
        return personaDao.encontrarTodasPersonas();
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")/*hace referencia al path: /personas/{id}  */
    public Persona encontrarPersona(@PathParam("id") int id) {
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        System.out.println("persona encontrada: " + persona);
        return persona;
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona) {
        personaDao.insertarPersona(persona);
        System.out.println("Persona Agregada: " + persona);
        return persona;
    }

    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada) {
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        if (persona != null) {
            personaDao.actualizarPersona(personaModificada);
            System.out.println("Persona modificada " + personaModificada);
            return Response.ok().entity(personaModificada).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
public Response eliminarPersona(@PathParam("id") int id){
    personaDao.eliminarPersona(new Persona(id));
        System.out.println("Persona eliminada con el id: " + id);
        return Response.ok().build();
}

}
