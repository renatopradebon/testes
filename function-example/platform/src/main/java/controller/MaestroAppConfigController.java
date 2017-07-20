package controller;

import service.facade.MaestroAppConfigFacade;
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
import py.com.datapar.maestro.domain.commons.model.MaestroAppConfig;

/**
 * REST controller for managing MaestroAppConfig.
 */
@Api(value = "/api/maestro-app-config", description = "Maestro App Config Controller")
@Path("/api/maestro-app-config")
@Secured
public class MaestroAppConfigController {

    @Inject
    private Logger log;

    @Inject
    private MaestroAppConfigFacade maestroAppConfigFacade;

    /**
     * POST : Create a new maestroAppConfig.
     *
     * @param maestroAppConfig the maestroAppConfig to create
     * @return the Response with status 201 (Created) and with body the new
     * maestroAppConfig, or with status 400 (Bad Request) if the
     * maestroAppConfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new maestroAppConfig", notes = "Create a new maestroAppConfig")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createMaestroAppConfig(MaestroAppConfig maestroAppConfig) throws URISyntaxException {
        log.debug("REST request to save MaestroAppConfig : {}", maestroAppConfig);
        maestroAppConfigFacade.create(maestroAppConfig);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/maestro-app-config/" + maestroAppConfig.getId())),
                "maestroAppConfig", maestroAppConfig.getId())
                .entity(maestroAppConfig).build();
    }

    /**
     * PUT : Updates an existing maestroAppConfig.
     *
     * @param maestroAppConfig the maestroAppConfig to update
     * @return the Response with status 200 (OK) and with body the updated
     * maestroAppConfig, or with status 400 (Bad Request) if the
     * maestroAppConfig is not valid, or with status 500 (Internal Server Error)
     * if the maestroAppConfig couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update maestroAppConfig", notes = "Updates an existing maestroAppConfig")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateMaestroAppConfig(MaestroAppConfig maestroAppConfig) throws URISyntaxException {
        log.debug("REST request to update MaestroAppConfig : {}", maestroAppConfig);
        maestroAppConfigFacade.edit(maestroAppConfig);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "maestroAppConfig", maestroAppConfig.getId().toString())
                .entity(maestroAppConfig).build();
    }

    /**
     * GET : get all the maestroAppConfigs.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * maestroAppConfigs in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the maestroAppConfigs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllMaestroAppConfigs(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all MaestroAppConfigs");
        List<MaestroAppConfig> maestroAppConfigs = maestroAppConfigFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(maestroAppConfigs);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, maestroAppConfigFacade.count()), "/resources/api/maestro-app-config");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" maestroAppConfig.
     *
     * @param id the id of the maestroAppConfig to retrieve
     * @return the Response with status 200 (OK) and with body the
     * maestroAppConfig, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the maestroAppConfig")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getMaestroAppConfig(@PathParam("id") String id) {
        log.debug("REST request to get MaestroAppConfig : {}", id);
        MaestroAppConfig maestroAppConfig = maestroAppConfigFacade.find(id);
        return Optional.ofNullable(maestroAppConfig)
                .map(result -> Response.status(Response.Status.OK).entity(maestroAppConfig).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" maestroAppConfig.
     *
     * @param id the id of the maestroAppConfig to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the maestroAppConfig")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeMaestroAppConfig(@PathParam("id") String id) {
        log.debug("REST request to delete MaestroAppConfig : {}", id);
        maestroAppConfigFacade.remove(maestroAppConfigFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "maestroAppConfig", id.toString()).build();
    }

}
