package controller;

import service.facade.EstagioExecucaoEntregaFacade;
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
import py.com.datapar.maestro.domain.entrega.model.EstagioExecucaoEntrega;

/**
 * REST controller for managing EstagioExecucaoEntrega.
 */
@Api(value = "/api/estagio-execucao-entrega", description = "Estagio Execucao Entrega Controller")
@Path("/api/estagio-execucao-entrega")
@Secured
public class EstagioExecucaoEntregaController {

    @Inject
    private Logger log;

    @Inject
    private EstagioExecucaoEntregaFacade estagioExecucaoEntregaFacade;

    /**
     * POST : Create a new estagioExecucaoEntrega.
     *
     * @param estagioExecucaoEntrega the estagioExecucaoEntrega to create
     * @return the Response with status 201 (Created) and with body the new
     * estagioExecucaoEntrega, or with status 400 (Bad Request) if the
     * estagioExecucaoEntrega has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new estagioExecucaoEntrega", notes = "Create a new estagioExecucaoEntrega")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createEstagioExecucaoEntrega(EstagioExecucaoEntrega estagioExecucaoEntrega) throws URISyntaxException {
        log.debug("REST request to save EstagioExecucaoEntrega : {}", estagioExecucaoEntrega);
        estagioExecucaoEntregaFacade.create(estagioExecucaoEntrega);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/estagio-execucao-entrega/" + estagioExecucaoEntrega.getId())),
                "estagioExecucaoEntrega", estagioExecucaoEntrega.getId())
                .entity(estagioExecucaoEntrega).build();
    }

    /**
     * PUT : Updates an existing estagioExecucaoEntrega.
     *
     * @param estagioExecucaoEntrega the estagioExecucaoEntrega to update
     * @return the Response with status 200 (OK) and with body the updated
     * estagioExecucaoEntrega, or with status 400 (Bad Request) if the
     * estagioExecucaoEntrega is not valid, or with status 500 (Internal Server
     * Error) if the estagioExecucaoEntrega couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update estagioExecucaoEntrega", notes = "Updates an existing estagioExecucaoEntrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateEstagioExecucaoEntrega(EstagioExecucaoEntrega estagioExecucaoEntrega) throws URISyntaxException {
        log.debug("REST request to update EstagioExecucaoEntrega : {}", estagioExecucaoEntrega);
        estagioExecucaoEntregaFacade.edit(estagioExecucaoEntrega);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "estagioExecucaoEntrega", estagioExecucaoEntrega.getId().toString())
                .entity(estagioExecucaoEntrega).build();
    }

    /**
     * GET : get all the estagioExecucaoEntregas.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * estagioExecucaoEntregas in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the estagioExecucaoEntregas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllEstagioExecucaoEntregas(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all EstagioExecucaoEntregas");
        List<EstagioExecucaoEntrega> estagioExecucaoEntregas = estagioExecucaoEntregaFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(estagioExecucaoEntregas);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, estagioExecucaoEntregaFacade.count()), "/resources/api/estagio-execucao-entrega");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" estagioExecucaoEntrega.
     *
     * @param id the id of the estagioExecucaoEntrega to retrieve
     * @return the Response with status 200 (OK) and with body the
     * estagioExecucaoEntrega, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the estagioExecucaoEntrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getEstagioExecucaoEntrega(@PathParam("id") String id) {
        log.debug("REST request to get EstagioExecucaoEntrega : {}", id);
        EstagioExecucaoEntrega estagioExecucaoEntrega = estagioExecucaoEntregaFacade.find(id);
        return Optional.ofNullable(estagioExecucaoEntrega)
                .map(result -> Response.status(Response.Status.OK).entity(estagioExecucaoEntrega).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" estagioExecucaoEntrega.
     *
     * @param id the id of the estagioExecucaoEntrega to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the estagioExecucaoEntrega")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeEstagioExecucaoEntrega(@PathParam("id") String id) {
        log.debug("REST request to delete EstagioExecucaoEntrega : {}", id);
        estagioExecucaoEntregaFacade.remove(estagioExecucaoEntregaFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "estagioExecucaoEntrega", id.toString()).build();
    }

}
