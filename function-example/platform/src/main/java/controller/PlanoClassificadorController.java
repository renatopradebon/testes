package controller;

import service.facade.PlanoClassificadorFacade;
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
import py.com.datapar.maestro.domain.produto.model.PlanoClassificador;

/**
 * REST controller for managing PlanoClassificador.
 */
@Api(value = "/api/plano-classificador", description = "Plano Classificador Controller")
@Path("/api/plano-classificador")
@Secured
public class PlanoClassificadorController {

    @Inject
    private Logger log;

    @Inject
    private PlanoClassificadorFacade planoClassificadorFacade;

    /**
     * POST : Create a new planoClassificador.
     *
     * @param planoClassificador the planoClassificador to create
     * @return the Response with status 201 (Created) and with body the new
     * planoClassificador, or with status 400 (Bad Request) if the
     * planoClassificador has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new planoClassificador", notes = "Create a new planoClassificador")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createPlanoClassificador(PlanoClassificador planoClassificador) throws URISyntaxException {
        log.debug("REST request to save PlanoClassificador : {}", planoClassificador);
        planoClassificadorFacade.create(planoClassificador);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/plano-classificador/" + planoClassificador.getId())),
                "planoClassificador", planoClassificador.getId())
                .entity(planoClassificador).build();
    }

    /**
     * PUT : Updates an existing planoClassificador.
     *
     * @param planoClassificador the planoClassificador to update
     * @return the Response with status 200 (OK) and with body the updated
     * planoClassificador, or with status 400 (Bad Request) if the
     * planoClassificador is not valid, or with status 500 (Internal Server
     * Error) if the planoClassificador couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update planoClassificador", notes = "Updates an existing planoClassificador")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updatePlanoClassificador(PlanoClassificador planoClassificador) throws URISyntaxException {
        log.debug("REST request to update PlanoClassificador : {}", planoClassificador);
        planoClassificadorFacade.edit(planoClassificador);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "planoClassificador", planoClassificador.getId().toString())
                .entity(planoClassificador).build();
    }

    /**
     * GET : get all the planoClassificadors.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * planoClassificadors in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the planoClassificadors")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllPlanoClassificadors(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all PlanoClassificadors");
        List<PlanoClassificador> planoClassificadors = planoClassificadorFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(planoClassificadors);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, planoClassificadorFacade.count()), "/resources/api/plano-classificador");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" planoClassificador.
     *
     * @param id the id of the planoClassificador to retrieve
     * @return the Response with status 200 (OK) and with body the
     * planoClassificador, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the planoClassificador")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getPlanoClassificador(@PathParam("id") String id) {
        log.debug("REST request to get PlanoClassificador : {}", id);
        PlanoClassificador planoClassificador = planoClassificadorFacade.find(id);
        return Optional.ofNullable(planoClassificador)
                .map(result -> Response.status(Response.Status.OK).entity(planoClassificador).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" planoClassificador.
     *
     * @param id the id of the planoClassificador to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the planoClassificador")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removePlanoClassificador(@PathParam("id") String id) {
        log.debug("REST request to delete PlanoClassificador : {}", id);
        planoClassificadorFacade.remove(planoClassificadorFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "planoClassificador", id.toString()).build();
    }

}
