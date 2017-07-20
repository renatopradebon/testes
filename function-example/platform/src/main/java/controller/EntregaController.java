package controller;

import service.facade.EntregaFacade;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.ResponseBuilder;
import controller.util.Page;
import controller.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import py.com.datapar.maestro.domain.entrega.model.Entrega;

/**
 * REST controller for managing Entrega.
 */
@Api(value = "/api/entrega", description = "Entrega Controller")
@Path("/api/entrega")
@Secured
public class EntregaController {

    @Inject
    private Logger log;

    @Inject
    private EntregaFacade entregaFacade;

    /**
     * POST : Create a new entrega.
     *
     * @param entrega the entrega to create
     * @return the Response with status 201 (Created) and with body the new
     * entrega, or with status 400 (Bad Request) if the entrega has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new entrega", notes = "Create a new entrega")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createEntrega(Entrega entrega) throws URISyntaxException {
        log.debug("REST request to save Entrega : {}", entrega);
        entregaFacade.create(entrega);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/entrega/" + entrega.getId())),
                "entrega", entrega.getId())
                .entity(entrega).build();
    }

    /**
     * PUT : Updates an existing entrega.
     *
     * @param entrega the entrega to update
     * @return the Response with status 200 (OK) and with body the updated
     * entrega, or with status 400 (Bad Request) if the entrega is not valid, or
     * with status 500 (Internal Server Error) if the entrega couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update entrega", notes = "Updates an existing entrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateEntrega(Entrega entrega) throws URISyntaxException {
        log.debug("REST request to update Entrega : {}", entrega);
        entregaFacade.edit(entrega);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "entrega", entrega.getId().toString())
                .entity(entrega).build();
    }

    /**
     * GET : get all the entregas.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of entregas in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the entregas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllEntregas(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Entregas");
        List<Entrega> entregas = entregaFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(entregas);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, entregaFacade.count()), "/resources/api/entrega");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" entrega.
     *
     * @param id the id of the entrega to retrieve
     * @return the Response with status 200 (OK) and with body the entrega, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the entrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getEntrega(@PathParam("id") String id) {
        log.debug("REST request to get Entrega : {}", id);
        Entrega entrega = entregaFacade.find(id);
        return Optional.ofNullable(entrega)
                .map(result -> Response.status(Response.Status.OK).entity(entrega).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" entrega.
     *
     * @param id the id of the entrega to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the entrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeEntrega(@PathParam("id") String id) {
        log.debug("REST request to delete Entrega : {}", id);
        entregaFacade.remove(entregaFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "entrega", id.toString()).build();
    }

}
