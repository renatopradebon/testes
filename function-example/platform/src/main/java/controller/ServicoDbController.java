package controller;

import service.facade.ServicoDbFacade;
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
import py.com.datapar.maestro.domain.cliente.model.ServicoDb;

/**
 * REST controller for managing ServicoDb.
 */
@Api(value = "/api/servico-db", description = "Servico Db Controller")
@Path("/api/servico-db")
@Secured
public class ServicoDbController {

    @Inject
    private Logger log;

    @Inject
    private ServicoDbFacade servicoDbFacade;

    /**
     * POST : Create a new servicoDb.
     *
     * @param servicoDb the servicoDb to create
     * @return the Response with status 201 (Created) and with body the new
     * servicoDb, or with status 400 (Bad Request) if the servicoDb has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new servicoDb", notes = "Create a new servicoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createServicoDb(ServicoDb servicoDb) throws URISyntaxException {
        log.debug("REST request to save ServicoDb : {}", servicoDb);
        servicoDbFacade.create(servicoDb);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/servico-db/" + servicoDb.getId())),
                "servicoDb", servicoDb.getId())
                .entity(servicoDb).build();
    }

    /**
     * PUT : Updates an existing servicoDb.
     *
     * @param servicoDb the servicoDb to update
     * @return the Response with status 200 (OK) and with body the updated
     * servicoDb, or with status 400 (Bad Request) if the servicoDb is not
     * valid, or with status 500 (Internal Server Error) if the servicoDb
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update servicoDb", notes = "Updates an existing servicoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateServicoDb(ServicoDb servicoDb) throws URISyntaxException {
        log.debug("REST request to update ServicoDb : {}", servicoDb);
        servicoDbFacade.edit(servicoDb);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "servicoDb", servicoDb.getId().toString())
                .entity(servicoDb).build();
    }

    /**
     * GET : get all the servicoDbs.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of servicoDbs in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the servicoDbs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllServicoDbs(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ServicoDbs");
        List<ServicoDb> servicoDbs = servicoDbFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(servicoDbs);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, servicoDbFacade.count()), "/resources/api/servico-db");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" servicoDb.
     *
     * @param id the id of the servicoDb to retrieve
     * @return the Response with status 200 (OK) and with body the servicoDb, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the servicoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getServicoDb(@PathParam("id") String id) {
        log.debug("REST request to get ServicoDb : {}", id);
        ServicoDb servicoDb = servicoDbFacade.find(id);
        return Optional.ofNullable(servicoDb)
                .map(result -> Response.status(Response.Status.OK).entity(servicoDb).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" servicoDb.
     *
     * @param id the id of the servicoDb to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the servicoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeServicoDb(@PathParam("id") String id) {
        log.debug("REST request to delete ServicoDb : {}", id);
        servicoDbFacade.remove(servicoDbFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "servicoDb", id.toString()).build();
    }

}
