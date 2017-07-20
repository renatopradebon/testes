package controller;

import service.facade.EntregaAlvoFacade;
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
import py.com.datapar.maestro.domain.entrega.model.EntregaAlvo;

/**
 * REST controller for managing EntregaAlvo.
 */
@Api(value = "/api/entrega-alvo", description = "Entrega Alvo Controller")
@Path("/api/entrega-alvo")
@Secured
public class EntregaAlvoController {

    @Inject
    private Logger log;

    @Inject
    private EntregaAlvoFacade entregaAlvoFacade;

    /**
     * POST : Create a new entregaAlvo.
     *
     * @param entregaAlvo the entregaAlvo to create
     * @return the Response with status 201 (Created) and with body the new
     * entregaAlvo, or with status 400 (Bad Request) if the entregaAlvo has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new entregaAlvo", notes = "Create a new entregaAlvo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createEntregaAlvo(EntregaAlvo entregaAlvo) throws URISyntaxException {
        log.debug("REST request to save EntregaAlvo : {}", entregaAlvo);
        entregaAlvoFacade.create(entregaAlvo);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/entrega-alvo/" + entregaAlvo.getId())),
                "entregaAlvo", entregaAlvo.getId())
                .entity(entregaAlvo).build();
    }

    /**
     * PUT : Updates an existing entregaAlvo.
     *
     * @param entregaAlvo the entregaAlvo to update
     * @return the Response with status 200 (OK) and with body the updated
     * entregaAlvo, or with status 400 (Bad Request) if the entregaAlvo is not
     * valid, or with status 500 (Internal Server Error) if the entregaAlvo
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update entregaAlvo", notes = "Updates an existing entregaAlvo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateEntregaAlvo(EntregaAlvo entregaAlvo) throws URISyntaxException {
        log.debug("REST request to update EntregaAlvo : {}", entregaAlvo);
        entregaAlvoFacade.edit(entregaAlvo);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "entregaAlvo", entregaAlvo.getId().toString())
                .entity(entregaAlvo).build();
    }

    /**
     * GET : get all the entregaAlvoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of entregaAlvoes
     * in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the entregaAlvoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllEntregaAlvoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all EntregaAlvoes");
        List<EntregaAlvo> entregaAlvoes = entregaAlvoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(entregaAlvoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, entregaAlvoFacade.count()), "/resources/api/entrega-alvo");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" entregaAlvo.
     *
     * @param id the id of the entregaAlvo to retrieve
     * @return the Response with status 200 (OK) and with body the entregaAlvo,
     * or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the entregaAlvo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getEntregaAlvo(@PathParam("id") String id) {
        log.debug("REST request to get EntregaAlvo : {}", id);
        EntregaAlvo entregaAlvo = entregaAlvoFacade.find(id);
        return Optional.ofNullable(entregaAlvo)
                .map(result -> Response.status(Response.Status.OK).entity(entregaAlvo).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" entregaAlvo.
     *
     * @param id the id of the entregaAlvo to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the entregaAlvo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeEntregaAlvo(@PathParam("id") String id) {
        log.debug("REST request to delete EntregaAlvo : {}", id);
        entregaAlvoFacade.remove(entregaAlvoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "entregaAlvo", id.toString()).build();
    }

}
