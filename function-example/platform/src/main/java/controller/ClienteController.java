package controller;


import service.facade.ClienteFacade;
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
import py.com.datapar.maestro.domain.cliente.model.Cliente;

/**
 * REST controller for managing Cliente.
 */
@Api(value = "/api/cliente", description = "Cliente Controller")
@Path("/api/cliente")
@Secured
public class ClienteController {

    @Inject
    private Logger log;

    @Inject
    private ClienteFacade clienteFacade;

    /**
     * POST : Create a new cliente.
     *
     * @param cliente the cliente to create
     * @return the Response with status 201 (Created) and with body the new
     * cliente, or with status 400 (Bad Request) if the cliente has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new cliente", notes = "Create a new cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createCliente(Cliente cliente) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", cliente);
        clienteFacade.create(cliente);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/cliente/" + cliente.getId())),
                "cliente", cliente.getId())
                .entity(cliente).build();
    }

    /**
     * PUT : Updates an existing cliente.
     *
     * @param cliente the cliente to update
     * @return the Response with status 200 (OK) and with body the updated
     * cliente, or with status 400 (Bad Request) if the cliente is not valid, or
     * with status 500 (Internal Server Error) if the cliente couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update cliente", notes = "Updates an existing cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateCliente(Cliente cliente) throws URISyntaxException {
        log.debug("REST request to update Cliente : {}", cliente);
        clienteFacade.edit(cliente);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "cliente", cliente.getId().toString())
                .entity(cliente).build();
    }

    /**
     * GET : get all the clientes.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of clientes in
     * body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllClientes(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all Clientes");
        List<Cliente> clientes = clienteFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(clientes);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, clienteFacade.count()), "/resources/api/cliente");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" cliente.
     *
     * @param id the id of the cliente to retrieve
     * @return the Response with status 200 (OK) and with body the cliente, or
     * with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getCliente(@PathParam("id") String id) {
        log.debug("REST request to get Cliente : {}", id);
        Cliente cliente = clienteFacade.find(id);
        return Optional.ofNullable(cliente)
                .map(result -> Response.status(Response.Status.OK).entity(cliente).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" cliente.
     *
     * @param id the id of the cliente to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeCliente(@PathParam("id") String id) {
        log.debug("REST request to delete Cliente : {}", id);
        clienteFacade.remove(clienteFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "cliente", id.toString()).build();
    }

}
