package controller;

import py.com.angularjpa.Lista;
import repository.ListaRepository;
import controller.util.HeaderUtil;
import app.security.Secured;
import org.slf4j.Logger;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST controller for managing Lista.
 */
@Api(value = "/api/lista", description = "Lista Controller")
@Path("/api/lista")
@Secured
public class ListaController {

    @Inject
    private Logger log;

    @Inject
    private ListaRepository listaRepository;

    /**
     * POST : Create a new lista.
     *
     * @param lista the lista to create
     * @return the Response with status 201 (Created) and with body the new
     * lista, or with status 400 (Bad Request) if the lista has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new lista", notes = "Create a new lista")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createLista(Lista lista) throws URISyntaxException {
        log.debug("REST request to save Lista : {}", lista);
        listaRepository.create(lista);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/lista/" + lista.getId())),
                "lista", lista.getId().toString())
                .entity(lista).build();
    }

    /**
     * PUT : Updates an existing lista.
     *
     * @param lista the lista to update
     * @return the Response with status 200 (OK) and with body the updated
     * lista, or with status 400 (Bad Request) if the lista is not valid, or
     * with status 500 (Internal Server Error) if the lista couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update lista", notes = "Updates an existing lista")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateLista(Lista lista) throws URISyntaxException {
        log.debug("REST request to update Lista : {}", lista);
        listaRepository.edit(lista);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "lista", lista.getId().toString())
                .entity(lista).build();
    }

    /**
     * GET : get all the listas.
     *
     * @return the Response with status 200 (OK) and the list of listas in body
     *
     */
    @Timed
    @ApiOperation(value = "get all the listas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public List<Lista> getAllListas() {
        log.debug("REST request to get all Listas");
        List<Lista> listas = listaRepository.findAll();
        return listas;
    }

    /**
     * GET /:id : get the "id" lista.
     *
     * @param id the id of the lista to retrieve
     * @return the Response with status 200 (OK) and with body the lista, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the lista")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getLista(@PathParam("id") Long id) {
        log.debug("REST request to get Lista : {}", id);
        Lista lista = listaRepository.find(id);
        return Optional.ofNullable(lista)
                .map(result -> Response.status(Response.Status.OK).entity(lista).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" lista.
     *
     * @param id the id of the lista to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the lista")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeLista(@PathParam("id") Long id) {
        log.debug("REST request to delete Lista : {}", id);
        listaRepository.remove(listaRepository.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "lista", id.toString()).build();
    }

}
