package controller;

import service.facade.ConteudoAppFacade;
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
import py.com.datapar.maestro.domain.entrega.model.ConteudoApp;

/**
 * REST controller for managing ConteudoApp.
 */
@Api(value = "/api/conteudo-app", description = "Conteudo App Controller")
@Path("/api/conteudo-app")
@Secured
public class ConteudoAppController {

    @Inject
    private Logger log;

    @Inject
    private ConteudoAppFacade conteudoAppFacade;

    /**
     * POST : Create a new conteudoApp.
     *
     * @param conteudoApp the conteudoApp to create
     * @return the Response with status 201 (Created) and with body the new
     * conteudoApp, or with status 400 (Bad Request) if the conteudoApp has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new conteudoApp", notes = "Create a new conteudoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConteudoApp(ConteudoApp conteudoApp) throws URISyntaxException {
        log.debug("REST request to save ConteudoApp : {}", conteudoApp);
        conteudoAppFacade.create(conteudoApp);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/conteudo-app/" + conteudoApp.getId())),
                "conteudoApp", conteudoApp.getId())
                .entity(conteudoApp).build();
    }

    /**
     * PUT : Updates an existing conteudoApp.
     *
     * @param conteudoApp the conteudoApp to update
     * @return the Response with status 200 (OK) and with body the updated
     * conteudoApp, or with status 400 (Bad Request) if the conteudoApp is not
     * valid, or with status 500 (Internal Server Error) if the conteudoApp
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update conteudoApp", notes = "Updates an existing conteudoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConteudoApp(ConteudoApp conteudoApp) throws URISyntaxException {
        log.debug("REST request to update ConteudoApp : {}", conteudoApp);
        conteudoAppFacade.edit(conteudoApp);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "conteudoApp", conteudoApp.getId().toString())
                .entity(conteudoApp).build();
    }

    /**
     * GET : get all the conteudoApps.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of conteudoApps in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the conteudoApps")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConteudoApps(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConteudoApps");
        List<ConteudoApp> conteudoApps = conteudoAppFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(conteudoApps);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, conteudoAppFacade.count()), "/resources/api/conteudo-app");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" conteudoApp.
     *
     * @param id the id of the conteudoApp to retrieve
     * @return the Response with status 200 (OK) and with body the conteudoApp,
     * or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the conteudoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConteudoApp(@PathParam("id") String id) {
        log.debug("REST request to get ConteudoApp : {}", id);
        ConteudoApp conteudoApp = conteudoAppFacade.find(id);
        return Optional.ofNullable(conteudoApp)
                .map(result -> Response.status(Response.Status.OK).entity(conteudoApp).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" conteudoApp.
     *
     * @param id the id of the conteudoApp to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the conteudoApp")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConteudoApp(@PathParam("id") String id) {
        log.debug("REST request to delete ConteudoApp : {}", id);
        conteudoAppFacade.remove(conteudoAppFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "conteudoApp", id.toString()).build();
    }

}
