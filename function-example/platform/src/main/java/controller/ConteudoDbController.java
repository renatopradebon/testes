package controller;

import service.facade.ConteudoDbFacade;
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
import py.com.datapar.maestro.domain.entrega.model.ConteudoDb;

/**
 * REST controller for managing ConteudoDb.
 */
@Api(value = "/api/conteudo-db", description = "Conteudo Db Controller")
@Path("/api/conteudo-db")
@Secured
public class ConteudoDbController {

    @Inject
    private Logger log;

    @Inject
    private ConteudoDbFacade conteudoDbFacade;

    /**
     * POST : Create a new conteudoDb.
     *
     * @param conteudoDb the conteudoDb to create
     * @return the Response with status 201 (Created) and with body the new
     * conteudoDb, or with status 400 (Bad Request) if the conteudoDb has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new conteudoDb", notes = "Create a new conteudoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConteudoDb(ConteudoDb conteudoDb) throws URISyntaxException {
        log.debug("REST request to save ConteudoDb : {}", conteudoDb);
        conteudoDbFacade.create(conteudoDb);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/conteudo-db/" + conteudoDb.getId())),
                "conteudoDb", conteudoDb.getId())
                .entity(conteudoDb).build();
    }

    /**
     * PUT : Updates an existing conteudoDb.
     *
     * @param conteudoDb the conteudoDb to update
     * @return the Response with status 200 (OK) and with body the updated
     * conteudoDb, or with status 400 (Bad Request) if the conteudoDb is not
     * valid, or with status 500 (Internal Server Error) if the conteudoDb
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update conteudoDb", notes = "Updates an existing conteudoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConteudoDb(ConteudoDb conteudoDb) throws URISyntaxException {
        log.debug("REST request to update ConteudoDb : {}", conteudoDb);
        conteudoDbFacade.edit(conteudoDb);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "conteudoDb", conteudoDb.getId().toString())
                .entity(conteudoDb).build();
    }

    /**
     * GET : get all the conteudoDbs.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of conteudoDbs in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the conteudoDbs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConteudoDbs(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConteudoDbs");
        List<ConteudoDb> conteudoDbs = conteudoDbFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(conteudoDbs);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, conteudoDbFacade.count()), "/resources/api/conteudo-db");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" conteudoDb.
     *
     * @param id the id of the conteudoDb to retrieve
     * @return the Response with status 200 (OK) and with body the conteudoDb,
     * or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the conteudoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConteudoDb(@PathParam("id") String id) {
        log.debug("REST request to get ConteudoDb : {}", id);
        ConteudoDb conteudoDb = conteudoDbFacade.find(id);
        return Optional.ofNullable(conteudoDb)
                .map(result -> Response.status(Response.Status.OK).entity(conteudoDb).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" conteudoDb.
     *
     * @param id the id of the conteudoDb to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the conteudoDb")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConteudoDb(@PathParam("id") String id) {
        log.debug("REST request to delete ConteudoDb : {}", id);
        conteudoDbFacade.remove(conteudoDbFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "conteudoDb", id.toString()).build();
    }

}
