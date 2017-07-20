package controller;

import service.facade.ConfigSchemaFacade;
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
import py.com.datapar.maestro.domain.cliente.model.ConfigSchema;

/**
 * REST controller for managing ConfigSchema.
 */
@Api(value = "/api/config-schema", description = "Config Schema Controller")
@Path("/api/config-schema")
@Secured
public class ConfigSchemaController {

    @Inject
    private Logger log;

    @Inject
    private ConfigSchemaFacade configSchemaFacade;

    /**
     * POST : Create a new configSchema.
     *
     * @param configSchema the configSchema to create
     * @return the Response with status 201 (Created) and with body the new
     * configSchema, or with status 400 (Bad Request) if the configSchema has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new configSchema", notes = "Create a new configSchema")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConfigSchema(ConfigSchema configSchema) throws URISyntaxException {
        log.debug("REST request to save ConfigSchema : {}", configSchema);
        configSchemaFacade.create(configSchema);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/config-schema/" + configSchema.getId())),
                "configSchema", configSchema.getId())
                .entity(configSchema).build();
    }

    /**
     * PUT : Updates an existing configSchema.
     *
     * @param configSchema the configSchema to update
     * @return the Response with status 200 (OK) and with body the updated
     * configSchema, or with status 400 (Bad Request) if the configSchema is not
     * valid, or with status 500 (Internal Server Error) if the configSchema
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update configSchema", notes = "Updates an existing configSchema")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConfigSchema(ConfigSchema configSchema) throws URISyntaxException {
        log.debug("REST request to update ConfigSchema : {}", configSchema);
        configSchemaFacade.edit(configSchema);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "configSchema", configSchema.getId().toString())
                .entity(configSchema).build();
    }

    /**
     * GET : get all the configSchemas.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of configSchemas
     * in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the configSchemas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConfigSchemas(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConfigSchemas");
        List<ConfigSchema> configSchemas = configSchemaFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(configSchemas);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, configSchemaFacade.count()), "/resources/api/config-schema");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" configSchema.
     *
     * @param id the id of the configSchema to retrieve
     * @return the Response with status 200 (OK) and with body the configSchema,
     * or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the configSchema")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConfigSchema(@PathParam("id") String id) {
        log.debug("REST request to get ConfigSchema : {}", id);
        ConfigSchema configSchema = configSchemaFacade.find(id);
        return Optional.ofNullable(configSchema)
                .map(result -> Response.status(Response.Status.OK).entity(configSchema).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" configSchema.
     *
     * @param id the id of the configSchema to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the configSchema")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConfigSchema(@PathParam("id") String id) {
        log.debug("REST request to delete ConfigSchema : {}", id);
        configSchemaFacade.remove(configSchemaFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "configSchema", id.toString()).build();
    }

}
