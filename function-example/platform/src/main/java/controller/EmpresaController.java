package controller;

import service.facade.EmpresaFacade;
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
import py.com.datapar.maestro.domain.cliente.model.Empresa;

/**
 * REST controller for managing Empresa.
 */
@Api(value = "/api/empresa", description = "Empresa Controller")
@Path("/api/empresa")
@Secured
public class EmpresaController {

    @Inject
    private Logger log;

    @Inject
    private EmpresaFacade empresaFacade;

    /**
     * POST : Create a new empresa.
     *
     * @param empresa the empresa to create
     * @return the Response with status 201 (Created) and with body the new
     * empresa, or with status 400 (Bad Request) if the empresa has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new empresa", notes = "Create a new empresa")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createEmpresa(Empresa empresa) throws URISyntaxException {
        log.debug("REST request to save Empresa : {}", empresa);
        empresaFacade.create(empresa);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/empresa/" + empresa.getId())),
                "empresa", empresa.getId())
                .entity(empresa).build();
    }

    /**
     * PUT : Updates an existing empresa.
     *
     * @param empresa the empresa to update
     * @return the Response with status 200 (OK) and with body the updated
     * empresa, or with status 400 (Bad Request) if the empresa is not valid, or
     * with status 500 (Internal Server Error) if the empresa couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update empresa", notes = "Updates an existing empresa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateEmpresa(Empresa empresa) throws URISyntaxException {
        log.debug("REST request to update Empresa : {}", empresa);
        empresaFacade.edit(empresa);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "empresa", empresa.getId().toString())
                .entity(empresa).build();
    }

    /**
     * GET : get all the empresas.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of empresas in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the empresas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllEmpresas(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Empresas");
        List<Empresa> empresas = empresaFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(empresas);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, empresaFacade.count()), "/resources/api/empresa");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" empresa.
     *
     * @param id the id of the empresa to retrieve
     * @return the Response with status 200 (OK) and with body the empresa, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the empresa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getEmpresa(@PathParam("id") String id) {
        log.debug("REST request to get Empresa : {}", id);
        Empresa empresa = empresaFacade.find(id);
        return Optional.ofNullable(empresa)
                .map(result -> Response.status(Response.Status.OK).entity(empresa).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" empresa.
     *
     * @param id the id of the empresa to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the empresa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeEmpresa(@PathParam("id") String id) {
        log.debug("REST request to delete Empresa : {}", id);
        empresaFacade.remove(empresaFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "empresa", id.toString()).build();
    }

}
