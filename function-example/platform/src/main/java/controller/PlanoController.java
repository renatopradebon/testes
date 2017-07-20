package controller;

import service.facade.PlanoFacade;
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
import py.com.datapar.maestro.domain.produto.model.Plano;

/**
 * REST controller for managing Plano.
 */
@Api(value = "/api/plano", description = "Plano Controller")
@Path("/api/plano")
@Secured
public class PlanoController {

    @Inject
    private Logger log;

    @Inject
    private PlanoFacade planoFacade;

    /**
     * POST : Create a new plano.
     *
     * @param plano the plano to create
     * @return the Response with status 201 (Created) and with body the new
     * plano, or with status 400 (Bad Request) if the plano has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new plano", notes = "Create a new plano")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createPlano(Plano plano) throws URISyntaxException {
        log.debug("REST request to save Plano : {}", plano);
        planoFacade.create(plano);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/plano/" + plano.getId())),
                "plano", plano.getId())
                .entity(plano).build();
    }

    /**
     * PUT : Updates an existing plano.
     *
     * @param plano the plano to update
     * @return the Response with status 200 (OK) and with body the updated
     * plano, or with status 400 (Bad Request) if the plano is not valid, or
     * with status 500 (Internal Server Error) if the plano couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update plano", notes = "Updates an existing plano")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updatePlano(Plano plano) throws URISyntaxException {
        log.debug("REST request to update Plano : {}", plano);
        planoFacade.edit(plano);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "plano", plano.getId().toString())
                .entity(plano).build();
    }

    /**
     * GET : get all the planoes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of planoes in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the planoes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllPlanoes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Planoes");
        List<Plano> planoes = planoFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(planoes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, planoFacade.count()), "/resources/api/plano");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" plano.
     *
     * @param id the id of the plano to retrieve
     * @return the Response with status 200 (OK) and with body the plano, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the plano")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getPlano(@PathParam("id") String id) {
        log.debug("REST request to get Plano : {}", id);
        Plano plano = planoFacade.find(id);
        return Optional.ofNullable(plano)
                .map(result -> Response.status(Response.Status.OK).entity(plano).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" plano.
     *
     * @param id the id of the plano to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the plano")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removePlano(@PathParam("id") String id) {
        log.debug("REST request to delete Plano : {}", id);
        planoFacade.remove(planoFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "plano", id.toString()).build();
    }

}
