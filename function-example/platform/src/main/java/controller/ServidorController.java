package controller;

import service.facade.ServidorFacade;
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
import py.com.datapar.maestro.domain.cliente.model.Servidor;

/**
 * REST controller for managing Servidor.
 */
@Api(value = "/api/servidor", description = "Servidor Controller")
@Path("/api/servidor")
@Secured
public class ServidorController {

    @Inject
    private Logger log;

    @Inject
    private ServidorFacade servidorFacade;

    /**
     * POST : Create a new servidor.
     *
     * @param servidor the servidor to create
     * @return the Response with status 201 (Created) and with body the new
     * servidor, or with status 400 (Bad Request) if the servidor has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new servidor", notes = "Create a new servidor")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createServidor(Servidor servidor) throws URISyntaxException {
        log.debug("REST request to save Servidor : {}", servidor);
        servidorFacade.create(servidor);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/servidor/" + servidor.getId())),
                "servidor", servidor.getId())
                .entity(servidor).build();
    }

    /**
     * PUT : Updates an existing servidor.
     *
     * @param servidor the servidor to update
     * @return the Response with status 200 (OK) and with body the updated
     * servidor, or with status 400 (Bad Request) if the servidor is not valid,
     * or with status 500 (Internal Server Error) if the servidor couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update servidor", notes = "Updates an existing servidor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateServidor(Servidor servidor) throws URISyntaxException {
        log.debug("REST request to update Servidor : {}", servidor);
        servidorFacade.edit(servidor);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "servidor", servidor.getId().toString())
                .entity(servidor).build();
    }

    /**
     * GET : get all the servidors.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of servidors in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the servidors")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllServidors(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Servidors");
        List<Servidor> servidors = servidorFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(servidors);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, servidorFacade.count()), "/resources/api/servidor");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" servidor.
     *
     * @param id the id of the servidor to retrieve
     * @return the Response with status 200 (OK) and with body the servidor, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the servidor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getServidor(@PathParam("id") String id) {
        log.debug("REST request to get Servidor : {}", id);
        Servidor servidor = servidorFacade.find(id);
        return Optional.ofNullable(servidor)
                .map(result -> Response.status(Response.Status.OK).entity(servidor).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" servidor.
     *
     * @param id the id of the servidor to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the servidor")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeServidor(@PathParam("id") String id) {
        log.debug("REST request to delete Servidor : {}", id);
        servidorFacade.remove(servidorFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "servidor", id.toString()).build();
    }

}
