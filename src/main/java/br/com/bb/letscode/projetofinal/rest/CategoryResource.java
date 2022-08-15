package br.com.bb.letscode.projetofinal.rest;

import br.com.bb.letscode.projetofinal.form.ClientForm;
import br.com.bb.letscode.projetofinal.model.Category;
import br.com.bb.letscode.projetofinal.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;


    @PostConstruct
    @Transactional
    public void init() throws Exception {
        Category category1 = new Category();
        category1.setCode(100);
        category1.setName("Programador");

        Category category2 = new Category();
        category2.setCode(200);
        category2.setName("Comerciante");

        categoryService.createCategory(category1);
        categoryService.createCategory(category2);
    }

    @GET
    @Path("/list")
    public Response listCategorias() throws Exception  {
        return Response.status(Response.Status.OK).entity(categoryService.listCategory()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategoria(final @PathParam("id") long id) throws Exception  {
        return Response.status(Response.Status.OK).entity(categoryService.getCategory(id)).build();
    }

    @POST
    public Response createCategoria(@Valid Category categoria) throws Exception {
        return  Response.status(Response.Status.CREATED).entity(categoryService.createCategory(categoria)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategoria(final @PathParam("id") long id, @Valid Category categoria) throws Exception  {
        return Response.status(Response.Status.OK).entity(categoryService.updateCategory(id, categoria)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategoria(final @PathParam("id") long id) throws Exception  {
        categoryService.deleteCategory(id);
        return Response.status(Response.Status.OK).build();
    }

}
