package controller;

import service.facade.ConfigSchemaDbRevisionFacade;
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
import py.com.datapar.maestro.domain.cliente.model.ConfigSchemaDbRevision;

/**
 * REST controller for managing ConfigSchemaDbRevision.
 */
@Api(value = "/api/config-schema-db-revision", description = "Config Schema Db Revision Controller")
@Path("/api/config-schema-db-revision")
@Secured
public class ConfigSchemaDbRevisionController {

    @Inject
    private Logger log;

    @Inject
    private ConfigSchemaDbRevisionFacade configSchemaDbRevisionFacade;

    /**
     * POST : Create a new configSchemaDbRevision.
     *
     * @param configSchemaDbRevision the configSchemaDbRevision to create
     * @return the Response with status 201 (Created) and with body the new
     * configSchemaDbRevision, or with status 400 (Bad Request) if the
     * configSchemaDbRevision has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new configSchemaDbRevision", notes = "Create a new configSchemaDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConfigSchemaDbRevision(ConfigSchemaDbRevision configSchemaDbRevision) throws URISyntaxException {
        log.debug("REST request to save ConfigSchemaDbRevision : {}", configSchemaDbRevision);
        configSchemaDbRevisionFacade.create(configSchemaDbRevision);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/config-schema-db-revision/" + configSchemaDbRevision.getId())),
                "configSchemaDbRevision", configSchemaDbRevision.getId())
                .entity(configSchemaDbRevision).build();
    }

    /**
     * PUT : Updates an existing configSchemaDbRevision.
     *
     * @param configSchemaDbRevision the configSchemaDbRevision to update
     * @return the Response with status 200 (OK) and with body the updated
     * configSchemaDbRevision, or with status 400 (Bad Request) if the
     * configSchemaDbRevision is not valid, or with status 500 (Internal Server
     * Error) if the configSchemaDbRevision couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update configSchemaDbRevision", notes = "Updates an existing configSchemaDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConfigSchemaDbRevision(ConfigSchemaDbRevision configSchemaDbRevision) throws URISyntaxException {
        log.debug("REST request to update ConfigSchemaDbRevision : {}", configSchemaDbRevision);
        configSchemaDbRevisionFacade.edit(configSchemaDbRevision);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "configSchemaDbRevision", configSchemaDbRevision.getId().toString())
                .entity(configSchemaDbRevision).build();
    }

    /**
     * GET : get all the configSchemaDbRevisions.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * configSchemaDbRevisions in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the configSchemaDbRevisions")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConfigSchemaDbRevisions(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConfigSchemaDbRevisions");
        List<ConfigSchemaDbRevision> configSchemaDbRevisions = configSchemaDbRevisionFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(configSchemaDbRevisions);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, configSchemaDbRevisionFacade.count()), "/resources/api/config-schema-db-revision");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" configSchemaDbRevision.
     *
     * @param id the id of the configSchemaDbRevision to retrieve
     * @return the Response with status 200 (OK) and with body the
     * configSchemaDbRevision, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the configSchemaDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConfigSchemaDbRevision(@PathParam("id") String id) {
        log.debug("REST request to get ConfigSchemaDbRevision : {}", id);
        ConfigSchemaDbRevision configSchemaDbRevision = configSchemaDbRevisionFacade.find(id);
        return Optional.ofNullable(configSchemaDbRevision)
                .map(result -> Response.status(Response.Status.OK).entity(configSchemaDbRevision).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" configSchemaDbRevision.
     *
     * @param id the id of the configSchemaDbRevision to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the configSchemaDbRevision")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConfigSchemaDbRevision(@PathParam("id") String id) {
        log.debug("REST request to delete ConfigSchemaDbRevision : {}", id);
        configSchemaDbRevisionFacade.remove(configSchemaDbRevisionFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "configSchemaDbRevision", id.toString()).build();
    }

}
