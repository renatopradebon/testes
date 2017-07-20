package controller;

import service.facade.ConfigSchemaAppRevisionFacade;
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
import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaAppRevision;

/**
 * REST controller for managing ConfigSchemaAppRevision.
 */
@Api(value = "/api/config-schema-app-revision", description = "Config Schema App Revision Controller")
@Path("/api/config-schema-app-revision")
@Secured
public class ConfigSchemaAppRevisionController {

    @Inject
    private Logger log;

    @Inject
    private ConfigSchemaAppRevisionFacade configSchemaAppRevisionFacade;

    /**
     * POST : Create a new configSchemaAppRevision.
     *
     * @param configSchemaAppRevision the configSchemaAppRevision to create
     * @return the Response with status 201 (Created) and with body the new
     * configSchemaAppRevision, or with status 400 (Bad Request) if the
     * configSchemaAppRevision has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new configSchemaAppRevision", notes = "Create a new configSchemaAppRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConfigSchemaAppRevision(ConfigSchemaAppRevision configSchemaAppRevision) throws URISyntaxException {
        log.debug("REST request to save ConfigSchemaAppRevision : {}", configSchemaAppRevision);
        configSchemaAppRevisionFacade.create(configSchemaAppRevision);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/config-schema-app-revision/" + configSchemaAppRevision.getId())),
                "configSchemaAppRevision", configSchemaAppRevision.getId())
                .entity(configSchemaAppRevision).build();
    }

    /**
     * PUT : Updates an existing configSchemaAppRevision.
     *
     * @param configSchemaAppRevision the configSchemaAppRevision to update
     * @return the Response with status 200 (OK) and with body the updated
     * configSchemaAppRevision, or with status 400 (Bad Request) if the
     * configSchemaAppRevision is not valid, or with status 500 (Internal Server
     * Error) if the configSchemaAppRevision couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update configSchemaAppRevision", notes = "Updates an existing configSchemaAppRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConfigSchemaAppRevision(ConfigSchemaAppRevision configSchemaAppRevision) throws URISyntaxException {
        log.debug("REST request to update ConfigSchemaAppRevision : {}", configSchemaAppRevision);
        configSchemaAppRevisionFacade.edit(configSchemaAppRevision);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "configSchemaAppRevision", configSchemaAppRevision.getId().toString())
                .entity(configSchemaAppRevision).build();
    }

    /**
     * GET : get all the configSchemaAppRevisions.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * configSchemaAppRevisions in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the configSchemaAppRevisions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConfigSchemaAppRevisions(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConfigSchemaAppRevisions");
        List<ConfigSchemaAppRevision> configSchemaAppRevisions = configSchemaAppRevisionFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(configSchemaAppRevisions);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, configSchemaAppRevisionFacade.count()), "/resources/api/config-schema-app-revision");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" configSchemaAppRevision.
     *
     * @param id the id of the configSchemaAppRevision to retrieve
     * @return the Response with status 200 (OK) and with body the
     * configSchemaAppRevision, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the configSchemaAppRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConfigSchemaAppRevision(@PathParam("id") String id) {
        log.debug("REST request to get ConfigSchemaAppRevision : {}", id);
        ConfigSchemaAppRevision configSchemaAppRevision = configSchemaAppRevisionFacade.find(id);
        return Optional.ofNullable(configSchemaAppRevision)
                .map(result -> Response.status(Response.Status.OK).entity(configSchemaAppRevision).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" configSchemaAppRevision.
     *
     * @param id the id of the configSchemaAppRevision to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the configSchemaAppRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConfigSchemaAppRevision(@PathParam("id") String id) {
        log.debug("REST request to delete ConfigSchemaAppRevision : {}", id);
        configSchemaAppRevisionFacade.remove(configSchemaAppRevisionFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "configSchemaAppRevision", id.toString()).build();
    }

}
