package br.com.bb.letscode.projetofinal.rest;

import br.com.bb.letscode.projetofinal.form.ClientForm;
import br.com.bb.letscode.projetofinal.service.ClientService;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;


    @GET
    @Path("/list")
    public Response lisClients() {
        return Response.status(Response.Status.OK).entity(clientService.listClients()).build();
    }

    @GET
    @Path("/{id}")
    public Response getClient(@PathParam("id") final long id) {
        return Response.status(Response.Status.OK).entity(clientService.getClient(id)).build();
    }

    @POST
    public Response createClient(@Valid ClientForm clientForm) {
        return Response.status(Response.Status.OK).entity(clientService.createClient(clientForm)).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateClient(@Valid ClientForm clientForm, @PathParam("id") final long id){
        return Response.status(Response.Status.OK).entity(clientService.updateClient(id, clientForm)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") final long id) {
        clientService.deletClient(id);
        return Response.status(Response.Status.OK).build();
    }


}
