package controller;

import service.facade.FilialFacade;
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
import py.com.datapar.maestro.domain.cliente.model.Filial;

/**
 * REST controller for managing Filial.
 */
@Api(value = "/api/filial", description = "Filial Controller")
@Path("/api/filial")
@Secured
public class FilialController {

    @Inject
    private Logger log;

    @Inject
    private FilialFacade filialFacade;

    /**
     * POST : Create a new filial.
     *
     * @param filial the filial to create
     * @return the Response with status 201 (Created) and with body the new
     * filial, or with status 400 (Bad Request) if the filial has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new filial", notes = "Create a new filial")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createFilial(Filial filial) throws URISyntaxException {
        log.debug("REST request to save Filial : {}", filial);
        filialFacade.create(filial);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/filial/" + filial.getId())),
                "filial", filial.getId())
                .entity(filial).build();
    }

    /**
     * PUT : Updates an existing filial.
     *
     * @param filial the filial to update
     * @return the Response with status 200 (OK) and with body the updated
     * filial, or with status 400 (Bad Request) if the filial is not valid, or
     * with status 500 (Internal Server Error) if the filial couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update filial", notes = "Updates an existing filial")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateFilial(Filial filial) throws URISyntaxException {
        log.debug("REST request to update Filial : {}", filial);
        filialFacade.edit(filial);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "filial", filial.getId().toString())
                .entity(filial).build();
    }

    /**
     * GET : get all the filials.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of filials in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the filials")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllFilials(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Filials");
        List<Filial> filials = filialFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(filials);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, filialFacade.count()), "/resources/api/filial");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" filial.
     *
     * @param id the id of the filial to retrieve
     * @return the Response with status 200 (OK) and with body the filial, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the filial")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getFilial(@PathParam("id") String id) {
        log.debug("REST request to get Filial : {}", id);
        Filial filial = filialFacade.find(id);
        return Optional.ofNullable(filial)
                .map(result -> Response.status(Response.Status.OK).entity(filial).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" filial.
     *
     * @param id the id of the filial to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the filial")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeFilial(@PathParam("id") String id) {
        log.debug("REST request to delete Filial : {}", id);
        filialFacade.remove(filialFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "filial", id.toString()).build();
    }

}
