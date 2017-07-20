package controller;

import service.facade.ConteudoCommandFacade;
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
import py.com.datapar.maestro.domain.entrega.model.ConteudoCommand;

/**
 * REST controller for managing ConteudoCommand.
 */
@Api(value = "/api/conteudo-command", description = "Conteudo Command Controller")
@Path("/api/conteudo-command")
@Secured
public class ConteudoCommandController {

    @Inject
    private Logger log;

    @Inject
    private ConteudoCommandFacade conteudoCommandFacade;

    /**
     * POST : Create a new conteudoCommand.
     *
     * @param conteudoCommand the conteudoCommand to create
     * @return the Response with status 201 (Created) and with body the new
     * conteudoCommand, or with status 400 (Bad Request) if the conteudoCommand
     * has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "create a new conteudoCommand", notes = "Create a new conteudoCommand")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created")
        ,
        @ApiResponse(code = 400, message = "Bad Request")})
    @POST
    public Response createConteudoCommand(ConteudoCommand conteudoCommand) throws URISyntaxException {
        log.debug("REST request to save ConteudoCommand : {}", conteudoCommand);
        conteudoCommandFacade.create(conteudoCommand);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/conteudo-command/" + conteudoCommand.getId())),
                "conteudoCommand", conteudoCommand.getId())
                .entity(conteudoCommand).build();
    }

    /**
     * PUT : Updates an existing conteudoCommand.
     *
     * @param conteudoCommand the conteudoCommand to update
     * @return the Response with status 200 (OK) and with body the updated
     * conteudoCommand, or with status 400 (Bad Request) if the conteudoCommand
     * is not valid, or with status 500 (Internal Server Error) if the
     * conteudoCommand couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @Timed
    @ApiOperation(value = "update conteudoCommand", notes = "Updates an existing conteudoCommand")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 400, message = "Bad Request")
        ,
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @PUT
    public Response updateConteudoCommand(ConteudoCommand conteudoCommand) throws URISyntaxException {
        log.debug("REST request to update ConteudoCommand : {}", conteudoCommand);
        conteudoCommandFacade.edit(conteudoCommand);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "conteudoCommand", conteudoCommand.getId().toString())
                .entity(conteudoCommand).build();
    }

    /**
     * GET : get all the conteudoCommands.
     *
     * @param page the pagination information
     * @param size the pagination size information
     *
     * @return the Response with status 200 (OK) and the list of
     * conteudoCommands in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @Timed
    @ApiOperation(value = "get all the conteudoCommands")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")})
    @GET
    public Response getAllConteudoCommands(@QueryParam("page") int page, @QueryParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get all ConteudoCommands");
        List<ConteudoCommand> conteudoCommands = conteudoCommandFacade.findRange(page * size, size);
        ResponseBuilder builder = Response.ok(conteudoCommands);
        PaginationUtil.generatePaginationHttpHeaders(builder, new Page(page, size, conteudoCommandFacade.count()), "/resources/api/conteudo-command");
        return builder.build();
    }

    /**
     * GET /:id : get the "id" conteudoCommand.
     *
     * @param id the id of the conteudoCommand to retrieve
     * @return the Response with status 200 (OK) and with body the
     * conteudoCommand, or with status 404 (Not Found)
     */
    @Timed
    @ApiOperation(value = "get the conteudoCommand")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @GET
    public Response getConteudoCommand(@PathParam("id") String id) {
        log.debug("REST request to get ConteudoCommand : {}", id);
        ConteudoCommand conteudoCommand = conteudoCommandFacade.find(id);
        return Optional.ofNullable(conteudoCommand)
                .map(result -> Response.status(Response.Status.OK).entity(conteudoCommand).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" conteudoCommand.
     *
     * @param id the id of the conteudoCommand to delete
     * @return the Response with status 200 (OK)
     */
    @Timed
    @ApiOperation(value = "remove the conteudoCommand")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
        ,
        @ApiResponse(code = 404, message = "Not Found")})
    @Path("/{id}")
    @DELETE
    public Response removeConteudoCommand(@PathParam("id") String id) {
        log.debug("REST request to delete ConteudoCommand : {}", id);
        conteudoCommandFacade.remove(conteudoCommandFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "conteudoCommand", id.toString()).build();
    }

}
