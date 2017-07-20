package controller;

import service.facade.ServicoAppFacade;
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
import py.com.datapar.maestro.domain.cliente.model.ServicoApp;

/**
 * REST controller for managing ServicoApp.
 */
@Api(value = "/api/servico-app", description = "Servico App Controller")
@Path("/api/servico-app")
@Secured
public class ServicoAppController {

    @Inject
    private Logger log;

    @Inject
    private ServicoAppFacade servicoAppFacade;

    /**
     * POST : Create a new servicoApp.
     *
     * @param servicoApp the servicoApp to create
     * @return the Response with status 201 (Created) and with body the new
     * servicoApp, or with status 400 (Bad Request) if the servicoApp has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new servicoApp", notes = "Create a new servicoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createServicoApp(ServicoApp servicoApp) throws URISyntaxException {
        log.debug("REST request to save ServicoApp : {}", servicoApp);
        servicoAppFacade.create(servicoApp);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/servico-app/" + servicoApp.getId())),
                "servicoApp", servicoApp.getId())
                .entity(servicoApp).build();
    }

    /**
     * PUT : Updates an existing servicoApp.
     *
     * @param servicoApp the servicoApp to update
     * @return the Response with status 200 (OK) and with body the updated
     * servicoApp, or with status 400 (Bad Request) if the servicoApp is not
     * valid, or with status 500 (Internal Server Error) if the servicoApp
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update servicoApp", notes = "Updates an existing servicoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateServicoApp(ServicoApp servicoApp) throws URISyntaxException {
        log.debug("REST request to update ServicoApp : {}", servicoApp);
        servicoAppFacade.edit(servicoApp);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "servicoApp", servicoApp.getId().toString())
                .entity(servicoApp).build();
    }

    /**
     * GET : get all the servicoApps.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of servicoApps in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the servicoApps")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllServicoApps(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ServicoApps");
        List<ServicoApp> servicoApps = servicoAppFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(servicoApps);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, servicoAppFacade.count()), "/resources/api/servico-app");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" servicoApp.
     *
     * @param id the id of the servicoApp to retrieve
     * @return the Response with status 200 (OK) and with body the servicoApp,
     * or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the servicoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getServicoApp(@PathParam("id") String id) {
        log.debug("REST request to get ServicoApp : {}", id);
        ServicoApp servicoApp = servicoAppFacade.find(id);
        return Optional.ofNullable(servicoApp)
                .map(result -> Response.status(Response.Status.OK).entity(servicoApp).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" servicoApp.
     *
     * @param id the id of the servicoApp to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the servicoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeServicoApp(@PathParam("id") String id) {
        log.debug("REST request to delete ServicoApp : {}", id);
        servicoAppFacade.remove(servicoAppFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "servicoApp", id.toString()).build();
    }

}
